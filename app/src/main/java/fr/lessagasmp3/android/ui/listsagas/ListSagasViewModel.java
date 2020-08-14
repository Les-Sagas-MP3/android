package fr.lessagasmp3.android.ui.listsagas;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import fr.lessagasmp3.android.entity.Saga;
import fr.lessagasmp3.android.persistence.repository.SagaRepository;

public class ListSagasViewModel extends AndroidViewModel {

    private SagaRepository mRepository;

    private LiveData<List<Saga>> mAllSagas;

    public ListSagasViewModel(Application application) {
        super(application);
        mRepository = new SagaRepository(application);
        mAllSagas = mRepository.getAllSagas();
    }

    LiveData<List<Saga>> getAllSagas() {
        return mAllSagas;
    }

}
