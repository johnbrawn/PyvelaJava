package askdat.pyvela.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import askdat.pyvela.R;
import askdat.pyvela.teststart.TestStartActivity;


public class TestsChooseFragment extends Fragment {

    private GridView gridView;

    private GridViewAdapter adapter;

    public static TestsChooseFragment newInstance() {
        TestsChooseFragment fragment = new TestsChooseFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.frag_tests_choose, container, false);

        adapter = new GridViewAdapter(getContext(), getData());

        gridView = root.findViewById(R.id.test_choose_grid);
        gridView.setAdapter(adapter);
        gridView.setNumColumns(2);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), TestStartActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }

    public GridCell[] getData() {
        return new GridCell[] {
                new GridCell(R.drawable.ic_book, "UNT"),
                new GridCell(R.drawable.ic_books, "UNT FULL"),
                new GridCell(R.drawable.ic_book, "EAEA"),
                new GridCell(R.drawable.ic_books, "EAEA FULL"),
        };
    }


    public static class GridViewAdapter extends BaseAdapter{

        private int mCount;

        private Context mContext;

        private GridCell[] mData;

        @Override
        public int getCount() {
            return mCount;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            if (view == null) {
                LayoutInflater layoutInflater = LayoutInflater.from(mContext);
                view = layoutInflater.inflate(R.layout.temp_grid_cell, null);
            }

            ImageView imageView = view.findViewById(R.id.grid_cell_image);
            TextView textView = view.findViewById(R.id.grid_cell_text);

            imageView.setImageResource(mData[i].getImage());
            textView.setText(mData[i].getTitle());

            return view;
        }

        public GridViewAdapter(Context context, GridCell[] data) {
            this.mContext = context;
            this.mData = data;
            this.mCount = data.length;
        }
    }


    public static class GridCell {

        private int Image;

        private String Title;

        public GridCell(int Image, String Title) {
            this.Image = Image;
            this.Title = Title;
        }

        public int getImage() {
            return Image;
        }

        public void setImage(int Image) {
            this.Image = Image;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }
    }
}
