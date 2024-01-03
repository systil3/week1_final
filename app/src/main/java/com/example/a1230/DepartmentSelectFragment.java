package com.example.a1230;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class DepartmentSelectFragment extends Fragment {

    private Context context;
    private FragmentManager fragmentManager;
    public DepartmentSelectFragment(FragmentManager fragmentManager, Context context) {
        this.fragmentManager = fragmentManager;
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_department_select, container, false);
        AppCompatButton management_button = view.findViewById(R.id.management_button);
        AppCompatButton general_affairs_button = view.findViewById(R.id.general_affairs_button);
        AppCompatButton accounting_button = view.findViewById(R.id.accounting_button);
        AppCompatButton machinary_button = view.findViewById(R.id.machinary_button);
        AppCompatButton technical_research_button = view.findViewById(R.id.technical_research_button);
        AppCompatButton human_resources_button = view.findViewById(R.id.human_resources_button);
        AppCompatButton system_management_button = view.findViewById(R.id.system_management_button);
        AppCompatButton facility_management_button = view.findViewById(R.id.facility_management_button);
        Button homeButton = requireActivity().findViewById(R.id.homeButton);
        Button backButton = requireActivity().findViewById(R.id.backButton);
        Bundle bundle = new Bundle();
        TodoListFragment todoListFragment = new TodoListFragment(fragmentManager, context);
        management_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt("dept_id", 0);
                todoListFragment.setArguments(bundle);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, todoListFragment);
                transaction.addToBackStack("TodoListFragment");
                transaction.commit();
            }
        });

        general_affairs_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt("dept_id", 1);
                todoListFragment.setArguments(bundle);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, todoListFragment);
                transaction.addToBackStack("TodoListFragment");
                transaction.commit();
            }
        });

        accounting_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt("dept_id", 2);
                todoListFragment.setArguments(bundle);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, todoListFragment);
                transaction.addToBackStack("TodoListFragment");
                transaction.commit();
            }
        });

        machinary_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt("dept_id", 3);
                todoListFragment.setArguments(bundle);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, todoListFragment);
                transaction.addToBackStack("TodoListFragment");
                transaction.commit();
            }
        });

        technical_research_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt("dept_id", 4);
                todoListFragment.setArguments(bundle);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, todoListFragment);
                transaction.addToBackStack("TodoListFragment");
                transaction.commit();
            }
        });

        human_resources_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt("dept_id", 5);
                todoListFragment.setArguments(bundle);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, todoListFragment);
                transaction.addToBackStack("TodoListFragment");
                transaction.commit();
            }
        });

        system_management_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt("dept_id", 6);
                todoListFragment.setArguments(bundle);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, todoListFragment);
                transaction.addToBackStack("TodoListFragment");
                transaction.commit();
            }
        });

        facility_management_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle.putInt("dept_id", 7);
                todoListFragment.setArguments(bundle);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_container, new TodoListFragment(fragmentManager, context));
                transaction.addToBackStack("TodoListFragment");
                transaction.commit();
            }
        });

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
}