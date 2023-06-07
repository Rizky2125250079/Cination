package our.if4b.cination;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiService {
    @FormUrlEncoded
    @GET("post")
    Call<ValueData<Post>> getPost();

    @FormUrlEncoded
    @POST("auth/login")
    Call<ValueData<User>> login(@Field("username") String username,
                            @Field("password") String password);


    @FormUrlEncoded
    @POST("auth/register")
    Call<ValueData<User>>  register(@Field("username") String username,
                               @Field("password") String password);

    @FormUrlEncoded
    @POST("post")
    Call<ValueNoData> addPost(@Field("negara") String negara,
                              @Field("ibukota") String ibukota);

    @FormUrlEncoded
    @PUT("post")
    Call<ValueNoData> updatePost(@Field("id") String id,
                                 @Field("negara") String negara,
                                 @Field("ibukota") String ibukota);

    @FormUrlEncoded
    @DELETE("post")
    Call<ValueNoData> deletePost(@Field("id") String id);

}
