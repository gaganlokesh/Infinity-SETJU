package com.setju.android.infinity;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {

    private ArrayList<Category> mCategories;
    private CategoryAdapter mAdapter;

    public EventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        getActivity().setTitle("Events");

        final RecyclerView recyclerView = view.findViewById(R.id.event_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mCategories = new ArrayList<>();

        mAdapter = new CategoryAdapter(getActivity(), mCategories);

        recyclerView.setAdapter(mAdapter);


        mAdapter.add(new Category(R.string.Dance, R.drawable.dance, getResources().getDrawable(R.drawable.dance)));
        mAdapter.add(new Category(R.string.music, R.drawable.music, getResources().getDrawable(R.drawable.music)));
        mAdapter.add(new Category(R.string.Theatre, R.drawable.theatre, getResources().getDrawable(R.drawable.theatre)));
        mAdapter.add(new Category(R.string.Fashion, R.drawable.fashion, getResources().getDrawable(R.drawable.fashion)));
        mAdapter.add(new Category(R.string.Fine_arts, R.drawable.finearts, getResources().getDrawable(R.drawable.finearts)));
        mAdapter.add(new Category(R.string.Quiz, R.drawable.quiz,getResources().getDrawable(R.drawable.quiz)));
        mAdapter.add(new Category(R.string.Tech, R.drawable.tech, getResources().getDrawable(R.drawable.tech)));
        mAdapter.add(new Category(R.string.Literary, R.drawable.literary, getResources().getDrawable(R.drawable.literary)));
        mAdapter.add(new Category(R.string.Photography, R.drawable.photography, getResources().getDrawable(R.drawable.photography)));
        mAdapter.add(new Category(R.string.Informal, R.drawable.informal, getResources().getDrawable(R.drawable.informal)));
        mAdapter.add(new Category(R.string.gaming, R.drawable.gaming, getResources().getDrawable(R.drawable.gaming)));
        mAdapter.add(new Category(R.string.welfare, R.drawable.welfare, getResources().getDrawable(R.drawable.welfare)));
        mAdapter.add(new Category(R.string.Food_Technology, R.drawable.foodtech, getResources().getDrawable(R.drawable.foodtech)));
//        mAdapter.add(new Category(R.string.Food_Technology, getResources().getDrawable(R.drawable.foodtech)));



        return view;
    }

}
