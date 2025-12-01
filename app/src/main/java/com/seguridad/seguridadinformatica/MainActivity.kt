package com.seguridad.seguridadinformatica

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
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

        binding.lblName.text = "Pablo Alfonso Vargas Melgar"
        binding.lblEmail.text = "pablo.alfonso.vargas@example.com"
        binding.lblId.text = "2470004440202"
    }
}