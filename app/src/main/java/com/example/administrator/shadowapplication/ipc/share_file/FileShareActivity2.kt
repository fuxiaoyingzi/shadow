package com.example.administrator.shadowapplication.ipc.share_file

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.administrator.shadowapplication.R
import java.io.FileInputStream
import java.io.ObjectInputStream

class FileShareActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_share2)
        val filePath = "ffffff"
        val objectInputStream = ObjectInputStream(FileInputStream(filePath))
        val user = objectInputStream.readObject()as ShareUser
        Log.d("hh",user.userName +"---"+user.userAddress)
    }
}
