package com.example.administrator.shadowapplication.hot_fix.andfix

import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.administrator.shadowapplication.R
import kotlinx.android.synthetic.main.activity_and_fix.*
import org.jetbrains.anko.toast
import java.io.File

class AndFixActivity : AppCompatActivity(), View.OnClickListener {
    private val PATCH_POSTFIX = ".apatch" //apatch文件后缀
    private val PATCH_NAME = "shadow" //apatch文件名称
    private lateinit var patchPath: String //apatch文件路径
    override fun onClick(v: View?) {
        if (v == null) return
        when (v.id) {
            R.id.tvCreateBug -> {//生成bug
                printLog()
            }

            R.id.tvFixBug -> { //修复bug
                FixPackageManager.getInstance().addPatch(getPatchName())
            }
        }
    }

    //获取apatch文件的完整路径名称
    private fun getPatchName(): String? {
        Log.d("hh", patchPath + PATCH_NAME + PATCH_POSTFIX)
        return patchPath + PATCH_NAME + PATCH_POSTFIX
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_and_fix)
        tvCreateBug.setOnClickListener(this)
        tvFixBug.setOnClickListener(this)

        //构造apatch文件路径
        patchPath = Environment.getExternalStorageDirectory().absolutePath + "/apatch/"
        Log.d("hh", "patchPath = $patchPath")
        val patchFile = File(patchPath)
        if (patchFile.exists()) {
            patchFile.mkdir()
        }
    }

    //刻意制造bug
    private fun printLog() {
        val printLog = "hello shadow"
        toast(printLog)
    }
}
