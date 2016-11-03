package com.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView_tea, textView_coffee, textView_coolHot;
    private String itemTitle = "";
    private int menuClickConut = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_tea = (TextView) findViewById(R.id.textView_tea);
        textView_coffee = (TextView) findViewById(R.id.textView_coffee);
        textView_coolHot = (TextView) findViewById(R.id.textView_Cool_Hot);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        SubMenu menu1 = menu.addSubMenu(0, 0, Menu.NONE, "茶品");
        SubMenu menu2 = menu.addSubMenu(0, 1, Menu.NONE, "咖啡");
        SubMenu menu3 = menu.addSubMenu(0, 2, Menu.NONE, "冷熱");

        menu1.add(1, 0, Menu.NONE, "紅茶");
        menu1.add(1, 1, Menu.NONE, "奶茶");
        menu1.add(1, 2, Menu.NONE, "綠茶");
        menu1.setGroupCheckable(1, true, true);

        menu2.add(2, 0, Menu.NONE, "拿鐵");
        menu2.add(2, 1, Menu.NONE, "焦糖");
        menu2.add(2, 2, Menu.NONE, "卡布奇諾");
        menu2.setGroupCheckable(2, true, true);

        menu3.add(3, 0, Menu.NONE, "冷");
        menu3.add(3, 1, Menu.NONE, "熱");
        menu3.setGroupCheckable(3, true, true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        new MyLog(item.getTitle().toString() + ":" + item.isChecked() + "");

        itemTitle = itemTitle + item.getTitle().toString();
        boolean flag = !item.isChecked();
        item.setChecked(flag);
        if (flag) {
            textView_tea.setText(itemTitle);
        } else {
            textView_tea.setText("");
        }
        //        switch (item.getGroupId()) {
        //            case 1:
        //                if (flag) {
        //                    textView_tea.setText(item.getTitle().toString());
        //                } else {
        //                    textView_tea.setText("");
        //                }
        //                break;
        //            case 2:
        //                if (flag) {
        //                    textView_coffee.setText(item.getTitle().toString());
        //                } else {
        //                    textView_coffee.setText("");
        //                }
        //                break;
        //            case 3:
        //                if (flag) {
        //                    textView_coolHot.setText(item.getTitle().toString());
        //                } else {
        //                    textView_coolHot.setText("");
        //                }
        //                break;
        //        }
        new MyLog(item.getTitle().toString() + ":" + item.isChecked() + "");
        return super.onOptionsItemSelected(item);
    }
}
