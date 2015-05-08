package com.android.ashwini.instagramviewer;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AllCommentsDialog extends DialogFragment implements AdapterView.OnItemClickListener {

    private ListView lvAllComments;
    private ArrayList comments = new ArrayList();
    private ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_comments, null, false);
        lvAllComments = (ListView) view.findViewById(R.id.lvAllComments);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        comments = getArguments().getStringArrayList("comments");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, comments);
        lvAllComments.setAdapter(adapter);
        lvAllComments.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        dismiss();
    }
}
