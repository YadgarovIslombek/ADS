package com.A1tech.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.A1tech.ADS.R;
import com.A1tech.Helper.LocalStorage;
import com.A1tech.Model.Order;
import com.google.gson.Gson;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {
    List<Order> orderList;
    Context context;
    int pQuantity = 1;
    String _subtotal, _price, _quantity;
    LocalStorage localStorage;
    Gson gson;
    public OrderAdapter(List<Order> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_order, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Order order = orderList.get(position);
        holder.orderId.setText("#" + order.getId());
        holder.date.setText(order.getDate());
        holder.total.setText((int) order.getSubTotal());
        holder.status.setText(order.getStatus());
    }

    @Override
    public int getItemCount() {

        return orderList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView orderId, date, total, status;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            orderId = itemView.findViewById(R.id.order_id);
            date = itemView.findViewById(R.id.date);
            total = itemView.findViewById(R.id.total_amount);
            status = itemView.findViewById(R.id.status);

        }
    }
}
