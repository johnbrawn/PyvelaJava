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

import askdat.pyvela.R;

public class TestsHistoryFragment extends Fragment {

    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.frag_tests_history, container, false);

        listView = root.findViewById(R.id.tests_history_list);

        TestInfo[] data = new TestInfo[] {
                new TestInfo("UNT FULL", 92),
                new TestInfo("UNT FULL", 99),
                new TestInfo("UNT MATH", 21),
                new TestInfo("UNT PHYSICS", 18),
                new TestInfo("UNT PHYSICS", 18),
                new TestInfo("UNT PHYSICS", 18),
                new TestInfo("UNT PHYSICS", 18)
        };

        listView.setAdapter(new TestInfoAdapter(getContext(), R.layout.temp_test_info, data));
        return root;
    }


    public static class TestInfoAdapter extends ArrayAdapter<TestInfo> {

        private TestInfo[] mData;

        private int mResource;

        private LayoutInflater inflater;

        public TestInfoAdapter(@NonNull Context context, int resource, @NonNull TestInfo[] data) {
            super(context, resource, data);

            this.inflater = LayoutInflater.from(context);
            this.mData = data;
            this.mResource = resource;
        }

        @Override
        public int getCount() {
            return mData.length;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(mResource, null);
            }
            TestInfo data = mData[position];

            TextView score = convertView.findViewById(R.id.test_info_score);
            TextView title = convertView.findViewById(R.id.test_info_title);
            ImageView image = convertView.findViewById(R.id.test_info_image);

            title.setText(data.getTestType());
            score.setText(data.getScore());
            image.setImageResource(R.drawable.ic_book);

            return convertView;
        }


    }


    public static class TestInfo {

        private String TestType;

        private String Score;

        public TestInfo(String testType, int score) {
            TestType = testType;
            Score = String.valueOf(score);
        }

        public TestInfo(String testType, String score) {
            TestType = testType;
            Score = score;
        }

        public String getTestType() {
            return TestType;
        }

        public String getScore() {
            return Score;
        }
    }
}


