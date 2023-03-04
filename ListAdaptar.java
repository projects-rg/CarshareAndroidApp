package com.example.carshareprototype;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class ListAdaptar extends ArrayAdapter {

    private Activity mContext;
    List<Lists> listingList;

    public ListAdaptar(Activity mContext, List<Lists> listingList){
        super(mContext,R.layout.list_item,listingList);
        this.mContext = mContext;
        this.listingList = listingList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.list_item, null,true);

        TextView tvAddress = listItemView.findViewById(R.id.tvAddress);
        TextView tvDestination = listItemView.findViewById(R.id.tvDestination);
        TextView tvSeats = listItemView.findViewById(R.id.tvSeats);
        TextView tvContactNumber = listItemView.findViewById(R.id.tvContactNumber);

        Lists lists = listingList.get(position);
        tvAddress.setText(lists.getStartingAddress());
        tvDestination.setText(lists.getDestination());
        tvSeats.setText(lists.getSeatsAvailable());
        tvContactNumber.setText(lists.getContactNumber());

        return listItemView;


    }
}
