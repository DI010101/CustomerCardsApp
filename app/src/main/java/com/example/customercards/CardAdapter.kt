package com.example.customercards

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class CardAdapter(
    private val cards: List<CustomerCard>,
    private val context: Context,
    private val onClick: (CustomerCard) -> Unit
) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCardName: TextView = view.findViewById(R.id.tvCardName)
        val tvCardType: TextView = view.findViewById(R.id.tvCardType)
        val tvCardDate: TextView = view.findViewById(R.id.tvCardDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cards[position]
        
        holder.tvCardName.text = card.name
        
        val typeText = when (card.codeType) {
            "QR_CODE" -> "QR-Code"
            else -> "Barcode"
        }
        holder.tvCardType.text = typeText
        
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN)
        holder.tvCardDate.text = dateFormat.format(Date(card.createdAt))
        
        holder.itemView.setOnClickListener {
            onClick(card)
        }
    }

    override fun getItemCount() = cards.size
}
