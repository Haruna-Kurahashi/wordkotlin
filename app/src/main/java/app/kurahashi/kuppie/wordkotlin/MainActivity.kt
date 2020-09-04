package app.kurahashi.kuppie.wordkotlin

import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter = ArrayAdapter<kotlin.Any?>(this, android.R.layout.simple_expandable_list_item_1)
        var adapter2 = ArrayAdapter<kotlin.Any?>(this, android.R.layout.simple_expandable_list_item_1)

        listView1.setAdapter(adapter)
        listView2.setAdapter(adapter2)

        listView1.setOnItemClickListener{ parent, view, position, id ->
            val adapter = listView1.getAdapter() as ArrayAdapter<*>
            val item = adapter.getItem(position) as String?
            adapter.remove(item as Nothing?)
            adapter.add(item)
        }

        listView2.onItemClickListener =
            OnItemClickListener { parent, view, position, id ->
                val adapter = listView2.adapter as ArrayAdapter<*>
                val item = adapter.getItem(position) as String?
                adapter.remove(item as Nothing?)
                adapter.add(item)
            }

        listView1.setOnItemLongClickListener(OnItemLongClickListener { parent, view, position, id ->
            val adapter = listView1.getAdapter() as ArrayAdapter<*>
            val item = adapter.getItem(position) as String?
            adapter.remove(item as Nothing?)
            false
        })

        listView2.onItemLongClickListener =
            OnItemLongClickListener { parent, view, position, id ->
                val adapter = listView2.adapter as ArrayAdapter<*>
                val item = adapter.getItem(position) as String?
                adapter.remove(item as Nothing?)
                false
            }
    }
}