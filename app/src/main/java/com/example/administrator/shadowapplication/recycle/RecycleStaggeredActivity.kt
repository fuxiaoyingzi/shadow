package com.example.administrator.shadowapplication.recycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_recycle_staggered.*
import java.util.ArrayList


/**
 * 瀑布流样式的listview
 */
class RecycleStaggeredActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_staggered)
        recycleViewLayout.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        val mAdapter = WaterFallAdapter(this)
        val list = ArrayList<String>()
        list.add("https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2394573821.webp")
        list.add("https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2394573821.webp")
        list.add("https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2394573821.webp")
        list.add("https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2394573821.webp")
        list.add("https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2394573821.webp")
        list.add("https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2394573821.webp")
        list.add("https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2394573821.webp")
        list.add("https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2394573821.webp")
        list.add("https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2394573821.webp")
        list.add("https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2394573821.webp")
        list.add("https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2394573821.webp")
        list.add("https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2394573821.webp")
        list.add("https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2394573821.webp")
        mAdapter.getRandomHeight(list);
        recycleViewLayout.adapter = mAdapter

    }
}
