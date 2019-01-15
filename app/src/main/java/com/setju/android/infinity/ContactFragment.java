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
public class ContactFragment extends Fragment {


    RecyclerView rv1, rv2, rv3, rv4, rv5;
    ArrayList<Contact> contacts1, contacts2, contacts3, contacts4, contacts5;
    ContactAdapter culturalAdapter, organizerAdapter, sponsorshipAdapter, marketingAdapter, eventAdapter;


    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        getActivity().setTitle("Contact");

        rv1 = view.findViewById(R.id.contactRV1);

        rv1.setLayoutManager(new LinearLayoutManager(getActivity()));

        contacts1 = new ArrayList<>();

        culturalAdapter = new ContactAdapter(getActivity(), contacts1);

        rv1.setAdapter(culturalAdapter);

        culturalAdapter.add(new Contact("Dr. Santosh Naik", "Department of ISE", "9999999999"));


        //---Organizer------------------------------------------

        rv2 = view.findViewById(R.id.contactRV2);

        rv2.setLayoutManager(new LinearLayoutManager(getActivity()));

        contacts2 = new ArrayList<>();

        organizerAdapter = new ContactAdapter(getActivity(), contacts2);

        rv2.setAdapter(organizerAdapter);

        organizerAdapter.add(new Contact("Ravi Kumar", "Department of ECE", "9999999999"));
        organizerAdapter.add(new Contact("Shilpa Das", "Department of CSE", "9999999999"));



        //---Sponsorship Team---------------------------------------

        rv5 = view.findViewById(R.id.contactRV5);

        rv5.setLayoutManager(new LinearLayoutManager(getActivity()));

        contacts5 = new ArrayList<>();

        sponsorshipAdapter = new ContactAdapter(getActivity(), contacts5);

        rv5.setAdapter(sponsorshipAdapter);

        sponsorshipAdapter.add(new Contact("Rachith", "Department of CSE", "9999999999"));
        sponsorshipAdapter.add(new Contact("Rajeshwari", "Department of ASE", "9999999999"));

        //---Marketing Team-----------------------------------------

        rv4 = view.findViewById(R.id.contactRV4);

        rv4.setLayoutManager(new LinearLayoutManager(getActivity()));

        contacts4 = new ArrayList<>();

        marketingAdapter = new ContactAdapter(getActivity(), contacts4);

        rv4.setAdapter(marketingAdapter);

        marketingAdapter.add(new Contact("Darshan", "Department of CE", "9999999999"));
        marketingAdapter.add(new Contact("Deepak", "Department of CE", "9999999999"));


        //---Event Organizing Team---------------------------------

        rv3 = view.findViewById(R.id.contactRV3);

        rv3.setLayoutManager(new LinearLayoutManager(getActivity()));

        contacts3 = new ArrayList<>();

        eventAdapter = new ContactAdapter(getActivity(), contacts3);

        rv3.setAdapter(eventAdapter);

        eventAdapter.add(new Contact("Harisanthosh", "Department of ME", "9999999999"));
        eventAdapter.add(new Contact("Manish", "Department of ME", "9999999999"));

        return view;
    }

}
