package app.hono.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import app.hono.viewpager.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    lateinit private var binding: ActivityMainBinding
    private val images:IntArray = intArrayOf(
        R.drawable.image01,
        R.drawable.image02,
        R.drawable.image03
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewPager.adapter = CustomPagerAdapter(this, images)
    }
}
