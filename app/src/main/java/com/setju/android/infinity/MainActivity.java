package com.setju.android.infinity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AHBottomNavigation.OnTabSelectedListener {

    public static final int RC_SIGN_IN = 1;


    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private String username;
    private AHBottomNavigation ahBottomNavigation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null){
                    //User signed in
                    username = user.getDisplayName();

                }
                else {

                    ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

                    NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                    if (networkInfo != null && networkInfo.isConnected()) {
                        //User signed out
                        startActivityForResult(
                                AuthUI.getInstance()
                                        .createSignInIntentBuilder()
                                        .setIsSmartLockEnabled(false)
                                        .setTheme(R.style.Login)
                                        .setLogo(R.drawable.logo)
                                        .setAvailableProviders(Arrays.asList(
                                                new AuthUI.IdpConfig.EmailBuilder().build(),
                                                new AuthUI.IdpConfig.GoogleBuilder().build()
                                        ))
                                        .build(),
                                RC_SIGN_IN);
                    } else {
                        setContentView(R.layout.empty_layout);
                    }

                }
            }
        };


        // FirebaseMessaging.getInstance().subscribeToTopic("Music");

        ahBottomNavigation =  findViewById(R.id.bottom_navigation);

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab1,R.drawable.ic_action_home, R.color.color_tab1);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab2,R.drawable.ic_action_event, R.color.color_tab1);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab3,R.drawable.ic_action_feed,R.color.color_tab1);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.tab4, R.drawable.ic_action_contact, R.color.color_tab1);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.tab5,R.drawable.ic_action_about, R.color.color_tab1);

        ahBottomNavigation.addItem(item1);
        ahBottomNavigation.addItem(item2);
        ahBottomNavigation.addItem(item3);
        ahBottomNavigation.addItem(item4);
        ahBottomNavigation.addItem(item5);

        ahBottomNavigation.setColored(true);

        ahBottomNavigation.setAccentColor(Color.parseColor("#FFFFFF"));
        ahBottomNavigation.setInactiveColor(Color.parseColor("#616161"));

        ahBottomNavigation.setNotificationBackgroundColor(getResources().getColor(R.color.colorAccent));


        ahBottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        ahBottomNavigation.setCurrentItem(0);

        ahBottomNavigation.setOnTabSelectedListener(this);
        this.getSupportFragmentManager().executePendingTransactions();



        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, homeFragment).commit();

        String notification = getIntent().getStringExtra("notify");
        if(notification!=null){
            ahBottomNavigation.setNotification("1", 2);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){

            if(resultCode == RESULT_OK){

                HomeFragment homeFragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container_main, homeFragment).commit();

                Toast.makeText(MainActivity.this, "Sign in Successful", Toast.LENGTH_SHORT).show();

            }
            else if (resultCode == RESULT_CANCELED){
                finish();
            }
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

    @Override
    public boolean onTabSelected(int position, boolean wasSelected) {


        switch (position){

            case 0:
                HomeFragment homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.container_main, homeFragment).commit();
            return true;

            case 1:
                EventFragment eventFragment = new EventFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container_main, eventFragment).commit();
                return true;

            case 2:
                FeedFragment feedFragment=new FeedFragment();
                ahBottomNavigation.setNotification("", 2);
            getSupportFragmentManager().beginTransaction().replace(R.id.container_main, feedFragment).commit();
            return true;

            case 3:
                ContactFragment contactFragment = new ContactFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container_main, contactFragment).commit();
                return true;

            case 4:
                AppFragment appFragment = new AppFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.container_main, appFragment).commit();
        }
        return true;

    }


}
