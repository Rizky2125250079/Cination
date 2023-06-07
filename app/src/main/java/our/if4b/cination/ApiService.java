package our.if4b.cination;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("getAllPost")
    Call<ValueData<List<Post>>> getPost();

    @FormUrlEncoded
    @POST("auth/login")
    Call<ValueData<User>> login(@Field("username") String username,
                            @Field("password") String password);


    @FormUrlEncoded
    @POST("auth/register")
    Call<ValueData<User>>  register(@Field("username") String username,
                               @Field("password") String password);

    @FormUrlEncoded
    @POST("insertPost")
    Call<ValueNoData> addPost(@Field("key") String key,
                              @Field("negara") String negara,
                              @Field("ibukota") String ibukota);

    @FormUrlEncoded
    @POST("updatePost")
    Call<ValueNoData> updatePost(@Field("id") String id,
                                 @Field("negara") String negara,
                                 @Field("ibukota") String ibukota);

    @FormUrlEncoded
    @POST("deletePost")
    Call<ValueNoData> deletePost(@Field("key") String key,
                                 @Field("id") String id);

}
