package askdat.pyvela.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import askdat.pyvela.R;

public class BySubjectAdapter extends ArrayAdapter {

    LayoutInflater inflater;
    ArrayList<BySubjectContainer> Data;
    int Resources;

    public BySubjectAdapter(Context context, int resources, ArrayList<BySubjectContainer> data) {
        super(context, resources, data);

        this.inflater = LayoutInflater.from(context);
        this.Resources = resources;
        this.Data = data;
    }

    @Override
    public View getView(int position, View conteiner, ViewGroup parent) {

        View root = inflater.inflate(R.layout.by_subject_data, parent, false);
        TextView Name = (TextView) root.findViewById(R.id.data);
        ImageView Photo = (ImageView) root.findViewById(R.id.image);

        BySubjectContainer bySubjectContainer = Data.get(position);
        Name.setText(bySubjectContainer.getName());
        Photo.setImageResource(bySubjectContainer.getPhoto());

        return root;
    }
}
