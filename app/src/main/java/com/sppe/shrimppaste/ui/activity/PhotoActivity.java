package com.sppe.shrimppaste.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.sppe.shrimppaste.R;
import com.sppe.shrimppaste.data.contacts.Contacts;
import com.sppe.shrimppaste.util.GlideHelp;

public class PhotoActivity extends AppCompatActivity {

    private ImageView ivContent;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        this.ivContent = (ImageView) findViewById(R.id.iv_content);
        this.progressBar = (ProgressBar) findViewById(R.id.progress);
        Intent intent = getIntent();
        String url = intent.getStringExtra(Contacts.BUNDLE_RUL);

        Glide.with(this).load(url).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target,
                                        boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                           DataSource dataSource, boolean isFirstResource) {
                progressBar.clearAnimation();
                progressBar.setVisibility(View.GONE);
                return false;
            }
        }).apply(GlideHelp.optionsNoPlace).into(ivContent);
    }


}
