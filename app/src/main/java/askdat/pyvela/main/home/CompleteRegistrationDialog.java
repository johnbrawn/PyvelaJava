package askdat.pyvela.main.home;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import askdat.pyvela.R;
import askdat.pyvela.SharedPrefsClass;

public class CompleteRegistrationDialog extends DialogFragment {

    // Class
    SharedPrefsClass sharedPrefsClass;

    // Widgets
    EditText NumberPhone;
    EditText IIN;
    Button Back;
    Button Start;

    String PhoneText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefsClass = new SharedPrefsClass();
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup parent, final Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);

        getDialog().setTitle("Тіркелу");
        View root = inflater.inflate(R.layout.dialog_complete_registration, parent, false);

        NumberPhone = (EditText) root.findViewById(R.id.NumberPhone);
        IIN = (EditText) root.findViewById(R.id.IIN);
        Back = (Button) root.findViewById(R.id.Back);
        Start = (Button) root.findViewById(R.id.Start);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        sharedPrefsClass.appPrefs(getContext());

            Start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    PhoneText = NumberPhone.getText().toString();
                    String IINText = IIN.getText().toString();

                    if ((validPhone(PhoneText) == false) && IINText.length() != 12) {
                        Toast.makeText(getActivity(), "Pattern NO", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getActivity(), "Yes", Toast.LENGTH_LONG).show();

                        sharedPrefsClass.saveStr("PhoneNumber", PhoneText);
                        sharedPrefsClass.saveStr("IIN", IINText);
                        dismiss();
                    }
                }
            });

            return root;
        }

        public boolean validPhone(String number){
            Pattern p = Pattern.compile("(\\+)(7)(\\d{10})");
            Matcher m = p.matcher(number);
            return m.matches();
        }
    }