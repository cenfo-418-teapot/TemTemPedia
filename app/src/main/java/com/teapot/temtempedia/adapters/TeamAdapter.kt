package com.teapot.temtempedia.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.chip.Chip
import com.teapot.temtempedia.R
import com.teapot.temtempedia.data.getAllTemtems
import com.teapot.temtempedia.models.Team

class TeamAdapter(options: FirestoreRecyclerOptions<Team>, val context: Context) :
    FirestoreRecyclerAdapter<Team, TeamAdapter.ViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val teamView =
            LayoutInflater.from(parent.context).inflate(R.layout.team_item, parent, false)
        return ViewHolder(teamView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Team) {
        holder.title.text = model.title
        holder.description.text = getTemtemNames(model.temtemIds)
        holder.amount.text = model.temtemIds?.size.toString()
    }

    private fun getTemtemNames(idList: List<Int>?): String{
        if (idList == null || idList.isEmpty()){
            return "No temtems found"
        }
        return getAllTemtems(context)
            .filter { idList.contains(it.id)}
            .map { it.nombre}
            .reduce{acc, name -> "$acc, $name" }
    }

    class ViewHolder(teamView: View) : RecyclerView.ViewHolder(teamView) {

        val title: TextView = teamView.findViewById(R.id.tvTitle)
        val description: TextView = teamView.findViewById(R.id.tvDescription)
        val amount: Chip = teamView.findViewById(R.id.cpAmount)

    }

}
