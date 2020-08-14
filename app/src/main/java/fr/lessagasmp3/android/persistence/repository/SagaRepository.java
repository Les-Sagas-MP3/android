package fr.lessagasmp3.android.persistence.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import fr.lessagasmp3.android.entity.Saga;
import fr.lessagasmp3.android.persistence.dao.SagaDao;
import fr.lessagasmp3.android.persistence.room.SagaRoomDatabase;

public class SagaRepository {

    private SagaDao mSagaDao;
    private LiveData<List<Saga>> mAllSagas;

    public SagaRepository(Application application) {
        SagaRoomDatabase db = SagaRoomDatabase.getDatabase(application);
        mSagaDao = db.dao();
        mAllSagas = mSagaDao.getAllSagas();
    }

    public LiveData<List<Saga>> getAllSagas() {
        return mAllSagas;
    }

    public void insert (Saga saga) {
        new insertAsyncTask(mSagaDao).execute(saga);
    }

    private static class insertAsyncTask extends AsyncTask<Saga, Void, Void> {

        private SagaDao mAsyncTaskDao;

        insertAsyncTask(SagaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Saga... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void deleteAll() {
        new deleteAllAsyncTask(mSagaDao).execute();
    }

    private static class deleteAllAsyncTask extends AsyncTask<Saga, Void, Void> {

        private SagaDao mAsyncTaskDao;

        deleteAllAsyncTask(SagaDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Saga... params) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

}
