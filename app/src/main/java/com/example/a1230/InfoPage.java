package com.example.a1230;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a1230.databinding.InfopageBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class InfoPage extends Fragment {
    private InfopageBinding binding;
    private View view;
    private Person person;
    private FragmentManager fragmentManager;
    public InfoPage(Person person, FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        this.person = person;
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        view = inflater.inflate(R.layout.infopage, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.PersonalInfoView);
        ImageView personImageView = view.findViewById(R.id.PersonImageView);
        //replace image
        Integer resourceId = getResources().getIdentifier("profile_" + String.valueOf(person.getId()),
                "drawable", requireContext().getPackageName());
        Log.w("image id", String.valueOf(resourceId));
        personImageView.setImageResource(resourceId);

        HashMap<String, String> infos = new HashMap<String, String>();
        //infos.put("Name", person.getName());
        TextView personNameTextView = view.findViewById(R.id.PersonNameTextView);
        personNameTextView.setText(person.getName());
        infos.put("Department", person.getDept());
        infos.put("Gender", person.getGender());
        infos.put("Phone Number", person.getPhone());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);  // LayoutManager 설정

        InfoPageAdapter infoPageAdapter = new InfoPageAdapter(infos, fragmentManager);
        recyclerView.setAdapter(infoPageAdapter);// 어댑터 설정
        binding = InfopageBinding.inflate(inflater, container, false);

        // homebutton에 기능 추가
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