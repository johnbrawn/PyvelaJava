package askdat.pyvela;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class TestStartActivity extends android.support.v4.app.FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test_start);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.act_test_start_placeholder, new TestStartFragment())
                .commit();
    }
}
