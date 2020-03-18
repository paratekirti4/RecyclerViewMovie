package com.example.movielistactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.movielistactivity.Adapter.MovieAdapter;
import com.example.movielistactivity.Model.MovieResponse;
import com.example.movielistactivity.R;
import com.example.movielistactivity.WebServices.ApiService;
import com.example.movielistactivity.WebServices.NetworkUtils;
import com.example.movielistactivity.WebServices.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {

    Context context = this;
    private ArrayList<MovieResponse> insType1List = new ArrayList<>();
    private MovieAdapter movieAdapter;
    private RecyclerView rvMovieType;
    public static final String TAG = "@Kirti";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        callMovieWS();

    }

    private void initUi() {
        rvMovieType = findViewById(R.id.rvMovieType);
    }


    private void callMovieWS() {
        final ProgressDialog pd = new ProgressDialog(context);
        pd.setMessage("Loading data please wait...");
        pd.show();
        pd.setCanceledOnTouchOutside(false);

        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<List<MovieResponse>> call = apiService.movie_type();
        Log.i(TAG, "MovieTypeUrl: " + call.request().url().toString());


        call.enqueue(new Callback<List<MovieResponse>>() {

            @Override
            public void onResponse
                    (Call<List<MovieResponse>> call, Response<List<MovieResponse>> response) {

                if (response.isSuccessful()) {
                    Log.i(TAG, "MovieTypeResp:" + response.toString() + response.code() + response.message());

                    //Set Gender Type Adapter on Listview view to display url data on it
                    // insTypeList=new ArrayList<>();
                    insType1List = (ArrayList<MovieResponse>) response.body();
                    movieAdapter = new MovieAdapter(context, insType1List);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    ((LinearLayoutManager) mLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
                    // insTypeList.setLayoutManager(mLayoutManager);
                    rvMovieType.setLayoutManager(mLayoutManager);
                    rvMovieType.setAdapter(movieAdapter);
                    movieAdapter.notifyDataSetChanged();

                    pd.dismiss();

                }

            }

            @Override
            public void onFailure(Call<List<MovieResponse>> call, Throwable t) {
                Log.i(TAG, "Error: " + t.toString());
                Toast.makeText(context, t.getMessage(), LENGTH_LONG).show();
                Toast.makeText(context, "Something went wrong", LENGTH_LONG).show();
                pd.dismiss();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!NetworkUtils.checkNetworkConnection(context)) {
            callMovieWS();
        } else {
            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }


}
