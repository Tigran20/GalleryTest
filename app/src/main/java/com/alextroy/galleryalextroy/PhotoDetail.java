package com.alextroy.galleryalextroy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class PhotoDetail extends AppCompatActivity {

    private ImageView photo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        photo = findViewById(R.id.photo_detail);

        Glide.with(this).load(getPhoto()).into(photo);
    }

    public static void startPhotoDeatil(Context context, String photo) {
        Intent intent = new Intent(context, PhotoDetail.class);
        intent.putExtra("photo", photo);
        context.startActivity(intent);
    }

    private String getPhoto() {
        return getIntent().getStringExtra("photo");
    }
}
