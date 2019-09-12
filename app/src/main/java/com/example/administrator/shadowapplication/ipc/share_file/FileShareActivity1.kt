package com.example.administrator.shadowapplication.ipc.share_file

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_file_share1.*
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

/**
 * 进程间通信之文件共享
 * 文件共享适用于对并发读写操作要求不高的进程间使用
 */
class FileShareActivity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_share1)
        fileShare.setOnClickListener {
            val filePath = "h:/shadow"
            val objectOut = ObjectOutputStream(FileOutputStream(filePath))
            val shareUser = ShareUser(1,"shadow","china")
            objectOut.writeObject(shareUser)
            objectOut.close()
            startActivity(Intent(this@FileShareActivity1,FileShareActivity2::class.java))
        }
    }
}
