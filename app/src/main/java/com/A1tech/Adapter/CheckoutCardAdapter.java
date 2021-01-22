package com.A1tech.Adapter;

import android.content.Context;
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
import com.A1tech.Helper.LocalStorage;
import com.A1tech.Model.Cart;
import com.google.gson.Gson;
import java.util.List;
public class CheckoutCardAdapter extends RecyclerView.Adapter<CheckoutCardAdapter.MyViewHolder>{
    List<Cart> cartList;
    Context context;
    int pQuantity = 1;
    String _subtotal, _price, _quantity;
    LocalStorage localStorage;
    Gson gson;
    public CheckoutCardAdapter(List<Cart> cartList, Context context) {
        this.cartList = cartList;
        this.context = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_tolovproductlist, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Cart cart = cartList.get(position);
        localStorage = new LocalStorage(context);
        gson = new Gson();
        holder.title.setText(cart.getProductName());
        holder.attribute.setText(cart.getUnitName());
        _price = String.valueOf(cart.getPrice());
        _quantity = String.valueOf(cart.getMeasurement());
        holder.quantity.setText(_quantity);
        holder.price.setText(_price);
//        holder.currency.setText(cart.getCurrency());
        _subtotal = String.valueOf(Double.parseDouble(_price) * Integer.parseInt(_quantity));
//        holder.currency.setText(cart.getCurrency());
        holder.subTotal.setText(_subtotal);
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
        Button plus, minus, delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.product_image_tolov);
            title = itemView.findViewById(R.id.product_title_tolov);
            quantity = itemView.findViewById(R.id.quantity_tolov);
            currency = itemView.findViewById(R.id.product_currency_tolov);
            attribute = itemView.findViewById(R.id.product_attribute_tolov);
//            plus = itemView.findViewById(R.id.quantity_plus);
//            minus = itemView.findViewById(R.id.quantity_minus);
//            delete = itemView.findViewById(R.id.cart_delete);
            subTotal = itemView.findViewById(R.id.sub_total_tolov);
            price = itemView.findViewById(R.id.product_price_tolov);
        }
    }
}