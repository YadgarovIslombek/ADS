package com.A1tech.Helper;

import com.A1tech.ApiClient.InterfacesApi;
import com.A1tech.ApiClient.RetrofitClient;

public class Utils {
//Email Validation pattern
    public static final String regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";

    //Fragments Tags
    public static final String Login_Fragment = "LoginFragment";
    public static final String SignUp_Fragment = "SignUpFragment";
    public static final String ForgotPassword_Fragment = "ForgotPassword_Fragment";
    public static final String CategoryImage = RetrofitClient.BASE_URL + "assets/images/ProductImage/category/";
    public static final String ProductImage = RetrofitClient.BASE_URL + "assets/images/ProductImage/product/";
}
