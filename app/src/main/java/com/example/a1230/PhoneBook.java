package com.example.a1230;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a1230.databinding.PhonebookBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class PhoneBook extends Fragment {

    private PhonebookBinding binding;
    private View view;
    private FragmentManager fragmentManager;
    private Context context;
    private PersonDB personDB;
    public PhoneBook(FragmentManager fragmentManager, Context context) {
        this.fragmentManager = fragmentManager;
        this.context = context;
    }
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = PhonebookBinding.inflate(inflater, container, false);
        view = inflater.inflate(R.layout.phonebook, container, false);
        personDB = PersonDB.getInstance(getContext());
        ArrayList<Person> persons = (ArrayList<Person>)PersonDB.getInstance(context).personDao().getAll();
        // if db is empry, access json file to get person list
        if(persons.size() == 0) {
            AssetManager assetManager= getResources().getAssets();
            try {
                InputStream is= assetManager.open("JSON/phonebook.json");
                InputStreamReader isr= new InputStreamReader(is);
                BufferedReader reader= new BufferedReader(isr);

                StringBuffer buffer= new StringBuffer();
                String line= reader.readLine();
                while (line!=null){
                    buffer.append(line+"\n");
                    line=reader.readLine();
                }
                String jsonData= buffer.toString();
                JSONArray PBData= new JSONArray(jsonData);

                String s="";
                int n = PBData.length();
                ArrayList<Person> persons_Got = new ArrayList<Person>();

                for(int i=0; i<n;i++){
                    JSONObject jo=PBData.getJSONObject(i);
                    Integer id = Integer.parseInt(jo.getString("id"));
                    String name= jo.getString("name");
                    String phonenum= jo.getString("phone");
                    Integer gender= jo.getString("gender").equals("female") ? 0 : 1;
                    Integer dept= Integer.parseInt(jo.getString("department"));

                    persons_Got.add(new Person(id, name, gender, phonenum, dept));
                }
                class InsertRunnable implements Runnable {
                    @Override
                    public void run() {
                        try {
                            personDB.personDao().insertAll(persons_Got);

                        } catch (Exception e) {
                            Log.w("error in new thread2", e);
                        }
                    }
                } Thread t = new Thread(new InsertRunnable()); t.start();

            } catch (IOException e) {e.printStackTrace();} catch (JSONException e) {e.printStackTrace(); }
        }

        //create thread to access DB
        persons.sort((x, y) -> {
            if(x.getDeptNum() == y.getDeptNum())
            {
                return x.getName().compareTo(y.getName());}
            else {
                return x.getDeptNum().compareTo(y.getDeptNum());
            }
        });
        PhoneBookAdapter phoneBookAdapter = new PhoneBookAdapter(persons, fragmentManager);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.PhoneBookRecyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);  // LayoutManager 설정
        recyclerView.setAdapter(phoneBookAdapter);
        TextView numberOfPeopleTextView = view.findViewById(R.id.NumberOfPeopleTextView);
        numberOfPeopleTextView.setText("Number of Employees : " + String.valueOf(persons.size()));

        SearchView phoneBookSearchView = view.findViewById(R.id.PhoneBookSearchView);
        phoneBookSearchView.setSubmitButtonEnabled(true);
        phoneBookSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                ArrayList<Person> searchResult = (ArrayList<Person>) PersonDB.getInstance(context)
                        .personDao().searchNameByText(text);
                PhoneBookAdapter newPhoneBookAdapter = new PhoneBookAdapter(searchResult, fragmentManager);
                recyclerView.setAdapter(newPhoneBookAdapter);
                numberOfPeopleTextView.setText("Number of Search Result: " + String.valueOf(searchResult.size()));
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Person> searchResult = (ArrayList<Person>) PersonDB.getInstance(context)
                        .personDao().searchNameByText(newText);
                PhoneBookAdapter newPhoneBookAdapter = new PhoneBookAdapter(searchResult, fragmentManager);
                recyclerView.setAdapter(newPhoneBookAdapter);
                return true;
            }
        });

        Spinner phoneBookDeptSpinner = (Spinner) view.findViewById(R.id.PhoneBookDeptSpinner);
        ArrayAdapter phoneBookDeptSpnAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.depts, android.R.layout.simple_spinner_item);
        phoneBookDeptSpnAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        phoneBookDeptSpinner.setAdapter(phoneBookDeptSpnAdapter);
        phoneBookDeptSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    Integer dept_id = i - 1;
                    ArrayList<Person> filterResult = (ArrayList<Person>) personDB.personDao().selectAllPersonsInDept(dept_id);
                    PhoneBookAdapter newPhoneBookAdapter = new PhoneBookAdapter(filterResult, fragmentManager);
                    recyclerView.setAdapter(newPhoneBookAdapter);
                } else {
                    PhoneBookAdapter newPhoneBookAdapter = new PhoneBookAdapter(persons, fragmentManager);
                    recyclerView.setAdapter(newPhoneBookAdapter);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        Button homeButton= requireActivity().findViewById(R.id.homeButton);
        Button backButton = requireActivity().findViewById(R.id.backButton);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, new Index());
                fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                fragmentManager.popBackStack();
            }
        });

        return view;
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}