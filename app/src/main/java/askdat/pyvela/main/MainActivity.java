package askdat.pyvela.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import askdat.pyvela.R;
import askdat.pyvela.entrance.EntranceActivity;
import askdat.pyvela.entrance.SplashActivity;
import askdat.pyvela.tests.testhistory.TestsHistoryFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_profile:
                    fragmentTransaction.replace(R.id.main_content_fragments_placeholder,new ProfileFragment());
                    break;
                case R.id.navigation_home:
                    fragmentTransaction.replace(R.id.main_content_fragments_placeholder,new TestsChooseFragment());
                    break;
                case R.id.navigation_history:
                    ReplaceFragment(new TestsHistoryFragment());
                    break;
            }
            fragmentTransaction.commit();
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_content_fragments_placeholder, new TestsChooseFragment())
                .commit();


    }
    public void ReplaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_content_fragments_placeholder,fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }
    @Override
    public void onBackPressed(){

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_help) {
            return true;
        }
        else if (id == R.id.action_exit){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
