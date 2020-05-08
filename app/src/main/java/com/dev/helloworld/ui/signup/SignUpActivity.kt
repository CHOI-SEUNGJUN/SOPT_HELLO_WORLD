package com.dev.helloworld.ui.signup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dev.helloworld.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        initButton()
    }

    private fun initButton() {
        btnSignUp.setOnClickListener {
            val intent = Intent().apply {
                putExtra("id", edtId.text.toString())
                putExtra("pw", edtPassword.text.toString())
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
