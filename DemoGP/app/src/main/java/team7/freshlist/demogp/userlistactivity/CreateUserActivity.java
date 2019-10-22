package team7.freshlist.demogp.userlistactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import team7.freshlist.demogp.BaseActivity;
import team7.freshlist.demogp.R;
import team7.freshlist.demogp.models.CreateUsers;
import team7.freshlist.demogp.requests.ServiceGenerator;
import team7.freshlist.demogp.requests.UserPost;

public class CreateUserActivity extends AppCompatActivity {
    private EditText mFirstName;
    private EditText mLastName;
    private Button mSubmit;
    private String fname, lname;
    private static final String TAG = "CreateUserActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_user);

        Toolbar toolbar = (Toolbar)findViewById(R.id.cutoolbar);

//        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));

        toolbar.setTitle("Create User");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BaseActivity.class);
                startActivity(intent);
                finish();


            }
        });

        setTitle("Create User");

        mFirstName = findViewById(R.id.firstnameid);
        mLastName = findViewById(R.id.lastnameid);
        fname = mFirstName.getText().toString();
        lname = mLastName.getText().toString();

        mSubmit = findViewById(R.id.btn);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(mFirstName.getText().toString().length() == 0) {

                  mFirstName.setError("First name field is required");
              }
              else if (mLastName.getText().toString().length() == 0) {
                    mLastName.setError("Last name is required");
                }
                if(mFirstName.getText().toString().length() > 0 && mLastName.getText().toString().length() > 0) {
                    postData();
                }
            }
        });

    }

    private void postData() {
        //calling our createuser retrofit service
        UserPost userPost = ServiceGenerator.createUsers();
        //passing our data to Create user model
        CreateUsers user = new CreateUsers(mFirstName.toString(), mLastName.toString());
        //passing our user data as a body to the retrofit post endpoint
        Call<CreateUsers> postCall = userPost.postuserData(user);

        postCall.enqueue(new Callback<CreateUsers>() {
            @Override
            public void onResponse(Call<CreateUsers> call, Response<CreateUsers> response) {
                Log.d(TAG, "onResponse: "+response.body());

                Toast.makeText(getApplicationContext(),"Response is"+String.valueOf(response.code()),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<CreateUsers> call, Throwable t) {

            }
        });

    }


}
