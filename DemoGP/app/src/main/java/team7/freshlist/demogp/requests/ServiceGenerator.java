package team7.freshlist.demogp.requests;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import team7.freshlist.demogp.utils.Constants;

public class ServiceGenerator {

    private static Retrofit.Builder retrofitbuilder = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitbuilder.build();


    private static UserApi userApi = retrofit.create(UserApi.class);

    private static UserPost createUser = retrofit.create(UserPost.class);


    public static UserApi getUserApi() {
        return userApi;
    }

    public static UserPost createUsers() {
        return createUser;
    }



}
