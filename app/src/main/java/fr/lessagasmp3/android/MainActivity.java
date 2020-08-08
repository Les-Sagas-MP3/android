package fr.lessagasmp3.android;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fr.lessagasmp3.android.task.GetRssMessages;
import fr.lessagasmp3.android.task.GetSagas;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_news, R.id.navigation_list_sagas)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sync:
                Toast.makeText(this, "Download started", Toast.LENGTH_SHORT).show();
                GetSagas getSagas = new GetSagas(getResources().getString(R.string.core_url) + "/api/sagas", getContentResolver());
                getSagas.execute();
                GetRssMessages getRssMessages = new GetRssMessages(getResources().getString(R.string.core_url) + "/api/rss?feedTitle=Nouveautés", getContentResolver());
                getRssMessages.execute();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}