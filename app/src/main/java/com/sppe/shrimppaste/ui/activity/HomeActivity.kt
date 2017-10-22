package com.sppe.shrimppaste.ui.activity

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.MenuItem

import com.sppe.shrimppaste.R
import com.sppe.shrimppaste.ui.fragment.AndroidFragment
import com.sppe.shrimppaste.ui.fragment.GirlFragment
import com.sppe.shrimppaste.ui.fragment.IosFragment
import com.sppe.shrimppaste.ui.fragment.PersonalFragment

import com.sppe.shrimppaste.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*

/**
 *
 * Created by WangHaoFei on 2017/10/22.
 */
class HomeActivity : BaseActivity() {

    private var fragmentList: Array<Fragment> = arrayOf(
            AndroidFragment(),
            IosFragment(),
            GirlFragment(),
            PersonalFragment()
    )

    private var currentFragment: Fragment = fragmentList[0]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initNavigationView()
    }

    private fun initNavigationView() {
        supportFragmentManager.beginTransaction().add(R.id.rl_home_content, fragmentList[0]).commit()
        bnv_home_bottom.setOnNavigationItemSelectedListener { item ->
            onBottomTabItemClick(item)
            supportFragmentManager.beginTransaction()
                    .replace(R.id.rl_home_content, currentFragment).commit()
            true
        }
    }

    private fun onBottomTabItemClick(item: MenuItem) {
        when (item.itemId) {
            R.id.item_android -> currentFragment = fragmentList[0]
            R.id.item_ios -> currentFragment = fragmentList[1]
            R.id.item_girl -> currentFragment = fragmentList[2]
            R.id.item_more -> currentFragment = fragmentList[3]
        }
    }

}
