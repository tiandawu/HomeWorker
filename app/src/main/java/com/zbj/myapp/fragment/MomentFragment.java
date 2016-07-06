package com.zbj.myapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zbj.myapp.R;
import com.zbj.myapp.bean.MyContact;

import java.util.ArrayList;

/**
 * Created by tiandawu on 2016/7/5.
 */
public class MomentFragment extends Fragment {

    private ListView mListView;
    private ArrayList<MyContact> contacts = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_moment, container, false);
        mListView = (ListView) view.findViewById(R.id.moment_listview);
        initData();
        return view;
    }


    private void initData() {

        contacts = new ArrayList<>();

        /**
         * 创建3个家人组的联系人
         */

        for (int i = 0; i < 3; i++) {
            MyContact myContact = new MyContact();
            myContact.setName("家人-联系人-" + i);
            myContact.setGroupFlag(0);
            contacts.add(myContact);
        }

        /**
         * 创建4个朋友组的联系人
         */

        for (int i = 0; i < 4; i++) {
            MyContact myContact = new MyContact();
            myContact.setName("朋友-联系人-" + i);
            myContact.setGroupFlag(1);
            contacts.add(myContact);
        }

        /**
         * 创建6个同事组的联系人
         */

        for (int i = 0; i < 4; i++) {
            MyContact myContact = new MyContact();
            myContact.setName("同事-联系人-" + i);
            myContact.setGroupFlag(1);
            contacts.add(myContact);
        }


        mListView.setAdapter(new MyAdapter());

    }

    private class MyAdapter extends BaseAdapter {

        private final int VIEW_TYPE_COUNT = 2;

        @Override
        public int getCount() {
            return contacts.size();
        }

        @Override
        public Object getItem(int i) {
            return contacts.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

//        @Override
//        public int getItemViewType(int position) {
//            return super.getItemViewType(position);
//        }
//
//        @Override
//        public int getViewTypeCount() {
//            return VIEW_TYPE_COUNT;
//        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            MyViewHolder holder = null;
            if (null == view) {
                holder = new MyViewHolder();
                view = View.inflate(getActivity(), R.layout.item_child, null);
                holder.mChildNameTv = (TextView) view.findViewById(R.id.item_child_tv_name);
                view.setTag(holder);
            }

            holder = (MyViewHolder) view.getTag();
            holder.mChildNameTv.setText(contacts.get(i).getName());

            return view;
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {


            return super.getDropDownView(position, convertView, parent);
        }


        private class MyViewHolder {
            private TextView mChildNameTv;
        }


    }

}
