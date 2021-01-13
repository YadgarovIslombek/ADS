package com.A1tech.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.A1tech.ADS.R;
import com.A1tech.Adapter.ProductTypeAdapter;
import com.A1tech.ApiClient.RetrofitClient;
import com.A1tech.JsonResponseProductGroup;
import com.A1tech.Model.ProductType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ProductTypeActivity extends AppCompatActivity {
    View progress;

    List<ProductType> productTypeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProductTypeAdapter productTypeAdapter;
    Toolbar toolbar;
    TextView txt_tool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producttype);
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
                productTypeList = new ArrayList<>(Arrays.asList(jsonResponseProductGroup.getProductTypes()));
                productTypeAdapter = new ProductTypeAdapter(productTypeList,ProductTypeActivity.this);
                recyclerView.setAdapter(productTypeAdapter);
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
}
   /* @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ProductActivity.class);
        view.getContext().startActivity(intent);
        Toast.makeText(this, "Clicked" + view, Toast.LENGTH_SHORT).show();
    }*/


