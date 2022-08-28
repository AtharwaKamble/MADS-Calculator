package com.example.madscalculator.History;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madscalculator.R;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private ArrayList<HistoryData> historyDataList;
    private Context context;

    public HistoryAdapter(Context context, ArrayList<HistoryData> historyDataArrayList) {
        this.context = context;
        this.historyDataList = historyDataArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.history_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HistoryData historyData = historyDataList.get(position);
        holder.textOperation.setText(historyData.getOperation() );
        holder.textResult.setText(historyData.getResults());

    }


    @Override
    public int getItemCount() {
        return historyDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView textOperation, textResult;

        public ViewHolder(View itemView) {
            super(itemView);
            textOperation = itemView.findViewById(R.id.tv_operation);
            textResult = itemView.findViewById(R.id.tv_result);
        }
    }
}
