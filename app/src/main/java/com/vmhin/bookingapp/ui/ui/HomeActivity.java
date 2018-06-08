package com.vmhin.bookingapp.ui.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.vmhin.bookingapp.R;
import com.vmhin.bookingapp.ui.adapters.BookingAdapter;
import com.vmhin.bookingapp.ui.db.DBHelper;
import com.vmhin.bookingapp.ui.models.BookingDetails;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private List<BookingDetails> bookingDetailsList = new ArrayList<>();
    private BookingAdapter mAdapter;

    DBHelper dbHelper;

    public static BookingDetails bookingDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbHelper = new DBHelper(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.bookingList);

        mAdapter = new BookingAdapter(bookingDetailsList, this, new BookingAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(BookingDetails item) {
                bookingDetails = item;
                Intent intent = new Intent(HomeActivity.this,AddViewDetailsActivity.class);
                intent.putExtra("BookingAdapter","BookingAdapter");
                startActivity(intent);
                finish();
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareTempDetailsData();

        AppCompatButton logBtn = (AppCompatButton) findViewById(R.id.logBtn);
        AppCompatButton settingsBtn = (AppCompatButton) findViewById(R.id.settingsBtn);
        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,AddViewDetailsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,SettingsActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


    private void prepareTempDetailsData() {

        bookingDetailsList.addAll(dbHelper.getAllTrips());

        Log.e("size",""+bookingDetailsList.size());

        mAdapter.notifyDataSetChanged();
    }
}
