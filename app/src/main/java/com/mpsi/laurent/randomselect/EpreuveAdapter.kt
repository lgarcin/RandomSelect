package com.mpsi.laurent.randomselect

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dialog.view.*
import kotlinx.android.synthetic.main.epreuve_item.view.*
import java.time.format.DateTimeFormatter

class EpreuveAdapter : RecyclerView.Adapter<EpreuveAdapter.EpreuveViewHolder>() {
    private var epreuves: List<Epreuve> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpreuveViewHolder =
            EpreuveViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.epreuve_item, parent, false))

    override fun onBindViewHolder(holder: EpreuveViewHolder, position: Int) {
        val epreuve: Epreuve = epreuves[position]
        holder.nomView.text = epreuve.nom
        holder.dateView.text = epreuve.date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        holder.itemView.setOnClickListener {
            AlertDialog.Builder(it.context).run {
                val view = LayoutInflater.from(it.context).inflate(R.layout.dialog, it as ViewGroup, false)
                setView(view)
                view.eleves_view.adapter = ArrayAdapter(it.context, android.R.layout.simple_list_item_1, epreuve.eleves)
                create().show()
            }
        }
        holder.itemView.setOnLongClickListener {
            ViewModelProviders.of(it.context as FragmentActivity).get(EpreuveViewModel::class.java).delete(epreuve)
            true
        }
    }

    fun setItems(list_epreuves: List<Epreuve>) {
        epreuves = list_epreuves
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = epreuves.size

    class EpreuveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomView: TextView = itemView.name_view
        val dateView: TextView = itemView.date_view
    }
}