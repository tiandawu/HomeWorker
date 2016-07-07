package com.zbj.myapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zbj.myapp.R;
import com.zbj.myapp.adapter.MyListAdapter;
import com.zbj.myapp.bean.MyContact;


/**
 * Created by tiandawu on 2016/7/5.
 */
public class MomentFragment extends Fragment {

    private ListView mListView;
    private MyListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_moment, container, false);
        mListView = (ListView) view.findViewById(R.id.moment_listview);
        initData();
        return view;
    }


    private void initData() {
        adapter = new MyListAdapter(getActivity());
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyContact contact = (MyContact) adapter.getItem(i);
                if (!contact.isExpandable()) {
                    contact.setExpandable(true);
                    adapter.expandItem(i);
                } else {
                    contact.setExpandable(false);
                    adapter.closeItem(i);
                }
            }
        });
    }

}
