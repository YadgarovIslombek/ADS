package com.A1tech.Activity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import com.A1tech.ADS.R;
import com.A1tech.fragments.AddressFragment;
public class CheckoutActivity extends BaseActivity {
    Toolbar toolbar;
    TextView txt_tool;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        //Toolbar
        setSupportActionBar(toolbar);
        //Toolbarga text qo'ydim > put text in toolbar

        // this text
        //frame ni ichiga fragment chaqirdim > call fragment  inside frame in activity
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left);
        ft.replace(R.id.content_frame, new AddressFragment());
        ft.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                Intent intent = new Intent(CheckoutActivity.this, CartActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), CartActivity.class));
    }
}



//    View progress;
//    Cart cart;
//    EditText edt_qoshimcha;
//    RadioButton radio_naxt, radio_card;
//    TextView productprice, jami_price, price_dostavka,txt_address,lat,longt;
//    Button btn_tasdiq;
//    String _lat,_longt, _productprice, _txt_address;


// txt_address = (TextView) findViewById(R.id.txt_address);
//         edt_qoshimcha = (EditText)findViewById(R.id.edt_qosh);
//         productprice = (TextView) findViewById(R.id.mahsulotSummasi);
//         radio_naxt =  (RadioButton)findViewById(R.id.radioButton_naht);
//         radio_card =  (RadioButton)findViewById(R.id.radioButton_cart);
//         jami_price = (TextView)findViewById(R.id.JamiSummaPrice);
//         price_dostavka =(TextView)findViewById(R.id.DostavkaPrice);
//         btn_tasdiq =(Button)findViewById(R.id.btn_tasdiq);
//         lat = (TextView)findViewById(R.id.lat);
//         longt =(TextView)findViewById(R.id.longt);
//         toolbar = (Toolbar)findViewById(R.id.toolbar_payment);
//         setSupportActionBar(toolbar);
//         txt_tool =(TextView)findViewById(R.id.tool_title);
//         txt_tool.setText("Buyurtma");
//         txt_tool.setGravity(Gravity.CENTER);
//         Intent intent = getIntent();
//         _txt_address = intent.getStringExtra("address");
//         _lat = intent.getStringExtra("lat");
//         _longt = intent.getStringExtra("longtt");
//         _productprice = intent.getStringExtra("ProductNarxlar");
//         Log.e("test---",_productprice);
//         lat.setText(_lat);
//         longt.setText(_longt);
//         txt_address.setText(_txt_address);
//         productprice.setText(_productprice +"");