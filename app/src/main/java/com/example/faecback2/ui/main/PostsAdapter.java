package com.example.faecback2.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faecback2.R;
import com.example.faecback2.databinding.ActivityMainBinding;
import com.example.faecback2.databinding.PostRowItemBinding;
import com.example.faecback2.pojo.PostModel;

import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {
    List<PostModel> movieList=new ArrayList<>();



    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//inflate of layout and Components
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_row_item, null, false);
        return new PostsViewHolder(PostRowItemBinding.bind(view));
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {// put new data ever time
        PostModel postModel = movieList.get(position);
        holder.binding.tvBody.setText(postModel.getBody());
        holder.binding.tvTitle.setText(postModel.getTitle());
        holder.binding.tvUserId.setText(postModel.getId()+"");


    }


    @Override
    public int getItemCount() {
        return movieList.size();
    }

       public void setList(List<PostModel> moviesList) {
        this.movieList = moviesList;
        notifyDataSetChanged();
    }

    class PostsViewHolder extends RecyclerView.ViewHolder {//declare elements and but resources
        PostRowItemBinding binding;

        public PostsViewHolder(@NonNull PostRowItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


    }

}



