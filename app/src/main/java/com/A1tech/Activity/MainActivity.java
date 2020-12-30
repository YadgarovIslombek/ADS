package com.A1tech.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.A1tech.ADS.R;
import com.A1tech.Helper.Converter;
import com.A1tech.Model.User;
import com.A1tech.fragments.ComunicateFragment;
import com.A1tech.fragments.HomeFragment;
import com.A1tech.fragments.OrderFragment;
import com.A1tech.fragments.ProfileFragment;
import com.A1tech.fragments.TestFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;


public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener   {
    private CardView card_btn;
    private static int cart_count = 0;
    User user;

    static void centerToolbarTitle(@NonNull final Toolbar toolbar) {
        final CharSequence title = toolbar.getTitle();
        final ArrayList<View> outViews = new ArrayList<>(1);
        toolbar.findViewsWithText(outViews, title, View.FIND_VIEWS_WITH_TEXT);
        if (!outViews.isEmpty()) {
            final TextView titleView = (TextView) outViews.get(0);
            titleView.setGravity(Gravity.CENTER_HORIZONTAL);
            titleView.setTextColor(Color.parseColor("#FFFFFF"));
            final Toolbar.LayoutParams layoutParams = (Toolbar.LayoutParams) titleView.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            toolbar.requestLayout();
            //also you can use titleView for changing font: titleView.setTypeface(Typeface);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        MenuItem menuItem = menu.findItem(R.id.cart_action);
//        menuItem.setIcon(Converter.convertLayoutToImage(MainActivity.this, cart_count, R.drawable.ic_baseline_shopping_cart_24));
//        final MenuItem searchItem = menu.findItem(R.id.action_search);
//        return true;
//    }
   // @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.cart_action:
//                startActivity(new Intent(getApplicationContext(), CartActivity.class));
//                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        centerToolbarTitle(toolbar);
        cart_count = cartCount();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView = navigationView.getHeaderView(0);

        TextView nav_user = hView.findViewById(R.id.nav_header_name);
        LinearLayout nav_footer = findViewById(R.id.footer_text);
        if (user != null) {
            nav_user.setText(user.getUserName());
        }
        nav_footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                localStorage.logoutUser();
                startActivity(new Intent(getApplicationContext(), LoginRegister.class));
                finish();
                overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right);
                // Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_LONG).show();
            }
        });

        displaySelectedScreen(R.id.nav_home);
    }

    private void displaySelectedScreen(int itemId) {
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_myshipping:
                fragment = new OrderFragment();
                break;
            case R.id.nav_mylocation:
                fragment = new TestFragment();
                break;
            case R.id.nav_personal:
                fragment = new ProfileFragment();
                break;
//            case R.id.nav_operator:
//                fragment = new TestFragment();
//                break;
            case R.id.nav_aloqa:
                fragment = new ComunicateFragment();
                break;

//            case R.id.nav_offers:
//                fragment = new OffrersFragment();
//                break;
//
//            case R.id.nav_my_order:
//                fragment = new MyOrderFragment();
//                break;
//            case R.id.nav_my_cart:
//                startActivity(new Intent(getApplicationContext(), CartActivity.class));
//                overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
//                break;
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left);
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //calling the method displayselectedscreen and passing the id of selected menu
        displaySelectedScreen(item.getItemId());
        //make this method blank
        return true;
    }
    @Override
    public void onAddProduct() {
        super.onAddProduct();
        cart_count++;
        invalidateOptionsMenu();

    }

    @Override
    public void onRemoveProduct() {
        super.onRemoveProduct();
    }
}

//         toolbar1 = (Toolbar)findViewById(R.id.toolbar_main);
//        setSupportActionBar(toolbar1);
//        slidingRootNav = new SlidingRootNavBuilder(this)
//                .withDragDistance(100)
//                .withRootViewScale(0.75f)
//                .withRootViewElevation(25)
//                .withToolbarMenuToggle(toolbar1)
//                .withMenuOpened(false)
//                .withContentClickableWhenMenuOpened(false)
//                .withSavedState(savedInstanceState)
//                .withMenuLayout(R.layout.drawer_menu)
//                .inject();
//
//        screenIcons = loadScreenIcons();
//        screenTitles = loadScreenTitles();
//
//        DrawerAdapter adapter = new DrawerAdapter(Arrays.asList(
//                createItemFor(POS_CLOSE),
//                createItemFor(POS_DASHBOARD).setChecked(true),
//                createItemFor(POS_MY_PROFILE),
//                createItemFor(POS_NEARBY_RES),
//                createItemFor(POS_SETTINGS),
//                createItemFor(POS_ABOUT_US),
//                new SpaceItem(260),
//                createItemFor(POS_LOGOUT)
//        ));
//        adapter.setListener(this);
//
//        RecyclerView recyclerView = findViewById(R.id.drawer_list);
//        recyclerView.setNestedScrollingEnabled(false);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
//
//        adapter.setSelected(POS_DASHBOARD);










//    private DrawerItem createItemFor(int position) {
//        return new SimpleItem(screenIcons[position], screenTitles[position])
//                .withIconTint(color(R.color.ADSColor))
//                .withTextTint(color(R.color.tint))
//                .withSelectedTextTint(color(R.color.ADSColor))
//                .withSelectedTextTint(color(R.color.ADSColor));
//    }
//
//    @ColorInt
//    private int color(@ColorRes int res) {
//        return ContextCompat.getColor(this, res);
//    }
//
//    private String[] loadScreenTitles() {
//        return getResources().getStringArray(R.array.id_activityScreenTitles);
//    }
//
//    private Drawable[] loadScreenIcons() {
//        TypedArray ta = getResources().obtainTypedArray(R.array.id_activityScreenTIcons);
//        Drawable[] icons = new Drawable[ta.length()];
//        for (int i = 0; i < ta.length(); i++) {
//            int id = ta.getResourceId(i, 0);
//            if (id != 0) {
//                icons[i] = ContextCompat.getDrawable(this, id);
//
//            }
//        }
//        ta.recycle();
//        return icons;
//    }
//
//    @Override
//    public void onBackPressed() {
//        finish();
//    }
//
//
//    @Override
//    public void onItemSelected(int position) {
//        FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
//
//        if(position == POS_DASHBOARD){
//            DashboardFragment dashboardFragment = new DashboardFragment();
//            transaction.replace(R.id.container,  dashboardFragment);
//        }else if (position == POS_MY_PROFILE){
//            DashboardFragment dashboardFragment = new DashboardFragment();
//            transaction.replace(R.id.container,  dashboardFragment);
//        }else if (position == POS_NEARBY_RES){
//            DashboardFragment dashboardFragment = new DashboardFragment();
//            transaction.replace(R.id.container,  dashboardFragment);
//        }else if (position == POS_SETTINGS){
//            DashboardFragment dashboardFragment = new DashboardFragment();
//            transaction.replace(R.id.container,  dashboardFragment);
//        }else if (position == POS_ABOUT_US){
//            DashboardFragment dashboardFragment = new DashboardFragment();
//            transaction.replace(R.id.container,  dashboardFragment);
//        }else if (position == POS_LOGOUT){
//            finish();
//        }
//        slidingRootNav.closeMenu();
//        transaction.addToBackStack(null);
//        transaction.commit();
//
//    }

