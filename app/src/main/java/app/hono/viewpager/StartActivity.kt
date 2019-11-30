package app.hono.viewpager

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_start.*
import java.io.IOException
import java.util.*

class StartActivity : AppCompatActivity() {

    private var uri: Uri? = null


    private val realmConfig = RealmConfiguration.Builder()
        .deleteRealmIfMigrationNeeded()
        .build()

    private val realm: Realm by lazy {
        Realm.getInstance(realmConfig)
    }

    companion object {
        const val REQUEST_CODE_PHOTO: Int = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        galleryButton.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent,
                REQUEST_CODE_PHOTO
            )
        }

        saveButton.setOnClickListener {
            realm.executeTransaction {
                val diary = it.createObject(Diary::class.java, UUID.randomUUID().toString())
                diary.imageId = uri.toString()
                diary.menuContent = menuEditText.text.toString()
                diary.memoContent = memoEditText.text.toString()
                diary.date = Date(System.currentTimeMillis())
                realm.copyToRealm(diary)
            }

            val intent:Intent = Intent(application, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_PHOTO && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                uri = data.data
                try {
                    var bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                    imageView.setImageBitmap(bitmap)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }
}
