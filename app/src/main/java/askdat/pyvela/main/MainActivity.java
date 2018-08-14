package askdat.pyvela.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Map;

import askdat.pyvela.R;
import askdat.pyvela.SharedPrefsClass;
import askdat.pyvela.entrance.EntranceActivity;
import askdat.pyvela.tests.testhistory.TestsHistoryFragment;

public class MainActivity extends AppCompatActivity {

    private SharedPrefsClass sharedPrefsClass;
    private boolean twice = false;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_profile:
                    ReplaceFragment(new ProfileFragment(),"ProfileFragment");
                    break;
                case R.id.navigation_home:
                    ReplaceFragment(new TestsChooseFragment(),"TestsChooseFragment");
                    break;
                case R.id.navigation_history:
                    ReplaceFragment(new TestsHistoryFragment(),"TestsHistoryFragment()");
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

        sharedPrefsClass = new SharedPrefsClass();
    }
    public void ReplaceFragment(Fragment fragment,String tag){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_content_fragments_placeholder,fragment,tag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_help) {
            Map a=sharedPrefsClass.getaLl(this);
            Toast.makeText(this,(a).toString(),Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.action_exit){
            sharedPrefsClass.appPrefs(this);
            sharedPrefsClass.saveBool("bool",false);
            Intent intent = new Intent(this,EntranceActivity.class);

            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("ImageChangeFragment");
        if (fragment != null && fragment.isVisible())
            super.onBackPressed();
        else {
            if (twice) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                System.exit(0);
            }

            twice = true;
            Toast.makeText(this, "Please press BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    twice = false;
                }
            }, 2000);
        }
    }
}
