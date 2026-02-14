package com.example.customercards

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.DefaultDecoderFactory

class ScannerActivity : AppCompatActivity() {
    private lateinit var barcodeView: DecoratedBarcodeView

    private val callback = object : BarcodeCallback {
        override fun barcodeResult(result: BarcodeResult?) {
            if (result != null && result.text != null) {
                val codeType = when (result.barcodeFormat) {
                    BarcodeFormat.QR_CODE -> "QR_CODE"
                    else -> "BARCODE"
                }

                val intent = Intent()
                intent.putExtra("SCANNED_CODE", result.text)
                intent.putExtra("CODE_TYPE", codeType)
                setResult(RESULT_OK, intent)
                finish()
            }
        }

        override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {
            // Nicht benötigt
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Code scannen"

        barcodeView = findViewById(R.id.barcodeScanner)
        
        // Unterstütze verschiedene Barcode-Formate
        val formats = listOf(
            BarcodeFormat.QR_CODE,
            BarcodeFormat.EAN_13,
            BarcodeFormat.EAN_8,
            BarcodeFormat.CODE_128,
            BarcodeFormat.CODE_39,
            BarcodeFormat.UPC_A,
            BarcodeFormat.UPC_E,
            BarcodeFormat.ITF,
            BarcodeFormat.CODABAR
        )

        barcodeView.barcodeView.decoderFactory = DefaultDecoderFactory(formats)
        barcodeView.initializeFromIntent(intent)
        barcodeView.decodeContinuous(callback)
    }

    override fun onResume() {
        super.onResume()
        barcodeView.resume()
    }

    override fun onPause() {
        super.onPause()
        barcodeView.pause()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
