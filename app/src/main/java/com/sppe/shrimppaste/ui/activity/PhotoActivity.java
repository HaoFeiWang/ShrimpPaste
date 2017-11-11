package com.sppe.shrimppaste.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.sppe.shrimppaste.R;
import com.sppe.shrimppaste.data.contacts.Contacts;
import com.sppe.shrimppaste.net.GlideHelp;

public class PhotoActivity extends AppCompatActivity {

    private ImageView ivContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        this.ivContent = (ImageView) findViewById(R.id.iv_content);
        Intent intent = getIntent();
        String url = intent.getStringExtra(Contacts.BUNDLE_RUL);

        GlideHelp glideHelp = new GlideHelp();
        glideHelp.fillImageNoPlace(this,url,ivContent);
    }


}
