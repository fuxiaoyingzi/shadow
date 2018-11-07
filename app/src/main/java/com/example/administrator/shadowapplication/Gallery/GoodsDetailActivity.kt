package com.example.administrator.shadowapplication.Gallery

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import com.example.administrator.shadowapplication.Gallery.bean.*
import com.example.administrator.shadowapplication.R
import com.example.administrator.shadowapplication.crash_log.ToastUtil
import kotlinx.android.synthetic.main.activity_view_pager_change_data.*
import java.util.*


class GoodsDetailActivity : AppCompatActivity() {

    private lateinit var mGalleryListBeans: ArrayList<GalleryDataBean>
    private lateinit var mGalleryIdList: ArrayList<String>
    private var galleryLoading:Boolean = false

    private lateinit var mPagerDataBeans: ArrayList<PagerDataBean>
    private lateinit var mViewPagerIdList: ArrayList<String>
    private lateinit var mPagerDataMap: HashMap<String, ArrayList<PagerDataBean>> //缓存的goods或者medal的集合

    private lateinit var imageAdapter: GoodsGalleryAdapter
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
        imageAdapter = GoodsGalleryAdapter(this)
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
        mPagerDataBeans = ArrayList()
        mViewPagerIdList = ArrayList()
        mPagerDataMap = HashMap()
        adapter = MyAdapter(supportFragmentManager)
        testViewPagerData("1", 3,GalleryDataBean.TYPE_GOODS)
        testViewPagerData("2", 1,GalleryDataBean.TYPE_VEDIO)
        //每次更新数据的时候都要更新mViewPagerListBeans
        updatePagerList("1","1")
        updateTitleAndBottom()
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
                        if (pagerCurrentPosition == mPagerDataBeans.size - 1 && startX - endX > 0 && startX - endX >= width / 4) {
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
    private fun testViewPagerData(mainId: String, size: Int,type:String) {
        val pagerBeanList:ArrayList<PagerDataBean> = ArrayList()
        if (type == GalleryDataBean.TYPE_GOODS){ //构造商品详情
            val goodsImageBeans: ArrayList<GoodsImageBean> = ArrayList()
            for (i in 0 until size) { //生成goods数据
                goodsImageBeans.add(createPagerGoodsData(mainId, mainId+(i + 1).toString(), R.drawable.pic0))
            }
            val goodsDetail = GoodsDetailBean(mainId,goodsImageBeans)
            goodsDetail.text = "商品详情"
            goodsDetail.product_favorite ="0"
            for (i in goodsImageBeans){ //转换为pagerBean
                val pagerBean = PagerDataBean(mainId,i.rela_id,type,i.media_url,goodsDetail.text,goodsDetail.product_favorite,goodsDetail,null)
                pagerBeanList.add(pagerBean)
            }
        }
        else{
            val medalBeans: ArrayList<MedalImageBean> = ArrayList()
            for (i in 0 until size) { //生成medal数据
                medalBeans.add(createPagerMedalData(mainId+(i + 1).toString(), R.drawable.pic0,type))
            }
            val medalDetail = MedalDetailBean(mainId,type,"1",medalBeans)
            if (type == GalleryDataBean.TYPE_MEDAL){
                medalDetail.text = "媒体图片详情"
            }else{
                medalDetail.text ="媒体视频详情"
            }
            for (i in medalBeans){//转换为pagerBean
                val pagerBean = PagerDataBean(medalDetail.id,i.rela_id,type,i.pic_url,medalDetail.text,medalDetail.radio_praise,null,medalDetail)
                pagerBeanList.add(pagerBean)
            }
        }
        mPagerDataMap[mainId] = pagerBeanList
    }


    //生成goods 数据
    private fun createPagerGoodsData(mainId: String, differentId:  String, imageUrl: Int): GoodsImageBean {
        return GoodsImageBean(mainId,GalleryDataBean.TYPE_GOODS,imageUrl,differentId)
    }

    //生成medal数据
    private fun createPagerMedalData( subId: String, imageUrl: Int,type: String): MedalImageBean {
        val medalImageBean = MedalImageBean(imageUrl,subId)
        if (type == GalleryDataBean.TYPE_MEDAL){
            medalImageBean.video_url = ""
        }else{
            medalImageBean.video_url ="hello shadow,我是视频地址"
        }

        return medalImageBean
    }


    //生成gallery数据
    private fun loadGalleryData() {
        mGalleryListBeans.add(createGalleryData("1", "","2", R.drawable.pic0,GalleryDataBean.TYPE_GOODS,"11"))
        mGalleryListBeans.add(createGalleryData("1", "","2", R.drawable.pic1,GalleryDataBean.TYPE_GOODS,"12"))
        mGalleryListBeans.add(createGalleryData("1", "","2", R.drawable.pic2,GalleryDataBean.TYPE_GOODS,"13"))
        mGalleryListBeans.add(createGalleryData("2", "1","3", R.drawable.pic3,GalleryDataBean.TYPE_VEDIO,"21"))
        mGalleryListBeans.add(createGalleryData("3", "2","4", R.drawable.pic4,GalleryDataBean.TYPE_MEDAL,"31"))
        mGalleryListBeans.add(createGalleryData("3", "2","4", R.drawable.pic5,GalleryDataBean.TYPE_MEDAL,"32"))
        mGalleryListBeans.add(createGalleryData("3", "2","4", R.drawable.pic6,GalleryDataBean.TYPE_MEDAL,"33"))
        mGalleryListBeans.add(createGalleryData("3", "2","4", R.drawable.pic7,GalleryDataBean.TYPE_MEDAL,"34"))
        mGalleryListBeans.add(createGalleryData("4", "3","5", R.drawable.pic8,GalleryDataBean.TYPE_GOODS,"41"))
        mGalleryListBeans.add(createGalleryData("4", "3","5", R.drawable.pic9,GalleryDataBean.TYPE_GOODS,"42"))
        mGalleryListBeans.add(createGalleryData("5", "4","6", R.drawable.pic0,GalleryDataBean.TYPE_GOODS,"51"))
        mGalleryListBeans.add(createGalleryData("5", "4","6", R.drawable.pic1,GalleryDataBean.TYPE_GOODS,"52"))
        mGalleryListBeans.add(createGalleryData("5", "4","6", R.drawable.pic2,GalleryDataBean.TYPE_GOODS,"53"))
        mGalleryListBeans.add(createGalleryData("6", "5","7", R.drawable.pic3,GalleryDataBean.TYPE_VEDIO,"61"))
        mGalleryListBeans.add(createGalleryData("7", "6", "8",R.drawable.pic4,GalleryDataBean.TYPE_GOODS,"71"))
        mGalleryListBeans.add(createGalleryData("7", "6", "8", R.drawable.pic5,GalleryDataBean.TYPE_GOODS,"72"))
        mGalleryListBeans.add(createGalleryData("7", "6", "8", R.drawable.pic6,GalleryDataBean.TYPE_GOODS,"73"))
        mGalleryListBeans.add(createGalleryData("7", "6", "8", R.drawable.pic7,GalleryDataBean.TYPE_GOODS,"74"))
        mGalleryListBeans.add(createGalleryData("8", "7", "9",R.drawable.pic8,GalleryDataBean.TYPE_GOODS,"81"))
        mGalleryListBeans.add(createGalleryData("8", "7", "9",R.drawable.pic9,GalleryDataBean.TYPE_GOODS,"82"))

        for (i in mGalleryListBeans.indices) {
            val galleryListBean = mGalleryListBeans[i]
            mGalleryIdList.add(galleryListBean.rela_id)
        }

    }

    //生成gallery数据
    private fun loadMoreGalleryData() {
        mGalleryListBeans.add(createGalleryData("9", "8","10", R.drawable.pic0,GalleryDataBean.TYPE_GOODS,"91"))
        mGalleryListBeans.add(createGalleryData("9", "8","10", R.drawable.pic1,GalleryDataBean.TYPE_GOODS,"92"))
        mGalleryListBeans.add(createGalleryData("9", "8","10", R.drawable.pic2,GalleryDataBean.TYPE_GOODS,"93"))
        mGalleryListBeans.add(createGalleryData("10", "9","11", R.drawable.pic3,GalleryDataBean.TYPE_VEDIO,"101"))
        mGalleryListBeans.add(createGalleryData("11", "10","12", R.drawable.pic4,GalleryDataBean.TYPE_MEDAL,"111"))
        mGalleryListBeans.add(createGalleryData("11", "10","12",R.drawable.pic5,GalleryDataBean.TYPE_MEDAL,"112"))
        mGalleryListBeans.add(createGalleryData("11", "10","12", R.drawable.pic6,GalleryDataBean.TYPE_MEDAL,"113"))
        mGalleryListBeans.add(createGalleryData("11", "10","12", R.drawable.pic7,GalleryDataBean.TYPE_MEDAL,"114"))
        mGalleryListBeans.add(createGalleryData("12", "11","13", R.drawable.pic8,GalleryDataBean.TYPE_GOODS,"121"))
        mGalleryListBeans.add(createGalleryData("12", "11","13", R.drawable.pic9,GalleryDataBean.TYPE_GOODS,"122"))
        mGalleryListBeans.add(createGalleryData("13", "12","14", R.drawable.pic0,GalleryDataBean.TYPE_GOODS,"131"))
        mGalleryListBeans.add(createGalleryData("13", "12","14", R.drawable.pic1,GalleryDataBean.TYPE_GOODS,"132"))
        mGalleryListBeans.add(createGalleryData("13", "12","14", R.drawable.pic2,GalleryDataBean.TYPE_GOODS,"133"))
        mGalleryListBeans.add(createGalleryData("14", "13","15", R.drawable.pic3,GalleryDataBean.TYPE_VEDIO,"141"))
        mGalleryListBeans.add(createGalleryData("15", "14", "16",R.drawable.pic4,GalleryDataBean.TYPE_GOODS,"151"))
        mGalleryListBeans.add(createGalleryData("15", "14", "16", R.drawable.pic5,GalleryDataBean.TYPE_GOODS,"152"))
        mGalleryListBeans.add(createGalleryData("15", "14", "16", R.drawable.pic6,GalleryDataBean.TYPE_GOODS,"153"))
        mGalleryListBeans.add(createGalleryData("15", "14", "16", R.drawable.pic7,GalleryDataBean.TYPE_GOODS,"154"))
        mGalleryListBeans.add(createGalleryData("16", "15", "",R.drawable.pic8,GalleryDataBean.TYPE_GOODS,"161"))
        mGalleryListBeans.add(createGalleryData("16", "15", "",R.drawable.pic9,GalleryDataBean.TYPE_GOODS,"162"))

        var  i = mGalleryIdList.size
        while (i < mGalleryListBeans.size){
            val galleryListBean = mGalleryListBeans[i]
            mGalleryIdList.add(galleryListBean.rela_id)
            i++
        }

        imageAdapter.setGalleryListBeans(mGalleryListBeans,false)
        galleryLoading = false
    }
    private fun createGalleryData( id:String,  leftId:String, rightId:String, pic_url:Int, type:String,  rela_id:String): GalleryDataBean {
        return GalleryDataBean(id,leftId,rightId,pic_url,type,rela_id)

    }

    //更新gallery 的位置
    private fun updateGalleryPosition(position: Int) {
        val viewPagerListBean = mPagerDataBeans[position]
        updatePosition(viewPagerListBean.distinctionId, false)
    }

    //更新view pager的位置
    private fun updateViewPagerPosition(position: Int) {
        val galleryListBean = mGalleryListBeans[position]
        updatePosition(galleryListBean.rela_id, true)
    }

    //更新,避免重复更新
    private fun updatePosition(position: String, refreshViewPager: Boolean) {
        if (refreshViewPager) { //由gallery 控制 更新view pager位置
            val pagerPosition = mViewPagerIdList.indexOf(position)
            if (pagerPosition == -1) {
                //判断pager 数据只有个的时候，向前更新还是向后更新数据
                if (pagerCurrentPosition == mPagerDataBeans.size - 1 && gallery.selectedItemPosition > galleryCurrentPosition) {
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
            val mainId: String = mGalleryListBeans[galleryCurrentPosition - 1].id
            val subId :String = mGalleryListBeans[galleryCurrentPosition - 1].rela_id
            updatePagerList(mainId,subId)

            if (pager.currentItem == mPagerDataBeans.size - 1) {//只有一条数据的时候，手动更新gallery数据
                updateGalleryPosition(mPagerDataBeans.size - 1)
            }
            if (pagerTouchUpdate) { //pager 滑动更新
                pager.setCurrentPosition(mPagerDataBeans.size - 1, true)
            } else if (pager.currentItem != mPagerDataBeans.size - 1) { //gallery 触发更新
                pager.setCurrentItem(mPagerDataBeans.size - 1, true)
            }
            updateTitleAndBottom()
            cacheLastData(mPagerDataBeans.size)
        } else{
            ToastUtil.showMsg("滑动到第一页,前面没有数据了")
        }

    }

    //更新下一个view pager数据
    private fun updateNextPagerData(pagerTouchUpdate: Boolean) {
        if (galleryCurrentPosition != mGalleryListBeans.size - 1) {//不是最后一个
            val mainId: String = mGalleryListBeans[galleryCurrentPosition + 1].id
            val subId :String = mGalleryListBeans[galleryCurrentPosition + 1].rela_id
            updatePagerList(mainId,subId)
            if (pager.currentItem == 0) { //只有一条数据的时候，手动更新gallery数据
                updateGalleryPosition(0)
            }
            if (pagerTouchUpdate) { //pager滑动触发更新
                pager.setCurrentPosition(0, true)
            } else if (pager.currentItem != 0) { //gallery 触发更新
                pager.setCurrentItem(0, true)
            }
            updateTitleAndBottom()
            cacheNextData(mPagerDataBeans.size)
        } else {
            ToastUtil.showMsg("滑动到最后一页,后面没有数据了")
        }
    }

    private fun updateTitleAndBottom() {
        val  pagerDataBean: PagerDataBean = mPagerDataBeans[0]
        tvTitle.text = pagerDataBean.title
        statusBtn.text = "当前状态：${pagerDataBean.isCheck}"
    }

    /**
     * 缓存下一个产品的数据
     */
    private fun cacheNextData(size: Int) {
        val cacheDataPosition = galleryCurrentPosition  + size
        if (cacheDataPosition != mGalleryListBeans.size -1){//不是最后一个
            val mainId: String = mGalleryListBeans[cacheDataPosition + 1].id
            if (mPagerDataMap[mainId] != null ){ //已经缓存过了
                return
            }
            var size = 0
            for (i in cacheDataPosition + 1 until mGalleryListBeans.size) {
                size++
                if (i + 1 == mGalleryListBeans.size || mGalleryListBeans[i].id !== mGalleryListBeans[i + 1].id) {
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
            val mainId: String = mGalleryListBeans[cacheDataPosition - 1].id
            if (mPagerDataMap[mainId] != null ){ //已经缓存过了
                return
            }
            var size = 0
            var i = cacheDataPosition - 1
            while (i >= 0) {
                size++
                if (i - 1 < 0 || mGalleryListBeans[i].id !== mGalleryListBeans[i - 1].id) {
                    break
                }
                i--
            }
            if (size == 0) size = 1
            testViewPagerData(mainId, size, mGalleryListBeans[cacheDataPosition - 1].type)
        }
    }

    //更新view pager当前数据
    private fun updatePagerList(mainId: String,differentId: String) {
        if (mPagerDataMap[mainId] == null){ //重新缓存数据
            val position = mGalleryIdList.indexOf(differentId)
            var size = 0
            for (i in position until mGalleryListBeans.size) {
                size++
                if (i + 1 == mGalleryListBeans.size || mGalleryListBeans[i].id !== mGalleryListBeans[i + 1].id) {
                    break
                }
            }
            if (size == 0) size = 1
            testViewPagerData(mainId, size, mGalleryListBeans[position].type)
            updatePagerList(mainId,differentId)
            cacheNextData(mPagerDataMap[mainId]!!.size)
        }else{
            mPagerDataBeans.clear()
            mViewPagerIdList.clear()
            mPagerDataBeans.addAll(mPagerDataMap[mainId]!!)
            for (i in mPagerDataBeans.indices) {
                val viewPagerListBean = mPagerDataBeans[i]
                mViewPagerIdList.add(viewPagerListBean.distinctionId)
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
                    ft.remove(fragments[i.toString()])
                }
            }
            ft.commit()
            fm.executePendingTransactions()
        }
        adapter.setImagesId(mPagerDataBeans)
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
                    ft.remove(fragments[i.toString()])
                }
            }
            ft.commit()
            fm.executePendingTransactions()
        }

        // 通过数据修改
        testViewPagerData("3", 4,GalleryDataBean.TYPE_MEDAL)
        adapter.setImagesId(mPagerDataBeans)
    }

    inner class MyAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        private var mChildCount = 0
        private var mImageGoods: ArrayList<PagerDataBean> = ArrayList()
        var mFragmentMap: HashMap<String, GoodsDetailFragment> = HashMap()

        fun setImagesId(imagesId: List<PagerDataBean>) {
            this.mImageGoods.clear()
            this.mFragmentMap.clear()
            mImageGoods.addAll(imagesId)
            notifyDataSetChanged()
        }


        override fun getItem(position: Int): Fragment {
            val fragment = GoodsDetailFragment.getInstance(mImageGoods[position])
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
