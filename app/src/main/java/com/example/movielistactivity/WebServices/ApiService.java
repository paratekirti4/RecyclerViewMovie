package com.example.movielistactivity.WebServices;

import java.util.List;

import com.example.movielistactivity.Model.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @Headers({
            "Accept:application/json",
            "Content-Type: application/json"
    })

    @GET(ApiConstant.MOVIES_URL)
    Call<List<MovieResponse>> movie_type();


}
