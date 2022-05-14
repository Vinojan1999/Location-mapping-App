package com.vinojanv.android.mappingApp.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.vinojanv.android.mappingApp.R
import com.vinojanv.android.mappingApp.database.AppDatabase
import com.vinojanv.android.mappingApp.model.Client

class ClientListAdapter(private val mList: List<Client>, private val activity: Activity?) : RecyclerView.Adapter<ClientListAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = mList[position]

        // sets the text to the textview from our itemHolder class
        holder.name.text = itemsViewModel.name
        holder.lat.text = "Lat: "+ itemsViewModel.lat
        holder.long.text = "Lgt: "+ itemsViewModel.lng

        holder.closeBtn.setOnClickListener {
            holder.clientDao.remove(itemsViewModel)
            holder.card.removeAllViews()
        }

        holder.card.setOnClickListener {
            val bundle = bundleOf(
                "name" to itemsViewModel.name,
                "lat" to itemsViewModel.lat,
                "lng" to itemsViewModel.lng
            )
            it.findNavController().navigate(R.id.action_FirstFragment_to_mapsFragment, bundle)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val db = AppDatabase.getDatabase(itemView.context)
        val clientDao = db.clientDao()

        val name: TextView = itemView.findViewById(R.id.nameTextView)
        val lat: TextView = itemView.findViewById(R.id.latTextView)
        val long: TextView = itemView.findViewById(R.id.lngTextView)
        val card: CardView = itemView.findViewById(R.id.clientCard)
        val closeBtn: ImageButton = itemView.findViewById(R.id.closeButton)
    }
}
