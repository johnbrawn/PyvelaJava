package askdat.pyvela.main.home;

    import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import askdat.pyvela.R;

public class EntFragmentAdapter extends BaseAdapter {
        Context ctx;
        LayoutInflater lInflater;
        ArrayList<EntFragmentContainer> objects;

        EntFragmentAdapter(Context context, ArrayList<EntFragmentContainer> products) {
            ctx = context;
            objects = products;
            lInflater = (LayoutInflater) ctx
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


        @Override
        public int getCount() {
            return objects.size();
        }

        @Override
        public Object getItem(int position) {
            return objects.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;
            if (view == null) {
                view = lInflater.inflate(R.layout.frag_ent_data, parent, false);
            }

            EntFragmentContainer p = getProduct(position);

            CheckBox cbBuy = (CheckBox) view.findViewById(R.id.cbBox);
            cbBuy.setText(p.name);
            cbBuy.setOnCheckedChangeListener(myCheckChangeList);
            cbBuy.setTag(position);
            cbBuy.setChecked(p.box);
            return view;
        }

        EntFragmentContainer getProduct(int position) {
            return ((EntFragmentContainer) getItem(position));
        }

        ArrayList<EntFragmentContainer> getBox() {
            ArrayList<EntFragmentContainer> box = new ArrayList<EntFragmentContainer>();
            for (EntFragmentContainer p : objects) {
                if (p.box)
                    box.add(p);
            }
            return box;
        }
        OnCheckedChangeListener myCheckChangeList = new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                getProduct((Integer) buttonView.getTag()).box = isChecked;

            }
        };
    }

