package com.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private GridView mPhotoWall;
    private List<Integer> photoList;
    private PhotoWallAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        mPhotoWall = (GridView) findViewById(R.id.photo_wall);
        photoList = Photo.getPhotoList();
        adapter = new PhotoWallAdapter(context, photoList);
        mPhotoWall.setAdapter(adapter);

        mPhotoWall.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);//設定GridView點選模式
        mPhotoWall.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            private int checkedCount = 0;

            //點擊標的項目
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int position, long id, boolean checked) {
                if (checked) {
                    checkedCount++;
                    adapter.setNewSelection(position, checked);
                } else {
                    checkedCount--;
                    adapter.removeSelection(position);
                }
                actionMode.setSubtitle("sub title");
                actionMode.setTitle(checkedCount + " selected");
            }

            //建立CAB
            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.contextual_menu, menu);
                return true;
            }

            //CAB已準備好
            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            //點擊CAB MenuItem
            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                SparseBooleanArray sparseBooleanArray = adapter.getSparseBooleanArray();

                switch (menuItem.getItemId()) {

                    case R.id.item_Delete:
                        for (int index = 0; index < sparseBooleanArray.size(); index++) {
                            for (int i = 0; i < photoList.size(); i++) {
                                int imageResId = photoList.get(i);
                                if (imageResId == sparseBooleanArray.keyAt(index)) {
                                    photoList.remove(i);
                                    break;
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }
                        checkedCount = 0;
                        adapter.clearSelection();
                        actionMode.finish();
                        break;

                    case R.id.item_share:

                        ArrayList<Uri> imageUris = new ArrayList<Uri>();
                        for (int index = 0; index < sparseBooleanArray.size(); index++) {
                            for (int i = 0; i < photoList.size(); i++) {
                                int imageResId = photoList.get(i);
                                if (imageResId == sparseBooleanArray.keyAt(index)) {
                                    Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + imageResId);
                                    imageUris.add(uri);
                                    break;
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }

                        Intent shareIntent = new Intent();
                        shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
                        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
                        shareIntent.setType("image/*");
                        startActivity(Intent.createChooser(shareIntent, "share image to.."));
                        break;
                }
                return false;
            }

            //銷毀CAB
            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
                checkedCount = 0;
                adapter.clearSelection();
            }
        });
    }
}
