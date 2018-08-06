package askdat.pyvela.entrance;

import android.os.Bundle;
import android.support.v4.app.*;

import askdat.pyvela.R;

public class EntranceActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_entrance);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.entrance_fragment_placeholder, new AuthorizationFragment())
                .commit();

    }
    public void ReplaceFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.entrance_fragment_placeholder,fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }


}
