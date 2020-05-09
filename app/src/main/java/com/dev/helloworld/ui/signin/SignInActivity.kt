package com.dev.helloworld.ui.signin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.dev.helloworld.R
import com.dev.helloworld.databinding.ActivitySignInBinding
import com.dev.helloworld.ui.BaseActivity
import com.dev.helloworld.ui.signup.SignUpActivity
import com.dev.helloworld.ui.some.SomeActivity
import com.dev.helloworld.util.shortToast
import kotlinx.android.synthetic.main.activity_sign_in.*
import javax.inject.Inject

class SignInActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: SignInViewModel
    lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        initBinding()
        initButton()
        observeViewModel()
    }

    private fun initBinding() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[SignInViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun initButton() {
        binding.btnSignUp.setOnClickListener {
            startActivityForResult(Intent(this, SignUpActivity::class.java), GOGOSIGNUP)
        }
    }

    private fun observeViewModel() {
        viewModel.isSuccess.observe(this, Observer { success ->
            if (success) {
                Intent(this, SomeActivity::class.java)
                    .run { startActivity(this) }
                finish()
            } else shortToast("what the.....")
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == GOGOSIGNUP) {
            viewModel.getInfoFromSignUp(data?.getStringExtra("id")!!, data.getStringExtra("pw")!!)
        }
    }

    private companion object {
        const val GOGOSIGNUP = 1020
    }
}
