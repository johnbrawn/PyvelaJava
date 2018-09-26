package askdat.pyvela.tests.testsend;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import askdat.pyvela.R;

public class TestsEndFragment extends Fragment implements TestsEndContract.View{

    private TestsEndContract.Presenter mPresenter;

    private ListView mListView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        mPresenter = new TestsEndPresenter(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_tests_end, null);

        mListView  = root.findViewById(R.id.tests_end_list);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void setPresenter(TestsEndContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showResults(int maxScore, Result[] results) {
        mListView.setAdapter(new ResultsAdapter(getContext(), R.layout.temp_test_info, maxScore, results));
    }

    private static class ResultsAdapter extends ArrayAdapter<Result> {

        private LayoutInflater mInflater;

        private int mResourse;

        private Result[] mData;

        private int mScore;

        private boolean isFirstPass;

        public ResultsAdapter(@NonNull Context context, int resource, int score,  @NonNull Result[] objects) {
            super(context, resource, objects);

            mInflater = LayoutInflater.from(context);
            mResourse = resource;
            mData = objects;
            mScore = score;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if (convertView == null) {
                convertView = mInflater.inflate(mResourse, null);
            }

            if (isFirstPass) {

                TextView score = (TextView)mInflater.inflate(R.layout.temp_score, null);
                score.setText(String.valueOf(mScore));

                return score;
            }

            Result result = mData[position];

            TextView indicator = convertView.findViewById(R.id.test_info_indicator_text);
            TextView score = convertView.findViewById(R.id.test_info_score);
            TextView title = convertView.findViewById(R.id.test_info_title);

            indicator.setText(result.getFirstChar());
            score.setText(result.getScore());
            title.setText(result.getSubject());

            ((RelativeLayout.LayoutParams) title.getLayoutParams()).addRule(RelativeLayout.CENTER_VERTICAL);

            TextView date = convertView.findViewById(R.id.test_info_date);

            ((RelativeLayout) convertView).removeView(date);

            return convertView;
        }
    }
}
