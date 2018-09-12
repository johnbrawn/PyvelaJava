package askdat.pyvela.main.home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import askdat.pyvela.R;
import askdat.pyvela.SharedPrefsClass;

public class EntFragment extends Fragment {

    ArrayList<EntFragmentContainer> products = new ArrayList<EntFragmentContainer>();
    EntFragmentAdapter boxAdapter;
    SharedPrefsClass sharedPrefsClass;

    CheckBox Box;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefsClass = new SharedPrefsClass();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_ent, parent, false);
        fillData();

        boxAdapter = new EntFragmentAdapter(getContext(), products);
        Box = (CheckBox)root. findViewById((R.id.cbBox));
        ListView lvMain = (ListView) root.findViewById(R.id.lvMain);
        lvMain.setAdapter(boxAdapter);
        sharedPrefsClass.appPrefs(getContext());

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Checked();
            }
        });

        return root;
    }
    void fillData() {
        products.add(new EntFragmentContainer("Математика",false));
        products.add(new EntFragmentContainer("Физика",false));
        products.add(new EntFragmentContainer("Химия",false));
        products.add(new EntFragmentContainer("Биология",false));
        products.add(new EntFragmentContainer("Қазақстан тарихы",false));
        products.add(new EntFragmentContainer("Орыс тілі",false));
        products.add(new EntFragmentContainer("Математикалы сауаттылық",false));
        products.add(new EntFragmentContainer("Оқу сауаттылығы",false));
        products.add(new EntFragmentContainer("Адам қоғам құқық",false));
        products.add(new EntFragmentContainer("Орыс тілі",false));
        products.add(new EntFragmentContainer("Математикалы сауаттылық",false));
        products.add(new EntFragmentContainer("Оқу сауаттылығы",false));
        products.add(new EntFragmentContainer("Адам қоғам құқық",false));
        products.add(new EntFragmentContainer("Адам қоғам құқық",false));
        products.add(new EntFragmentContainer("Математикалы сауаттылық",false));
        products.add(new EntFragmentContainer("Оқу сауаттылығы",false));
        products.add(new EntFragmentContainer("Адам қоғам құқық",false));
        products.add(new EntFragmentContainer("Адам қоғам құқық",false));
    }

    public void Checked(){
        CompleteRegistrationDialog registrationDialog = new CompleteRegistrationDialog();
        int count = 0;
        for (EntFragmentContainer p : boxAdapter.getBox()) {
            if (p.box) {
                count += 1;
            }
        }

        registrationDialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
        if (count == 1){
            Toast.makeText(getContext(), "1", Toast.LENGTH_LONG).show();
            //if (sharedPrefsClass.sharedPrefs.getString("PhoneNumber", "asda") == "asda") {
            registrationDialog.show(getFragmentManager(), "Dialogs show");
            registrationDialog.setTargetFragment(EntFragment.this, 2);
//                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//
//                        .setAction("Action", null).show();
//
//            StartTestDialog startTestDialog = new StartTestDialog();
//            startTestDialog.show(getFragmentManager(), "Dialog Show");
        }
        else if (count == 2 ) {
            Toast.makeText(getContext(), "2", Toast.LENGTH_LONG).show();
            //if (sharedPrefsClass.sharedPrefs.getString("PhoneNumber", "asda") == "asda") {
            registrationDialog.show(getFragmentManager(), "Dialogs show");
            registrationDialog.setTargetFragment(EntFragment.this, 2);
//                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//
//                        .setAction("Action", null).show();

        }
    }
}
