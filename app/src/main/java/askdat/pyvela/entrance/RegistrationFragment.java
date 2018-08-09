package askdat.pyvela.entrance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import askdat.pyvela.R;
import askdat.pyvela.data.remote.DataBaseClass;
import askdat.pyvela.entrance.EntranceActivity;
import askdat.pyvela.main.MainActivity;

public class RegistrationFragment extends Fragment {

    Button SignUp;
    EditText email, password1, password2;
    DataBaseClass dataBaseClass;
    EntranceActivity entranceActivity;
    private EntranceActivity Parent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBaseClass = new DataBaseClass();
        entranceActivity = new EntranceActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.registration, container, false);

        email = (EditText) root.findViewById(R.id.email);
        password1 = (EditText) root.findViewById(R.id.Password1);
        password2 = (EditText) root.findViewById(R.id.Password2);
        SignUp = (Button) root.findViewById(R.id.SignUp);

        SignUp.setOnClickListener(listener);

        return root;

    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            boolean status = RegistrationEntry((email.getText()).toString(), (email.getText()).toString());
            if (email.length() == 0 || password1.length() == 0 || password2.length() == 0) {
                Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();
            } else if (!isValidEmailAddress(email.getText().toString())) {
                Toast.makeText(getActivity(), "2", Toast.LENGTH_SHORT).show();
            } else if (!password2.getText().toString().equals(password1.getText().toString())) {
                Toast.makeText(getActivity(), "3", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        }
    };

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public boolean RegistrationEntry(String login, String pass) {
        try {
            dataBaseClass.execute("login=" + login + "&password=" + pass);
            dataBaseClass.get();
        } catch (InterruptedException | IllegalStateException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return true;
    }
    @Override
    public void onAttach(Context context) {
        this.Parent = (EntranceActivity)context;
        super.onAttach(context);
    }
}