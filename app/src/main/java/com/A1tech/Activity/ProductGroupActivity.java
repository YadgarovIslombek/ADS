package com.A1tech.Activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.A1tech.ADS.R;
import com.A1tech.Adapter.ProductGroupAdapter;
import com.A1tech.ApiClient.RetrofitClient;
import com.A1tech.JsonResponseProductGroup;
import com.A1tech.Model.ProductType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ProductGroupActivity extends AppCompatActivity {
    View progress;

    List<ProductType> productGroupList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProductGroupAdapter productGroupAdapter;
    Toolbar toolbar;
    TextView txt_tool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producttype);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        changeActionBarTitle(getSupportActionBar());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        //upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        recyclerView = (RecyclerView) findViewById(R.id.recV);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        progress = findViewById(R.id.progress_bar);
        showProgressDialog();
        Call<JsonResponseProductGroup> call = RetrofitClient.getData(getApplicationContext()).getProductType();
        call.enqueue(new Callback<JsonResponseProductGroup>() {
            @Override
            public void onResponse(Call<JsonResponseProductGroup> call, Response<JsonResponseProductGroup> response) {

                Log.e("Bajarildi", response.toString());
                JsonResponseProductGroup jsonResponseProductGroup = response.body();
                productGroupList = Arrays.asList(jsonResponseProductGroup.getProductTypes());
                productGroupAdapter = new ProductGroupAdapter(productGroupList, ProductGroupActivity.this);
                recyclerView.setAdapter(productGroupAdapter);
                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<JsonResponseProductGroup> call, Throwable t) {
                Log.e("Xatolik", t.getLocalizedMessage());
                hideProgressDialog();
            }
        });


    }
    private void hideProgressDialog() {
        progress.setVisibility(View.GONE);
    }

    private void showProgressDialog() {
        progress.setVisibility(View.VISIBLE);
    }
    private void changeActionBarTitle(ActionBar actionBar) {
        // Create a LayoutParams for TextView
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                RelativeLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        TextView tv = new TextView(getApplicationContext());
        // Apply the layout parameters to TextView widget
        tv.setLayoutParams(lp);
        tv.setGravity(Gravity.CENTER);
        tv.setTypeface(null, Typeface.BOLD);
        // Set text to display in TextView
        tv.setText("Products"); // ActionBar title text
        tv.setTextSize(20);

        // Set the text color of TextView to red
        // This line change the ActionBar title text color
        tv.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        // Set the ActionBar display option
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        // Finally, set the newly created TextView as ActionBar custom view
        actionBar.setCustomView(tv);
    }
}
   /* @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ProductActivity.class);
        view.getContext().startActivity(intent);
        Toast.makeText(this, "Clicked" + view, Toast.LENGTH_SHORT).show();
    }*/


