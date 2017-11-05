package com.sppe.shrimppaste.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.sppe.shrimppaste.R;
import com.sppe.shrimppaste.ui.fragment.AndroidFragment;
import com.sppe.shrimppaste.ui.fragment.GirlFragment;
import com.sppe.shrimppaste.ui.fragment.IosFragment;
import com.sppe.shrimppaste.ui.fragment.PersonalFragment;

/**
 * Created by WHF on 2017/11/5.
 */

public class HomeActivity extends AppCompatActivity {

    private Fragment[] fragmentList;
    private int currentPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initFragmentList();
        initNavigationView();
    }

    private void initFragmentList() {
        fragmentList = new Fragment[4];
        fragmentList[0] = new GirlFragment();
        fragmentList[1] = new AndroidFragment();
        fragmentList[2] = new IosFragment();
        fragmentList[3] = new PersonalFragment();

        currentPosition = 0;
    }


    private void initNavigationView() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bnv_home_bottom);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.rl_home_content, fragmentList[currentPosition]).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        onBottomTabItemClick(item);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.rl_home_content, fragmentList[currentPosition]).commit();
                        return true;
                    }
                }
        );
    }

    private void onBottomTabItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_girl:
                currentPosition = 0;
                break;
            case R.id.item_android:
                currentPosition = 1;
                break;
            case R.id.item_ios:
                currentPosition = 2;
                break;
            case R.id.item_more:
                currentPosition = 3;
                break;
            default:
                break;
        }
    }

}
