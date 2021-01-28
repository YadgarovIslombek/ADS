package com.A1tech.ApiClient;
import com.A1tech.JsonResponseProductGroup;
import com.A1tech.JsonResponseType;
import com.A1tech.Model.CategoryResult;
import com.A1tech.Model.ClientResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import static com.A1tech.ApiClient.Constats.ALL_PRODUCT_TYPE;
import static com.A1tech.ApiClient.Constats.LOGIN;
import static com.A1tech.ApiClient.Constats.SIGN_UP;
import static com.A1tech.ApiClient.Constats.PRODUCT_BY_GROUP_ID;
import static com.A1tech.ApiClient.Constats.TYPE_BY_ID;

public interface InterfacesApi {
    @GET(TYPE_BY_ID)
    Call<JsonResponseProductGroup> getTypeById(
            @Query("Id") int typeID);
    @GET(ALL_PRODUCT_TYPE)
    Call<JsonResponseProductGroup>getProductGroup();
    @GET(PRODUCT_BY_GROUP_ID)
    Call<CategoryResult> getProductById
            (@Query("groupId") int productGroupId);
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
