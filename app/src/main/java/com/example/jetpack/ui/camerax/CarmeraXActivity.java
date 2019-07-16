package com.example.jetpack.ui.camerax;

import android.os.Bundle;
import android.util.Rational;
import android.util.Size;
import android.view.TextureView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraX;
import androidx.camera.core.Preview;
import androidx.camera.core.PreviewConfig;

import com.example.jetpack.R;

public class CarmeraXActivity extends AppCompatActivity {

    TextureView text_ure_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carmera_x);
        text_ure_view = findViewById(R.id.text_ure_view);
        initCamera();
    }

    void initCamera() {
        PreviewConfig config = new PreviewConfig.Builder()
                .build();
        Preview preview = new Preview(config);
        preview.setOnPreviewOutputUpdateListener(new Preview.OnPreviewOutputUpdateListener() {
            @Override
            public void onUpdated(Preview.PreviewOutput output) {
                text_ure_view.setSurfaceTexture(output.getSurfaceTexture());
            }
        });
        CameraX.bindToLifecycle(this, preview);
    }

    @Override
    protected void onDestroy() {
        CameraX.unbindAll();
        super.onDestroy();
    }
}
