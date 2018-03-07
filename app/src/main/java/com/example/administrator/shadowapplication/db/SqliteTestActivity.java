package com.example.administrator.shadowapplication.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.crash_log.ToastUtil;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

public class SqliteTestActivity extends AppCompatActivity {
    private Button saveImage, getImage;
    private SQLiteDatabase db;
    private ImageView sqlImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqltest);
        saveImage = findViewById(R.id.saveImage);
        getImage = findViewById(R.id.getImage);
        sqlImage = findViewById(R.id.iv_sql);
        db = new MySqlite(this, "shadow.db", null, 1).getReadableDatabase();
        saveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_bg_0);
                byte[] bytes = new byte[bitmap.getWidth() * bitmap.getHeight() * 4];
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                bytes = outputStream.toByteArray();
                ContentValues contentValues = new ContentValues();
                contentValues.put("image", bytes);
                db.insert("shadow", null, contentValues);

            }
        });

        getImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] colums = new String[]{"id", "image"};
                Cursor cursor = db.query("shadow", colums, null, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        int imageIndex = cursor.getColumnIndex("image");
                        byte[] bytes = cursor.getBlob(imageIndex);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        sqlImage.setImageBitmap(bitmap);
                    }
                    cursor.close();
                }

            }
        });

        findViewById(R.id.btn_toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtil.showMsg("hello shadow");
                ToastUtil.showMsgLong(R.string.app_name);
            }
        });
        findViewById(R.id.btn_custom_toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View toastView = LayoutInflater.from(SqliteTestActivity.this).inflate(R.layout.toast_layout,null);
                //ToastUtil.showCustomMsg(toastView);
                ToastUtil.showCustomMsg(toastView, Toast.LENGTH_LONG, Gravity.BOTTOM);

            }
        });


        String xml = "<apps>" +
                "<app>" +
                "<id>1</id>" +
                "<name>shadow</name>" +
                "<version>hello world</version>" +
                "</app>" +
                "<app>" +
                "<id>2</id>" +
                "<name>shadow2</name>" +
                "<version>hello world2</version>" +
                "</app>" +
                "</apps>";
        xmlParsePull(xml);
        xmlParseSax(xml);

    }

    public void xmlParsePull(String xmlData) {
        try {
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            String id = "", name = "", version = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        if (nodeName.equals("id")) {
                            id = xmlPullParser.nextText();
                        } else if (nodeName.equals("name")) {
                            name = xmlPullParser.nextText();
                        } else if (nodeName.equals("version")) {
                            version = xmlPullParser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (nodeName.equals("app")) {
                            Log.d("hh", "id is " + id);
                            Log.d("hh", "name is " + name);
                            Log.d("hh", "version is " + version);
                        }
                        break;

                }
                eventType = xmlPullParser.next();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    public void xmlParseSax(String xmlData) {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            XMLReader xmlReader = saxParserFactory.newSAXParser().getXMLReader();
            SaxHandle saxHandle = new SaxHandle();
            xmlReader.setContentHandler(saxHandle);
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
