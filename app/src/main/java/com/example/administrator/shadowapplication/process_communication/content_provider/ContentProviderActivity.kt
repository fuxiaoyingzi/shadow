package com.example.administrator.shadowapplication.process_communication.content_provider

import android.content.ContentValues
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_content_provider.*

class ContentProviderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userUri = BookContentProvider.USER_CONTENT_URI
        val bookUri = BookContentProvider.BOOK_CONTENT_URI
        setContentView(R.layout.activity_content_provider)
        insert.setOnClickListener {

            val contentValue = ContentValues()
            contentValue.put("_id", 1)
            contentValue.put("name", "android 开发艺术探索")
            contentResolver.insert(bookUri, contentValue)


            val userValue = ContentValues()
            userValue.put("_id", 1)
            userValue.put("name", "shadow")
            userValue.put("sex", 0)
            contentResolver.insert(userUri, userValue)

        }

        delete.setOnClickListener {
            contentResolver.delete(userUri, null, null)
        }


        update.setOnClickListener {
            val userValue = ContentValues()
            userValue.put("_id", 1)
            userValue.put("name", "shadow update")
            userValue.put("sex", 1)
            contentResolver.update(userUri, userValue, "_id = ?", arrayOf("1"))
        }

        query.setOnClickListener {
            val cursor = contentResolver.query(userUri, null, null, null, null)
            while (cursor.moveToNext()) {
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val sex = cursor.getInt(2)
                Log.d("hh", "id = $id,name = $name,sex = $sex")
            }

        }
    }
}
