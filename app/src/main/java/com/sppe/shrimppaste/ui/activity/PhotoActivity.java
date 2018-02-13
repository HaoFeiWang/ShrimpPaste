package com.sppe.shrimppaste.ui.activity;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.sppe.shrimppaste.R;
import com.sppe.shrimppaste.data.contacts.Contacts;

public class PhotoActivity extends AppCompatActivity {
    private static final String TAG = Contacts.LOG_TAG + PhotoActivity.class.getSimpleName();

    private ImageView ivContent;

    private String url;

    private int marginTop;
    private int marginLeft;

    private float screenWidth;
    private float screenHeight;

    private float width;
    private float height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        initBundleExtra();
        initScreenSize();

        initView();
        initData();
    }

    private void initView() {
        ivContent = (ImageView) findViewById(R.id.iv_content);

        float scaleX = width / screenWidth;
        float scaleY = height / screenHeight;

        ivContent.setTranslationX(marginLeft - (screenWidth - width) / 2);
        ivContent.setTranslationY((screenHeight - height) / 2 - marginTop);

        ivContent.setScaleX(scaleX);
        ivContent.setScaleY(scaleY);
    }

    private void initBundleExtra() {
        Intent intent = getIntent();
        url = intent.getStringExtra(Contacts.GirlPhotoBundle.URL);
        marginTop = intent.getIntExtra(Contacts.GirlPhotoBundle.MARGIN_LEFT, 0);
        marginLeft = intent.getIntExtra(Contacts.GirlPhotoBundle.MARGIN_TOP, 0);
        marginLeft -= getResources().getDimensionPixelSize(R.dimen.action_bar_height);

        width = (float) intent.getIntExtra(Contacts.GirlPhotoBundle.WIDTH, 0);
        height = (float) (getResources().getDimensionPixelSize(R.dimen.girl_item_height));
    }

    private void initScreenSize() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            screenWidth = displayMetrics.widthPixels;
            screenHeight = displayMetrics.heightPixels;
        }
    }

    private void initData() {
        Glide.with(this)
                .load(url)
                .apply(new RequestOptions().override((int) width, (int) height))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                                   DataSource dataSource, boolean isFirstResource) {
                        ivContent.setImageDrawable(resource);
                        setImageFullScreen();
                        return true;
                    }
                }).into(ivContent);
    }

    private void setImageFullScreen() {

        float transX = (screenWidth - width) / 2;
        float transY = (screenHeight - height) / 2;

//        startScaleAnim(scaleX, scaleY, transX, transY);
    }

    private void startScaleAnim(float scaleX, float scaleY, float transX, float transY) {
        ivContent.animate()
                .scaleX(scaleX).scaleY(scaleY)
                .translationX(transX).translationY(transY)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        loadRealData();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();
    }

    private void loadRealData() {
        Log.i(TAG, "scale anim end!");

        Glide.with(this)
                .load(url)
                .apply(new RequestOptions().override((int) screenWidth, (int) screenHeight))
                .into(ivContent);
    }


}
