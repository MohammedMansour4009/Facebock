package com.example.faecback2.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.faecback2.R;
import com.example.faecback2.databinding.ActivityMainBinding;
import com.example.faecback2.pojo.PostModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    PostModelView postModelView;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        postModelView = ViewModelProviders.of(this ).get(PostModelView.class);

        postModelView.getPosts();

        final PostsAdapter adapter =new PostsAdapter();
        binding.reMain.setLayoutManager(new LinearLayoutManager(this ));
        binding.reMain.setAdapter(adapter);


        postModelView.listMutableLiveData.observe(this, new Observer<List<PostModel>>() {
            @Override
            public void onChanged(List<PostModel> postModels) {
                adapter.setList(postModels);
            }
        });




    }
}