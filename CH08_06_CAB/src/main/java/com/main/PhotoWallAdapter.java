package com.main;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by ki264 on 2016/11/8.
 */

public class PhotoWallAdapter extends BaseAdapter {
    private Context context;
    private List<Integer> photoList;
    private SparseBooleanArray sparseBooleanArray;

    public PhotoWallAdapter(Context context, List<Integer> photoList) {
        sparseBooleanArray = new SparseBooleanArray();
        this.context = context;
        this.photoList = photoList;
    }

    public SparseBooleanArray getSparseBooleanArray() {
        return sparseBooleanArray;
    }

    //將使用者店選的項目記錄到sparseBooleanArray集合中
    public void setNewSelection(int position, boolean value) {
        sparseBooleanArray.put(photoList.get(position), value);
        notifyDataSetChanged();
    }

    //於sparseBooleanArray集合中判讀所指定位置之項目是否有被使用者點選
    public boolean isPositionChecked(int position) {
        Boolean result = sparseBooleanArray.get(photoList.get(position));
        return result == null ? false : result;
    }

    //於spareBooleanArray集合中移除指定位置之項目
    public void removeSelection(int position) {
        sparseBooleanArray.delete(photoList.get(position));
        notifyDataSetChanged();
    }

    //清除sparseBooleanArray集合內容
    public void clearSelection() {
        sparseBooleanArray = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return photoList.size();
    }

    @Override
    public Object getItem(int i) {
        return photoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        final ViewHolder gridViewImageHolder;
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.photo_layout, null);
            gridViewImageHolder = new ViewHolder();
            gridViewImageHolder.photo = (ImageView) view.findViewById(R.id.photo);
            gridViewImageHolder.mask = (View) view.findViewById(R.id.mask);
            view.setTag(gridViewImageHolder);
        } else {
            gridViewImageHolder = (ViewHolder) view.getTag();
        }
        gridViewImageHolder.photo.setImageResource(photoList.get(i));
        if (isPositionChecked(i)) {
            gridViewImageHolder.mask.setVisibility(View.VISIBLE);
        } else {
            gridViewImageHolder.mask.setVisibility(View.GONE);
        }
        return view;
    }

    static class ViewHolder {
        ImageView photo;
        View mask;
    }
}
