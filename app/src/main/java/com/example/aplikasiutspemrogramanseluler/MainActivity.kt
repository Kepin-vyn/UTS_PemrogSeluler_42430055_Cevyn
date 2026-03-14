package com.example.aplikasiutspemrogramanseluler

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNamaDosen: EditText
    private lateinit var btnMasuk: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNamaDosen = findViewById(R.id.editTextNamaDosen)
        btnMasuk = findViewById(R.id.btnMasuk)

        btnMasuk.setOnClickListener {
            val namaDosen = editTextNamaDosen.text.toString()
            if (namaDosen.isNotEmpty()) {
                val intent = Intent(this@MainActivity, PanelGeneratorActivity::class.java)
                intent.putExtra("NAMA_DOSEN", namaDosen)
                startActivity(intent)
            }
        }
    }
}
