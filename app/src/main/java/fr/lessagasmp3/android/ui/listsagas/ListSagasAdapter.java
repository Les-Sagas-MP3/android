package fr.lessagasmp3.android.ui.listsagas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.lessagasmp3.android.R;
import fr.lessagasmp3.android.entity.Saga;

public class ListSagasAdapter extends RecyclerView.Adapter<ListSagasAdapter.SagaViewHolder> {

    private final LayoutInflater mInflater;
    private List<Saga> mSagas; // Cached copy

    ListSagasAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public SagaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new SagaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SagaViewHolder holder, int position) {
        if (mSagas != null) {
            Saga current = mSagas.get(position);
            holder.wordItemView.setText(current.getTitle());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Saga");
        }
    }

    void setSagas(List<Saga> words){
        mSagas = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mSagas has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mSagas != null)
            return mSagas.size();
        else return 0;
    }

    class SagaViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private SagaViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }
}