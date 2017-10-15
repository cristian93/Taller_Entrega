package com.i053114.taller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.i053114.taller.Adapter.AdapterComent;
import com.i053114.taller.Adapter.AdapterPost;
import com.i053114.taller.Connection.HttpManagerUser;
import com.i053114.taller.Models.Coment;
import com.i053114.taller.Models.Posts;
import com.i053114.taller.Parser.JsonComment;
import com.i053114.taller.Parser.JsonPost;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by chris on 14/10/2017.
 */

public class MainActivityPosts extends AppCompatActivity {


    ProgressBar progressBarPhoto;
    RecyclerView recyclerViewPhoto;
    List<Posts> postsList;
    AdapterPost adapterPost;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts);


        //toolbar = (Toolbar) findViewById(R.id.id_tb_toolbar);
        showToolbar(getResources().getString(R.string.List_post),true);

        progressBarPhoto = (ProgressBar) findViewById(R.id.id_pb_item);
        recyclerViewPhoto = (RecyclerView) findViewById(R.id.id_rv_item);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewPhoto.setLayoutManager(linearLayoutManager);

        loadData(Integer.toString(getIntent().getExtras().getInt("userId")));

        // loadData();

        //String valor = Integer.toString(getIntent().getExtras().getInt("albumId"));
        //Toast.makeText(this, valor, Toast.LENGTH_SHORT).show();



    }

    public void showToolbar (String title , boolean  upButton){
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle(title);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);


    }


    public void  actualizarpost  (){

        loadData(Integer.toString(getIntent().getExtras().getInt("userId")));

    }

    @Override

    public boolean onCreateOptionsMenu (Menu menu){
       getMenuInflater().inflate(R.menu.menu, menu);
        return  true;
    }


    @Override
    public boolean onOptionsItemSelected (MenuItem item){

        actualizarpost();
        return super.onOptionsItemSelected(item);
    }

    public Boolean isOnLine(){
        // Hacer llamado al servicio de conectividad utilizando el ConnectivityManager
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Obtener el estado de la conexion a internet en el dispositivo
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // Validar el estado obtenido de la conexion
        if (networkInfo != null){
            return true;
        }else {
            return false;
        }
    }



    public void loadData(String userId){
        if (isOnLine()){
            TaskPhoto taskPhoto = new TaskPhoto();
            taskPhoto.execute("https://jsonplaceholder.typicode.com/posts?userId=" + userId);
        }else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }

    public  void  processData(){
        adapterPost = new AdapterPost( postsList, getApplicationContext());
        recyclerViewPhoto.setAdapter(adapterPost);

    }


    public class TaskPhoto extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBarPhoto.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... strings) {
            String content = null;
            try {
                content = HttpManagerUser.getData(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                postsList = JsonPost.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            processData();



            progressBarPhoto.setVisibility(View.GONE);
        }
    }
}
