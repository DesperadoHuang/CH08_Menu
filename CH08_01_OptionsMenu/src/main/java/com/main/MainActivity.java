package com.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        /*
        * 建立選單
        * menu.add(定義群組ID,定義選項ID,定義排序順序,定義選項文字)
        */

        menu.add(0, 0, menu.NONE, "新增");
        menu.add(0, 1, menu.NONE, "儲存");
        menu.add(0, 2, menu.NONE, "查詢");
        menu.add(0, 3, menu.NONE, "更新");
        menu.add(0, 4, menu.NONE, "設定");
        menu.add(0, 5, menu.NONE, "返回");
        menu.add(0, 6, menu.NONE, "關於");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getGroupId() == 0) {
            textView.setText(item.getItemId() + ":[" + item.getTitle().toString() + "]被按下了");
        }
        return super.onOptionsItemSelected(item);
    }
}
