package app.hono.viewpager

import android.app.Application
import io.realm.Realm

class LunchDiaryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}