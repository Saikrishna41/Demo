package team7.freshlist.demogp.requests;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import team7.freshlist.demogp.models.CreateUsers;

public interface UserPost {

    @POST("api/users")
    Call<CreateUsers> postuserData(
            @Body
            CreateUsers user

    );

}
