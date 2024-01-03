package com.example.a1230;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Index#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Index extends Fragment {

    public static Index newInstance() {
        return new Index();
    }
    private View view;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Index.
     */
    // TODO: Rename and change types and number of parameters

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment using data binding
        view = inflater.inflate(R.layout.fragment_index, container, false);
        // Access the TextView from the binding
        LinearLayout PageLink1View = view.findViewById(R.id.PageLink1);
        LinearLayout PageLink2View = view.findViewById(R.id.PageLink2);
        LinearLayout PageLink3View = view.findViewById(R.id.PageLink3);
        Context context = getActivity().getApplicationContext();

        // Set OnClickListener on the TextView
        PageLink1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, new PhoneBook(fragmentManager, context));
                transaction.addToBackStack("PhoneBook");
                transaction.commit();
            }
        });

        PageLink2View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                transaction.replace(R.id.fragment_container, new ImageGridFragment(fragmentManager, context));
                transaction.addToBackStack("ImageGridFragment");
                transaction.commit();
            }
        });

        PageLink3View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, new DepartmentSelectFragment(fragmentManager, context));
                transaction.addToBackStack("DepartmentSelectFragment");
                transaction.commit();
            }
        });
        return view;
    }
}