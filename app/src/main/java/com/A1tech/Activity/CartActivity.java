package com.A1tech.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.A1tech.ADS.R;
import com.A1tech.Adapter.CartAdapter;
import com.A1tech.Helper.LocalStorage;
import com.A1tech.Model.Cart;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends BaseActivity {
    View progress;
    LocalStorage localStorage;
    List<Cart> cartList = new ArrayList<>();
    Gson gson;
    RecyclerView recyclerView;
    CartAdapter adapter;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    ImageView emptyCart;
    LinearLayout checkoutLL;
    TextView totalPrice;
    private String mState = "SHOW_MENU";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF5CCC78")));
        changeActionBarTitle(getSupportActionBar());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        //upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        setContentView(R.layout.activity_cart);
        localStorage = new LocalStorage(getApplicationContext());
        gson = new Gson();
        emptyCart = findViewById(R.id.empty_cart_img);
        checkoutLL = findViewById(R.id.checkout_LL);
        totalPrice = findViewById(R.id.total_price);
        totalPrice.setText("" + getTotalPrice() + " So'm");
//        Intent intent = getIntent();
//        productTypeId = intent.getIntExtra("category",0);

        setUpCartRecyclerview();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cart_menu, menu);
        MenuItem item = menu.findItem(R.id.cart_delete);
        if (mState.equalsIgnoreCase("HIDE_MENU")) {
            item.setVisible(false);
        } else {
            item.setVisible(true);
        }
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart_delete:

                AlertDialog diaBox = showDeleteDialog();
                diaBox.show();

                return true;

            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(CartActivity.this, ProductGroupActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private AlertDialog showDeleteDialog() {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)

                //set message, title, and icon
                .setTitle("O'chirish")
                .setMessage("Hammasini o'chirish")
                .setIcon(R.drawable.ic_delete_black_24dp)

                .setPositiveButton("O'chirish", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        localStorage.deleteCart();
                        adapter.notifyDataSetChanged();
                        emptyCart.setVisibility(View.VISIBLE);
                        checkoutLL.setVisibility(View.GONE);
                        mState = "HIDE_MENU";
                        invalidateOptionsMenu();
                        dialog.dismiss();
                    }

                })

                .setNegativeButton("Bekor qilish", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CartActivity.this, ProductGroupActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void setUpCartRecyclerview() {
        cartList = new ArrayList<>();
        cartList = getCartList();
        if (cartList.isEmpty()) {
            mState = "HIDE_MENU";
            invalidateOptionsMenu();
            emptyCart.setVisibility(View.VISIBLE);
            checkoutLL.setVisibility(View.GONE);
        }
        recyclerView = findViewById(R.id.cart_rv);
        recyclerView.setHasFixedSize(true);
        recyclerViewlayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(recyclerViewlayoutManager);
        adapter = new CartAdapter(cartList, CartActivity.this);
        recyclerView.setAdapter(adapter);
    }


    public void onCheckoutClicked(View view) {

        Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);

        intent.putExtra("ProductNarxlar", (String.valueOf(getTotalPrice())));
        Log.e("ppr", String.valueOf(totalPrice));
        Log.e("ppr------2", String.valueOf((String.valueOf(getTotalPrice()))));

//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    @Override
    public void updateTotalPrice() {

        totalPrice.setText("" + getTotalPrice() + " So'm");
        if (getTotalPrice() == 0.0) {
            mState = "HIDE_MENU";
            invalidateOptionsMenu();
            emptyCart.setVisibility(View.VISIBLE);
            checkoutLL.setVisibility(View.GONE);
        }
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
        tv.setText("Savat"); // ActionBar title text
        tv.setTextSize(19);

        // Set the text color of TextView to red
        // This line change the ActionBar title text color
        tv.setTextColor(getResources().getColor(R.color.white));

        // Set the ActionBar display option
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        // Finally, set the newly created TextView as ActionBar custom view
        actionBar.setCustomView(tv);
    }

}