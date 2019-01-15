package com.setju.android.infinity;


import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {

    private ArrayList<Feed> mFeedData;
    private FeedAdapter mFeedAdapter;
    private static FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    private ProgressDialog progressDialog;


    public FeedFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_feed, container, false);

        this.getActivity().setTitle("Feeds");

        RecyclerView mRecyclerView = view.findViewById(R.id.feed_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        mRecyclerView.setLayoutManager(linearLayoutManager);

        mFeedData = new ArrayList<>();
        mFeedAdapter = new FeedAdapter(this.getActivity(), mFeedData);

        mRecyclerView.setAdapter(mFeedAdapter);

        progressDialog = new ProgressDialog(this.getActivity());

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected()) {

            progressDialog.setMessage("Loading Feeds...");
            progressDialog.show();

            FirebaseDatabaseInit();
        }
        else {
            Toast.makeText(getActivity(), "No Internet Connection.", Toast.LENGTH_LONG).show();
        }

        return view;
    }

    void FirebaseDatabaseInit(){
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mDatabaseReference = mFirebaseDatabase.getReference().child("feeds");

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

//                String Title = dataSnapshot.child("Title").getValue(String.class);
//                Log.i(FeedActivity.class.getSimpleName(), "String is "+Title);
//                String Body = dataSnapshot.child("Body").getValue(String.class);
//                Log.i(FeedActivity.class.getSimpleName(), "String is "+Body);
//
//                Feed newFeed = new Feed(Title,Body);

                Feed newFeed = dataSnapshot.getValue(Feed.class);
                mFeedAdapter.add(newFeed);
                progressDialog.dismiss();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mDatabaseReference.addChildEventListener(mChildEventListener);
    }

}
