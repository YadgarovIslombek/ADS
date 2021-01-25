package com.A1tech.ApiClient;

public class Constats {
    public static final String SIGN_UP = "login?action=create_client_by_mobile";                    //Regestratsiya
    public static final String LOGIN = "login?action=get_client_by_user_name";                      //Login qiladi
    public static final String ALL_PRODUCT_TYPE = "group?action=get_product_group_all";            //bazadaki barcha product_typeni oladi
    public static final String PRODUCT_BY_GROUP_ID = "product?action=get_products_by_group_id";     // productni GroupId boyicha oladi
    public static final String TYPE_BY_ID = "group?action=get_product_group_by_type_id";                   // Typni id bo'yicha oladi. ex:Oziq-ovqat, Xo'jalik mollari...
}
