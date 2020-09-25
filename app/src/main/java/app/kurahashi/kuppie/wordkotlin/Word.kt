package app.kurahashi.kuppie.wordkotlin

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Word : RealmObject() {
    @PrimaryKey
    open var id : Long = 0
    open var english : String = ""
    open var japanese : String = ""
}