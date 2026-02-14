package com.example.customercards

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder

class CardDetailActivity : AppCompatActivity() {
    private lateinit var tvCardName: TextView
    private lateinit var tvCardCode: TextView
    private lateinit var ivBarcode: ImageView
    private lateinit var btnDelete: Button
    private lateinit var dbHelper: CardDatabaseHelper
    private var cardId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        dbHelper = CardDatabaseHelper(this)

        tvCardName = findViewById(R.id.tvCardName)
        tvCardCode = findViewById(R.id.tvCardCode)
        ivBarcode = findViewById(R.id.ivBarcode)
        btnDelete = findViewById(R.id.btnDelete)

        cardId = intent.getLongExtra("CARD_ID", -1)

        if (cardId != -1L) {
            loadCard()
        } else {
            Toast.makeText(this, "Fehler beim Laden der Karte", Toast.LENGTH_SHORT).show()
            finish()
        }

        btnDelete.setOnClickListener {
            showDeleteDialog()
        }
    }

    private fun loadCard() {
        val card = dbHelper.getCard(cardId)
        
        if (card != null) {
            tvCardName.text = card.name
            tvCardCode.text = card.code
            supportActionBar?.title = card.name

            generateBarcode(card.code, card.codeType)
        } else {
            Toast.makeText(this, "Karte nicht gefunden", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun generateBarcode(code: String, type: String) {
        try {
            val format = when (type) {
                "QR_CODE" -> BarcodeFormat.QR_CODE
                else -> BarcodeFormat.CODE_128
            }

            val multiFormatWriter = MultiFormatWriter()
            val bitMatrix: BitMatrix = if (type == "QR_CODE") {
                multiFormatWriter.encode(code, format, 800, 800)
            } else {
                multiFormatWriter.encode(code, format, 1200, 400)
            }

            val barcodeEncoder = BarcodeEncoder()
            val bitmap: Bitmap = barcodeEncoder.createBitmap(bitMatrix)
            ivBarcode.setImageBitmap(bitmap)
        } catch (e: Exception) {
            Toast.makeText(this, "Fehler beim Generieren des Codes", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

    private fun showDeleteDialog() {
        AlertDialog.Builder(this)
            .setTitle("Karte löschen")
            .setMessage("Möchten Sie diese Karte wirklich löschen?")
            .setPositiveButton("Löschen") { _, _ ->
                deleteCard()
            }
            .setNegativeButton("Abbrechen", null)
            .show()
    }

    private fun deleteCard() {
        val deleted = dbHelper.deleteCard(cardId)
        if (deleted > 0) {
            Toast.makeText(this, "Karte gelöscht", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Fehler beim Löschen", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
