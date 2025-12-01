package com.seguridad.seguridadinformatica

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.seguridad.seguridadinformatica.databinding.ActivityLoginBinding
import com.seguridad.seguridadinformatica.util.SecurityUtil

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (SecurityUtil.getPin(this) == null) {
            startActivity(Intent(this, RegisterPinActivity::class.java))
            finish()
            return
        }

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val entered = binding.txtPin.text.toString()
            val saved = SecurityUtil.getPin(this)

            if (entered == saved) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "PIN incorrecto", Toast.LENGTH_SHORT).show()
            }
        }
    }
}