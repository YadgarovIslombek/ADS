package com.A1tech.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.A1tech.ADS.R;
import com.A1tech.Activity.CartActivity;
import com.A1tech.Helper.LocalStorage;
import com.A1tech.Model.Cart;
import com.google.gson.Gson;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    List<Cart> cartList;
    Context context;
    Cart cart;
    int pQuantity = 1;
    double _subtotal;
    int _price, _quantity;
    LocalStorage localStorage;
    Gson gson;

    public CartAdapter(List<Cart> cartList, Context context) {
        this.cartList = cartList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView;

        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_cart_product, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        final Cart cart = cartList.get(position);
        localStorage = new LocalStorage(context);
        gson = new Gson();
        holder.title.setText(""+cart.getProductName());
        holder.attribute.setText(cart.getUnitName());
        _price = cart.getPrice();
        _quantity = cart.getMeasurement();

        holder.quantity.setText(""+_quantity);
        holder.price.setText(""+_price);
        //holder.currency.setText(cart.getCurrency());
        _subtotal = (_price) * (_quantity);
        holder.subTotal.setText(""+_subtotal);

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pQuantity = Integer.parseInt(holder.quantity.getText().toString());
                if (pQuantity >= 1) {
                    int total_item = Integer.parseInt(holder.quantity.getText().toString());
                    total_item++;
                    holder.quantity.setText(total_item + "");
                    for (int i = 0; i < cartList.size(); i++) {

                        if (cartList.get(i).getProductId()==cart.getProductId()) {

                            // Log.d("totalItem", total_item + "");

                            _subtotal = Double.parseDouble(holder.price.getText().toString()) * total_item;
                            cartList.get(i).setMeasurement(Integer.valueOf(holder.quantity.getText().toString()));
                            cartList.get(i).setSubTotal(_subtotal);
                            holder.subTotal.setText(""+_subtotal);
                            String cartStr = gson.toJson(cartList);
                            //Log.d("CART", cartStr);
                            localStorage.setCart(cartStr);
                            ((CartActivity) context).updateTotalPrice();
                        }
                    }
                }


            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                pQuantity = Integer.parseInt(holder.quantity.getText().toString());
                if (pQuantity != 1) {
                    int total_item = Integer.parseInt(holder.quantity.getText().toString());
                    total_item--;
                    holder.quantity.setText(total_item + "");
                    for (int i = 0; i < cartList.size(); i++) {
                        if (cartList.get(i).getProductId()==cart.getProductId()) {

                            //holder.quantity.setText(total_item + "");
                            //Log.d("totalItem", total_item + "");
                            _subtotal = Double.parseDouble(holder.price.getText().toString()) * total_item;
                            cartList.get(i).setMeasurement(Integer.valueOf(holder.quantity.getText().toString()));
                            cartList.get(i).setSubTotal(_subtotal);
                            holder.subTotal.setText(""+_subtotal);
                            String cartStr = gson.toJson(cartList);
                            //Log.d("CART", cartStr);
                            localStorage.setCart(cartStr);
                            ((CartActivity) context).updateTotalPrice();

                        }
                    }

                }
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cartList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, cartList.size());
                Gson gson = new Gson();
                String cartStr = gson.toJson(cartList);
                Log.d("CART", cartStr);
                localStorage.setCart(cartStr);
                ((CartActivity) context).updateTotalPrice();


            }
        });


    }

    @Override
    public int getItemCount() {

        return cartList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        ProgressBar progressBar;
        CardView cardView;
        TextView offer, currency, price, quantity, attribute, addToCart, subTotal;
        ImageView plus, minus;
        Button delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.product_image);
            title = itemView.findViewById(R.id.product_title_tolov);
            quantity = itemView.findViewById(R.id.quantity);
            currency = itemView.findViewById(R.id.product_currency);
            attribute = itemView.findViewById(R.id.product_attribute);
            plus = (ImageView) itemView.findViewById(R.id.quantity_plus);
            minus = itemView.findViewById(R.id.quantity_minus);
            delete = itemView.findViewById(R.id.cart_delete);
            subTotal = itemView.findViewById(R.id.sub_total);
            price = itemView.findViewById(R.id.product_price);
        }
    }
}