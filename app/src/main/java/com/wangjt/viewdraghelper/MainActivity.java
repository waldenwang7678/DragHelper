package com.wangjt.viewdraghelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.wangjt.viewdraghelper.feedback.FeedbackActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        Button feedback = (Button) findViewById(R.id.feedback);
        Button drag_test = (Button) findViewById(R.id.drag_test);
        feedback.setOnClickListener(this);
        drag_test.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.feedback:
                Intent feedbackIntent = new Intent(this, FeedbackActivity.class);
                startActivity(feedbackIntent);
                break;
            case R.id.drag_test:
                Intent dragIntent = new Intent(this, DragViewActivity.class);
                startActivity(dragIntent);
                break;
        }
    }
}
