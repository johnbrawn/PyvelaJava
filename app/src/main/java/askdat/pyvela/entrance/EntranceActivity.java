package askdat.pyvela.entrance;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.*;
import android.widget.Toast;

import askdat.pyvela.R;

public class EntranceActivity extends FragmentActivity {

    private boolean twice = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_entrance);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.entrance_fragment_placeholder, new AuthorizationFragment(),"AuthorizationFragment")
                .addToBackStack(null)
                .commit();

    }
    public void ReplaceFragment(Fragment fragment, String tag){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.entrance_fragment_placeholder, fragment, tag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null)
                .commit();
    }
    @Override
    public void onBackPressed(){
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("RegistrationFragment");
        if (fragment !=null && fragment.isVisible())
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
