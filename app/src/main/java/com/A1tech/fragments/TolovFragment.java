package com.A1tech.fragments;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.A1tech.ADS.R;
import com.A1tech.Activity.BaseActivity;
import com.A1tech.Activity.CartActivity;
import com.A1tech.Activity.MainActivity;
import com.A1tech.Adapter.CheckoutCardAdapter;
import com.A1tech.Helper.LocalStorage;
import com.A1tech.Model.Cart;
import com.A1tech.Model.Order;
import com.A1tech.Model.OrderItem;
import com.A1tech.Model.PlaceOrder;
import com.A1tech.Model.Client;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
public class TolovFragment extends Fragment {
    LocalStorage localStorage;
    List<Cart> cartList = new ArrayList<>();
    Gson gson;
    RecyclerView recyclerView;
    CheckoutCardAdapter adapter;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    TextView back, order;
    TextView total, shipping, totalAmount;
    Double _total, _shipping, _totalAmount;
    ProgressDialog progressDialog;
    List<Order> orderList = new ArrayList<>();
    List<OrderItem> orderItemList = new ArrayList<>();
    PlaceOrder confirmOrder;
    String orderNo;
    String id;
    OrderItem orderItem = new OrderItem();
    RelativeLayout relativeLayout;
    FloatingActionButton button;
    FragmentManager fragmentManager;
    public TolovFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tolov, container, false);
        localStorage = new LocalStorage(getContext());
        recyclerView = view.findViewById(R.id.cart_rv);
        totalAmount = view.findViewById(R.id.total_amount);
        total = view.findViewById(R.id.total);
        shipping = view.findViewById(R.id.shipping_amount);
        back = view.findViewById(R.id.back);
        order = view.findViewById(R.id.place_order);
        relativeLayout = view.findViewById(R.id.dialog_chek);
        button = (FloatingActionButton) view.findViewById(R.id.btnCheck);
        progressDialog = new ProgressDialog(getContext());
        gson = new Gson();
        //orderList = ((BaseActivity) getActivity()).getOrderList();
        cartList = ((BaseActivity) getContext()).getCartList();
        Client client = gson.fromJson(localStorage.getUserLogin(), Client.class);
        _total = ((BaseActivity) getActivity()).getTotalPrice();
        total.setText(_total + "");
        shipping.setText(_shipping + "");
        totalAmount.setText(_totalAmount + "");

        for (int i = 0; i < cartList.size(); i++) {

            orderItem = new OrderItem(cartList.get(i).getProductName(), cartList.get(i).getMeasurement(), cartList.get(i).getUnitName(), cartList.get(i).getPrice(), cartList.get(i).getSubTotal());
            orderItemList.add(orderItem);
        }

        confirmOrder = new PlaceOrder(client.getUserName(), client.getPhoneNumber(), client.getEmail(), client.getClientId(), orderItemList);
//        String confirmOrder12 = gson.toJson(confirmOrder);
//        localStorage.setOrder(confirmOrder12);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Confirm Order==>", gson.toJson(confirmOrder));
                progressDialog.setMessage("Confirming Order...");
                progressDialog.show();
                relativeLayout.setVisibility(View.VISIBLE);
                relativeLayout.getScrollBarFadeDuration();
                button.setVisibility(View.VISIBLE);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//             OrderFragment orderFragment = new OrderFragment();
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
//                fragmentTransaction.replace(R.id.content_frame,orderFragment).commit();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CartActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });
        setUpCartRecyclerview();
        return view;
    }
    private void setUpCartRecyclerview() {
        recyclerView.setHasFixedSize(true);
        recyclerViewlayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(recyclerViewlayoutManager);
        adapter = new CheckoutCardAdapter(cartList, getContext());
        recyclerView.setAdapter(adapter);
    }

}