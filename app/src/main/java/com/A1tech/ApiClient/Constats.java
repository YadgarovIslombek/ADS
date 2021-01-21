package com.A1tech.ApiClient;

public class Constats {
    public static final String SIGN_UP = "login?action=create_client_by_mobile";                    //Regestratsiya
    public static final String LOGIN = "login?action=get_client_by_user_name";                      //Login qiladi
    public static final String ALL_PRODUCT_GROUP = "group?action=get_product_group_all";            //bazadaki barcha product_groupni oladi
    public static final String PRODUCT_BYTYPEID = "product?action=get_product_by_id{id}";  // productni TypeId boyicha oladi
}
