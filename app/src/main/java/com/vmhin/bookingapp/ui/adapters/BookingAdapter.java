package com.vmhin.bookingapp.ui.adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vmhin.bookingapp.R;
import com.vmhin.bookingapp.ui.models.BookingDetails;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.MyViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(BookingDetails item);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView titleText, dateText, destinationText, tripTypeText;

        public MyViewHolder(View view) {
            super(view);
            titleText = (AppCompatTextView) view.findViewById(R.id.titleText);
            dateText = (AppCompatTextView) view.findViewById(R.id.dateText);
            destinationText = (AppCompatTextView) view.findViewById(R.id.destinationText);
            tripTypeText = (AppCompatTextView) view.findViewById(R.id.tripTypeText);
        }

        public void bind(final BookingDetails item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    private List<BookingDetails> materialDetailsList;
    private Context context;
    private final OnItemClickListener listener;

    public BookingAdapter(List<BookingDetails> materialDetailsList, Context context, OnItemClickListener listener) {
        this.materialDetailsList = materialDetailsList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.bind(materialDetailsList.get(position), listener);

        BookingDetails materialDetails = materialDetailsList.get(position);
        holder.titleText.setText(materialDetails.getTitle());
        holder.dateText.setText(materialDetails.getDate());
        holder.destinationText.setText(materialDetails.getDestination());
        holder.tripTypeText.setText(materialDetails.getTripType());

    }

    @Override
    public int getItemCount() {
        return materialDetailsList.size();
    }
}
