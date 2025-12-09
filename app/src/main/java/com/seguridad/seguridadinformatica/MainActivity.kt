package com.seguridad.seguridadinformatica

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.auth0.android.jwt.JWT
import com.seguridad.seguridadinformatica.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idToken = intent.getStringExtra("idToken")

        if(idToken != null){
            val jwt = JWT(idToken)

            val name = jwt.getClaim("name").asString() ?: "Nombre no disponible"
            val email = jwt.getClaim("email").asString() ?: "Correo no disponible"
            val userId = jwt.getClaim("sub").asString() ?: "ID no disponible"

            binding.lblName.text = name
            binding.lblEmail.text = email
            binding.lblId.text = userId

        } else {
            binding.lblName.text = "Error: sin token"
        }


    }
}