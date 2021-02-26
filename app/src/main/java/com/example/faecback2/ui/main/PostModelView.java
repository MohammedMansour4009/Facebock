package com.example.faecback2.ui.main;

import android.util.Log;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.faecback2.data.PostsClient;
import com.example.faecback2.pojo.PostModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PostModelView extends ViewModel {
    MutableLiveData<List<PostModel>> listMutableLiveData = new MediatorLiveData<>();//this save list post
    final private String TAG = "Post";
    CompositeDisposable compositeDisposable; // because => when finished the activity ,  destroy the Observable

    public void getPosts() {
//commit because i need to convert to RXJava
//            PostsClient.getINSTANCE().getPost().enqueue(new Callback<List<PostModel>>() {
//                @Override
//                public void onResponse(Call<List<PostModel>> call,Response<List<PostModel>> response) {
//                    listMutableLiveData.setValue(response.body());
//                }
//
//                @Override
//                public void onFailure(Call<List<PostModel>> call, Throwable t) {
//
//                }
//            });

        // u can change Observable to Single
        Observable<List<PostModel>> observable = PostsClient.getINSTANCE().getPost()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());//because setValue most have in main thread

        Observer<List<PostModel>> listObserver = new Observer<List<PostModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable .add(d);
            }

            @Override
            public void onNext(List<PostModel> postModels) {
                listMutableLiveData.setValue(postModels);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(listObserver);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    compositeDisposable.clear();
    }
}



