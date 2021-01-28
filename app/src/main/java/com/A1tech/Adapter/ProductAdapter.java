package com.A1tech.Adapter;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.A1tech.ADS.R;
import com.A1tech.Activity.BaseActivity;
import com.A1tech.Activity.ProductActivity;
import com.A1tech.ApiClient.AddorRemoveCallbacks;
import com.A1tech.Helper.LocalStorage;
import com.A1tech.Helper.Utils;
import com.A1tech.Model.Cart;
import com.A1tech.Model.ProductModel;
import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyviewHolder>  {
    List<ProductModel> productList = new ArrayList<>();
    Context context;
    String Tag;
    int pQuantity = 1;
    LocalStorage localStorage;
    Gson gson;
    List<Cart> cartList = new ArrayList<>();
    int _quantity, _price;
    double _subtotal;
    String _attribute;

    public ProductAdapter(List<ProductModel> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    public ProductAdapter(List<ProductModel> productList, Context context, String tag) {
        this.productList = productList;
        this.context = context;
        Tag = tag;
    }


    @NonNull
    @Override
    public ProductAdapter.MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_products, parent,false);
        return new ProductAdapter.MyviewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        final ProductModel product = productList.get(position);
        localStorage = new LocalStorage(context);
        gson = new Gson();
        cartList = ((BaseActivity) context).getCartList();
        holder.title.setText(product.getProductName());

        /*if (product.getPrice() != null && product.getPrice().length() != 0 && product.getDiscount() != null && product.getDiscount().length() != 0  ) {

            double M = Double.parseDouble(product.getPrice());
            double S = Double.parseDouble(product.getDiscount());
            double discount = M - S;

            int disPercent = (int) Math.round((discount / M) * 100);

            if (disPercent > 1) {
                holder.offer.setText(disPercent + "% OFF");
            } else {
                holder.offer.setVisibility(View.GONE);
            }

        } else {
            holder.offer.setVisibility(View.GONE);
        }
*/

        holder.attribute.setText("1 " +product.getUnitName());
        holder.currency.setText(" So'm");
        holder.price.setText(""+product.getPrice());
//        Picasso.get()
//                .load(Utils.ProductImage + product.getImage())
//                .into(holder.imageView, new Callback() {
//                    @Override
//                    public void onSuccess() {
//                        holder.progressBar.setVisibility(View.GONE);
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//                        Log.d("Error : ", e.getMessage());
//                    }
//                });

        if (!cartList.isEmpty()) {
            for (int i = 0; i < cartList.size(); i++) {
                if (cartList.get(i).getProductId()==product.getProductId()) {
                    holder.addToCart.setVisibility(View.GONE);
                    holder.constraintLayout.setVisibility(View.VISIBLE);
                    holder.quantity.setText(Integer.toString(cartList.get(i).getMeasurement()));
                    _quantity = cartList.get(i).getMeasurement();
                    _price = product.getPrice();
                    _subtotal = Double.parseDouble(String.valueOf(_price)) * Double.parseDouble(String.valueOf(_quantity));
                    holder.subTotal.setText(_quantity + "X" + _price + "= " + _subtotal + " So'm");
                    Log.e("Tag12 : ", cartList.get(i).getProductId() + "-->" + product.getProductId());
                }
            }
        } else {

            holder.quantity.setText("1");
        }

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pQuantity = Integer.parseInt(holder.quantity.getText().toString());
                if (pQuantity >= 1) {
                    int total_item = Integer.parseInt(holder.quantity.getText().toString());
                    total_item++;
                    holder.quantity.setText(total_item + "");
                    for (int i = 0; i < cartList.size(); i++) {

                        if (cartList.get(i).getProductId()==product.getProductId()) {

                            // Log.d("totalItem", total_item + "");

                            _subtotal = Double.parseDouble(holder.price.getText().toString()) * total_item;
                            cartList.get(i).setMeasurement(Integer.valueOf(holder.quantity.getText().toString()));
                            cartList.get(i).setSubTotal(_subtotal);
                            holder.subTotal.setText(total_item + "X" + holder.price.getText().toString() + "=" + _subtotal + " So'm");
                            String cartStr = gson.toJson(cartList);
                            //Log.d("CART", cartStr);
                            localStorage.setCart(cartStr);
                            notifyItemChanged(position);
                        }
                    }
                }

            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pQuantity = Integer.parseInt(holder.quantity.getText().toString());
                if (pQuantity != 1) {
                    int total_item = Integer.parseInt(holder.quantity.getText().toString());
                    total_item--;
                    holder.quantity.setText(total_item + "");
                    for (int i = 0; i < cartList.size(); i++) {
                        if (cartList.get(i).getProductId()==product.getProductId()) {

                            //holder.quantity.setText(total_item + "");
                            //Log.d("totalItem", total_item + "");
                            _subtotal = Double.parseDouble(holder.price.getText().toString()) * total_item;
                            cartList.get(i).setMeasurement(Integer.valueOf(holder.quantity.getText().toString()));
                            cartList.get(i).setSubTotal(_subtotal);
                            holder.subTotal.setText(total_item + "X" + holder.price.getText().toString() + "= " + _subtotal+ " So'm");
                            String cartStr = gson.toJson(cartList);
                            //Log.d("CART", cartStr);
                            localStorage.setCart(cartStr);
                            notifyItemChanged(position);
                        }
                    }

                }

            }
        });


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(context, ProductActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);*/
                Toast.makeText(context, "Mahsulot tanlangan", Toast.LENGTH_LONG).show();
            }
        });
        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                intent.putExtra("category", productType.getProductTypeId());
                holder.addToCart.setVisibility(View.GONE);
//                holder.subTotal.setVisibility(View.VISIBLE);
                holder.constraintLayout.setVisibility(View.VISIBLE);
                _price = product.getPrice();
                _quantity = (Integer.parseInt(holder.quantity.getText().toString()));
                _attribute = product.getUnitName();

                if (_quantity != 0) {
                    _subtotal = (_price) * (_quantity);
                    holder.subTotal.setText(_quantity + "X" + _price + "= " + _subtotal +" So'm");
                    if (context instanceof ProductActivity) {
                        Cart cart = new Cart(product.getProductId(), product.getProductName(),_price, _attribute, _quantity, _subtotal);
                        cartList = ((BaseActivity) context).getCartList();
                        cartList.add(cart);
                        String cartStr = gson.toJson(cartList);
                        //Log.d("CART", cartStr);
                        localStorage.setCart(cartStr);
                        ((AddorRemoveCallbacks) context).onAddProduct();
                    }
                } else {
                    Toast.makeText(context, "s", Toast.LENGTH_SHORT).show();
                }


            }
        });

       /* holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductViewActivity.class);
                intent.putExtra("id", product.getId());
                intent.putExtra("title", product.getName());
                intent.putExtra("image", product.getImage());
                intent.putExtra("price", product.getPrice());
                intent.putExtra("currency", product.getCurrency());
                intent.putExtra("attribute", product.getAttribute());
                intent.putExtra("discount", product.getDiscount());
                intent.putExtra("description", product.getDescription());


                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
            }
        });
*/
    }

    @Override
    public int getItemCount() {

        return productList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    public static class MyviewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        ProgressBar progressBar;
        CardView cardView;
        TextView offer, currency, price, quantity, attribute, addToCart, subTotal;
        ImageView plus, minus;
        ConstraintLayout constraintLayout;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.product_image);
            title = itemView.findViewById(R.id.product_title_tolov);
//            progressBar = itemView.findViewById(R.id.prog);
            cardView = itemView.findViewById(R.id.card_view);
//            offer = itemView.findViewById(R.id.product_discount);
            currency = itemView.findViewById(R.id.product_currency);
            price = itemView.findViewById(R.id.product_price);
            quantity = itemView.findViewById(R.id.quantity);
            addToCart = itemView.findViewById(R.id.add_to_cart);
            attribute = itemView.findViewById(R.id.product_attribute);
            plus = itemView.findViewById(R.id.quantity_plus);
            minus = itemView.findViewById(R.id.quantity_minus);
            subTotal = itemView.findViewById(R.id.sub_total);
            constraintLayout = itemView.findViewById(R.id.plyus_minus);
        }
    }
}