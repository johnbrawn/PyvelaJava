package askdat.pyvela.tests.teststart;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import askdat.pyvela.R;
import askdat.pyvela.tests.Args;
import askdat.pyvela.tests.teststart.Connector.*;

public class TestStartActivity extends FragmentActivity implements AdapterView.OnItemSelectedListener,
        ViewPager.OnPageChangeListener, SeekBar.OnSeekBarChangeListener, InteractorListener{

    public final static String ARG_TEST_TYPE = "tsttp";

    public final static String ARG_SUBJECTS = "sbjcts";

    private ViewPager mViewPager;

    private Spinner mSpinner;

    private SeekBar mSeekBar;

    private Interactor mInteractor;

    private TextView mCurrentPage;

    private ActivityListener mActivityListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_test_start);

        mViewPager = findViewById(R.id.test_start_pager);
        mSpinner = findViewById(R.id.test_start_spinner);
        mSeekBar = findViewById(R.id.test_start_seekbar);
        mCurrentPage = findViewById(R.id.test_start_currentpage);

        int subjectsId[];
        int testType;
        Intent intent = getIntent();

        if (intent != null) {
            testType = intent.getIntExtra(ARG_TEST_TYPE, -1);
            subjectsId = intent.getIntArrayExtra(ARG_SUBJECTS);
        }

        // FAKE DATA, because getIntent() returns null
        subjectsId = new int[] {Args.MATH_LITERACY,
                                  Args.READING_LITERACY,
                                  Args.HISTORY_OF_KAZAKHSTAN,
                                  Args.MATH,
                                  Args.PHYSICS};

        mInteractor = new Interactor(subjectsId);
        mActivityListener = mInteractor;
        mInteractor.addListener(this);
    }

    @Override
    protected void onStart() {

        // Instantinate Spinner
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mInteractor.getSubjects());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(this);

        // Instantinate ViewPager
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setAdapter(new ExercisesPagerAdapter(getSupportFragmentManager(),
                                                        mInteractor.getCurrentAmountOfExercises(),
                                                        mInteractor));
        // Instantinate SeekBar
        mSeekBar.setOnSeekBarChangeListener(this);
        mSeekBar.setMax(mInteractor.getCurrentAmountOfExercises()-1);
        mSeekBar.getProgressDrawable().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
        mSeekBar.getThumb().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);

        mCurrentPage.setText("1");

        super.onStart();
    }


    // SPINNER EVENTS
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        mActivityListener.onSpinnerItemSelected(i, mViewPager.getCurrentItem());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { }

    // VIEW PAGER EVENTS
    @Override
    public void onPageSelected(int position) {
        mSeekBar.setProgress(position);
        mCurrentPage.setText(String.valueOf(position+1));
    }

    @Override
    public void onPageScrollStateChanged(int state) { }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

    // SEEKBAR EVENTS
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        mCurrentPage.setText(String.valueOf(i+1));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
//        int mediumAnimationDuration = getResources().getInteger(android.R.integer.config_mediumAnimTime);
//        mSeekBar.animate().alpha(1f).setDuration(mediumAnimationDuration);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
//        int longAnimationDuration = getResources().getInteger(android.R.integer.config_longAnimTime);
//        mSeekBar.animate().alpha(0f).setDuration(longAnimationDuration);
        mViewPager.setCurrentItem(mSeekBar.getProgress());
    }

    // CUSTOM EVENTS

    @Override
    public void onExtendAdapter() {
        ((ExercisesPagerAdapter) mViewPager.getAdapter()).setCount(30);
        ((ExercisesPagerAdapter) mViewPager.getAdapter()).notifyDataSetChanged();
        mSeekBar.setMax(29);
    }

    @Override
    public void onResetAdapter() {
        mViewPager.setAdapter(null);
        ExercisesPagerAdapter adapter = new ExercisesPagerAdapter(getSupportFragmentManager(), 20, mInteractor);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(19);
        mSeekBar.setMax(19);
        mSeekBar.setProgress(19);
    }

    @Override
    public void onChangeCount() {
        ((ExercisesPagerAdapter) mViewPager.getAdapter()).setCount(20);
        ((ExercisesPagerAdapter) mViewPager.getAdapter()).notifyDataSetChanged();
        mSeekBar.setMax(19);
    }


    private static class ExercisesPagerAdapter extends FragmentStatePagerAdapter{

        private int mCount;

        private Interactor mInteractor;

        ExercisesPagerAdapter(FragmentManager fm, int count, Interactor interactor) {
            super(fm);

            mInteractor = interactor;
            mCount = count;
        }

        @Override
        public Fragment getItem(int position) {
            return ExerciseFragment.newInstance(position, mInteractor);
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











