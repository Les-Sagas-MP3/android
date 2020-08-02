package fr.lessagasmp3.android;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.widget.Toolbar;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import fr.lessagasmp3.android.contentprovider.SagaProvider;
import fr.lessagasmp3.android.database.SagaTable;
import fr.lessagasmp3.android.task.GetSaga;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.addLogAdapter(new AndroidLogAdapter());

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.menu);
        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_sync:
                                GetSaga getSaga = new GetSaga(getResources().getString(R.string.core_url), getContentResolver());
                                getSaga.execute();
                                return true;
                        }
                        return true;
                    }
                });

        Cursor cursor = this.getContentResolver().query(SagaProvider.CONTENT_URI, null, null, null, null);
        startManagingCursor(cursor);
        ListAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.two_line_list_item,
                cursor, new String[] {SagaTable.COLUMN_TITLE, SagaTable.COLUMN_URL},
                new int[] {android.R.id.text1, android.R.id.text2});
        setListAdapter(adapter);
    }

}