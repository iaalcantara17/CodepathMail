package com.example.codepathmail

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmailAdapter(private val emails: MutableList<Email>) :
    RecyclerView.Adapter<EmailAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val senderTextView: TextView = itemView.findViewById(R.id.senderTv)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTv)
        val summaryTextView: TextView = itemView.findViewById(R.id.summaryTv)
        val dateTextView: TextView = itemView.findViewById(R.id.dateTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.email_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val email = emails[position]

        holder.senderTextView.text = email.sender
        holder.titleTextView.text = email.title
        holder.summaryTextView.text = email.summary
        holder.dateTextView.text = email.date

        val boldStyle = if (email.isUnread) Typeface.BOLD else Typeface.NORMAL
        holder.senderTextView.setTypeface(null, boldStyle)
        holder.titleTextView.setTypeface(null, boldStyle)

        holder.itemView.setOnClickListener {
            val p = holder.adapterPosition
            if (p != RecyclerView.NO_POSITION) {
                val clicked = emails[p]
                if (clicked.isUnread) {
                    clicked.isUnread = false
                    notifyItemChanged(p)
                }
            }
        }
    }

    override fun getItemCount(): Int = emails.size

    fun addEmails(newEmails: List<Email>) {
        val start = emails.size
        emails.addAll(newEmails)
        notifyItemRangeInserted(start, newEmails.size)
    }
}
