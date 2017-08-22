package com.wangjt.viewdraghelper.feedback;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wangjt.viewdraghelper.R;

import java.util.ArrayList;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView appealView;
    private RecyclerView inquiryView;
    ArrayList<String> mData = new ArrayList<>();
    private GrideAdapter appealAdapter;
    private GrideAdapter inquiryAdapter;
    private SparseArray<Boolean> appealMap;
    private SparseArray<Boolean> inquiryMap;
    private EditText content;
    private String feedbackContent;
    private TextView submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        init();
        getOutData();
        initView();
    }

    private void init() {
        for (int i = 0; i < 7; i++) {
            mData.add("战五渣 " + i);
        }
    }

    private void initView() {
        appealView = (RecyclerView) findViewById(R.id.feed_back_appeal);
        inquiryView = (RecyclerView) findViewById(R.id.feed_back_inquiry);
        content = (EditText) findViewById(R.id.feed_back_content);
        submit = (TextView) findViewById(R.id.submit);
        submit.setOnClickListener(this);

        RecyclerView.LayoutManager grideManager = new GridLayoutManager(this, 3);
        RecyclerView.LayoutManager grideManager1 = new GridLayoutManager(this, 3);
        appealAdapter = new GrideAdapter(this, mData);
        inquiryAdapter = new GrideAdapter(this, mData);
        if (appealMap != null && appealMap.size() > 0) {
            appealAdapter.setFlagMap(appealMap);
        }
        if (inquiryMap != null && inquiryMap.size() > 0) {
            inquiryAdapter.setFlagMap(inquiryMap);
        }

        appealView.setLayoutManager(grideManager);
        appealView.setAdapter(appealAdapter);
        inquiryView.setLayoutManager(grideManager1);
        inquiryView.setAdapter(inquiryAdapter);

        if (feedbackContent != null && feedbackContent.length() > 0) {
            content.setText(feedbackContent);
        }
    }

    @Override
    protected void onDestroy() {
        storageData();
        super.onDestroy();
    }

    private void storageData() {

        String feedbackContentStr = content.getText().toString().trim();
        if (feedbackContentStr.length() > 0) {
            SPUtil.put(this, "feedbackContent", feedbackContentStr);
        }

        SparseArray<Boolean> appealMap = appealAdapter.getFlagMap();
        SparseArray<Boolean> inquiryMap = inquiryAdapter.getFlagMap();

        String appealStr = "";
        String inquiryStr = "";
        if (appealMap != null && appealMap.size() > 0) {
            for (int i = 0; i < appealMap.size(); i++) {
                appealStr += appealMap.get(i) ? "1" : "0";
            }
            SPUtil.put(this, "appealStr", appealStr);
        }
        if (inquiryMap != null && inquiryMap.size() > 0) {
            for (int i = 0; i < inquiryMap.size(); i++) {
                inquiryStr += inquiryMap.get(i) ? "1" : "0";
            }
            SPUtil.put(this, "inquiryStr", inquiryStr);
        }
    }

    private void getOutData() {
        feedbackContent = SPUtil.get(this, "feedbackContent");
        String appealStr = SPUtil.get(this, "appealStr");
        appealMap = new SparseArray<Boolean>();
        inquiryMap = new SparseArray<Boolean>();

        if (appealStr != null && appealStr.length() > 0) {
            for (int i = 0; i < appealStr.length(); i++) {
                char a = appealStr.charAt(i);
                appealMap.put(i, a == '1');
            }
        }
        String inquiryStr = SPUtil.get(this, "inquiryStr");
        if (inquiryStr != null && inquiryStr.length() > 0) {
            for (int i = 0; i < inquiryStr.length(); i++) {
                char a = inquiryStr.charAt(i);
                inquiryMap.put(i, a == '1');
            }
        }
    }

    private void clearDate() {
        content.setText(null);
        if (appealMap != null && appealMap.size() > 0) {
            for (int i = 0; i < appealMap.size(); i++) {
                appealMap.put(i, false);
            }
            appealAdapter.setFlagMap(appealMap);
            appealAdapter.notifyDataSetChanged();
        }
        if (inquiryMap != null && inquiryMap.size() > 0) {
            for (int i = 0; i < inquiryMap.size(); i++) {
                inquiryMap.put(i, false);
            }
            inquiryAdapter.setFlagMap(inquiryMap);
            inquiryAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch (R.id.submit) {
            case R.id.submit:  //提交成功后 数据清空
                clearDate();
                break;
        }
    }
}
