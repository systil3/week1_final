package com.example.a1230;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.icu.text.IDNA;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class PhoneBookAdapter extends RecyclerView.Adapter<PhoneBookAdapter.ViewHolder>{

    private ArrayList<Person> localDataSet;
    private FragmentManager fragmentManager;
    //===== 뷰홀더 클래스 =====================================================
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private TextView infoView;
        private ImageView callImageView;
        private ImageView msgImageView;
        public ViewHolder(@NonNull View view) {
            super(view);
            textView = view.findViewById(R.id.recyclerTextView);
            infoView = view.findViewById(R.id.recyclerInfoView);
            callImageView = view.findViewById(R.id.PhoneBookCallImageView);
            msgImageView = view.findViewById(R.id.PhoneBookMsgImageView);
        }
        public TextView getTextView() {
            return textView;
        }
    }
    //========================================================================

    public PhoneBookAdapter (ArrayList<Person> dataSet, FragmentManager fragmentManager) {
        localDataSet = dataSet;
        this.fragmentManager = fragmentManager;
    }
    //--------------------------------------------------
    @NonNull
    @Override   // ViewHolder 객체를 생성하여 리턴한다.
    public PhoneBookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recycler_item, parent, false);
        return new PhoneBookAdapter.ViewHolder(view);
    }

    @Override   // ViewHolder안의 내용을 position에 해당되는 데이터로 교체한다.
    public void onBindViewHolder(@NonNull PhoneBookAdapter.ViewHolder holder, int position) {
        Person person = localDataSet.get(position);
        if(person != null) {
            String name = person.getName();
            if(name.length() > 18)
                name = name.substring(0, 15) + "...";

            holder.textView.setText(name);
            holder.infoView.setText(person.getDept());
        }
        else {holder.textView.setText("error!");}

        //To Information Page
        holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    InfoPage infoPage = new InfoPage(person, fragmentManager);
                    Bundle bundle = new Bundle();
                    bundle.putString("personId", person.getName());
                    infoPage.setArguments(bundle);

                    fragmentTransaction.replace(R.id.fragment_container, infoPage);
                    fragmentTransaction.addToBackStack("Infopage");
                    fragmentTransaction.commit();
                    Log.d("FragmentTransaction", "Transaction committed");
                }
            });

        //To Phone Call
        holder.callImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = holder.callImageView.getContext();
                String mNum = person.getPhone();
                Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                dialIntent.setData(Uri.parse("tel:" +
                        person.getPhone().replaceAll("-","")));
                context.startActivity(dialIntent);
            }
        });

        //To Message
        holder.msgImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = holder.callImageView.getContext();
                Intent msgIntent = new Intent(Intent.ACTION_SENDTO);
                msgIntent.setData(Uri.parse("smsto:" +
                        person.getPhone().replaceAll("-","")));
                context.startActivity(msgIntent);
            }
        });
    }

    @Override   // 전체 데이터의 갯수를 리턴한다.
    public int getItemCount() {
        return localDataSet.size();
    }
}