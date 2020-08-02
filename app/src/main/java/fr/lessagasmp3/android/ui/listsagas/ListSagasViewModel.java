package fr.lessagasmp3.android.ui.listsagas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListSagasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ListSagasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is list sagas fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}