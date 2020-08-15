package fr.lessagasmp3.android.persistence.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import fr.lessagasmp3.android.entity.RssMessage;
import fr.lessagasmp3.android.persistence.MainRoomDatabase;
import fr.lessagasmp3.android.persistence.dao.RssMessageDao;

public class RssMessageRepository {

    private RssMessageDao mRssMessageDao;
    private LiveData<List<RssMessage>> mAllRssMessages;

    public RssMessageRepository(Application application) {
        MainRoomDatabase db = MainRoomDatabase.getDatabase(application);
        mRssMessageDao = db.rssMessageDao();
        mAllRssMessages = mRssMessageDao.getAllRssMessages();
    }

    public LiveData<List<RssMessage>> getAllRssMessages() {
        return mAllRssMessages;
    }

    public void insert (RssMessage saga) {
        new insertAsyncTask(mRssMessageDao).execute(saga);
    }

    private static class insertAsyncTask extends AsyncTask<RssMessage, Void, Void> {

        private RssMessageDao mAsyncTaskDao;

        insertAsyncTask(RssMessageDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final RssMessage... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void deleteAll() {
        new deleteAllAsyncTask(mRssMessageDao).execute();
    }

    private static class deleteAllAsyncTask extends AsyncTask<RssMessage, Void, Void> {

        private RssMessageDao mAsyncTaskDao;

        deleteAllAsyncTask(RssMessageDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final RssMessage... params) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

}
