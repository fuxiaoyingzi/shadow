package com.example.administrator.shadowapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.shadowapplication.R;
import com.squareup.timessquare.CalendarPickerView;

import java.util.Calendar;
import java.util.Date;

import static com.squareup.timessquare.CalendarPickerView.SelectionMode.RANGE;

public class TextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        EditText searchAction = (EditText) findViewById(R.id.et_search);
        searchAction.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Toast.makeText(TextViewActivity.this, "search", Toast.LENGTH_SHORT).show();
                    handled = true;
                }
                return handled;
            }
        });

        AutoCompleteTextView autoComplete = (AutoCompleteTextView) findViewById(R.id.auto_comp);
        String[] autoCompleText = getResources().getStringArray(R.array.countries_array);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, autoCompleText);
        autoComplete.setAdapter(arrayAdapter);

        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        CalendarPickerView calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        Date today = new Date();
        calendar.init(today, nextYear.getTime())
                .withSelectedDate(today);

        calendar.init(today, nextYear.getTime())
                .inMode(RANGE);

    }
}
