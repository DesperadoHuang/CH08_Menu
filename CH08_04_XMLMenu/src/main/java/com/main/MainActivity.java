package com.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private ImageView imageView;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        imageView = (ImageView) findViewById(R.id.imageView);
        relativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
        registerForContextMenu(relativeLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //MenuInflater 的作用可將 Menu XML 檔案實體轉換成 JAVA Menu 的物件型態
        MenuInflater inflater = getMenuInflater();
        //根據 menu xml 建出 menu 實體物件結構
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        actionByMenuItem(item);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        actionByMenuItem(item);
        return true;
    }

    private void actionByMenuItem(MenuItem item) {
        Toast.makeText(context, "按下了" + item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.mario:
                imageView.setImageResource(R.drawable.mario);
                break;
            case R.id.sonic:
                imageView.setImageResource(R.drawable.sonic);
                break;
            case R.id.quit:
                imageView.setImageResource(R.drawable.ic_menu_close_clear_cancel);
                break;
        }
    }
}
