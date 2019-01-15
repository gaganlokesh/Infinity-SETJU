package com.setju.android.infinity;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EventDetailActivity extends AppCompatActivity {

    private TextView descriptionTV, titleTextView, feeTV, dtTV, venueTV, rulesTV, prizeTV, c1, p1;
    private ImageView arrow_description, arrow_rules;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);


        setTitle("Event Details");

        //---------------------------------------------------------------------------
        descriptionTV = findViewById(R.id.description_body);
        titleTextView = findViewById(R.id.event_detail_tv);
        feeTV = findViewById(R.id.fee);
        dtTV = findViewById(R.id.dt);
        venueTV = findViewById(R.id.venue);
        rulesTV = findViewById(R.id.rules_body);
        prizeTV = findViewById(R.id.prize);
        c1 = findViewById(R.id.coordinator_name);
        p1 = findViewById(R.id.coordinator_phone);

        arrow_description = findViewById(R.id.arrow_description);
        arrow_rules = findViewById(R.id.arrow_rules);

        //---------------------------------------------------------------------------------
        final String name = getIntent().getStringExtra("name");
        String about = getIntent().getStringExtra("about");
        String fee = getIntent().getStringExtra("fee");
        String dt = getIntent().getStringExtra("dt");
        String venue = getIntent().getStringExtra("venue");
        String rules = getIntent().getStringExtra("rules");
        String prize = getIntent().getStringExtra("prize");
        String coordinator = getIntent().getStringExtra("coordinator");
        phone= getIntent().getStringExtra("phone");



        titleTextView.setText(name);

        descriptionTV.setText(about);
        arrow_description.setImageResource(R.drawable.ic_action_collapse);

        LinearLayout description_layout = findViewById(R.id.description_layout);
        description_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                switch (descriptionTV.getVisibility()) {

                    case View.GONE:
                        descriptionTV.setVisibility(View.VISIBLE);
                        arrow_description.setImageResource(R.drawable.ic_action_collapse);
                        break;

                    case View.VISIBLE:
                        descriptionTV.setVisibility(View.GONE);
                        arrow_description.setImageResource(R.drawable.ic_action_expand);
                        break;

                    case View.INVISIBLE:
                        descriptionTV.setVisibility(View.GONE);
                        break;

                    default:
                        break;
                }


            }
        });

        feeTV.setText(fee);
        dtTV.setText(dt);
        venueTV.setText(venue);

        rulesTV.setText(rules);
        arrow_rules.setImageResource(R.drawable.ic_action_expand);


        LinearLayout rules_layout = findViewById(R.id.rules_layout);
        rules_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                switch (rulesTV.getVisibility()) {

                    case View.GONE:
                        rulesTV.setVisibility(View.VISIBLE);
                        arrow_rules.setImageResource(R.drawable.ic_action_collapse);
                        break;

                    case View.VISIBLE:
                        rulesTV.setVisibility(View.GONE);
                        arrow_rules.setImageResource(R.drawable.ic_action_expand);
                        break;

                    case View.INVISIBLE:
                        rulesTV.setVisibility(View.GONE);
                        break;

                    default:
                        break;
                }


            }
        });

        prizeTV.setText(prize);
        c1.setText(coordinator);

        p1.setText(phone);
        p1.setPaintFlags(p1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri callUri = Uri.parse("tel:" + phone);
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(callUri);
                startActivity(intent);
            }
        });

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
