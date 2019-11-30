package app.hono.viewpager

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Diary(
    @PrimaryKey open var id: String = UUID.randomUUID().toString(),
    open var imageId: String? = null,
    open var menuContent: String = "",
    open var memoContent: String = "",
    open var date: Date = Date(System.currentTimeMillis())
) : RealmObject()