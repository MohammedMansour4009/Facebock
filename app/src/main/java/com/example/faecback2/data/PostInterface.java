package com.example.faecback2.data;

import com.example.faecback2.pojo.PostModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PostInterface {
    @GET("posts")
//    public Call<List<PostModel>> getPost (); //commit because i need to convert to RXJava
    public Observable<List<PostModel>> getPost ();  // u can change Observable to Single


}
