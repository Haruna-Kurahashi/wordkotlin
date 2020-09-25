package app.kurahashi.kuppie.wordkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.list_item.view.*

class WordAdapter(data: OrderedRealmCollection<Word>) :
    RealmRecyclerViewAdapter<Word, WordAdapter.ViewHolder>(data, true) {
    private lateinit var listener: OnItemClickListener

    class ViewHolder(val cell: View) : RecyclerView.ViewHolder(cell) {
        val english:TextView = cell.englishword
        val japanese: TextView = cell.japaneseword
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordAdapter.ViewHolder, position: Int) {
        val word : Word? = getItem(position)
        holder.english.text = word?.english
        holder.japanese.text = word?.japanese
        holder.cell.setOnClickListener {
            if (word != null) {
                listener.onItemClick(word)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(item: Word)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

}