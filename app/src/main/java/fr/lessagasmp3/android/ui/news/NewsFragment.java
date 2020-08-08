package fr.lessagasmp3.android.ui.news;

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

import com.orhanobut.logger.Logger;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import fr.lessagasmp3.android.R;
import fr.lessagasmp3.android.common.DateCommon;
import fr.lessagasmp3.android.contentprovider.RssMessageProvider;
import fr.lessagasmp3.android.database.RssMessageTable;
import fr.lessagasmp3.android.model.RssMessage;

public class NewsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news, container, false);

        Activity activity = getActivity();
        if(activity != null) {
            ListView lv = (ListView)root.findViewById(R.id.list);
            List<RssMessage> sagas = getNews();
            TextView txtView = (TextView)root.findViewById(R.id.empty);
            if(sagas.size() > 0) {
                txtView.setVisibility(View.INVISIBLE);
            } else {
                txtView.setVisibility(View.VISIBLE);
            }
            lv.setAdapter(new ListRssMessagesAdapter(activity, getNews()));
        }
        return root;
    }

    public List<RssMessage> getNews(){
        Cursor cursor = getActivity().getContentResolver().query(RssMessageProvider.CONTENT_URI, null, null, null, null);
        cursor.moveToFirst();
        List<RssMessage> entries = new ArrayList<>();
        while(!cursor.isAfterLast()) {
            RssMessage entry = new RssMessage();
            entry.setId(cursor.getLong(cursor.getColumnIndex(RssMessageTable.COLUMN_ID)));
            entry.setCreatedBy(cursor.getString(cursor.getColumnIndex(RssMessageTable.COLUMN_CREATED_BY)));
            entry.setUpdatedBy(cursor.getString(cursor.getColumnIndex(RssMessageTable.COLUMN_UPDATED_BY)));
            entry.setFeedTitle(cursor.getString(cursor.getColumnIndex(RssMessageTable.COLUMN_FEED_TITLE)));
            entry.setTitle(cursor.getString(cursor.getColumnIndex(RssMessageTable.COLUMN_TITLE)));
            entry.setPubdate(cursor.getString(cursor.getColumnIndex(RssMessageTable.COLUMN_PUBDATE)));
            entry.setDescription(cursor.getString(cursor.getColumnIndex(RssMessageTable.COLUMN_DESCRIPTION)));
            entry.setLink(cursor.getString(cursor.getColumnIndex(RssMessageTable.COLUMN_LINK)));
            entry.setAuthor(cursor.getString(cursor.getColumnIndex(RssMessageTable.COLUMN_AUTHOR)));
            entry.setGuid(cursor.getString(cursor.getColumnIndex(RssMessageTable.COLUMN_GUID)));
            try {
                entry.setCreatedAt(DateCommon.DATE_FORMAT.parse(cursor.getString(cursor.getColumnIndex(RssMessageTable.COLUMN_CREATED_AT))));
                entry.setUpdatedAt(DateCommon.DATE_FORMAT.parse(cursor.getString(cursor.getColumnIndex(RssMessageTable.COLUMN_UPDATED_AT))));
            } catch (ParseException e) {
                if(e.getMessage() != null) {
                    Logger.e(e.getMessage(), e);
                }
            }
            entries.add(entry);
            cursor.moveToNext();
        }
        cursor.close();
        return entries;
    }
}