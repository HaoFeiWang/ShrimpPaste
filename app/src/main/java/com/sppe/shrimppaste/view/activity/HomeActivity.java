package com.sppe.shrimppaste.view.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.sppe.shrimppaste.R;
import com.sppe.shrimppaste.view.fragment.AndroidFragment;
import com.sppe.shrimppaste.view.fragment.GirlFragment;
import com.sppe.shrimppaste.view.fragment.IosFragment;
import com.sppe.shrimppaste.view.fragment.PersonalFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.home_bottom_bnv)
    BottomNavigationView bottomNavigationView;

    private Fragment[] fragmentList;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        initFragments();
        initNavigationView();
    }

    private void initNavigationView() {
        bottomNavigationView.setSelectedItemId(0);
        getSupportFragmentManager().beginTransaction().add(R.id.home_content_rl,fragmentList[0]).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                onBottomTabItemClick(item);
                if (currentFragment!=null){
                    getSupportFragmentManager().beginTransaction()
                        .replace(R.id.home_content_rl,currentFragment).commit();
                    Log.e("==========",currentFragment.toString());
                }
                return true;
            }
        });
    }

    private void onBottomTabItemClick(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_bottom_tab_android:
                currentFragment = fragmentList[0];
                break;
            case R.id.menu_item_bottom_tab_ios:
                currentFragment = fragmentList[1];
                break;
            case R.id.menu_item_bottom_tab_girl:
                currentFragment = fragmentList[2];
                break;
            case R.id.menu_item_bottom_tab_personal:
                currentFragment = fragmentList[3];
                break;
        }
    }

    private void initFragments() {
        fragmentList = new Fragment[4];
        fragmentList[0] = new AndroidFragment();
        fragmentList[1] = new IosFragment();
        fragmentList[2] = new GirlFragment();
        fragmentList[3] = new PersonalFragment();
    }


}
