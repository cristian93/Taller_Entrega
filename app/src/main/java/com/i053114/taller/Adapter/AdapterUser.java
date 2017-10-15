package com.i053114.taller.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.i053114.taller.MainActivity;
import com.i053114.taller.MainActivityComment;
import com.i053114.taller.MainActivityPosts;
import com.i053114.taller.Models.User;
import com.i053114.taller.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 13/10/2017.
 */

public class AdapterUser  extends RecyclerView.Adapter<AdapterUser.ViewHolder>{
    List<User> userDateList= new ArrayList<>();
    Context context;

    public AdapterUser (List<User> userDateList , Context context){
        this.userDateList=userDateList;
        this.context= context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main, parent,false);

        ViewHolder viewHolder= new ViewHolder(item);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewid.setText(Integer.toString(userDateList.get(position).getId() ) );
        holder.textViewname.setText(userDateList.get(position).getName());
        holder.textViewusername.setText(userDateList.get(position).getUsername());
        holder.textViewaddress.setText(userDateList.get(position).getAddress());
        holder.textViewcompany.setText(userDateList.get(position).getCompany());

        String [] images ;
        images  = new String[ 6];
        images [0]="https://scontent-mia3-1.xx.fbcdn.net/v/t31.0-8/16665649_1615500745133528_2665002130043165363_o.jpg?oh=4175018eaff30bcf6f900b7120c9aa8d&oe=5A4318F9";
        images [1]="images [0]=\"https://scontent-mia3-1.xx.fbcdn.net/v/t31.0-8/16665649_1615500745133528_2665002130043165363_o.jpg?oh=4175018eaff30bcf6f900b7120c9aa8d&oe=5A4318F9\";\n";
        images [2]="http://panoramanegro.com.ar/wp-content/uploads/2014/05/atletico_nacional.jpg";
        images [3]="https://i.pinimg.com/564x/b6/06/4d/b6064d3ae074842b02582109df6e3d66.jpg";
        images [4]="https://i.pinimg.com/236x/3d/43/5d/3d435d62a7669de0a5c26f7c3a25539e.jpg ";
        images [5]="https://i.pinimg.com/236x/7f/0d/a4/7f0da459cac924bc76183dc9caf7da6c.jpg";

        int number = (int ) (Math.random()*5);
        Picasso.with(context).load(images[number]).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return userDateList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView textViewid;
        TextView textViewname;
        TextView textViewusername;
        TextView textViewaddress;
        TextView textViewcompany;
        ImageView imageView;

        public ViewHolder (View item){
            super(item);
            item.setOnClickListener(this);

            textViewid=(TextView) item.findViewById(R.id.id_tv_userinput_name);
            textViewname =(TextView) item.findViewById(R.id.id_tv_userinput_name2);
            textViewusername= (TextView) item.findViewById(R.id.id_tv_userinput_email);
          textViewaddress=(TextView) item.findViewById(R.id.id_tv_userinput_phone);
           textViewcompany=(TextView) item.findViewById(R.id.id_tv_userinput_namecompany);
           imageView= (ImageView) item.findViewById(R.id. img_item_cardview);


        }


        @Override
        public void onClick(View view) {
            Context contextitem = view.getContext();
            Intent intent= new Intent( context, MainActivityPosts.class);
            intent.putExtra("userId", userDateList.get(getLayoutPosition()).getId());
            contextitem.startActivity(intent);
        }
    }

}