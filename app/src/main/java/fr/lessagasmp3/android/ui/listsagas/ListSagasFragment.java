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

import fr.lessagasmp3.android.R;
import fr.lessagasmp3.android.task.GetSagas;

public class ListSagasFragment extends Fragment {

    private static boolean firstCreationOfFragment = true;

    private ListSagasViewModel mSagaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list_sagas, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerview);
        final ListSagasAdapter adapter = new ListSagasAdapter(this.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        mSagaViewModel = new ViewModelProvider(this).get(ListSagasViewModel.class);
        mSagaViewModel.getAllSagas().observe(getViewLifecycleOwner(), adapter::setSagas);

        if(firstCreationOfFragment) {
            Activity activity = this.getActivity();
            if(activity != null) {
                GetSagas getSagas = new GetSagas(this.getActivity().getString(R.string.core_url) + "/api/sagas", this.getActivity().getApplication());
                getSagas.execute();
            }
            firstCreationOfFragment = false;
        }

        return root;
    }
}