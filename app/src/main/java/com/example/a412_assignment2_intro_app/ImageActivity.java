package com.example.a412_assignment2_intro_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;

public class ImageActivity extends AppCompatActivity {

    private File photoFile;
    private Uri photoUri;

    Button backButton;
    Button resetButton;
    Button testButton;
    Button imageCapture;
    ImageView imageView;
    int picCount;

    private static final int CAMERA_REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_image);

        backButton = findViewById(R.id.btn_back);
        resetButton = findViewById(R.id.btn_test1);
        testButton = findViewById(R.id.btn_change);
        imageCapture = findViewById(R.id.btn_img_capture);
        imageView = findViewById(R.id.imageView);
        picCount = 0;

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent explicitIntent = new Intent(ImageActivity.this, MainActivity.class);
                startActivity(explicitIntent);
            }
        });

        //button to reset the imageview to have an "empty" image
        resetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                imageView.setImageResource(android.R.color.transparent);
            }
        });

        //Debugger type button to just change the imageview's image. The cat and dog are cute so I am keeping them for the final ver.
        testButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bitmap picture;
                if (picCount++ % 2 == 0) {
                    picture = BitmapFactory.decodeResource(getResources(), R.drawable.img_zog);
                } else {
                    picture = BitmapFactory.decodeResource(getResources(), R.drawable.img_zat);
                }
                imageView.setImageBitmap(picture);
            }
        });

        imageCapture.setOnClickListener(v -> {
            try {
                photoFile = createImageFile();
                photoUri = FileProvider.getUriForFile(
                        this,
                        getPackageName() + ".fileprovider",
                        photoFile
                );

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                cameraIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private File createImageFile() throws IOException {
        File imageDir = new File(getCacheDir(), "images");
        if (!imageDir.exists()) {
            imageDir.mkdirs();
        }

        return File.createTempFile("captured_", ".jpg", imageDir);
    }

    @Override
    @Deprecated
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            Glide.with(this).load(photoUri).into(imageView);
        }
    }

}