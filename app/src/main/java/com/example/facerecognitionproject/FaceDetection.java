package com.example.facerecognitionproject;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.facerecognitionproject.Helper.RectOverlay;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;

import java.util.List;

public class FaceDetection {

    public FaceDetection() {
    }

    public InputImage imageFromBitmap(Bitmap bitmap, int rotationDegree) {
         return InputImage.fromBitmap(bitmap, rotationDegree);
    }

    public void detectFaces(InputImage image) {
        FaceDetectorOptions options =
                new FaceDetectorOptions.Builder()
                        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_FAST)
                        .setContourMode(FaceDetectorOptions.LANDMARK_MODE_NONE)
                        .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_NONE)
                        .setMinFaceSize(0.1f)
                        .build();

        FaceDetector detector = com.google.mlkit.vision.face.FaceDetection.getClient(options);

        Task<List<Face>> result =
                detector.process(image)
                        .addOnSuccessListener(
                                new OnSuccessListener<List<Face>>() {
                                    @Override
                                    public void onSuccess(List<Face> faces) {

                                        for (Face face : faces) {
                                            Rect bounds = face.getBoundingBox();
                                            RectOverlay rectOverlay = new RectOverlay(
                                                    AddFaceFragment.graphicOverlay, bounds);
                                            AddFaceFragment.graphicOverlay.add(rectOverlay);
                                            AddFaceFragment.alertDialog.dismiss();
                                        }
                                    }
                                })
                        .addOnFailureListener(
                                new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        AddFaceFragment.alertDialog.setMessage("Any face not found");

                                        AddFaceFragment.alertDialog.dismiss();
                                        Toast.makeText(CameraSource.activity, "not found", Toast.LENGTH_LONG).show();

                                    }
                                });

    }
}
