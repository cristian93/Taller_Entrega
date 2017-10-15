package com.i053114.taller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.i053114.taller.Adapter.AdapterUser;
import com.i053114.taller.Connection.HttpManagerUser;
import com.i053114.taller.Models.User;
import com.i053114.taller.Parser.JsonUser;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Button button;
     RecyclerView recyclerView;
   List<User> userList;
     AdapterUser adapterUser;
    Toolbar toolbar2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);


        //toolbar = (Toolbar)findViewById(R.id.id_tb_toolbar2);

        showToolbar(getResources().getString (R.string.List_post));

        progressBar = (ProgressBar) findViewById(R.id.id_pb_item);

        recyclerView =(RecyclerView) findViewById(R.id.id_rv_item);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        loadData();



    }
    public void showToolbar (String title ){
        //setSupportActionBar(toolbar2);
        //getSupportActionBar().setTitle(title);



    }


    public void  actualizarpost  (){

       // loadData();

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




    public Boolean isOnLine() {
        // Hacer llamado al servicio de conectividad utilizando el ConnectivityManager
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Obtener el estado de la conexion a internet en el dispositivo
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // Validar el estado obtenido de la conexion
        if (networkInfo != null) {
            return true;
        } else {
            return false;
        }
    }

    public void loadData() {
        if (isOnLine()) {
            TaskUser taskUser = new TaskUser();
            taskUser.execute("http://jsonplaceholder.typicode.com/users");
        } else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }

    public void processData() {
        adapterUser = new AdapterUser(userList, getApplicationContext());
        recyclerView.setAdapter(adapterUser);


    }

    public  class TaskUser extends AsyncTask<String,String,String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }


        @Override
        protected String doInBackground(String... strings) {
            String content = null;
            try {
                content = HttpManagerUser.getData(strings[0]);
            }catch (IOException e){

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
                userList = JsonUser.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            processData();

            progressBar.setVisibility(View.GONE);
        }
    }

}
