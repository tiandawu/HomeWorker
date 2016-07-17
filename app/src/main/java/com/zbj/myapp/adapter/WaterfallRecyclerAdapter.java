package com.zbj.myapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zbj.myapp.R;

import java.util.ArrayList;

/**
 * Created by tiandawu on 2016/7/17.
 */
public class WaterfallRecyclerAdapter extends RecyclerView.Adapter<WaterfallRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<String> datas;

    public WaterfallRecyclerAdapter(Context context, ArrayList<String> datas) {
        this.mContext = context;
        this.datas = datas;
    }

    @Override
    public WaterfallRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ViewHolder holder = new ViewHolder(View.inflate(mContext, R.layout.item_waterfall, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(WaterfallRecyclerAdapter.ViewHolder holder, int position) {
        holder.simpleDraweeView.setImageURI(Uri.parse(datas.get(position)));

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView simpleDraweeView;


        public ViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.item_waterfall);
        }
    }

}
