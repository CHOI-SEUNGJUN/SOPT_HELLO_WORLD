package com.dev.helloworld

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        initButton()
    }

    private fun initButton() {
        btnSignUp.setOnClickListener {
            startActivityForResult(Intent(this, SignUpActivity::class.java), GOGOSIGNUP)
        }

        btnSignIn.setOnClickListener {
            startActivity(Intent(this, SomeActivity::class.java))
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            edtId.setText(data?.getStringExtra("id"))
            edtPassword.setText(data?.getStringExtra("pw"))
        }
    }

    companion object {
        const val GOGOSIGNUP = 1020
    }
}
