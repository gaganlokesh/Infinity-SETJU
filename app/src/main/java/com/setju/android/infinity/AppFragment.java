package com.setju.android.infinity;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.marcoscg.easylicensesdialog.EasyLicensesDialogCompat;


/**
 * A simple {@link Fragment} subclass.
 */
public class AppFragment extends Fragment {



    public AppFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_app, container, false);

        getActivity().setTitle("About App");

//        FloatingActionButton feedbackFab = view.findViewById(R.id.feedback_fab);
//
//        feedbackFab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(getActivity(), FeedbackActivity.class);
//
//                startActivity(intent);
//            }
//        });

        TextView versionTextView = view.findViewById(R.id.app_version);
        String version = "V" + BuildConfig.VERSION_NAME;
        versionTextView.setText(version);

        ImageView facebook = view.findViewById(R.id.dev_facebook);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri fbUri = Uri.parse("fb://facewebmodal/f?href=https://www.facebook.com/gagan.lokesh/");

                Intent fbIntent = new Intent(Intent.ACTION_VIEW, fbUri);
                fbIntent.setPackage("com.facebook.katana");

                try{
                    startActivity(fbIntent);
                }

                catch (ActivityNotFoundException ex){
                    Uri newfb = Uri.parse("https://www.facebook.com/gagan.lokesh");
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(newfb);
                    startActivity(i);
                }
            }
        });

        ImageView linkedin = view.findViewById(R.id.dev_linkedin);
        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri linkedinUri = Uri.parse("https://in.linkedin.com/in/gaganlokesh");

                Intent linkedinIntent = new Intent(Intent.ACTION_VIEW, linkedinUri);
                linkedinIntent.setPackage("com.linkedin.android");

                try{
                    startActivity(linkedinIntent);
                }

                catch (ActivityNotFoundException ex){
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(linkedinUri);
                    startActivity(i);
                }
            }
        });


        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.sign_out :

                Toast.makeText(getActivity(), "Signed out", Toast.LENGTH_LONG);

                AuthUI.getInstance().signOut(getActivity());
                return true;

            case R.id.feedback:

                Intent intent = new Intent(getActivity(), FeedbackActivity.class);
                startActivity(intent);
                return true;

            case R.id.license:

                new EasyLicensesDialogCompat(getActivity())
                        .setTitle("Licenses")
                        .setCancelable(true)
                        .setPositiveButton(android.R.string.ok, null)
                        .show();

                return true;

            case R.id.privacy_policy:
                Uri webUri = Uri.parse("https://sites.google.com/view/infinity-privacy-policy");

                Intent webIntent = new Intent(Intent.ACTION_VIEW);
                webIntent.setData(webUri);
                startActivity(webIntent);
                return true;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);

    }

}
