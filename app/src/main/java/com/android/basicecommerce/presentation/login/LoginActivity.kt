package com.android.basicecommerce.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.android.basicecommerce.R
import com.android.basicecommerce.databinding.ActivityLoginBinding
import com.android.basicecommerce.di.Injector
import com.android.basicecommerce.presentation.MainActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import javax.inject.Inject


class LoginActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        private val REQUEST_GOOGLE_SIGN_IN = 101
    }

    @Inject
    lateinit var factory: LoginViewModelFactory

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var callbackManager: CallbackManager

    private var userName = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        (application as Injector).createLoginSubComponent()
            .inject(this)

        viewModel = ViewModelProvider(this, factory)
            .get(LoginViewModel::class.java)

        binding.viewModel = viewModel

        // Google Authentication
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        // Facebook Authentication
        callbackManager = CallbackManager.Factory.create()

        observeUserName()
        observePassword()
        observeInput()

        loginClicked()
        googleSignInClicked()
        facebookSignInClicked()
    }

    override fun onStart() {
        super.onStart()
        val googleAccount = GoogleSignIn.getLastSignedInAccount(this)

        if (googleAccount != null) {
            startNextActivity()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInWithGoogle(task)
        }
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun startNextActivity() {
        val intent = MainActivity.newIntent(this)
        startActivity(intent)
    }

    private fun observeInput() {
//        viewModel.isFormValid.observe(this, { valid ->
//            binding.btnLogin.isEnabled = valid
//        })
    }

    private fun validateForm() {
        binding.btnLogin.isEnabled = userName.isNotEmpty() && password.isNotEmpty()
    }

    private fun observeUserName() {
        viewModel.getUserName().observe(this, { userName ->
            this.userName = userName
            validateForm()
            if (userName.isEmpty()) binding.textInputUserName.error = "Jangan kosong"
            else binding.textInputUserName.error = null
        })
    }

    private fun observePassword() {
        viewModel.getPassword().observe(this, { password ->
            this.password = password
            validateForm()
            if (password.isEmpty()) binding.textInputPassword.error = "Jangan Kosong"
            else binding.textInputPassword.error = null
        })
    }

    private fun loginClicked() {
        binding.btnLogin.setOnClickListener {
            viewModel.changeLoadingState(true)

            val login = viewModel.login("", "")
            login.observe(this, { isSuccessLogin ->
                if (isSuccessLogin) {
                    viewModel.changeLoadingState(false)
                    startNextActivity()
                }
            })
        }
    }

    private fun googleSignInClicked() {
        binding.btnGoogleLogin.setOnClickListener {
            signInUseGoogleAccount()
        }
    }

    private fun signInUseGoogleAccount() {
        val intent = googleSignInClient.signInIntent
        startActivityForResult(intent, REQUEST_GOOGLE_SIGN_IN)
    }

    private fun handleSignInWithGoogle(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            startNextActivity()
        } catch (e: ApiException) {
            Log.e("MyTAG", "Google SignIn result failed code :  ${e.statusCode}")
        }
    }

    private fun facebookSignInClicked() {
        binding.btnFacebookLogin.registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
            override fun onSuccess(result: LoginResult?) {
                startNextActivity()
            }

            override fun onCancel() { }

            override fun onError(error: FacebookException?) {
                Log.e("MyTAG", "Facebook SignIn result error : ${error?.message}")
            }
        })
    }

}