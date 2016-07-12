package com.zbj.myapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zbj.myapp.R;

import java.util.ArrayList;

/**
 * Created by tiandawu on 2016/7/8.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {


    private Context mContext;
    //    private int[] numbers;
    private ArrayList<Integer> numbers;

    public MyRecyclerViewAdapter(Context mContext, ArrayList<Integer> datas) {
        this.mContext = mContext;
        this.numbers = datas;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(View.inflate(mContext, R.layout.item_recycler_view, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.showNumber.setText("数字： " + numbers.get(position));
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView showNumber;

        public MyViewHolder(View itemView) {
            super(itemView);
            showNumber = (TextView) itemView.findViewById(R.id.show_number);
        }
    }
}
