package app.hono.viewpager

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import app.hono.viewpager.databinding.ItemViewPagerBinding
import java.text.SimpleDateFormat
import java.util.*
import android.graphics.BitmapFactory
import android.graphics.Bitmap

class CustomPagerAdapter(val context: Context, val diaryList: List<Diary>) : PagerAdapter() {

    override fun getCount(): Int = diaryList.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = ItemViewPagerBinding.inflate(LayoutInflater.from(context), container, false)
        diaryList[position].let {
            val bmImg = BitmapFactory.decodeFile(it.imagePath)
            binding.imageView.setImageBitmap(bmImg)
            binding.menuTextView.text = it.menuContent
            binding.memoTextView.text = it.memoContent
            binding.textView.text = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.JAPANESE).format(it.date)
        }

        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}
