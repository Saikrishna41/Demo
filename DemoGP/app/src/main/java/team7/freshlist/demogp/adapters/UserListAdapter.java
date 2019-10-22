package team7.freshlist.demogp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import team7.freshlist.demogp.R;
import team7.freshlist.demogp.models.User;

public class UserListAdapter  extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private ArrayList<String> mUsers = new ArrayList<>();
    private ArrayList<String> mUsersImg = new ArrayList<>();
    Context context;

    User us = new User();

    public UserListAdapter( ArrayList<String > mUsers, ArrayList<String> mUsersImg, Context context) {
        this.mUsers = mUsers;
        this.mUsersImg = mUsersImg;
        this.context = context;


    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_userlist,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


            holder.userName.setText(mUsers.get(position));
            Glide.with(this.context)
                .load(mUsersImg.get(position))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.userImage);

//       Picasso.get().load(mUsersImg.get(position)).into(holder.userImage);
         // holder.userImage.setImageURI(Uri.parse(mUsersImg.get(position)));
//            Picasso.get().load(mUsersImg.get(position)).into();




    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView userImage;
        TextView userName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.uimg);
            userName = itemView.findViewById(R.id.name);
        }
    }


}
