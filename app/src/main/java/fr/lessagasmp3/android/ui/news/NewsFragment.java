package fr.lessagasmp3.android.ui.news;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import fr.lessagasmp3.android.R;
import fr.lessagasmp3.android.task.GetRssMessages;

public class NewsFragment extends Fragment {

    private static boolean firstCreationOfFragment = true;

    private NewsViewModel mRssMessageViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerview);
        final NewsAdapter adapter = new NewsAdapter(this.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        mRssMessageViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        mRssMessageViewModel.getAllRssMessages().observe(getViewLifecycleOwner(), adapter::setRssMessages);

        if(firstCreationOfFragment) {
            Activity activity = this.getActivity();
            if(activity != null) {
                GetRssMessages getRssMessages = new GetRssMessages(this.getActivity().getString(R.string.core_url) + "/api/rss?feedTitle=Nouveautés", this.getActivity().getApplication());
                getRssMessages.execute();
            }
            firstCreationOfFragment = false;
        }

        return root;
    }

}