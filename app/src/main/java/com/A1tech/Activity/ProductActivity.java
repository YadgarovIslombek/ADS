package com.A1tech.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.A1tech.ADS.R;
import com.A1tech.Adapter.ProductAdapter;
import com.A1tech.ApiClient.RetrofitClient;
import com.A1tech.Helper.Converter;
import com.A1tech.Helper.LocalStorage;
import com.A1tech.Model.CategoryResult;
import com.A1tech.Model.ProductModel;
import com.A1tech.Model.ProductType;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends BaseActivity {
    private static int cart_count = 0;
    String Tag = "List";
    View progress;
    LocalStorage localStorage;
    Gson gson = new Gson();
    List<ProductModel> productList = new ArrayList<>();
    ProductAdapter mAdapter;
    ProductModel productModel;
    ProductType productType;
    int productTypeId;
    private RecyclerView recyclerView;
    Toolbar toolbar;
    TextView txt_tool;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        localStorage = new LocalStorage(getApplicationContext());
        Intent intent = getIntent();
        productTypeId = intent.getIntExtra("category",0);
        productModel = new ProductModel(productTypeId);
        cart_count = cartCount();
        recyclerView = findViewById(R.id.product_rv);

        toolbar = (Toolbar)findViewById(R.id.toolbar_product);
        setSupportActionBar(toolbar);
        txt_tool =(TextView)findViewById(R.id.tool_title);
        txt_tool.setText("Mahsulotlar");
        txt_tool.setGravity(Gravity.CENTER);
//        getCategoryProduct();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        progress = findViewById(R.id.progress_bar1);
        showProgressDialog();
        Call<CategoryResult> call = RetrofitClient.getData(getApplicationContext()).getProductById(productTypeId);
        call.enqueue(new Callback<CategoryResult>() {
            @Override
            public void onResponse(Call<CategoryResult> call, Response<CategoryResult> response) {
                Log.e("Bajarildi", response.toString());
                CategoryResult categoryResult = response.body();
                productList = categoryResult.getProductList();
                mAdapter = new ProductAdapter(productList, ProductActivity.this, Tag);
                recyclerView.setAdapter(mAdapter);
                hideProgressDialog();
            }
            @Override
            public void onFailure(Call<CategoryResult> call, Throwable t) {
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



//    private void setUpRecyclerView() {
//
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(mAdapter);
//
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(ProductActivity.this, ProductTypeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;

            case R.id.cart_action:
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem = menu.findItem(R.id.cart_action);
        menuItem.setIcon(Converter.convertLayoutToImage(ProductActivity.this, cart_count, R.drawable.ic_baseline_shopping_cart_24));
        return true;
    }


    @Override
    public void onAddProduct() {
        cart_count++;
        invalidateOptionsMenu();

    }

    @Override
    public void onRemoveProduct() {
        cart_count--;
        invalidateOptionsMenu();

    }

}






 /*Call<JsonResponse> call = Client.getProduct().getAllProducts();

           call.enqueue(new Callback<JsonResponse>() {
   @Override
   public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {
           Log.e("Bajarildi", response.toString());
           JsonResponse jsonResponse = response.body();
           list = new ArrayList<>(Arrays.asList(jsonResponse.getProduct()));
           productAdapter = new ProductAdapter(list,context);
           recyclerView.setAdapter(productAdapter);
           }

   @Override
   public void onFailure(Call<JsonResponse> call, Throwable t) {
           Log.e("Xatolik", t.getLocalizedMessage());

           }
           });

   */





















    /*   getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem item1 = menu.findItem(R.id.item_id);
        searchView = (SearchView)item1.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                productAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                productAdapter.getFilter().filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);*/