package com.example.faecback2.data;

import com.example.faecback2.pojo.PostModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsClient {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private PostInterface postInterface;
    private static PostsClient INSTANCE;

    public PostsClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())// convert return from  call of observable
                .build();
        postInterface = retrofit.create(PostInterface.class);

    }

    public static PostsClient getINSTANCE() {
        if (null == INSTANCE)
            INSTANCE = new PostsClient();
        return INSTANCE;
    }

//    public Call<List<PostModel>>getPost() {//commit because i need to convert to RXJava
//        return postInterface.getPost();
//    }
    public Observable<List<PostModel>> getPost() { // u can change Observable to Single
        return postInterface.getPost();
    }
}
