package pt.ipt.dam2022.api.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pt.ipt.dam2022.api.R
import pt.ipt.dam2022.api.model.Note

class NoteListAdapter(
        private val notes: List<Note>, private val context: Context
) : RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
            parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.node_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder?.let {
            it.bindView(note)
        }
    }

    /**
     * returns the number of nodes that the JSON from API has
     */
    override fun getItemCount(): Int {
        return notes.size
    }

    /**
     * add the data extracted each 'note'
     * to the interface
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // this function acts like glue, from data to the cardView
        fun bindView(note: Note) {
            // find objects from interface
            val title = itemView.findViewById<TextView>(R.id.noteItem_title)
            val description: TextView = itemView.findViewById(R.id.nodeItem_description)

            // assign data to interface objects
            title.text = note.title
            description.text = note.description
        }
    }
}