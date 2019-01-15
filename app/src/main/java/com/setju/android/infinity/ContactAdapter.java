package com.setju.android.infinity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gagan Lokesh on 31-03-2018.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> mContacts;
    private Context mContext;

    ContactAdapter(Context context, ArrayList<Contact> contacts) {
        mContext = context;
        mContacts = contacts;
    }


    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactViewHolder(LayoutInflater.from(mContext).inflate(R.layout.contact_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {

        Contact currentContact = mContacts.get(position);

        holder.nameTV.setText(currentContact.getContactName());
        holder.departmentTV.setText(currentContact.getContactDepartment());

    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    void add(Contact contact) {
        int size = mContacts.size();
        mContacts.add(contact);
        notifyItemInserted(size);
        notifyItemRangeChanged(0, size);
        notifyDataSetChanged();

    }

    public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nameTV;
        TextView departmentTV;
//        ImageView call;
//        ImageView email;

        public ContactViewHolder(View itemView) {
            super(itemView);

            nameTV = itemView.findViewById(R.id.faculty_name);
            departmentTV = itemView.findViewById(R.id.department_name);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Contact currentContact = mContacts.get(getAdapterPosition());
            String phone = currentContact.getContactPhone();


            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel: "+ phone));
            mContext.startActivity(callIntent);
        }
    }
}
