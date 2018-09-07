package askdat.pyvela.tests.testhistory;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import askdat.pyvela.tests.testhistory.TestsHistoryContract.Presenter;
import askdat.pyvela.R;

public class TestsHistoryFragment extends Fragment implements TestsHistoryContract.View{

    ListView mListView;
    Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mPresenter = new TestsHistoryPresenter(this);

        View root =  inflater.inflate(R.layout.frag_tests_history, container, false);
        mListView = root.findViewById(R.id.tests_history_list);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.OnResume();
    }

    @Override
    public void showList(ArrayList<TestInfo> items) {
        mListView.setAdapter(new TestsInfoAdapter(getContext(), R.layout.temp_test_info, items));
    }

    @Override
    public void onDestroy() {
        mPresenter.OnDestroy();
        super.onDestroy();
    }

    public static class TestsInfoAdapter extends ArrayAdapter<TestInfo> {

        private ArrayList<TestInfo> mData;

        private int mResource;

        private LayoutInflater inflater;

        public TestsInfoAdapter(@NonNull Context context, int resource, @NonNull ArrayList<TestInfo> data) {
            super(context, resource, data);

            this.inflater = LayoutInflater.from(context);
            this.mData = data;
            this.mResource = resource;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(mResource, null);
            }
            TestInfo data = mData.get(position);

            TextView score = convertView.findViewById(R.id.test_info_score);
            TextView title = convertView.findViewById(R.id.test_info_title);
            ImageView image = convertView.findViewById(R.id.test_info_image);

            title.setText(data.getTitle());
            score.setText(data.getScore());
            image.setImageResource(R.drawable.ic_book);

            return convertView;
        }
    }
}


