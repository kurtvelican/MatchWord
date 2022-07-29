package com.mp.matchword.ScoreOperations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mp.matchword.R;

import java.util.ArrayList;

public class ScoreBoardAdapter extends RecyclerView.Adapter<ScoreBoardAdapter.ScoreBoardHolder> {

    Context context;
    ArrayList<ScoreInfo> scoreInfoArrayList;


    public ScoreBoardAdapter(Context context, ArrayList<ScoreInfo> scoreInfoArrayList) {
        this.context = context;
        this.scoreInfoArrayList = scoreInfoArrayList;
    }


    @NonNull
    @Override
    public ScoreBoardAdapter.ScoreBoardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.score_item,parent,false);

        return new ScoreBoardHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreBoardAdapter.ScoreBoardHolder holder, int position) {

        ScoreInfo scoreInfo = scoreInfoArrayList.get(position);

        holder.UserName.setText(scoreInfo.UserName);
        holder.Score.setText(String.valueOf(scoreInfo.Score));
    }

    @Override
    public int getItemCount() {
        return scoreInfoArrayList.size();
    }

    public static class ScoreBoardHolder extends RecyclerView.ViewHolder{

        TextView UserName , Score;

        public ScoreBoardHolder(@NonNull View itemView) {
            super(itemView);

            UserName = itemView.findViewById(R.id.UserName);
            Score = itemView.findViewById(R.id.Score);

        }
    }
}
