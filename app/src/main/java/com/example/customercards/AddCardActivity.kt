package com.example.customercards

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class AddCardActivity : AppCompatActivity() {
    private lateinit var etCardName: EditText
    private lateinit var etCardCode: EditText
    private lateinit var rgCodeType: RadioGroup
    private lateinit var btnSave: Button
    private lateinit var btnScan: Button
    private lateinit var dbHelper: CardDatabaseHelper
    private val SCAN_REQUEST_CODE = 200
    private val CAMERA_PERMISSION_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Neue Karte hinzufügen"

        dbHelper = CardDatabaseHelper(this)

        etCardName = findViewById(R.id.etCardName)
        etCardCode = findViewById(R.id.etCardCode)
        rgCodeType = findViewById(R.id.rgCodeType)
        btnSave = findViewById(R.id.btnSave)
        btnScan = findViewById(R.id.btnScan)

        btnScan.setOnClickListener {
            if (checkCameraPermission()) {
                openScanner()
            } else {
                requestCameraPermission()
            }
        }

        btnSave.setOnClickListener {
            saveCard()
        }
    }

    private fun checkCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            CAMERA_PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openScanner()
            } else {
                Toast.makeText(this, "Kamera-Berechtigung erforderlich", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openScanner() {
        val intent = Intent(this, ScannerActivity::class.java)
        startActivityForResult(intent, SCAN_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SCAN_REQUEST_CODE && resultCode == RESULT_OK) {
            val scannedCode = data?.getStringExtra("SCANNED_CODE")
            val codeType = data?.getStringExtra("CODE_TYPE")
            
            scannedCode?.let {
                etCardCode.setText(it)
            }
            
            codeType?.let {
                when (it) {
                    "QR_CODE" -> findViewById<RadioButton>(R.id.rbQRCode).isChecked = true
                    "BARCODE" -> findViewById<RadioButton>(R.id.rbBarcode).isChecked = true
                }
            }
        }
    }

    private fun saveCard() {
        val name = etCardName.text.toString().trim()
        val code = etCardCode.text.toString().trim()
        
        if (name.isEmpty()) {
            Toast.makeText(this, "Bitte Kartennamen eingeben", Toast.LENGTH_SHORT).show()
            return
        }
        
        if (code.isEmpty()) {
            Toast.makeText(this, "Bitte Code eingeben oder scannen", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedId = rgCodeType.checkedRadioButtonId
        val codeType = when (selectedId) {
            R.id.rbQRCode -> "QR_CODE"
            R.id.rbBarcode -> "BARCODE"
            else -> "BARCODE"
        }

        val card = CustomerCard(0, name, code, codeType, System.currentTimeMillis())
        val id = dbHelper.insertCard(card)

        if (id > 0) {
            Toast.makeText(this, "Karte gespeichert", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Fehler beim Speichern", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
