package com.seguridad.seguridadinformatica

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.seguridad.seguridadinformatica.databinding.ActivityRegisterPinBinding
import com.seguridad.seguridadinformatica.util.SecurityUtil

class RegisterPinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterPinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterPinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (SecurityUtil.getPin(this) != null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        binding.btnSavePin.setOnClickListener {
            val pin1 = binding.txtPin1.text.toString()
            val pin2 = binding.txtPin2.text.toString()

            if (pin1.length < 4) {
                Toast.makeText(this, "El PIN debe tener al menos 4 dígitos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pin1 != pin2) {
                Toast.makeText(this, "Los PIN no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            SecurityUtil.savePin(this, pin1)

            Toast.makeText(this, "PIN registrado", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}