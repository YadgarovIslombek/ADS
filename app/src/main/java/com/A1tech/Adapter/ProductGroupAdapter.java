package com.A1tech.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.A1tech.ADS.R;
import com.A1tech.Activity.ProductActivity;
import com.A1tech.Model.ProductModel;
import com.A1tech.Model.ProductType;

import java.util.List;

public class ProductGroupAdapter extends RecyclerView.Adapter<ProductGroupAdapter.MyViewHolder> {
    List<ProductType> productTypeList;
    List<ProductModel>productModelList;
    Context context;

    ProductModel productModel;
//    public MyOnClickListener myOnClickListener;

    public ProductGroupAdapter(List<ProductType> productTypeList, Context context) {
        this.productTypeList = productTypeList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductGroupAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.producttype_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductGroupAdapter.MyViewHolder holder, int position) {

        final ProductType productType = productTypeList.get(position);

        //db = new DB();
        /*holder.idMahsulotGuruhi.setText(Integer.toString(productTypeList.get(position).getIdMahsulotGuruhi()));
        holder.mahsulotTuriId.setText(Integer.toString(productTypeList.get(position).getMahsulotTuriId()));*/
        holder.productName.setText(productType.getProductGroupName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setOnClickListener(this);
                  Intent intent = new Intent(context, ProductActivity.class);
                  intent.putExtra("category", productType.getProducTypeId());
                Log.e("CATEGORY","QAYSI ID  "+ productType.getProducTypeId());
                  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                  context.startActivity(intent);




            }
        });

    }

    @Override
    public int getItemCount() {
        return productTypeList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  {
        public TextView productName;
        CardView cardView;
        RatingBar ratingBar;
//        MyOnClickListener myOnClickListener;

        public MyViewHolder(@NonNull View view) {
            super(view);
            productName = (TextView) view.findViewById(R.id.txt_name1);
            cardView =(CardView)view.findViewById(R.id.card_view12);
          /*  view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(){
                        Intent intent = new Intent(view.getContext(), ProductActivity.class);
                        view.getContext().startActivity(intent);
                    }
                    }
            });*/
      /*      view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            myOnClickListener.OnClick();
        }
    }

    public interface MyOnClickListener {
        void OnClick();
    }*/

        }
    }
}
