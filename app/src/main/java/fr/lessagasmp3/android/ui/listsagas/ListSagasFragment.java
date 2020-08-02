package fr.lessagasmp3.android.ui.listsagas;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.orhanobut.logger.Logger;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import fr.lessagasmp3.android.R;
import fr.lessagasmp3.android.common.DateCommon;
import fr.lessagasmp3.android.contentprovider.SagaProvider;
import fr.lessagasmp3.android.database.SagaTable;
import fr.lessagasmp3.android.model.Saga;

public class ListSagasFragment extends Fragment {

    private ListSagasViewModel listSagasViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listSagasViewModel =
                ViewModelProviders.of(this).get(ListSagasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_list_sagas, container, false);

        Activity activity = getActivity();
        if(activity != null) {
            ListView lv = (ListView)root.findViewById(R.id.list);
            List<Saga> sagas = getSagas();
            TextView txtView = (TextView)root.findViewById(R.id.empty);
            if(sagas.size() > 0) {
                txtView.setVisibility(View.INVISIBLE);
            } else {
                txtView.setVisibility(View.VISIBLE);
            }
            lv.setAdapter(new ListSagasAdapter(activity, getSagas()));
        }
        return root;
    }

    public List<Saga> getSagas(){
        Cursor cursor = getActivity().getContentResolver().query(SagaProvider.CONTENT_URI, null, null, null, null);
        cursor.moveToFirst();
        List<Saga> sagas = new ArrayList<>();
        while(!cursor.isAfterLast()) {
            Saga saga = new Saga();
            saga.setId(cursor.getLong(cursor.getColumnIndex(SagaTable.COLUMN_ID)));
            saga.setCreatedBy(cursor.getString(cursor.getColumnIndex(SagaTable.COLUMN_CREATED_BY)));
            saga.setUpdatedBy(cursor.getString(cursor.getColumnIndex(SagaTable.COLUMN_UPDATED_BY)));
            saga.setTitle(cursor.getString(cursor.getColumnIndex(SagaTable.COLUMN_TITLE)));
            saga.setUrl(cursor.getString(cursor.getColumnIndex(SagaTable.COLUMN_URL)));
            saga.setUrlWiki(cursor.getString(cursor.getColumnIndex(SagaTable.COLUMN_URL_WIKI)));
            saga.setLevelArt(cursor.getInt(cursor.getColumnIndex(SagaTable.COLUMN_LEVEL_ART)));
            saga.setLevelTech(cursor.getInt(cursor.getColumnIndex(SagaTable.COLUMN_LEVEL_TECH)));
            saga.setNbReviews(cursor.getInt(cursor.getColumnIndex(SagaTable.COLUMN_NB_REVIEWS)));
            saga.setUrlReviews(cursor.getString(cursor.getColumnIndex(SagaTable.COLUMN_URL_REVIEWS)));
            saga.setNbBravos(cursor.getInt(cursor.getColumnIndex(SagaTable.COLUMN_NB_BRAVOS)));
            try {
                saga.setCreatedAt(DateCommon.DATE_FORMAT.parse(cursor.getString(cursor.getColumnIndex(SagaTable.COLUMN_CREATED_AT))));
                saga.setUpdatedAt(DateCommon.DATE_FORMAT.parse(cursor.getString(cursor.getColumnIndex(SagaTable.COLUMN_UPDATED_AT))));
            } catch (ParseException e) {
                if(e.getMessage() != null) {
                    Logger.e(e.getMessage(), e);
                }
            }
            sagas.add(saga);
            cursor.moveToNext();
        }
        cursor.close();
        return sagas;
    }
}