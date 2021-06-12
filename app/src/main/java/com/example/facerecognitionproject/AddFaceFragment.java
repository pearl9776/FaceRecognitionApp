package com.example.facerecognitionproject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.view.PreviewView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.facerecognitionproject.Helper.GraphicOverlay;
import com.google.android.material.textfield.TextInputLayout;

import dmax.dialog.SpotsDialog;

public class AddFaceFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private static int REQUEST_CODE_PERMISSIONS = 200;
    private static final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};

    static ConstraintLayout consLayout;
    static PreviewView mPreviewView;
    static ImageButton captureImage;
    static ImageButton switchCamera;
    static AddFaceFragment fragment;
    static AlertDialog alertDialog;
    static GraphicOverlay graphicOverlay;
    static ImageView imagePreview;
           TextInputLayout name;

    public AddFaceFragment() {}

    public static AddFaceFragment newInstance(String param1, String param2) {
        fragment = new AddFaceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_face, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);

        alertDialog = new SpotsDialog.Builder().setContext(getActivity())
                .setMessage("Please Wait,Proccessing...")
                .setCancelable(false).build();

        if(allPermissionsGranted()){
            CameraSource.start(getActivity(), mPreviewView, captureImage, switchCamera, name); //start camera if permission has been granted by user
        } else{
            ActivityCompat.requestPermissions(getActivity(), REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }

    }

    private void findViews(View view) {
        mPreviewView = view.findViewById(R.id.viewFinder);
        captureImage = view.findViewById(R.id.camera_capture_button);
        switchCamera = view.findViewById(R.id.flip_camera);
        name = view.findViewById(R.id.name_field);
        graphicOverlay = view.findViewById(R.id.graphic_overlay);
        imagePreview = view.findViewById(R.id.image_preview);
    }


    private boolean allPermissionsGranted(){

        for(String permission : REQUIRED_PERMISSIONS){
            if(ContextCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_PERMISSIONS){
            if(allPermissionsGranted()){
                CameraSource.start(getActivity(), mPreviewView, captureImage, switchCamera, name);
            } else{
                Toast.makeText(getActivity(), "Permissions not granted by the user.", Toast.LENGTH_SHORT).show();
                getActivity().finish();

            }
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onPause() {
        super.onPause();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onStop() {
        super.onStop();
    }


}

