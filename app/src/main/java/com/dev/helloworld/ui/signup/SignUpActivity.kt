package com.dev.helloworld.ui.signup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.dev.helloworld.R
import com.dev.helloworld.databinding.ActivitySignUpBinding
import com.dev.helloworld.ui.BaseActivity
import com.dev.helloworld.util.shortToast
import kotlinx.android.synthetic.main.activity_sign_up.*
import javax.inject.Inject

class SignUpActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setContentView(R.layout.activity_sign_up)

        initBinding()
        observeViewModel()
    }

    private fun initBinding() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[SignUpViewModel::class.java]
        val binding : ActivitySignUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun observeViewModel() {
        viewModel.isSuccess.observe(this, Observer { success ->
              if (success) {
                  val intent = Intent().apply {
                      putExtra("id", viewModel.id.value!!)
                      putExtra("pw", viewModel.pw.value!!)
                  }
                  setResult(Activity.RESULT_OK, intent)
                  finish()
              } else shortToast("what the..")
        })
    }

}
