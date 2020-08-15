package fr.lessagasmp3.android.ui.listsagas;

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

import fr.lessagasmp3.android.R;
import fr.lessagasmp3.android.task.GetSagasTask;

public class ListSagasFragment extends Fragment {

    private static boolean firstCreationOfFragment = true;

    private ListSagasViewModel mSagaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list_sagas, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerview);
        SwipeRefreshLayout mSwipeRefreshLayout = root.findViewById(R.id.swipeToRefresh);

        final ListSagasAdapter adapter = new ListSagasAdapter(this.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        mSagaViewModel = new ViewModelProvider(this).get(ListSagasViewModel.class);
        mSagaViewModel.getAllSagas().observe(getViewLifecycleOwner(), adapter::setSagas);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            GetSagasTask getSagas = new GetSagasTask(this.getActivity(), mSwipeRefreshLayout);
            getSagas.execute();
        });

        if(firstCreationOfFragment) {
            Activity activity = this.getActivity();
            if(activity != null) {
                mSwipeRefreshLayout.setRefreshing(true);
                GetSagasTask getSagas = new GetSagasTask(this.getActivity(), mSwipeRefreshLayout);
                getSagas.execute();
            }
            firstCreationOfFragment = false;
        }

        return root;
    }
}