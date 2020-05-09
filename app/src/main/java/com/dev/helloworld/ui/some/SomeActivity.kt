package com.dev.helloworld.ui.some

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.dev.helloworld.R
import com.dev.helloworld.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_some.*

class SomeActivity : BaseActivity() {

    private val fragments = listOf(HomeFragment(), BookFragment(), PersonFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_some)

        initViewPager()
        interlockViewPagerAndNavigation()
    }

    private fun initViewPager() {
        viewPager.apply {
            adapter = ViewPagerAdapter()
            offscreenPageLimit = fragments.size
        }
    }

    private fun interlockViewPagerAndNavigation() {
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menuHome -> viewPager.currentItem = 0
                R.id.menuBook -> viewPager.currentItem = 1
                R.id.menuPerson -> viewPager.currentItem = 2
            }
            true
        }

        viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                bottomNav.menu.getItem(position).isChecked = true
            }

        })
    }

    inner class ViewPagerAdapter : FragmentPagerAdapter(supportFragmentManager) {

        override fun getItem(position: Int): Fragment = fragments[position]

        override fun getCount(): Int = fragments.size
    }

}
