package com.A1tech.ApiClient;
import com.A1tech.JsonResponseProduct;
import com.A1tech.JsonResponseProductGroup;
import com.A1tech.Model.CategoryResult;
import com.A1tech.Model.ClientResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.A1tech.ApiClient.Constats.ALL_PRODUCT_GROUP;
import static com.A1tech.ApiClient.Constats.LOGIN;
import static com.A1tech.ApiClient.Constats.SIGN_UP;

public interface InterfacesApi {
    @GET(ALL_PRODUCT_GROUP)
    Call<JsonResponseProductGroup>getProductType();
//    @GET("product_type")
//    Call<CategoryResult> getProductById(@Path("id") int productTypeId);
//    @GET("product_type")
//    Call<JsonResponseProductGroup>getProductType();

    @GET("PRODUCT_BYTYPEID")
    Call<CategoryResult> getProductById(@Path("id") int productTypeId);

    @Headers("Content-Type: application/json")
    @POST(SIGN_UP)
    Call<ClientResponse> createUser(
           @Query("userName") String userName,
           @Query("password") String password,
           @Query("phoneNumber") String phoneNumber
           );
    @Headers("Content-Type: application/json")
    @POST(LOGIN)
        Call<ClientResponse> loginUser(
                @Query("password") String password,
                @Query("phoneNumber") String phoneNumber
        );

}
