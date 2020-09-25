package app.kurahashi.kuppie.wordkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val realm: Realm = Realm.getDefaultInstance()
    var state: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list1.layoutManager = LinearLayoutManager(this)
        val word = realm.where<Word>().findAll()
        val adapter = WordAdapter(word)
        list1.adapter = adapter
        var getId: Long = 0

        add1.setOnClickListener {
            if(state == 0) {
                realm.executeTransaction {
                    val maxId = realm.where<Word>().max("id")
                    val nextId = (maxId?.toLong() ?: 0L) + 1
                    getId = nextId
                    val word = realm.createObject<Word>(nextId)
                    val text = editText1.text.toString()
                    word.english = text
                    state = 1
                }
            }else{
                realm.executeTransaction {
                    val word = realm.where<Word>().equalTo("id", getId)?.findFirst()
                    val text = editText1.text.toString()
                    if (word != null) {
                        word.english = text
                    }
                    state = 0
                }
            }
            Snackbar.make(conteiner, "追加しました", Snackbar.LENGTH_SHORT).show()
        }

        add2.setOnClickListener {
            if(state == 0){
                realm.executeTransaction {
                    val maxId = realm.where<Word>().max("id")
                    val nextId = (maxId?.toLong() ?: 0L) + 1
                    getId = nextId
                    val word = realm.createObject<Word>(nextId)
                    val text = editText2.text.toString()
                    word.japanese = text
                    state = 1
                }
            }else {
                realm.executeTransaction {
                    val word = realm.where<Word>().equalTo("id", getId)?.findFirst()
                    val text = editText2.text.toString()
                    if (word != null) {
                        word.japanese = text
                    }
                    state = 0
                }
            }
            Snackbar.make(conteiner, "追加しました", Snackbar.LENGTH_SHORT).show()
        }

        adapter.setOnItemClickListener(object : WordAdapter.OnItemClickListener {
            override fun onItemClick(item: Word) {
                // クリック時の処理
                realm.executeTransaction {
                    realm.where<Word>().equalTo("id", item.id)
                        ?.findFirst()
                        ?.deleteFromRealm()
                }
                Snackbar.make(conteiner, "削除しました", Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}




