package com.uog.fyp.e.learning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.uog.fyp.e.learning.adapter.Customadapter;
import com.uog.fyp.e.learning.model.Retrophoto;
import com.uog.fyp.e.learning.network.RetrofitClientIntstance;
import com.uog.fyp.e.learning.service.GetDataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Customadapter adapter;
    private RecyclerView recyclerView;
    int albumId;
    String url;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading.....");
        progressDialog.show();

        GetDataService service= RetrofitClientIntstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Retrophoto>> call=service.getAllPhotos();
        call.enqueue(new Callback<List<Retrophoto>>() {
            @Override
            public void onResponse(Call<List<Retrophoto>> call, Response<List<Retrophoto>> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Retrophoto>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please Try later!", Toast.LENGTH_SHORT).show();
            }
        });
       Retrophoto retrophoto=new Retrophoto(albumId,url);
       Call<List<Retrophoto>> call1=service.createPost(retrophoto);
       call1.enqueue(new Callback<List<Retrophoto>>() {
           @Override
           public void onResponse(Call<List<Retrophoto>> call1, Response<List<Retrophoto>> response) {

           }

           @Override
           public void onFailure(Call<List<Retrophoto>> call1, Throwable t) {

           }
       });

    }
    private void generateDataList(List<Retrophoto> photoList){
        recyclerView = findViewById(R.id.custom_recyclerView);
        adapter = new Customadapter(this,photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }



}