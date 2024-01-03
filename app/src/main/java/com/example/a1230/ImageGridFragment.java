package com.example.a1230;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageGridFragment extends Fragment {

    Context context;
    FragmentManager fragmentManager;
    ActivityResultLauncher<Intent> galleryLauncher;
    Uri selectedImageUri;
    ImageDB imageDB;
    public ImageGridFragment(FragmentManager fragmentManager, Context context) {
        this.fragmentManager = fragmentManager;
        this.context = context;
    }

    //for test (obsolete now)
    /*private int[] imageIDs = new int[] {
            R.drawable.gallery_image_01,
            R.drawable.gallery_image_02,
            R.drawable.gallery_image_03,
            R.drawable.gallery_image_04,
            R.drawable.gallery_image_05,
            R.drawable.gallery_image_06,
            R.drawable.gallery_image_07,
            R.drawable.gallery_image_08,
    };*/
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Log.w("result", result.getData().toString());
                        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {

                            selectedImageUri = result.getData().getData();
                            String imagePath = selectedImageUri.getPath();
                            String imageName = new File(imagePath).getName();

                            Integer dotIndex = imagePath.lastIndexOf('.');
                            imageName = dotIndex > 0 ? imageName.substring(0, dotIndex) : imageName;
                            Image newImage = new Image(selectedImageUri.toString(), imageName, -1);

                            // 이미지 DB에 삽입
                            class InsertRunnable implements Runnable {
                                @Override
                                public void run() {
                                    try {
                                        imageDB.imageDao().insertAll(newImage);
                                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                        ImageGridFragment refreshedImageGridFragment = new ImageGridFragment(
                                                fragmentManager, context);
                                        fragmentTransaction.replace(R.id.fragment_container, refreshedImageGridFragment);
                                        fragmentTransaction.addToBackStack("Image");
                                        fragmentTransaction.commit();
                                    } catch (Exception e) {
                                        Log.w("error in new thread2", e);
                                    }
                                }
                            } Thread t = new Thread(new InsertRunnable()); t.start();

                        }
                    }
                });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 프래그먼트 클래스에는 해당 프래그먼트의 레이아웃을 가져오기 위한 onCreateView
        imageDB = ImageDB.getInstance(context);
        View rootview = inflater.inflate(R.layout.fragment_imagegrid, container, false);
        GridView gridView = (GridView) rootview.findViewById(R.id.gridview);
        TextView loadimageTextView = rootview.findViewById(R.id.LoadImageTextView);
        ImageView testImageview = rootview.findViewById(R.id.TestImageView);

        //이미지 불러오기
        ArrayList<Image> images = (ArrayList<Image>) imageDB.imageDao().getAll();
        ImageGridAdapter adapter = new ImageGridAdapter(images, context, fragmentManager);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedUri = images.get(position).getUri();
                ImageFragment imageFragment = new ImageFragment(context, fragmentManager);
                Bundle args = new Bundle();
                args.putString("imageUri", selectedUri);
                imageFragment.setArguments(args);

                FragmentTransaction transaction = requireActivity().getSupportFragmentManager()
                        .beginTransaction();
                transaction.replace(R.id.fragment_container, imageFragment);
                transaction.addToBackStack("ImageFragment");
                transaction.commit();
            }
        });
        loadimageTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    //갤러리에서 파일 읽어들인 후 DB에 삽입
                    galleryLauncher.launch(intent);
                    //화면 새로고침

                } catch (NullPointerException e) {Log.w("ERROR", e);}
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



        return rootview;
    }
}