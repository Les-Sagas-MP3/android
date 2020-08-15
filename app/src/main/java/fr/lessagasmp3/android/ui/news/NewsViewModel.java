package fr.lessagasmp3.android.ui.news;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import fr.lessagasmp3.android.entity.RssMessage;
import fr.lessagasmp3.android.persistence.repository.RssMessageRepository;

public class NewsViewModel extends AndroidViewModel {

    private RssMessageRepository mRepository;

    private LiveData<List<RssMessage>> mAllRssMessages;

    public NewsViewModel(Application application) {
        super(application);
        mRepository = new RssMessageRepository(application);
        mAllRssMessages = mRepository.getAllRssMessages();
    }

    LiveData<List<RssMessage>> getAllRssMessages() {
        return mAllRssMessages;
    }

}
