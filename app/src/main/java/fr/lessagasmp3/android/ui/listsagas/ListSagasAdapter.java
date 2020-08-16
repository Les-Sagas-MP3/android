package fr.lessagasmp3.android.ui.listsagas;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.futuremind.recyclerviewfastscroll.SectionTitleProvider;

import java.util.ArrayList;
import java.util.List;

import fr.lessagasmp3.android.R;
import fr.lessagasmp3.android.entity.Saga;

public class ListSagasAdapter extends RecyclerView.Adapter<ListSagasAdapter.SagaViewHolder> implements SectionTitleProvider {

    private Context context;
    private final LayoutInflater mInflater;
    private List<Saga> mSagas; // Cached copy
    private ArrayList<Integer> mSectionPositions;

    ListSagasAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public SagaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_saga_item, parent, false);
        return new SagaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SagaViewHolder holder, int position) {
        if (mSagas != null) {
            Saga current = mSagas.get(position);
            holder.title.setText(current.getTitle());
            holder.bravos.setText(current.getNbBravos() + "\n" + getEmojiByUnicode(0x1F44F));
            if(current.getUrl().isEmpty()) {
                holder.website.setVisibility(View.INVISIBLE);
            } else {
                holder.website.setOnClickListener(v -> {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(current.getUrl()));
                    context.startActivity(i);
                });
            }
            if(current.getUrlWiki().isEmpty()) {
                holder.wiki.setVisibility(View.INVISIBLE);
            } else {
                holder.wiki.setOnClickListener(v -> {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(current.getUrl()));
                    context.startActivity(i);
                });
            }
            if(current.getLevelArt() > 100) {
                holder.topLevelArt.setProgress(current.getLevelArt() - 100);
                holder.bottomLevelArt.setProgress(0);
            } else if(current.getLevelArt() < 100) {
                holder.topLevelArt.setProgress(0);
                holder.bottomLevelArt.setProgress(current.getLevelArt());
            } else {
                holder.topLevelArt.setProgress(0);
                holder.bottomLevelArt.setProgress(0);
            }
            if(current.getLevelTech() > 100) {
                holder.topLevelTech.setProgress(current.getLevelTech() - 100);
                holder.bottomLevelTech.setProgress(0);
            } else if(current.getLevelTech() < 100){
                holder.topLevelTech.setProgress(0);
                holder.bottomLevelTech.setProgress(current.getLevelTech());
            } else {
                holder.topLevelTech.setProgress(0);
                holder.bottomLevelTech.setProgress(0);
            }
        } else {
            // Covers the case of data not being ready yet.
            holder.title.setText("No Saga");
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

    @Override
    public String getSectionTitle(int position) {
        //this String will be shown in a bubble for specified position
        return mSagas.get(position).getTitle().substring(0, 1);
    }

    class SagaViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView bravos;
        private final Button website;
        private final Button wiki;
        private final ProgressBar topLevelArt;
        private final ProgressBar bottomLevelArt;
        private final ProgressBar topLevelTech;
        private final ProgressBar bottomLevelTech;

        private SagaViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            bravos = itemView.findViewById(R.id.bravos);
            website = itemView.findViewById(R.id.website);
            wiki = itemView.findViewById(R.id.wiki);
            topLevelArt = itemView.findViewById(R.id.top_level_art);
            bottomLevelArt = itemView.findViewById(R.id.bottom_level_art);
            topLevelTech = itemView.findViewById(R.id.top_level_tech);
            bottomLevelTech = itemView.findViewById(R.id.bottom_level_tech);
        }
    }

    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }

}