package askdat.pyvela.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import askdat.pyvela.R;
import askdat.pyvela.SharedPrefsClass;
import askdat.pyvela.data.local.ImageChangeData;


public class ImageChangeFragment extends Fragment {

    private SharedPrefsClass sharedPrefsClass;

    public ImageChangeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefsClass = new SharedPrefsClass();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_image_change, container, false);
        GridView gridView = root.findViewById(R.id.image_change_gridview);
        gridView.setAdapter(new ImageChangeAdapter(getActivity()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sharedPrefsClass.appPrefs(getActivity());
                switch (position){
                    case 0:
                        sharedPrefsClass.saveInt("change_photo",0);
                        break;
                    case 1:
                        sharedPrefsClass.saveInt("change_photo",1);
                        break;
                    case 2:
                        sharedPrefsClass.saveInt("change_photo",2);
                        break;
                    case 3:
                        sharedPrefsClass.saveInt("change_photo",3);
                        break;
                    case 4:
                        sharedPrefsClass.saveInt("change_photo",4);
                        break;
                }
            }
        });


        return root;
    }

    public static class ImageChangeAdapter extends BaseAdapter {
        private Context mContext;

        public ImageChangeAdapter(Context c) {
            mContext = c;
            ImageChangeData.getInstance();
        }

        public int getCount() {
            return 4;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            int[] images=ImageChangeData.instance.Images;

            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(200, 200));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(images[position]);
            return imageView;
        }
    }


}
