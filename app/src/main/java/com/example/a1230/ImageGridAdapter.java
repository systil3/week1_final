package com.example.a1230;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ImageGridAdapter extends ArrayAdapter<Image> {
    private ArrayList<Image> images;
    private Context context;
    private FragmentManager fragmentManager;
    public ImageGridAdapter(ArrayList<Image> images, Context context, FragmentManager fragmentManager) {
        super(context, 0, (List<Image>) images);
        this.fragmentManager = fragmentManager;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = (ImageView) convertView;

        // 재활용 가능한 ImageView가 없으면 새로 생성합니다.
        if (imageView == null) {
            imageView = new ImageView(getContext());

            //디스플레이의 너비 가져옴
            DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();

            //각 셀의 최소 너비를 설정
            int cellsize = (int) Math.floor(displayMetrics.widthPixels/5);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(cellsize, cellsize)); // 이미지 크기 조절
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        // 현재 위치(position)의 이미지 리소스를 가져와서 설정.
        Image image = getItem(position);
        String uri = image.getUri();
        Log.w("image uri", uri);
        Glide.with(context)
                .load(uri)
                .into(imageView);

        return imageView;
    }
}