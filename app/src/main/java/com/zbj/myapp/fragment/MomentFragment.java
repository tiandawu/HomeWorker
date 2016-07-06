package com.zbj.myapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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

        for (int i = 0; i < 4; i++) {
            MyContact myContact = new MyContact();
            myContact.setName("家人-联系人-" + i);
            if (i == 0) {
                myContact.setGroupFlag(0);
            } else {
                myContact.setGroupFlag(1);
            }

            contacts.add(myContact);
        }

        /**
         * 创建4个朋友组的联系人
         */

        for (int i = 0; i < 5; i++) {
            MyContact myContact = new MyContact();
            myContact.setName("朋友-联系人-" + i);
            if (i == 0) {
                myContact.setGroupFlag(0);
            } else {
                myContact.setGroupFlag(1);
            }
            contacts.add(myContact);
        }

        /**
         * 创建6个同事组的联系人
         */

        for (int i = 0; i < 7; i++) {
            MyContact myContact = new MyContact();
            myContact.setName("同事-联系人-" + i);
            if (i == 0) {
                myContact.setGroupFlag(0);
            } else {
                myContact.setGroupFlag(1);
            }
            contacts.add(myContact);
        }

        final MyAdapter myAdapter = new MyAdapter();
        mListView.setAdapter(myAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int type = myAdapter.getItemViewType(position);

                if (type == 0) {
//                    if (position == 0) {
//                        for (int i = 0; i < 3; i++) {
//                            Log.e("tt", "name = " + contacts.get(position + 1).getName());
//                            if (contacts.get(position).getName().contains("家人")) {
//                                contacts.remove(1);
//                            }
//                        }
//                        myAdapter.notifyDataSetChanged();
//                    }

                } else {
//                    Log.e("tt", "++++++++++");
                }
            }
        });
    }


    private class MyAdapter extends BaseAdapter {

        private final int VIEW_ITEM_TYPE = 2;

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
        public int getItemViewType(int position) {

            if (contacts.get(position).getGroupFlag() == 0) {
                return 0;
            } else {
                return 1;
            }
        }


        @Override
        public int getViewTypeCount() {
            return VIEW_ITEM_TYPE;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyViewHolder holder;
            GroupViewHolder groupHolder;
            int type = contacts.get(position).getGroupFlag();

//            Log.e("tt", "type = " + type + "    name = " + contacts.get(position).getName());
            if (null == convertView) {

                if (0 == type) {
                    groupHolder = new GroupViewHolder();
                    convertView = View.inflate(getActivity(), R.layout.item_group, null);
                    groupHolder.indicator = (ImageView) convertView.findViewById(R.id.item_group_img_indicator);
                    groupHolder.groupName = (TextView) convertView.findViewById(R.id.item_group_tv_group_name);
                    convertView.setTag(groupHolder);
                } else if (1 == type) {
                    holder = new MyViewHolder();
                    convertView = View.inflate(getActivity(), R.layout.item_child, null);
                    holder.mItemName = (TextView) convertView.findViewById(R.id.item_child_tv_name);
                    convertView.setTag(holder);
                }

            }

            if (0 == type) {
                groupHolder = (GroupViewHolder) convertView.getTag();
                String name = contacts.get(position).getName();
                if (name.contains("家人")) {
                    groupHolder.groupName.setText("家人");
                } else if (name.contains("朋友")) {
                    groupHolder.groupName.setText("朋友");
                } else if (name.contains("同事")) {
                    groupHolder.groupName.setText("同事");
                }

            } else if (1 == type) {
                holder = (MyViewHolder) convertView.getTag();
                holder.mItemName.setText(contacts.get(position).getName());
            }

            return convertView;
        }

        class MyViewHolder {
            private TextView mItemName;
        }

        class GroupViewHolder {
            private ImageView indicator;
            private TextView groupName;
        }
    }
}
