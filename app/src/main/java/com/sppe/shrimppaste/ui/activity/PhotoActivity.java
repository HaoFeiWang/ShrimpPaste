package com.sppe.shrimppaste.ui.activity;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.sppe.shrimppaste.R;
import com.sppe.shrimppaste.data.contacts.Contacts;

public class PhotoActivity extends AppCompatActivity {
    private static final String TAG = Contacts.LOG_TAG + PhotoActivity.class.getSimpleName();

    private static final float MIN_SCALE = 0.3f;
    private static final float MIN_ALPHA = 0.3f;

    private RelativeLayout rlRoot;
    private ImageView ivContent;

    private String url;

    private int marginTop;
    private int marginLeft;

    private float screenWidth;
    private float screenHeight;

    private float width;
    private float height;

    private float touchSlop;

    private float scale;
    private float alpha;
    private float translationX;
    private float translationY;
    private boolean isScaling;
    private float thisScale;

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
        rlRoot = (RelativeLayout) findViewById(R.id.rl_photo_root);
        ivContent = (ImageView) findViewById(R.id.iv_content);

        thisScale = width / screenWidth;
        ivContent.setScaleX(thisScale);
        ivContent.setScaleY(thisScale);

        ivContent.setTranslationX(marginLeft - (screenWidth - width) / 2);
        ivContent.setTranslationY(marginTop - (screenHeight - height) / 2);
    }

    private void initBundleExtra() {
        Intent intent = getIntent();
        url = intent.getStringExtra(Contacts.GirlPhotoBundle.URL);
        marginTop = intent.getIntExtra(Contacts.GirlPhotoBundle.MARGIN_TOP, 0);
        marginLeft = intent.getIntExtra(Contacts.GirlPhotoBundle.MARGIN_LEFT, 0);
        marginTop -= getResources().getDimensionPixelSize(R.dimen.action_bar_height);

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

        touchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        scale = 1;
        alpha = 1;
    }

    private void initData() {
        Glide.with(this)
                .load(url)
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
                        startScaleAnim(1, 1, 0, 0);
                        return true;
                    }
                }).into(ivContent);
    }

    private void startScaleAnim(float scaleX, float scaleY,
                                float translationX, float translationY) {
        ivContent.animate()
                .scaleX(scaleX).scaleY(scaleY)
                .translationX(translationX).translationY(translationY)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Log.i(TAG, "onAnimationEnd");
                        rlRoot.setBackgroundColor(Color.BLACK);
                        setImageTouchListener();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .start();
    }

    private void setImageTouchListener() {
        ivContent.setOnTouchListener(new View.OnTouchListener() {

            float firstTouchX = 0;
            float firstTouchY = 0;

            float lastTouchX = 0;
            float lastTouchY = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        firstTouchX = event.getRawX();
                        firstTouchY = event.getRawY();
                        lastTouchX = firstTouchX;
                        lastTouchY = firstTouchY;
                        isScaling = false;
                        break;
                    case MotionEvent.ACTION_MOVE:

                        float currentX = event.getRawX();
                        float currentY = event.getRawY();

                        float deltaX = currentX - firstTouchX;
                        float deltaY = currentY - firstTouchY;

                        if (deltaY > touchSlop && deltaY > Math.abs(deltaX)) {
                            isScaling = true;
                        }

                        if (isScaling) {

                            float curScale = scale + (lastTouchY - currentY) / screenHeight;
                            if (curScale >= MIN_SCALE && curScale <= 1) {
                                scale = curScale;
                            }
                            ivContent.setScaleX(scale);
                            ivContent.setScaleY(scale);

                            float curAlpha = alpha + (lastTouchY - currentY) / screenHeight;
                            if (curAlpha > MIN_ALPHA && curAlpha <= 1) {
                                alpha = curAlpha;
                                rlRoot.setBackgroundColor(Color.argb((int) (255 * alpha), 0, 0, 0));
                            }

                            translationX = translationX + (currentX - lastTouchX);
                            translationY = translationY + (currentY - lastTouchY);
                            ivContent.setTranslationY(translationY);
                            ivContent.setTranslationX(translationX);
                        }
                        lastTouchX = currentX;
                        lastTouchY = currentY;

                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        Log.i(TAG, "scale = " + scale);
                        if (scale >= 0.8 && scale <= 1) {
                            scale = 1;
                            alpha = 1;
                            translationY = 0;
                            translationX = 0;
                            startScaleAnim(1, 1, 0, 0);
                        } else {
                            scale = thisScale;
                            alpha = thisScale;
                            translationX = marginLeft - (screenWidth - width) / 2;
                            translationY = marginTop - (screenHeight - height) / 2;

                            stopScaleAnim(thisScale, thisScale,
                                    marginLeft - (screenWidth - width) / 2,
                                    marginTop - (screenHeight - height) / 2);
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void stopScaleAnim(float scaleX, float scaleY,
                                float translationX, float translationY) {
        ivContent.animate()
                .scaleX(scaleX).scaleY(scaleY)
                .translationX(translationX).translationY(translationY)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        rlRoot.setBackgroundColor(Color.TRANSPARENT);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Log.i(TAG, "onAnimationEnd");
                        PhotoActivity.this.finish();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .start();
    }


    private void moveAndScaleImage(MotionEvent event) {

    }
}
