package askdat.pyvela.tests.teststart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import askdat.pyvela.R;
import askdat.pyvela.data.remote.TestStartData;

public class TestStartActivity extends FragmentActivity {

    private ViewPager mViewPager;

    private ExercisePagerAdapter adapter;

    private Spinner spinner;

    private ArrayAdapter<String> spinnerAdapter;

    private TestStartData mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test_start);

        adapter = new ExercisePagerAdapter(getSupportFragmentManager(), 20);
        mViewPager = findViewById(R.id.test_start_pager);
        mViewPager.setAdapter(adapter);
        spinner = findViewById(R.id.test_start_spinner);
        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                new String[] {"Math Literacy", "Reading Literacy", "History of Kazakhstan", "Math", "Phisycs"});
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    public TestStartData getDataManager() {
        return mData;
    }


    private static class ExercisePagerAdapter extends FragmentStatePagerAdapter {

        private int mCount;

        public ExercisePagerAdapter(FragmentManager fm, int count) {

            super(fm);
            this.mCount = count;
        }

        @Override
        public Fragment getItem(int position) {

            return ExerciseFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return mCount;
        }

        public void setCount(int count) {
            this.mCount = count;
        }
    }
}



