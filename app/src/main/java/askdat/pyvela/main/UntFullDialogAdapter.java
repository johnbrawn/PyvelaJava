package askdat.pyvela.main;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

import askdat.pyvela.R;

public class UntFullDialogAdapter extends ArrayAdapter {

    LayoutInflater inflater;
    ArrayList<UntFullContainer> Data;
    int Resources;

    public UntFullDialogAdapter(Context context, int resources, ArrayList<UntFullContainer> data) {

        super(context, resources, data);

        this.inflater = LayoutInflater.from(context);
        this.Resources = resources;
        this.Data = data;
    }
    @Override
    public View getView(int position, View conteiner, ViewGroup parent){

        View root = inflater.inflate(R.layout.unt_full_dialog_data, parent, false);
        TextView texts1 = (TextView) root.findViewById(R.id.textwithlist1);
        TextView texts2 = (TextView) root.findViewById(R.id.textwithlist2);

        UntFullContainer subjects=Data.get(position);
        texts1.setText(subjects.getText1());
        texts2.setText(subjects.getText2());

        return root;
    }
}