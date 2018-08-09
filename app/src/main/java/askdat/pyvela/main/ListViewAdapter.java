package askdat.pyvela.main;
import android.app.ActionBar;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import askdat.pyvela.R;

public class ListViewAdapter extends ArrayAdapter {

    LayoutInflater inflater;
    ArrayList<ContainerListView> Data;
    int Resources;

    public ListViewAdapter (Context context, int resources, ArrayList<ContainerListView> data) {
        super(context, resources, data);

        this.inflater = LayoutInflater.from(context);
        this.Resources = resources;
        this.Data = data;
    }
    @Override
    public View getView(int position, View conteiner, ViewGroup parent){
        View root = inflater.inflate(R.layout.text_listview, parent, false);
        TextView texts =(TextView)root.findViewById(R.id.textwithlist);

        ContainerListView subjects=Data.get(position);
        texts.setText(subjects.getText());


        return root;
    }
}