package com.seguridad.seguridadinformatica

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.result.Credentials
import com.seguridad.seguridadinformatica.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var account: Auth0
    private lateinit var apiClient: AuthenticationAPIClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        account = Auth0(
            "dhDZDsVovzYJGqcbO1dhXWE2hYIj0U0M",
            "dev-8gm8eqpotdln50ge.us.auth0.com"
        )

        apiClient = AuthenticationAPIClient(account)

        binding.btnLogin.setOnClickListener {
            doLogin()
        }
    }

    private fun doLogin() {
        val email = binding.txtEmail.text.toString().trim()
        val password = binding.txtPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Ingrese correo y contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        apiClient
            .login(email, password, "Username-Password-Authentication")
            .setScope("openid profile email")
            .start(object : Callback<Credentials, AuthenticationException> {

                override fun onSuccess(result: Credentials) {
                    val idToken = result.idToken
                    val accessToken = result.accessToken

                    if (accessToken != null) {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        intent.putExtra("idToken", idToken)
                        startActivity(intent)
                        finish()
                    }
                }

                override fun onFailure(error: AuthenticationException) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Login fallido: ${error.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }
}
