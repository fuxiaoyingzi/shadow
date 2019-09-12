package com.example.administrator.shadowapplication.Gallery;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.administrator.shadowapplication.R;

public class EcoGalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eco_gallery);
        EcoGallery ecoGallery = findViewById(R.id.gallery);
        ecoGallery.setAdapter(new ImageAdapter(this));
    }

    private class ImageAdapter extends BaseAdapter {
        private Context context;

        ImageAdapter(Context context) {
            this.context = context;
        }

        public int getCount() {
            return 3;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            // Not using convertView for sample app simplicity. You should probably use it in real application to get better performance.
            ImageView imageView = new ImageView(context);
            int resId;
            switch (position) {
                case 0:
                    resId = R.drawable.like;
                    break;
                case 1:
                    resId = R.drawable.ic_my_selector;
                    break;
                case 2:
                    resId = R.drawable.like;
                    break;
                default:
                    resId = R.drawable.ic_my_selector;
            }
            imageView.setImageResource(resId);
            return imageView;
        }
    }
}
