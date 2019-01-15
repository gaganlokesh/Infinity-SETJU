package com.setju.android.infinity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class EventListActivity extends AppCompatActivity {

    private ArrayList<Event> mEvents;
    private EventListAdapter mAdapter;
    private static FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    private String mChild;
    private int mImage;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        Toolbar eventToolbar = findViewById(R.id.toolbar_event);
        setSupportActionBar(eventToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        TextView toolbarTextView = findViewById(R.id.toolbar_event_tv);
//        toolbarTextView.setText(R.string.app_name);

        int title = getIntent().getIntExtra(Constants.CATEGORY_NAME, 0);
        mChild = getString(title);
        mImage = getIntent().getIntExtra(Constants.CATEGORY_IMAGE, 1);

        TextView CategoryTV = findViewById(R.id.category_name_tv);
        String displayText = mChild + " Events";
        CategoryTV.setText(displayText);

        ImageView imageView = findViewById(R.id.event_imageView);
        imageView.setImageResource(mImage);
//        int CategoryImage = Integer.parseInt(getIntent().getStringExtra(Constants.CATEGORY_IMAGE));

        RecyclerView mRecyclerView = findViewById(R.id.event_list_rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(linearLayoutManager);

        mEvents = new ArrayList<>();
        mAdapter = new EventListAdapter(this, mEvents);

        mRecyclerView.setAdapter(mAdapter);

        progressBar = findViewById(R.id.event_list_pb);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = null;
        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected()) {
            FirebaseDatabaseInit(mChild);
        }
        else {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(EventListActivity.this, "No Internet Connection.", Toast.LENGTH_LONG).show();
        }


        FloatingActionButton registerFAB = findViewById(R.id.register);
        registerFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(EventListActivity.this, WebviewActivity.class);
//                startActivity(intent);
                Toast.makeText(EventListActivity.this,"Event Registrations are now closed.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    void FirebaseDatabaseInit(String Child){
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("events").child(Child);

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Event newEvent = dataSnapshot.getValue(Event.class);
                mAdapter.add(newEvent);
                progressBar.setVisibility(View.GONE);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
onBackPressed();
return true;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
