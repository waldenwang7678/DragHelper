package com.wangjt.viewdraghelper.feedback;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangjt.viewdraghelper.R;

import java.util.ArrayList;

/**
 * Created by wangjt on 2017/8/21.
 */

public class GrideAdapter extends RecyclerView.Adapter {

    private ArrayList<String> mData;
    private Context context;
    private SparseArray<Boolean> colorFlagArray = new SparseArray<>();

    public GrideAdapter(Context context, ArrayList<String> data) {
        mData = data;
        this.context = context;
        if (mData == null) {
            mData = new ArrayList<>();
        }
        initColorFlag();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(View.inflate(context, R.layout.item_layout, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ItemHolder) {
            final ItemHolder itemHolder = (ItemHolder) holder;
            itemHolder.itemText.setText(mData.get(position));
            if (colorFlagArray.get(position)) {
                itemHolder.itemText.setBackgroundResource(R.drawable.shape_blue);
                itemHolder.itemText.setTextColor(Color.parseColor("#dddddd"));
            } else {
                itemHolder.itemText.setBackgroundResource(R.drawable.shape_white);
                itemHolder.itemText.setTextColor(Color.parseColor("#333333"));
            }
            itemHolder.itemText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    colorFlagArray.put(position, !colorFlagArray.get(position));
                    itemHolder.itemText.setBackgroundResource(colorFlagArray.get(position) ? R.drawable.shape_blue : R.drawable.shape_white);
                    itemHolder.itemText.setTextColor(colorFlagArray.get(position) ?Color.parseColor("#dddddd") :Color.parseColor("#333333"));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    //初始化 false ,显示为原色 白色
    private void initColorFlag() {
        for (int i = 0; i < mData.size(); i++) {
            colorFlagArray.put(i, false);
        }
    }

    public void setFlagMap(SparseArray<Boolean> map) {
        if (map != null && map.size() >= mData.size()) {
            colorFlagArray = map;
        }
    }

    public SparseArray<Boolean> getFlagMap() {
        return colorFlagArray;
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        public TextView itemText;

        public ItemHolder(View itemView) {
            super(itemView);
            itemText = (TextView) itemView.findViewById(R.id.item);
        }
    }

}
