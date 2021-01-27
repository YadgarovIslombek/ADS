package com.A1tech.Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.A1tech.ADS.R;
import com.A1tech.Adapter.ProductGroupAdapter;
import com.A1tech.ApiClient.RetrofitClient;
import com.A1tech.Helper.LocalStorage;
import com.A1tech.JsonResponseProductGroup;
import com.A1tech.Model.Client;
import com.A1tech.fragments.ComunicateFragment;
import com.A1tech.fragments.HomeFragment;
import com.A1tech.fragments.OrderFragment;
import com.A1tech.fragments.ProfileFragment;
import com.A1tech.fragments.TestFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.mahfa.dnswitch.DayNightSwitch;
import com.mahfa.dnswitch.DayNightSwitchAnimListener;
import com.mahfa.dnswitch.DayNightSwitchListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.SYSTEM_UI_FLAG_VISIBLE;


public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener   {
    private CardView card_btn;
    boolean doubleBackToExitPressedOnce = false;
    private static int cart_count = 0;
    Client client;
    SharedPreferences preferences;
    LocalStorage localStorage;
    View mDecorView;
    private DayNightSwitch day_night_switch;
    boolean isFullScreen = false;
    @SuppressLint("ResourceAsColor")

    static void centerToolbarTitle(@NonNull final Toolbar toolbar) {
        final CharSequence title = toolbar.getTitle();
        final ArrayList<View> outViews = new ArrayList<>(1);
        toolbar.findViewsWithText(outViews, title, View.FIND_VIEWS_WITH_TEXT);
        if (!outViews.isEmpty()) {
            final TextView titleView = (TextView) outViews.get(0);
            titleView.setGravity(Gravity.CENTER);
            titleView.setTextColor(Color.parseColor("#FFFFFF"));
            final Toolbar.LayoutParams layoutParams = (Toolbar.LayoutParams) titleView.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            toolbar.requestLayout();
            //also you can use titleView for changing font: titleView.setTypeface(Typeface);
        }
    }

    @Override
    public void onBackPressed() {
       /* FrameLayout fl = (FrameLayout) findViewById(R.id.content_frame);
        if (fl.getChildCount() == 1) {
            super.onBackPressed();
            if (fl.getChildCount() == 0) {
                new AlertDialog.Builder(this)
                        .setTitle("Close App?")
                        .setMessage("Do you really want to close this beautiful app?")
                        .setPositiveButton("YES",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        finish();
                                    }
                                })
                        .setNegativeButton("NO",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                    }
                                }).show();
                // load your first Fragment here
            }
        } else if (fl.getChildCount() == 0) {
            // load your first Fragment here
        } else {
            super.onBackPressed();
        }*/
       DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        assert  drawer !=null;
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        }
//
//        else if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
//        }
//        else {
//            doubleBackToExitPressedOnce = true;
//            Toast.makeText(this, "Chiqish uchun yana bir marta bosing!", Toast.LENGTH_SHORT).show();
//
//
//            new Handler(getMainLooper()).postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    doubleBackToExitPressedOnce = false;
//                }
//            }, 2000);
//        }
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else{
            new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Chiqish")
                    .setMessage("Dasturdan chiqmoqchimisiz?")
                    .setPositiveButton("ha", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    }).setNegativeButton("yo'q", null).show();}
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        centerToolbarTitle(toolbar);
        localStorage = new LocalStorage(this);

        localStorage = new LocalStorage(getApplicationContext());
        String userString = localStorage.getUserLogin();
        Gson gson = new Gson();
        userString = localStorage.getUserLogin();
        client = gson.fromJson(userString, Client.class);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //day_night_switch =(DayNightSwitch)findViewById(R.id.sw);
       // day_night_switch.setDuration(450);
        localStorage = new LocalStorage(getApplicationContext());
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
       final LinearLayout holder = findViewById(R.id.holder);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        {
            @Override
            public void onDrawerSlide (View drawerView,float slideOffset) {
                float scaleFactor = 7f;
                float slideX = drawerView.getWidth() * slideOffset;

                holder.setTranslationX(slideX);
                holder.setScaleX(1 - (slideOffset / scaleFactor));
                holder.setScaleY(1 - (slideOffset / scaleFactor));

                super.onDrawerSlide(drawerView, slideOffset);
            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
//            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);// will remove all possible our aactivity's window bounds
        }

        drawer.addDrawerListener(toggle);

        drawer.setScrimColor(Color.TRANSPARENT);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
       View hView = navigationView.getHeaderView(0);  TextView nav_user = hView.findViewById(R.id.nav_header_name);
        LinearLayout nav_footer = findViewById(R.id.footer_text);
        if (client != null) {
            nav_user.setText(client.getUserName());
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

