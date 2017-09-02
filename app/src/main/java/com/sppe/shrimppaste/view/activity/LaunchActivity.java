package com.sppe.shrimppaste.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.sppe.shrimppaste.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.to_main_btn)
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.to_main_btn:
                startActivity(new Intent(LaunchActivity.this,HomeActivity.class));
                Log.e("======","lets go");
                finish();
                break;
        }
    }

}
