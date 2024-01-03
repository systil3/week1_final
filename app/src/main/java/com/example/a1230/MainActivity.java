package com.example.a1230;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            setContentView(R.layout.activity_main);

            //set department
            DeptDB deptDB = DeptDB.getInstance(getApplicationContext());
            Map<Integer, String> deptMap = Map.of(
                    0, "Management",
                    1, "General Affairs",
                    2, "Accounting",
                    3, "Machinary",
                    4, "Technical Research",
                    5, "Human Resources",
                    6, "System Management",
                    7, "Facility Management");
            for(Integer i : deptMap.keySet()) {
                String deptName = deptMap.get(i);
                Department department = new Department(i, deptName);
                deptDB.deptDao().insert(department);
            } Log.w("inserted", String.valueOf(deptDB.deptDao().getAll().size()));

            //page transaction in main page
            Button BackButton = findViewById(R.id.backButton);
            BackButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction transaction2 = fragmentManager.beginTransaction();
                    transaction2.replace(R.id.fragment_container, new Index());
                    transaction2.addToBackStack("Index");
                    transaction2.commit();
                }
            });
            transaction.replace(R.id.fragment_container, new Index());
            transaction.commit();


        } catch (Exception e) {e.printStackTrace(); }
    }
}

