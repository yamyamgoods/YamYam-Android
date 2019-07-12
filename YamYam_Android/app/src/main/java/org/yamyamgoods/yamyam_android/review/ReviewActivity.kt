package org.yamyamgoods.yamyam_android.review

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.activity_review.*
import kotlinx.android.synthetic.main.top_navigation_tab_review.*
import org.jetbrains.anko.startActivity
import org.yamyamgoods.yamyam_android.R
import org.yamyamgoods.yamyam_android.review.adapter.ReviewStatePagerAdapter
import org.yamyamgoods.yamyam_android.reviewwrite.ReviewWriteActivity

class ReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        configureTitleBar()
        configureMainTab()

        btn_review_tab_write.setOnClickListener{
          this@ReviewActivity.startActivity<ReviewWriteActivity>(
                // 리뷰 작성 페이지로 넘기기
          )
        }
    }

    private fun configureTitleBar() {
        btn_review_tab_close.setOnClickListener {
            finish()
        }
    }

    private fun configureMainTab() {
        vp_review_nav.adapter = ReviewStatePagerAdapter(supportFragmentManager, 2)
        vp_review_nav.offscreenPageLimit = 2
        tl_review_nav.setupWithViewPager(vp_review_nav)

        val navCategoryMainLayout: View =
            (this.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
                .inflate(R.layout.top_navigation_tab_review, null, false)

        tl_review_nav.getTabAt(0)!!.customView =
            navCategoryMainLayout.findViewById(R.id.btn_review_nav_all) as ConstraintLayout
        tl_review_nav.getTabAt((1))!!.customView =
            navCategoryMainLayout.findViewById(R.id.btn_review_nav_photo) as ConstraintLayout

        tl_review_nav.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                selectedBestTab(position = tab!!.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                selectedBestTab(position = tab!!.position)
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                selectedBestTab(position = tab!!.position)
            }
        })
    }

    fun setAllReviewCount(num: Int) {
        Log.v("현주", "setReviewCount" + num.toString())
        tv_review_nav_all_num.text = num.toString()
    }

    fun setPhotoReviewCount(num: Int) {
        Log.v("현주", "setReviewCount" + num.toString())
        tv_review_nav_photo_num.text = num.toString()
    }


    private fun selectedBestTab(position: Int) {
        if (position == 0) {
            tv_review_nav_all.setTextColor(resources.getColor(R.color.colorTabDarkGray))
            tv_review_nav_photo.setTextColor(resources.getColor(R.color.colorTabLightGray))
        } else {
            tv_review_nav_all.setTextColor(resources.getColor(R.color.colorTabLightGray))
            tv_review_nav_photo.setTextColor(resources.getColor(R.color.colorTabDarkGray))
        }
    }

}
