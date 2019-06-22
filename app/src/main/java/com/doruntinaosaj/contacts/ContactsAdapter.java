package com.doruntinaosaj.contacts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {
    private Context mContext;
    private ArrayList<Contacts> mContactsList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public ContactsAdapter(Context context, ArrayList<Contacts> contactList){
        mContext = context;
        mContactsList = contactList;
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.contact, parent, false);
        return new ContactsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder contactsViewHolder, int i) {
        Contacts currentItem = mContactsList.get(i);

        String imageUrl = currentItem.getmImageUrl();
        String emriMbiemri = currentItem.getmEmri()+" "+currentItem.getmMbiemri();

        contactsViewHolder.mTextViewEmriMbiemri.setText(emriMbiemri);
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(contactsViewHolder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mContactsList.size();
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;
        public TextView mTextViewEmriMbiemri;

        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewEmriMbiemri = itemView.findViewById(R.id.emriMbiemri_view);

            itemView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
