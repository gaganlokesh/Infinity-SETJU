package com.setju.android.infinity;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.todddavies.components.progressbar.ProgressWheel;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }



    private ProgressWheel mDaysWheel;
    private ProgressWheel mHoursWheel;
    private ProgressWheel mMinutesWheel;
    private ProgressWheel mSecondsWheel;

    Time conferenceTime = new Time(Time.getCurrentTimezone());
    int hour = 9;
    int minute = 0;
    int second = 0;
    int monthDay = 20;
    int month = 3;
    int year;

    private int mDisplayDays;
    private int mDisplayHours;
    private int mDisplayMinutes;
    private int mDisplaySeconds;

    CarouselView carouselView;
    int[] sampleImages = {R.drawable.slider1, R.drawable.slider2, R.drawable.slider3, R.drawable.slider4, R.drawable.slider5, R.drawable.slider6};

    TextView tv_countdown, statusTextView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        this.getActivity().setTitle("Infinity");

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        TextView usernameTV = view.findViewById(R.id.user_welcome);


        if(user!=null) {
            String username = user.getDisplayName();
            if(username != null) {
                String welcome = "Welcome, " + username + "!";
                usernameTV.setText(welcome);
            }
            else {
                String welcome2 = "Welcome!";
                usernameTV.setText(welcome2);
            }
        }

        carouselView = view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);


        tv_countdown = view.findViewById(R.id.countdown);
        statusTextView = view.findViewById(R.id.infinity_status);

        conferenceTime.setToNow();
        year = conferenceTime.year;

        mDaysWheel = view.findViewById(R.id.activity_countdown_timer_days);
        mHoursWheel = view.findViewById(R.id.activity_countdown_timer_hours);
        mMinutesWheel = view.findViewById(R.id.activity_countdown_timer_minutes);
        mSecondsWheel = view.findViewById(R.id.activity_countdown_timer_seconds);


        configureConferenceDate();

        FloatingActionButton navButton = view.findViewById(R.id.nav_button);
        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri mapLocation = Uri.parse("https://goo.gl/maps/6yR7T1R6Ncv");

                try {
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapLocation);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }
                catch (ActivityNotFoundException ex){
                    Toast.makeText(getActivity(), "Install google maps to view directions.", Toast.LENGTH_LONG).show();
                }
                catch (Exception e){
                    e.getMessage();
                }
            }
        });


        ImageView instagram = view.findViewById(R.id.instagram);
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri instaUri = Uri.parse("https://www.instagram.com/juinfinity2018");

                Intent instagramIntent = new Intent(Intent.ACTION_VIEW, instaUri);
                instagramIntent.setPackage("com.instagram.android");

                try{
                    startActivity(instagramIntent);
                }

                catch (ActivityNotFoundException ex){
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(instaUri);
                    startActivity(i);
                }
            }
        });

        ImageView facebook = view.findViewById(R.id.facebook);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri fbUri = Uri.parse("fb://facewebmodal/f?href=https://www.facebook.com/infinitysetju2018/");

                Intent fbIntent = new Intent(Intent.ACTION_VIEW, fbUri);
                fbIntent.setPackage("com.facebook.katana");

                try{
                    startActivity(fbIntent);
                }

                catch (ActivityNotFoundException ex){
                    Uri newfb = Uri.parse("https://www.facebook.com/infinitysetju2018");
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(newfb);
                    startActivity(i);
                }
            }
        });

        ImageView website = view.findViewById(R.id.website);
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webUri = Uri.parse("https://www.infinitysetju.org");

                Intent webIntent = new Intent(Intent.ACTION_VIEW);
                webIntent.setData(webUri);
                startActivity(webIntent);
            }
        });

        ImageView youtube = view.findViewById(R.id.youtube);
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri youtubeUri = Uri.parse("https://www.youtube.com/channel/UC6c6WQWshnzG9n8P8wwNK2g");

                Intent youtubeIntent = new Intent(Intent.ACTION_VIEW, youtubeUri);
                youtubeIntent.setPackage("com.google.android.youtube");

                try{
                    startActivity(youtubeIntent);
                }

                catch (ActivityNotFoundException ex){
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(youtubeUri);
                    startActivity(i);
                }
            }
        });

        return view;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageDrawable(getResources().getDrawable(sampleImages[position]));
        }
    };




    private void configureConferenceDate() {
        conferenceTime.set(second, minute, hour, monthDay, month, year);
        conferenceTime.normalize(true);
        long confMillis = conferenceTime.toMillis(true);

        Time nowTime = new Time(Time.getCurrentTimezone());
        nowTime.setToNow();
        nowTime.normalize(true);
        long nowMillis = nowTime.toMillis(true);

        long milliDiff = confMillis - nowMillis;

        new CountDownTimer(milliDiff, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // decompose difference into days, hours, minutes and seconds
                mDisplayDays = (int) ((millisUntilFinished / 1000) / 86400);
                mDisplayHours = (int) (((millisUntilFinished / 1000) - (mDisplayDays * 86400)) / 3600);
                mDisplayMinutes = (int) (((millisUntilFinished / 1000) - ((mDisplayDays * 86400) + (mDisplayHours * 3600))) / 60);
                mDisplaySeconds = (int) ((millisUntilFinished / 1000) % 60);


                mDaysWheel.setText(String.valueOf(mDisplayDays));
                mDaysWheel.setProgress(mDisplayDays);

                mHoursWheel.setText(String.valueOf(mDisplayHours));
                mHoursWheel.setProgress(mDisplayHours * 15);

                mMinutesWheel.setText(String.valueOf(mDisplayMinutes));
                mMinutesWheel.setProgress(mDisplayMinutes * 6);

                Animation an = new RotateAnimation(0.0f, 90.0f, 250f, 273f);
                an.setFillAfter(true);

                mSecondsWheel.setText(String.valueOf(mDisplaySeconds));
                mSecondsWheel.setProgress(mDisplaySeconds * 6);

            }

            @Override
            public void onFinish() {

                mSecondsWheel.setText("0");
                mSecondsWheel.setProgress(0);

                mMinutesWheel.setText("0");
                mHoursWheel.setText("0");
                mDaysWheel.setText("0");

                statusTextView.setText(R.string.status);
                statusTextView.setVisibility(View.VISIBLE);

            }
        }.start();
    }

}
