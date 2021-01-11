package com.A1tech.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.A1tech.ADS.R;
import com.A1tech.Activity.BaseActivity;
import com.A1tech.Adapter.OrderAdapter;
import com.A1tech.Helper.LocalStorage;
import com.A1tech.Model.Order;
import com.A1tech.Model.Client;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {
    LocalStorage localStorage;
    LinearLayout linearLayout;
    private List<Order> orderList = new ArrayList<>();
    Gson gson = new Gson();
    private RecyclerView recyclerView;
    private OrderAdapter mAdapter;
    BaseActivity baseActivity;
    Order order;
    private List<Order> newOrderList = new ArrayList<>();
    public OrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_order, container, false);
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        recyclerView = view.findViewById(R.id.order_rv);
        linearLayout = view.findViewById(R.id.no_order_ll);
        localStorage = new LocalStorage(getContext());

        Client client = gson.fromJson(localStorage.getUserLogin(), Client.class);
//        order = new Order(user.getId(), user.getToken());
//        fetchOrderDetails(order);
        setupOrderRecycleView();
        return view;
    }

    private void setupOrderRecycleView() {


        if (orderList.isEmpty()) {
            linearLayout.setVisibility(View.VISIBLE);
        } else {
            linearLayout.setVisibility(View.GONE);
        }
        mAdapter = new OrderAdapter(orderList, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }
}