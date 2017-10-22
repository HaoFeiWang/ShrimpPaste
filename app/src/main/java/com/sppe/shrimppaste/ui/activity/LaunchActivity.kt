package com.sppe.shrimppaste.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.sppe.shrimppaste.R
import kotlinx.android.synthetic.main.activity_launch.*

/**
 *
 * Created by WangHaoFei on 2017/10/22.
 */
class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        btn_to_main.setOnClickListener {
            startActivity(Intent(this@LaunchActivity,HomeActivity::class.java))
        }
    }

}
