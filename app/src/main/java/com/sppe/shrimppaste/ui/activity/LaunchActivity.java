package com.sppe.shrimppaste.ui.activity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.sppe.shrimppaste.App;
import com.sppe.shrimppaste.R;
import com.sppe.shrimppaste.data.dao.PhotoEntry;
import com.sppe.shrimppaste.net.GlideHelp;
import com.sppe.shrimppaste.service.GankService;
import com.sppe.shrimppaste.service.GankServiceImpl;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 开屏页
 * Created by @author WHF on 2017/11/5.
 */
public class LaunchActivity extends AppCompatActivity {

    private static final int ANIMATION_DURATION = 5000;

    private ImageView ivContent;
    private TextView tvSkip;

    private GankService gankService;
    private App app;

    private ViewPropertyAnimator animator;

    private Disposable delayedDisposable;
    private Disposable imageDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        gankService = new GankServiceImpl(this);
        ivContent = (ImageView) findViewById(R.id.iv_content);
        tvSkip = (TextView) findViewById(R.id.tv_skip);

        app = (App) getApplication();

        if (app.isLaunch()) {
            delayedToHome();
        } else {
            initImage();
            app.setLaunched();
        }
    }

    @Override
    protected void onDestroy() {
        releaseMemory();
        super.onDestroy();
    }

    private void delayedToHome() {
        Observable
                .timer(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        delayedDisposable = d;
                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        startToHome();
                    }

                    @Override
                    public void onComplete() {
                        startToHome();
                    }
                });
    }

    private void initImage() {
        gankService.getRandowDbGirl()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PhotoEntry>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        imageDisposable = d;
                    }

                    @Override
                    public void onNext(PhotoEntry photoEntry) {
                        loadDbImage(photoEntry);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        startToHome();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void loadDbImage(PhotoEntry photoEntry) {
        if (photoEntry == null) {
            return;
        }
        Glide.with(LaunchActivity.this)
                .load(photoEntry.getUrl())
                .apply(GlideHelp.OPTIONS_NO_PLACE)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                                   DataSource dataSource, boolean isFirstResource) {
                        initTvSkip();
                        startAnimator();
                        return false;
                    }
                })
                .into(ivContent);
    }

    private void initTvSkip() {
        tvSkip.setVisibility(View.VISIBLE);
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startToHome();
            }
        });
    }

    private void startAnimator() {
        animator = ivContent.animate().scaleX(1.5f).scaleY(1.5f).setDuration(ANIMATION_DURATION)
                .setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int time = (int) animation.getCurrentPlayTime();
                        if (time >= ANIMATION_DURATION) {
                            startToHome();
                        }
                    }
                });
        animator.start();
    }

    public void startToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void releaseMemory() {
        if (animator != null) {
            animator.cancel();
            animator = null;
        }
        if (delayedDisposable != null && !delayedDisposable.isDisposed()) {
            delayedDisposable.dispose();
            delayedDisposable = null;
        }
        if (imageDisposable!=null && !imageDisposable.isDisposed()){
            imageDisposable.dispose();
            imageDisposable = null;
        }
    }
}
