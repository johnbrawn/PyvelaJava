package askdat.pyvela.main.profile;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import askdat.pyvela.R;
import askdat.pyvela.SharedPrefsClass;

public class ChangeNameDialogFragment extends DialogFragment {

    public interface onChangeName{
        void onChange(String name);
    }

    onChangeName mOnChangeName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Nurlan");
        final SharedPrefsClass sharedPrefsClass = new SharedPrefsClass();
        View root = inflater.inflate(R.layout.dialog_profile_change_name, container, false);
        final EditText name = root.findViewById(R.id.change_name);
        final EditText surname = root.findViewById(R.id.change_surname);
        Button save = root.findViewById(R.id.save);
        final Button cancel = root.findViewById(R.id.cancel);

        sharedPrefsClass.appPrefs(getActivity());

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnChangeName.onChange((name.getText()).toString() + ' ' + (surname.getText()).toString());
                sharedPrefsClass.saveStr("name", (name.getText()).toString() + ' ' + (surname.getText()).toString());
                dismiss();
            }
        });

        return root;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onAttach(Context context) {
       mOnChangeName = (onChangeName)getTargetFragment();
        super.onAttach(context);
    }
}