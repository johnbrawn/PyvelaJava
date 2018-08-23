package askdat.pyvela.main;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import askdat.pyvela.R;


public class BySubjectFragment extends Fragment {

    BySubjectAdapter adapter;
    ArrayList<BySubjectContainer> data;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.by_subject, container, false);

        listView = (ListView) root.findViewById(R.id.subjectListView);

        data = new ArrayList<BySubjectContainer>();

        data.add(new BySubjectContainer("Математика", R.drawable.pyvela_logo ));
        data.add(new BySubjectContainer("Физика", R.drawable.pyvela_logo ));
        data.add(new BySubjectContainer("Химия", R.drawable.pyvela_logo ));
        data.add(new BySubjectContainer("Биология", R.drawable.pyvela_logo ));


        adapter= new BySubjectAdapter(getContext(), R.layout.by_subject, data);
        listView.setAdapter(adapter);
        return root;


    }
}