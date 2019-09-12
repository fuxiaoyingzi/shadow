package com.example.administrator.shadowapplication.Gallery

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import com.example.administrator.shadowapplication.R
import com.example.administrator.shadowapplication.crash_log.ToastUtil
import kotlinx.android.synthetic.main.activity_view_pager_change_data.*
import java.util.*


class ViewPagerChangeDataActivity : AppCompatActivity() {

    private lateinit var mGalleryListBeans: ArrayList<GalleryListBean>
    private lateinit var mGalleryIdList: ArrayList<String>
    private var galleryLoading:Boolean = false

    private lateinit var mPagerGoodsBeans: ArrayList<PagerBean>
    private lateinit var mViewPagerIdList: ArrayList<String>
    private lateinit var mPagerDataMap: HashMap<String, ArrayList<PagerBean>> //缓存的goods或者medal的集合

    private lateinit var imageAdapter: ImageAdapter
    internal lateinit var adapter: MyAdapter
    var galleryCurrentPosition: Int = 0
    var pagerCurrentPosition: Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager_change_data)
        initGallery()
        initViewPager()
    }

    /**
     * 初始化gallery data
     */
    private fun initGallery() {
        mGalleryListBeans = ArrayList()
        mGalleryIdList = ArrayList()
        loadGalleryData()
        imageAdapter = ImageAdapter(this)
        imageAdapter.setGalleryListBeans(mGalleryListBeans,true)
        gallery.setSpacing(0)
        gallery.setSelection(0, true)
        gallery.adapter = imageAdapter
        gallery.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                imageAdapter.setSelectItem(position)
                updateViewPagerPosition(position)
                galleryCurrentPosition = position
                if (position == mGalleryListBeans.size -3 && !galleryLoading){
                    galleryLoading = true
                    loadMoreGalleryData()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    //初始化view pager
    private fun initViewPager() {
        mPagerGoodsBeans = ArrayList()
        mViewPagerIdList = ArrayList()
        mPagerDataMap = HashMap()
        adapter = MyAdapter(supportFragmentManager)
        testViewPagerData("1", 3,GalleryListBean.TYPE_GOODS)
        testViewPagerData("2", 1,GalleryListBean.TYPE_GOODS)
        //每次更新数据的时候都要更新mViewPagerListBeans
        updatePagerList("1","1")
        pager.adapter = adapter
        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                pagerCurrentPosition = position
                updateGalleryPosition(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
        pager.setOnTouchListener(object : View.OnTouchListener {
            var startX: Float = 0.toFloat()
            var endX: Float = 0.toFloat()
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        startX = event.x
                    }
                    MotionEvent.ACTION_UP -> {
                        endX = event.x
                        val windowManager = applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                        //获取屏幕的宽度
                        val size = Point()
                        windowManager.defaultDisplay.getSize(size)
                        val width = size.x
                        //首先要确定的是，是否到了最后一页，然后判断是否向左滑动，并且滑动距离是否符合，我这里的判断距离是屏幕宽度的4分之一（这里可以适当控制）
                        if (pagerCurrentPosition == mPagerGoodsBeans.size - 1 && startX - endX > 0 && startX - endX >= width / 4) {
                            updateNextPagerData(true)
                        } else if (pagerCurrentPosition == 0 && startX - endX < 0 && endX - startX >= width / 4) {
                            updateLastPagerData(true)
                        }
                    }
                }
                return false
            }

        })
    }


    //生成viewpager数据
    private fun testViewPagerData(mainId: String, size: Int,type:Int) {
        val pagerBeanList:ArrayList<PagerBean> = ArrayList()
        if (type == GalleryListBean.TYPE_GOODS){
            val pagerGoodsBeans: ArrayList<PagerGoodsBean> = ArrayList()
            for (i in 0 until size) { //生成goods数据
                pagerGoodsBeans.add(createPagerGoodsData(mainId, (i + 1).toString(), R.drawable.pic0))
            }
            for (i in pagerGoodsBeans){ //转换为pagerBean
                val pagerBean = PagerBean(i.mainId,i.subId,i.imageUrl,GalleryListBean.TYPE_GOODS)
                pagerBeanList.add(pagerBean)
            }
        }else{
            val pagerMedalBeans: ArrayList<PagerMedalBean> = ArrayList()
            for (i in 0 until size) { //生成medal数据
                pagerMedalBeans.add(createPagerMedalData(mainId, (i + 1).toString(), R.drawable.pic0))
            }
            for (i in pagerMedalBeans){//转换为pagerBean
                val pagerBean = PagerBean(i.mainId,i.subId,i.imageUrl,GalleryListBean.TYPE_MEDAL)
                pagerBeanList.add(pagerBean)
            }
        }

        mPagerDataMap[mainId] = pagerBeanList
    }


    //生成goods 数据
    private fun createPagerGoodsData(mainId: String, subId: String, imageUrl: Int?): PagerGoodsBean {
        return PagerGoodsBean(mainId, subId, imageUrl)
    }

    //生成medal数据
    private fun createPagerMedalData(mainId: String, subId: String, imageUrl: Int?): PagerMedalBean {
        return PagerMedalBean(mainId, subId, imageUrl)
    }


    //生成gallery数据
    private fun loadGalleryData() {
        mGalleryListBeans.add(createGalleryData("1", "1", R.drawable.pic0,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("1", "2", R.drawable.pic1,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("1", "3", R.drawable.pic2,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("2", "1", R.drawable.pic3,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("3", "1", R.drawable.pic4,GalleryListBean.TYPE_MEDAL))
        mGalleryListBeans.add(createGalleryData("3", "2", R.drawable.pic5,GalleryListBean.TYPE_MEDAL))
        mGalleryListBeans.add(createGalleryData("3", "3", R.drawable.pic6,GalleryListBean.TYPE_MEDAL))
        mGalleryListBeans.add(createGalleryData("3", "4", R.drawable.pic7,GalleryListBean.TYPE_MEDAL))
        mGalleryListBeans.add(createGalleryData("4", "1", R.drawable.pic8,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("4", "2", R.drawable.pic9,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("5", "1", R.drawable.pic0,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("5", "2", R.drawable.pic1,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("5", "3", R.drawable.pic2,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("6", "1", R.drawable.pic3,GalleryListBean.TYPE_MEDAL))
        mGalleryListBeans.add(createGalleryData("7", "1", R.drawable.pic4,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("7", "2", R.drawable.pic5,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("7", "3", R.drawable.pic6,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("7", "4", R.drawable.pic7,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("8", "1", R.drawable.pic8,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("8", "2", R.drawable.pic9,GalleryListBean.TYPE_GOODS))

        for (i in mGalleryListBeans.indices) {
            val galleryListBean = mGalleryListBeans.get(i)
            mGalleryIdList.add(galleryListBean.mainId + galleryListBean.subId)
        }

    }

    //生成gallery数据
    private fun loadMoreGalleryData() {
        mGalleryListBeans.add(createGalleryData("9", "1", R.drawable.pic0,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("9", "2", R.drawable.pic1,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("9", "3", R.drawable.pic2,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("10", "1", R.drawable.pic3,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("10", "2", R.drawable.pic4,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("11", "1", R.drawable.pic5,GalleryListBean.TYPE_MEDAL))
        mGalleryListBeans.add(createGalleryData("11", "2", R.drawable.pic6,GalleryListBean.TYPE_MEDAL))
        mGalleryListBeans.add(createGalleryData("11", "3", R.drawable.pic7,GalleryListBean.TYPE_MEDAL))
        mGalleryListBeans.add(createGalleryData("11", "4", R.drawable.pic8,GalleryListBean.TYPE_MEDAL))
        mGalleryListBeans.add(createGalleryData("12", "1", R.drawable.pic9,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("12", "2", R.drawable.pic0,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("12", "3", R.drawable.pic1,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("12", "4", R.drawable.pic2,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("13", "1", R.drawable.pic3,GalleryListBean.TYPE_MEDAL))
        mGalleryListBeans.add(createGalleryData("14", "1", R.drawable.pic4,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("14", "2", R.drawable.pic5,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("14", "3", R.drawable.pic6,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("15", "1", R.drawable.pic7,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("15", "2", R.drawable.pic8,GalleryListBean.TYPE_GOODS))
        mGalleryListBeans.add(createGalleryData("15", "3", R.drawable.pic9,GalleryListBean.TYPE_GOODS))

        var  i = mGalleryIdList.size
        while (i < mGalleryListBeans.size){
            val galleryListBean = mGalleryListBeans[i]
            mGalleryIdList.add(galleryListBean.mainId + galleryListBean.subId)
            i++
        }


        imageAdapter.setGalleryListBeans(mGalleryListBeans,false)
        galleryLoading = false
    }
    private fun createGalleryData(mainId: String, subId: String, imageUrl: Int?,type:Int): GalleryListBean {
        return GalleryListBean(mainId, subId, imageUrl,type)
    }

    //更新gallery 的位置
    private fun updateGalleryPosition(position: Int) {
        val viewPagerListBean = mPagerGoodsBeans[position]
        updatePosition(viewPagerListBean.mainId + viewPagerListBean.subId, false)
    }

    //更新view pager的位置
    private fun updateViewPagerPosition(position: Int) {
        val galleryListBean = mGalleryListBeans[position]
        updatePosition(galleryListBean.mainId + galleryListBean.subId, true)
    }

    //更新,避免重复更新
    private fun updatePosition(position: String, refreshViewPager: Boolean) {
        if (refreshViewPager) { //由gallery 控制 更新view pager位置
            val pagerPosition = mViewPagerIdList.indexOf(position)
            if (pagerPosition == -1) {
                //判断pager 数据只有个的时候，向前更新还是向后更新数据
                if (pagerCurrentPosition == mPagerGoodsBeans.size - 1 && gallery.selectedItemPosition > galleryCurrentPosition) {
                    updateNextPagerData(false)
                } else if (pagerCurrentPosition == 0) {
                    updateLastPagerData(false)
                }

            } else if (pager.currentItem != pagerPosition) {
                pager.currentItem = pagerPosition
            }
        } else { //由view pager控制更新gallery数据
            val galleryPosition = mGalleryIdList.indexOf(position)
            if (galleryPosition != -1 && gallery.selectedItemPosition != galleryPosition) {
                gallery.setSelection(galleryPosition, true)
            }
        }
    }

    //更新上一个view pager数据
    private fun updateLastPagerData(pagerTouchUpdate: Boolean) {
        if (galleryCurrentPosition != 0) {//不是第一个
            val mainId: String = mGalleryListBeans[galleryCurrentPosition - 1].mainId
            val subId :String = mGalleryListBeans[galleryCurrentPosition - 1].subId
            updatePagerList(mainId,subId)

            if (pager.currentItem == mPagerGoodsBeans.size - 1) {//只有一条数据的时候，手动更新gallery数据
                updateGalleryPosition(mPagerGoodsBeans.size - 1)
            }

            if (pagerTouchUpdate) { //pager 滑动更新
                pager.setCurrentPosition(mPagerGoodsBeans.size - 1, true)
            } else if (pager.currentItem != mPagerGoodsBeans.size - 1) { //gallery 触发更新
                pager.setCurrentItem(mPagerGoodsBeans.size - 1, true)
            }
            cacheLastData(mPagerGoodsBeans.size)
        } else{
            ToastUtil.showMsg("滑动到第一页,前面没有数据了")
        }

    }

    //更新下一个view pager数据
    private fun updateNextPagerData(pagerTouchUpdate: Boolean) {
        if (galleryCurrentPosition != mGalleryListBeans.size - 1) {//不是最后一个
            val mainId: String = mGalleryListBeans[galleryCurrentPosition + 1].mainId
            val subId :String = mGalleryListBeans[galleryCurrentPosition + 1].subId
            updatePagerList(mainId,subId)

            if (pager.currentItem == 0) { //只有一条数据的时候，手动更新gallery数据
                updateGalleryPosition(0)
            }
            if (pagerTouchUpdate) { //pager滑动触发更新
                pager.setCurrentPosition(0, true)
            } else if (pager.currentItem != 0) { //gallery 触发更新
                pager.setCurrentItem(0, true)
            }
            cacheNextData(mPagerGoodsBeans.size)
        } else {
            ToastUtil.showMsg("滑动到最后一页,后面没有数据了")
        }
    }


    /**
     * 缓存下一个产品的数据
     */
    private fun cacheNextData(size: Int) {
        val cacheDataPosition = galleryCurrentPosition  + size
        if (cacheDataPosition != mGalleryListBeans.size -1){//不是最后一个
            var size = 0
            val mainId: String = mGalleryListBeans[cacheDataPosition + 1].mainId
            for (i in cacheDataPosition + 1 until mGalleryListBeans.size) {
                size++
                if (i + 1 == mGalleryListBeans.size || mGalleryListBeans[i].mainId !== mGalleryListBeans[i + 1].mainId) {
                    break
                }
            }
            if (size == 0) size = 1
            testViewPagerData(mainId, size, mGalleryListBeans[cacheDataPosition + 1].type)
        }
    }

    /**
     * 缓存上一个产品的数据
     */
    private fun cacheLastData(size: Int) {
        val cacheDataPosition = galleryCurrentPosition  - size
        if (cacheDataPosition != 0){//不是第一个
            var size = 0
            val mainId: String = mGalleryListBeans[cacheDataPosition - 1].mainId
            var i = cacheDataPosition - 1
            while (i >= 0) {
                size++
                if (i - 1 < 0 || mGalleryListBeans[i].mainId !== mGalleryListBeans[i - 1].mainId) {
                    break
                }
                i--
            }
            if (size == 0) size = 1
            testViewPagerData(mainId, size, mGalleryListBeans[cacheDataPosition - 1].type)
        }

    }

    //更新view pager当前数据
    private fun updatePagerList(mainId: String,subId: String) {
        if (mPagerDataMap[mainId] == null){ //重新缓存数据
            val position = mGalleryIdList.indexOf(mainId+subId)
            var size = 0
            for (i in position until mGalleryListBeans.size) {
                size++
                if (i + 1 == mGalleryListBeans.size || mGalleryListBeans[i].mainId !== mGalleryListBeans[i + 1].mainId) {
                    break
                }
            }
            if (size == 0) size = 1
            testViewPagerData(mainId, size, mGalleryListBeans[position].type)
            updatePagerList(mainId,subId)
            cacheNextData(mPagerDataMap[mainId]!!.size)
        }else{
            mPagerGoodsBeans.clear()
            mViewPagerIdList.clear()
            mPagerGoodsBeans.addAll(mPagerDataMap[mainId]!!)
            for (i in mPagerGoodsBeans.indices) {
                val viewPagerListBean = mPagerGoodsBeans[i]
                mViewPagerIdList.add(viewPagerListBean.mainId + viewPagerListBean.subId)
            }
            updateViewPagerData()
        }

    }

    private fun updateViewPagerData() {
        if (pager.adapter != null) {
            val fm = supportFragmentManager
            val ft = fm.beginTransaction()
            val fragments = adapter.mFragmentMap
            if (fragments.isNotEmpty()) {
                for (i in 0 until fragments.size) {
                    fragments[i.toString()]?.let { ft.remove(it) }
                }
            }
            ft.commit()
            fm.executePendingTransactions()
        }

        adapter.setImagesId(mPagerGoodsBeans)
    }

    fun btn_click(v: View) {
        update()
    }

    private fun update() {
        if (pager.adapter != null) {
            val fm = supportFragmentManager
            val ft = fm.beginTransaction()
            val fragments = adapter.mFragmentMap
            if (fragments.isNotEmpty()) {
                for (i in 0 until fragments.size) {
                    fragments[i.toString()]?.let { ft.remove(it) }
                }
            }
            ft.commit()
            fm.executePendingTransactions()
        }

        // 通过数据修改
        testViewPagerData("3", 4,GalleryListBean.TYPE_MEDAL)
        adapter.setImagesId(mPagerGoodsBeans)
    }

    inner class MyAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        private var mChildCount = 0
        private var mImageGoods: ArrayList<PagerBean> = ArrayList()
        var mFragmentMap: HashMap<String, GalleryFragment> = HashMap()

        fun setImagesId(imagesId: List<PagerBean>) {
            this.mImageGoods.clear()
            this.mFragmentMap.clear()
            mImageGoods.addAll(imagesId)
            notifyDataSetChanged()
        }


        override fun getItem(position: Int): Fragment {
            val fragment = GalleryFragment.getInstance(mImageGoods[position])
            mFragmentMap[position.toString()] = fragment
            return fragment
        }

        override fun getCount(): Int {
            return mImageGoods.size
        }

        override fun notifyDataSetChanged() {
            // 重写这个方法，取到子Fragment的数量，用于下面的判断，以执行多少次刷新
            mChildCount = count
            super.notifyDataSetChanged()
        }

        override fun getItemPosition(`object`: Any): Int {
            if (mChildCount > 0) {
                // 这里利用判断执行若干次不缓存，刷新
                mChildCount--
                // 返回这个是强制ViewPager不缓存，每次滑动都刷新视图
                return PagerAdapter.POSITION_NONE
            }
            // 这个则是缓存不刷新视图
            return super.getItemPosition(`object`)
        }
        // end
    }

}
