package team7.freshlist.demogp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import team7.freshlist.demogp.adapters.UserListAdapter;
import team7.freshlist.demogp.models.User;
import team7.freshlist.demogp.requests.ServiceGenerator;
import team7.freshlist.demogp.requests.UserApi;
import team7.freshlist.demogp.requests.response.Api;
import team7.freshlist.demogp.requests.response.Value;
import team7.freshlist.demogp.userlistactivity.CreateUserActivity;

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    private RecyclerView mRecyclerView;
    private UserListAdapter mRecyclerViewAdapter;
    private ArrayList<String> mUsers = new ArrayList<>();
    private ArrayList<String> mUsersImg = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);

       // setSupportActionBar(toolbar);

        toolbar.setTitle("UserList");
        toolbar.inflateMenu(R.menu.menu);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.add) {

                    Intent intent = new Intent(getApplicationContext(), CreateUserActivity.class);
                    startActivity(intent);
                    finish();
                }
                return false;

            }
        });


        testRetrofitRequest();

        initRecyclerView();


    }

    private void testRetrofitRequest() {

        UserApi userApi = ServiceGenerator.getUserApi();

        Call<Api> responseCall = userApi
                .getUserdata();

        responseCall.enqueue(new Callback<Api>() {
            @Override
            public void onResponse(Call<Api> call, Response<Api> response) {
                assert response.body() != null;

                if(response.code() == 200) {
                    Api userList = response.body();
                    List<Value> uList = userList.data;

                    for(Value datum: uList){

                        User us = new User();
                        us.setFirst_name(datum.getFirstName());

                        us.setLast_name(datum.getLastName());
                        us.setAvatar(datum.getAvatar());
                        System.out.println("user list lastnames"+us.getLast_name());
                        mUsers.add(us.getFirst_name() +" "+ us.getLast_name());
                        mUsersImg.add(us.getAvatar());

                        System.out.println("muser array list contains"+mUsers.toString());


                    }

                        if(mUsers != null && mUsersImg != null) {
                             mRecyclerViewAdapter.notifyDataSetChanged();

                        }

                }
            }

            @Override
            public void onFailure(Call<Api> call, Throwable t) {

            }
        });


    }


    private void initRecyclerView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerViewAdapter = new UserListAdapter(mUsers, mUsersImg, this);


        mRecyclerView.setAdapter(mRecyclerViewAdapter);


    }

    }


