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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.futuremind.recyclerviewfastscroll.FastScroller;

import fr.lessagasmp3.android.R;
import fr.lessagasmp3.android.task.GetRssMessagesTask;

public class NewsFragment extends Fragment {

    private static boolean firstCreationOfFragment = true;

    private NewsViewModel mRssMessageViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerview);
        SwipeRefreshLayout mSwipeRefreshLayout = root.findViewById(R.id.swipeToRefresh);
        FastScroller fastScroller = root.findViewById(R.id.fastscroll);

        final NewsAdapter adapter = new NewsAdapter(this.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);
        fastScroller.setRecyclerView(recyclerView);

        mRssMessageViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        mRssMessageViewModel.getAllRssMessages().observe(getViewLifecycleOwner(), adapter::setRssMessages);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            GetRssMessagesTask getRssMessages = new GetRssMessagesTask(this.getActivity(), mSwipeRefreshLayout);
            getRssMessages.execute();
        });

        if(firstCreationOfFragment) {
            Activity activity = this.getActivity();
            if(activity != null) {
                GetRssMessagesTask getRssMessages = new GetRssMessagesTask(this.getActivity(), mSwipeRefreshLayout);
                getRssMessages.execute();
            }
            firstCreationOfFragment = false;
        }

        return root;
    }

}