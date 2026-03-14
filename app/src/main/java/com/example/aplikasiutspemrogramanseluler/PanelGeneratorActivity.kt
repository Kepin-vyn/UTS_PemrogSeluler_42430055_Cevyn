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
        textViewSapaan.text = getString(R.string.sapaan_dosen, namaDosen)

        // Membaca daftar nama artis dari assets/names.txt
        val daftarNama: List<String> = assets.open("names.txt").bufferedReader().readLines()

        btnProsesData.setOnClickListener {
            val jumlahMahasiswaStr = editTextJumlahMahasiswa.text.toString()
            val rataRataNilaiStr = editTextRataRataNilai.text.toString()

            if (jumlahMahasiswaStr.isNotEmpty() && rataRataNilaiStr.isNotEmpty()) {
                val jumlahMahasiswa = jumlahMahasiswaStr.toIntOrNull() ?: 0
                val rataRataNilai = rataRataNilaiStr.toDoubleOrNull() ?: 0.0

                // Tentukan status kelas
                val statusKelas = when {
                    rataRataNilai >= 80 -> getString(R.string.status_sangat_baik)
                    rataRataNilai >= 60 -> getString(R.string.status_cukup)
                    else -> getString(R.string.status_kurang)
                }
                textViewStatusKelas.text = getString(R.string.status_kelas, statusKelas)

                // Loop untuk menampilkan daftar absen dengan nama artis
                val daftarAbsen = StringBuilder()
                for (i in 1..jumlahMahasiswa) {
                    val nama = daftarNama[(i - 1) % daftarNama.size] // ulangi jika jumlah > 100
                    daftarAbsen.append("$i. $nama\n")
                }
                textViewDaftarAbsen.text = daftarAbsen.toString()
            }
        }
    }
}
