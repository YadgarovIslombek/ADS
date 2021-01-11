package com.A1tech.Helper;
import android.content.Context;
import android.content.SharedPreferences;
public class LocalStorage {
    private static final String KEY_FIREBASE_TOKEN = "firebaseToken";
    public static final String KEY_USER = "User";
    public static final String KEY_USER_ADDRESS = "user_address";

    private static final String IS_USER_LOGIN = "IsUserLoggedIn";


    private static LocalStorage instance = null;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int PRIVATE_MODE = 0;
    Context _context;

    public LocalStorage(Context context) {
        sharedPreferences = context.getSharedPreferences("Preferences", 0);
    }

    public static LocalStorage getInstance(Context context) {
        if (instance == null) {
            synchronized (LocalStorage.class) {
                if (instance == null) {
                    instance = new LocalStorage(context);
                }
            }
        }
        return instance;
    }

    public void createUserLoginSession(String user) {
        editor = sharedPreferences.edit();
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString(KEY_USER, user);
        editor.apply();
    }

    public String getUserLogin() {
        return sharedPreferences.getString(KEY_USER, "");
    }


    public void logoutUser() {
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public boolean checkLogin() {
        // Check login status
        return !this.isUserLoggedIn();
    }


    public boolean isUserLoggedIn() {
        return sharedPreferences.getBoolean(IS_USER_LOGIN, false);
    }

    public String getUserAddress() {
        if (sharedPreferences.contains(KEY_USER_ADDRESS))
            return sharedPreferences.getString(KEY_USER_ADDRESS, null);
        else return null;
    }


    public void setUserAddress(String user_address) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_ADDRESS, user_address);
        editor.apply();
    }

    public String getCart() {
        if (sharedPreferences.contains("CART"))
            return sharedPreferences.getString("CART", null);
        else return null;
    }


    public void setCart(String cart) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("CART", cart);
        editor.apply();
    }

    public void deleteCart() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("CART");
        editor.apply();
    }


    public String getOrder() {
        if (sharedPreferences.contains("ORDER"))
            return sharedPreferences.getString("ORDER", null);
        else return null;
    }


    public void setOrder(String order) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ORDER", order);
        editor.apply();
    }

    public void deleteOrder() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("ORDER");
        editor.apply();
    }


    public String getFirebaseToken() {
        return sharedPreferences.getString(KEY_FIREBASE_TOKEN, null);
    }

    public void setFirebaseToken(String firebaseToken) {
        editor = sharedPreferences.edit();
        editor.putString(KEY_FIREBASE_TOKEN, firebaseToken);
        editor.apply();
    }
}