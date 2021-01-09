package com.A1tech.ApiClient;
import com.A1tech.JsonResponseProduct;
import com.A1tech.JsonResponseProducttype;
import com.A1tech.Model.CategoryResult;
import com.A1tech.Model.ClientResponse;
import com.A1tech.Model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
public interface InterfacesApi {
    @GET("products")
    Call<JsonResponseProduct> getAllProducts();

//    @GET("/group?action=get_product_group_all") //bazadaki barcha product_groupni oladi
//    Call<JsonResponseProducttype>getProductType();

//    @GET("product_type") //bazadaki barcha product_groupni oladi
//    Call<JsonResponseProducttype>getProductType();

    @GET("group?action=get_product_group_all") //bazadaki barcha product_groupni oladi
    Call<JsonResponseProducttype>getProductType();

//    @GET("products/byId/{id}")
//    Call<CategoryResult> getProductById(@Path("id") int productTypeId);

    @GET("product?action=get_products_by_group_id&groupId{id}")
    Call<CategoryResult> getProductById(@Path("id") int productTypeId);

    @Headers("Content-Type: application/json")
    @POST("login?action=create_client_by_mobile")
    Call<ClientResponse> createUser(
           @Query("userName") String userName,
           @Query("password") String password,
           @Query("phoneNumber") String phoneNumber
           );
    @Headers("Content-Type: application/json")
    @POST("login?action=get_client_by_user_name")
        Call<ClientResponse> loginUser(
                @Query("password") String password,
                @Query("phoneNumber") String phoneNumber
        );



//   @FormUrlEncoded
//   @POST("login?action=create_client_by_mobile&userName=?&password=?&phoneNumber=?")
//    Call<JsonResponseSignUp> getSavaPost(@Body JsonResponseSignUp jsonResponseSignUp);

}
