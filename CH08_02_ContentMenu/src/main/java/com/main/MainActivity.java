package com.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private LinearLayout linearLayout1, linearLayout2;
    private TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        linearLayout1 = (LinearLayout) findViewById(R.id.layout1);
        linearLayout2 = (LinearLayout) findViewById(R.id.layout2);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);

        registerForContextMenu(linearLayout1);
        registerForContextMenu(linearLayout2);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        switch (v.getId()) {
            case R.id.layout1:
                menu.add(0, 0, 0, "咖啡");
                menu.add(0, 1, 0, "茶");
                break;
            case R.id.layout2:
                menu.add(1, 0, 0, "蛋糕");
                menu.add(1, 1, 0, "餅乾");
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Toast.makeText(context, "您選擇的是" + item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getGroupId()) {
            case 0:
                switch (item.getItemId()) {
                    case 0:
                        linearLayout1.setBackgroundResource(R.drawable.coffee);
                        break;
                    case 1:
                        linearLayout1.setBackgroundResource(R.drawable.tea);
                        break;
                }
                textView1.setVisibility(View.GONE);
                break;
            case 1:
                switch (item.getItemId()) {
                    case 0:
                        linearLayout2.setBackgroundResource(R.drawable.cake);
                        break;
                    case 1:
                        linearLayout2.setBackgroundResource(R.drawable.cookie);
                        break;
                }
                textView2.setVisibility(View.GONE);
                break;
        }

        return super.onContextItemSelected(item);
    }
}
