package com.example.a1230;

import android.content.Context;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.HashMap;

public class InfoPageAdapter extends RecyclerView.Adapter<InfoPageAdapter.ViewHolder>{
    private ArrayList<String> attrList;
    private ArrayList<String> valueList;
    private FragmentManager fragmentManager;
    //===== 뷰홀더 클래스 =====================================================
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView AttrView;
        private TextView ValueView;
        public ViewHolder(@NonNull View view) {
            super(view);
            AttrView = view.findViewById(R.id.PersonalInfoAttrView);
            ValueView = view.findViewById(R.id.PersonalInfoValueView);
        }
        public TextView getTextView() {
            return ValueView;
        }
    }
    //========================================================================

    public InfoPageAdapter (HashMap<String, String> Infos, FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        attrList = new ArrayList<String>(Infos.keySet());
        valueList = new ArrayList<String>(Infos.values());
    }
    //--------------------------------------------------

    @NonNull
    @Override   // ViewHolder 객체를 생성하여 리턴한다.
    public InfoPageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.personal_info_item, parent, false);
        return new InfoPageAdapter.ViewHolder(view);
    }

    @Override   // ViewHolder안의 내용을 position에 해당되는 데이터로 교체한다.
    public void onBindViewHolder(@NonNull InfoPageAdapter.ViewHolder holder, int position) {
        holder.AttrView.setText(attrList.get(position));
        holder.ValueView.setText(valueList.get(position));
    }

    @Override   // 전체 데이터의 갯수를 리턴한다.
    public int getItemCount() {
        assert attrList.size() == valueList.size();
        return attrList.size();
    }
}