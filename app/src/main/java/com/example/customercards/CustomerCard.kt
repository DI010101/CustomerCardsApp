package com.example.customercards

data class CustomerCard(
    val id: Long = 0,
    val name: String,
    val code: String,
    val codeType: String, // "QR_CODE" oder "BARCODE"
    val createdAt: Long = System.currentTimeMillis()
)
