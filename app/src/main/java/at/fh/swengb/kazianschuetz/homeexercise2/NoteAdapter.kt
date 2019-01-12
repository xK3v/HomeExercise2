package at.fh.swengb.kazianschuetz.homeexercise2

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter : RecyclerView.Adapter<NoteViewHolder>() {

    private var dataList = mutableListOf<Note>()

    fun updateData(list:List<Note>) {
        dataList = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(viewHolder: NoteViewHolder, position: Int) {
        val item = dataList[position]
        viewHolder.bindNote(item)
    }
}


class NoteViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
    fun bindNote(note:Note) {
        itemView.lb_title.text = note.title
        itemView.lb_content.text = note.content
    }

}