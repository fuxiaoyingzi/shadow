package com.example.administrator.shadowapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.adapter.TestRecycleAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 测试recycleview的侧滑删除和拖拽移动位置
 */
public class TestRecycleViewActivity extends AppCompatActivity {

    private TestRecycleAdapter adapter;
    private String[] stringArray;
    private List itemList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview_touch_test);
        recyclerView = findViewById(R.id.listView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(TestRecycleViewActivity.this,DividerItemDecoration.VERTICAL));
        itemList = new ArrayList();


        stringArray = getResources().getStringArray(R.array.recycle_list_item);
        for (int i = 0; i < stringArray.length; i++) {
            itemList.add(stringArray[i]);
        }


        adapter = new TestRecycleAdapter(itemList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        initTouch();
    }

    public void initTouch() {
        ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                //首先回调的方法 返回int表示是否监听该方向 getMovementFlags()方法来指定可以支持的拖放和滑动的方向。
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;//拖拽
                int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;//侧滑删除
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            //onMove()和onSwiped()，用于通知底层数据的更新
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //滑动事件
                Collections.swap(itemList, viewHolder.getAdapterPosition(), target.getAdapterPosition());
                adapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) { //滑动删除
                itemList.remove(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }

            @Override
            public boolean isLongPressDragEnabled() { //是否允许拖拽
                //要支持长按RecyclerView item进入拖动操作
                return true;
            }
        });
        touchHelper.attachToRecyclerView(recyclerView);

    }
}
