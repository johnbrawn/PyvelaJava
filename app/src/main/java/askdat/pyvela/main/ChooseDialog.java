package askdat.pyvela.main;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import askdat.pyvela.R;

public class ChooseDialog extends DialogFragment {

    ListViewAdapter adapter;
    ListView listView;
    UNT_FULL full;
    ArrayList<ContainerListView> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent , Bundle savedInstanceState){
        getDialog().setTitle("asasdas");
        View root= inflater.inflate(R.layout.listview,parent, false);

        listView =(ListView)root.findViewById(R.id.listviews);

        data = new ArrayList<ContainerListView>();
        data.add(new ContainerListView("Mathemtics Physics"));
        data.add(new ContainerListView("assas2 asd"));
        data.add(new ContainerListView("asdas3 asdas"));
        data.add(new ContainerListView("asdas4 wfe"));
        data.add(new ContainerListView("asdas5 krge"));
        adapter= new ListViewAdapter(getContext(), R.layout.listview, data);
        listView.setAdapter(adapter);

        full=new UNT_FULL();

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String[] item=listView.getItemAtPosition(i).toString().split(" ");
                Toast.makeText(getActivity(),"asd",Toast.LENGTH_SHORT).show();

                dismiss();
            }
        });

        return root;
    }
}