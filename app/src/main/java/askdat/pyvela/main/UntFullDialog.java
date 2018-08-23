 package askdat.pyvela.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class UntFullDialog extends DialogFragment {

    // Class
    UntFullDialogAdapter adapter;
    ArrayList<UntFullContainer> data;

    // Widgets
    ListView listView;

    public interface Replace{
        void ReplaceText(String text1, String text2);
    }

    Replace replace;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        getDialog().setTitle("Subject");
        View root = inflater.inflate(R.layout.unt_full_dialog, parent, false);

        listView = (ListView) root.findViewById(R.id.listviews);

        data = new ArrayList<UntFullContainer>();
        data.add(new UntFullContainer("Математика", "Физика"));
        data.add(new UntFullContainer("Физика", "Химия"));
        data.add(new UntFullContainer("Математика", "География"));
        data.add(new UntFullContainer("Биология", "Химим"));
        data.add(new UntFullContainer("Дүние жүзі тарихы", "Адам қоғам құқық"));

        adapter= new UntFullDialogAdapter(getContext(), R.layout.unt_full_dialog, data);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String subject1=data.get(i).getText1();
                String subject2=data.get(i).getText2();
                replace.ReplaceText(subject1, subject2);
                dismiss();
            }
        });
        return root;
    }
    @Override
    public void onAttach(Context context) {
        replace = (Replace) getTargetFragment();
        super.onAttach(context);
    }
}