package cn.edu.xidian.www.mymusicpro;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static cn.edu.xidian.www.mymusicpro.R.layout.activity_display_message;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_display_message);
        //setTitle("aaaaa");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        Intent intent = getIntent();
//        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
//        TextView textView = new TextView(this);
//        textView.setTextSize(40);
//        textView.setText(message);

//        LinearLayout layout = (LinearLayout) findViewById(R.id.content);
//        layout.addView(textView);
    }
}
