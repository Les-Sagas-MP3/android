package fr.lessagasmp3.android.ui.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.lessagasmp3.android.R;
import fr.lessagasmp3.android.entity.RssMessage;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.RssMessageViewHolder> {

    private final LayoutInflater mInflater;
    private List<RssMessage> mRssMessages; // Cached copy

    NewsAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public RssMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new RssMessageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RssMessageViewHolder holder, int position) {
        if (mRssMessages != null) {
            RssMessage current = mRssMessages.get(position);
            holder.wordItemView.setText(current.getTitle());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No RssMessage");
        }
    }

    void setRssMessages(List<RssMessage> words){
        mRssMessages = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mRssMessages has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mRssMessages != null)
            return mRssMessages.size();
        else return 0;
    }

    class RssMessageViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private RssMessageViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
        }
    }
}