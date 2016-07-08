package com.zbj.myapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zbj.myapp.R;
import com.zbj.myapp.bean.MyContact;

import java.util.ArrayList;

/**
 * Created by tiandawu on 2016/7/7.
 */
public class MyListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<MyContact> contacts;
    private MyContact root;


    public MyListAdapter(Context context) {
        this.mContext = context;
        contacts = new ArrayList<>();
        root = new MyContact();

        root.setLevel(0);
        root.setName(root.getLevel() + "-联系人");
        contacts.add(root);
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_child, null);
        }
        MyViewHolder holder = MyViewHolder.getHolder(convertView);
        MyContact contact = (MyContact) getItem(position);
        holder.childName.setText(contact.getName());

        int level = contact.getLevel();
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.childName.getLayoutParams();
        params.setMargins(level * 20 + 20, 0, 0, 0);
        holder.childName.setLayoutParams(params);


        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.indicator.getLayoutParams();
        layoutParams.setMargins(level * 20 + 20, 0, 0, 0);
        holder.indicator.setLayoutParams(layoutParams);
        if (contact.isExpandable()) {
            holder.indicator.setImageResource(R.mipmap.indicator_down);
        } else {
            holder.indicator.setImageResource(R.mipmap.indicator_right);
        }

        return convertView;
    }

    private static class MyViewHolder {
        private TextView childName;
        private ImageView indicator;

        public MyViewHolder(View convertView) {
            childName = (TextView) convertView.findViewById(R.id.item_child_tv_name);
            indicator = (ImageView) convertView.findViewById(R.id.item_child_img_indicator);
        }

        public static MyViewHolder getHolder(View convertView) {
            MyViewHolder holder = (MyViewHolder) convertView.getTag();
            if (holder == null) {
                holder = new MyViewHolder(convertView);
                convertView.setTag(holder);
            }
            return holder;
        }
    }

    /**
     * 添加条目
     *
     * @param position
     */
    public void expandItem(int position) {
        MyContact contact = (MyContact) getItem(position);
        for (int i = 0; i < 2; i++) {
            MyContact myContact = new MyContact();
            myContact.setLevel(contact.getLevel() + 1);
            myContact.setName(contact.getLevel() + 1 + "-联系人-" + i);
            contact.addChild(myContact);
        }

        contacts.clear();
        contacts.add(root);
        getNewListData(root);
        notifyDataSetChanged();
    }

    /**
     * 关闭条目
     *
     * @param position
     */
    public void closeItem(int position) {
        MyContact contact = (MyContact) getItem(position);
        contact.clearAllChild();

        contacts.clear();
        contacts.add(root);
        getNewListData(root);
        notifyDataSetChanged();

    }


    /**
     * 遍历条目添加新的数据
     *
     * @param contact
     * @return
     */
    private ArrayList<MyContact> getNewListData(MyContact contact) {
        for (MyContact myContact : contact.getChildList()) {
            contacts.add(myContact);
            /**
             * 递归操作，遍历
             */
            getNewListData(myContact);
        }

        return contacts;
    }


}
