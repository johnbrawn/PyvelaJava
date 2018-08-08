package askdat.pyvela.teststart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import askdat.pyvela.R;
import askdat.pyvela.data.local.TestStartData;

public class TestStartActivity extends FragmentActivity {

    private ViewPager mViewPager;

    private ExercisePagerAdapter adapter;

    private TestStartData mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test_start);

        adapter = new ExercisePagerAdapter(getSupportFragmentManager(), 20);
        mViewPager = findViewById(R.id.test_start_pager);
        mViewPager.setAdapter(adapter);
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



