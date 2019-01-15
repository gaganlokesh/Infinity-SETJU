package com.setju.android.infinity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gagan Lokesh on 23-03-2018.
 */

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventListViewHolder>{

    private Context mContext;
    private ArrayList<Event> events;

    public EventListAdapter(Context context, ArrayList<Event> Events){
        mContext = context;
        events = Events;
    }


    @Override
    public EventListAdapter.EventListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EventListViewHolder(LayoutInflater.from(mContext).inflate(R.layout.event_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(EventListAdapter.EventListViewHolder holder, int position) {


        Event currentEvent = events.get(position);
        holder.eventName.setText(currentEvent.getName());
        holder.evenType.setText(currentEvent.getType());
    }


    @Override
    public int getItemCount() {
        return events.size();
    }

    void add(Event event){
        int size = events.size();
        events.add(event);
        notifyItemInserted(size);
//        notifyItemRangeChanged(0, size);
//        notifyDataSetChanged();

    }

    public class EventListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView eventName;
        TextView evenType;

         EventListViewHolder(View itemView) {
            super(itemView);

            eventName = itemView.findViewById(R.id.event_name_view);
            evenType = itemView.findViewById(R.id.event_type);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

             Event currentEve = events.get(getAdapterPosition());
            Intent intent = new Intent(mContext, EventDetailActivity.class);

            intent.putExtra("name", currentEve.getName());
            intent.putExtra("about", currentEve.getAbout());
            intent.putExtra("fee", currentEve.getFee());
            intent.putExtra("dt", currentEve.getDt());
            intent.putExtra("venue", currentEve.getVenue());
            intent.putExtra("rules", currentEve.getRules());
            intent.putExtra("prize", currentEve.getPrize());
            intent.putExtra("coordinator", currentEve.getC1());
            intent.putExtra("phone", currentEve.getP1());

            mContext.startActivity(intent);
        }
    }
}
