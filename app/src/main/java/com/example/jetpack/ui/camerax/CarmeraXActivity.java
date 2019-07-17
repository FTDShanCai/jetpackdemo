package com.example.jetpack.ui.camerax;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Rational;
import android.util.Size;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraX;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageAnalysisConfig;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureConfig;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.core.PreviewConfig;

import com.example.jetpack.Constants;
import com.example.jetpack.R;
import com.example.jetpack.util.MyLog;

import java.io.File;
import java.io.IOException;

public class CarmeraXActivity extends AppCompatActivity {

    TextureView text_ure_view;

    private int widthPixels, heightPixels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carmera_x);
        text_ure_view = findViewById(R.id.text_ure_view);
        text_ure_view.post(new Runnable() {
            @Override
            public void run() {
                initConfig();
                initCamera();
            }
        });

        text_ure_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageCapture != null) {
                    try {
                        File file = File.createTempFile("img_" + System.currentTimeMillis(), ".jpg", Environment.getExternalStorageDirectory());
                        imageCapture.takePicture(file, new ImageCapture.OnImageSavedListener() {
                            @Override
                            public void onImageSaved(@NonNull File file) {
                                Intent intent = new Intent();
                                intent.putExtra(Constants.Key.PATH, file.getPath());
                                setResult(Constants.ResultRefresh, intent);
                                finish();
                            }

                            @Override
                            public void onError(@NonNull ImageCapture.UseCaseError useCaseError, @NonNull String message, @Nullable Throwable cause) {
                                Toast.makeText(CarmeraXActivity.this, "take photo error", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    void initConfig() {
        DisplayMetrics metrics = new DisplayMetrics();
        text_ure_view.getDisplay().getRealMetrics(metrics);

        widthPixels = metrics.widthPixels;
        heightPixels = metrics.heightPixels;

    }

    void initCamera() {
        CameraX.unbindAll();
        CameraX.bindToLifecycle(this, providerPreview(), providerImageAnalysis(), providerImageCapture());
    }

    @Override
    protected void onDestroy() {
        CameraX.unbindAll();
        super.onDestroy();
    }

    private Preview providerPreview() {
        PreviewConfig config = new PreviewConfig.Builder()
                .setTargetAspectRatio(new Rational(widthPixels, heightPixels))//宽高比
                .setTargetResolution(new Size(widthPixels, heightPixels))//分辨率
                .setLensFacing(CameraX.LensFacing.BACK)//前后摄像头
                .build();
        Preview preview = new Preview(config);
        preview.setOnPreviewOutputUpdateListener(new Preview.OnPreviewOutputUpdateListener() {
            @Override
            public void onUpdated(Preview.PreviewOutput output) {
                text_ure_view.setSurfaceTexture(output.getSurfaceTexture());
            }
        });
        return preview;
    }

    private ImageAnalysis providerImageAnalysis() {
        ImageAnalysisConfig config = new ImageAnalysisConfig.Builder()
                .setImageReaderMode(ImageAnalysis.ImageReaderMode.ACQUIRE_LATEST_IMAGE)
                .setTargetResolution(new Size(1280, 720))
                .build();
        ImageAnalysis imageAnalysis = new ImageAnalysis(config);
        imageAnalysis.setAnalyzer(new ImageAnalysis.Analyzer() {
            @Override
            public void analyze(ImageProxy image, int rotationDegrees) {
                MyLog.d("analyze:" + rotationDegrees);
            }
        });
        return imageAnalysis;
    }

    ImageCapture imageCapture;

    private ImageCapture providerImageCapture() {
        ImageCaptureConfig config = new ImageCaptureConfig.Builder()
                .setCaptureMode(ImageCapture.CaptureMode.MAX_QUALITY)
                .setTargetRotation(getWindowManager().getDefaultDisplay().getRotation())
                .build();
        imageCapture = new ImageCapture(config);
        return imageCapture;
    }

}
