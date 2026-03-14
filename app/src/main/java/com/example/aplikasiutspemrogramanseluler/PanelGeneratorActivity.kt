package com.example.aplikasiutspemrogramanseluler

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PanelGeneratorActivity : AppCompatActivity() {

    private lateinit var textViewSapaan: TextView
    private lateinit var textViewStatusKelas: TextView
    private lateinit var textViewDaftarAbsen: TextView
    private lateinit var editTextJumlahMahasiswa: EditText
    private lateinit var editTextRataRataNilai: EditText
    private lateinit var btnProsesData: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panel_generator)

        textViewSapaan = findViewById(R.id.textViewSapaan)
        textViewStatusKelas = findViewById(R.id.textViewStatusKelas)
        textViewDaftarAbsen = findViewById(R.id.textViewDaftarAbsen)
        editTextJumlahMahasiswa = findViewById(R.id.editTextJumlahMahasiswa)
        editTextRataRataNilai = findViewById(R.id.editTextRataRataNilai)
        btnProsesData = findViewById(R.id.btnProsesData)

        // Mengambil data dari Intent
        val namaDosen = intent.getStringExtra("NAMA_DOSEN") ?: ""

        // Menampilkan sapaan menggunakan resource string dengan placeholder
        textViewSapaan.text = getString(R.string.sapaan_dosen, namaDosen)

        btnProsesData.setOnClickListener {
            // Mendapatkan nilai dari input
            val jumlahMahasiswaStr = editTextJumlahMahasiswa.text.toString()
            val rataRataNilaiStr = editTextRataRataNilai.text.toString()

            if (jumlahMahasiswaStr.isNotEmpty() && rataRataNilaiStr.isNotEmpty()) {
                val jumlahMahasiswa = jumlahMahasiswaStr.toIntOrNull() ?: 0
                val rataRataNilai = rataRataNilaiStr.toDoubleOrNull() ?: 0.0

                // Logika 1: Tentukan status kelas
                // Menggunakan resource string untuk status agar dapat diterjemahkan
                val statusKelas = when {
                    rataRataNilai >= 80 -> getString(R.string.status_sangat_baik)
                    rataRataNilai >= 60 -> getString(R.string.status_cukup)
                    else -> getString(R.string.status_kurang)
                }
                // Menampilkan status kelas menggunakan resource string dengan placeholder
                textViewStatusKelas.text = getString(R.string.status_kelas, statusKelas)

                // Logika 2: Perulangan untuk daftar absen
                val daftarAbsen = StringBuilder()
                for (i in 1..jumlahMahasiswa) {
                    if (i > 100) break // Safety limit
                    // Menggunakan resource string dengan placeholder untuk item daftar absen
                    daftarAbsen.append(getString(R.string.daftar_absen_item, i))
                }
                textViewDaftarAbsen.text = daftarAbsen.toString()
            }
        }
    }
}
