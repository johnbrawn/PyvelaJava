package askdat.pyvela.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import askdat.pyvela.R;
import askdat.pyvela.main.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private ProfileFragment mProfileFragment;

    private TestsChooseFragment mTestChooseFragmentq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_home);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_profile:
                    ReplaceFragment(new ProfileFragment());
                    break;
                case R.id.navigation_home:
                    ReplaceFragment(new TestsChooseFragment());
                    break;
                case R.id.navigation_history:
                    break;
            }
            fragmentTransaction.commit();
            return false;
        }
    };


    public void ReplaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_placeholder,fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

}
