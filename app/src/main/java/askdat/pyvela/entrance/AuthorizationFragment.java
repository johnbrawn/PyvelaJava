package askdat.pyvela.entrance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.concurrent.ExecutionException;

import askdat.pyvela.R;
import askdat.pyvela.SharedPrefsClass;
import askdat.pyvela.data.remote.DataBaseClass;
import askdat.pyvela.main.MainActivity;

public class AuthorizationFragment extends Fragment {
    private static final String TAG = "myLogs";
    private EntranceActivity Parent;
    private Button signIn, signUp,forgot;
    private EditText login, pass;
    private DataBaseClass dataBaseClass;
    private SharedPrefsClass sharedPrefsClass;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefsClass = new SharedPrefsClass();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_authorization,container,false);
        signIn = root.findViewById(R.id.auth_sign_in);
        signUp = root.findViewById(R.id.auth_sign_up);
        login = root.findViewById(R.id.auth_login);
        pass = root.findViewById(R.id.auth_password);
        forgot = root.findViewById(R.id.auth_restore_password);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String status = Entry((login.getText()).toString(),(pass.getText()).toString());
                if (status.equals("true") && login.getText().length() !=0 && pass.getText().length() != 0) {
                    sharedPrefsClass.appPrefs(getActivity());
                    sharedPrefsClass.saveBool("bool",true);
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
                else if (status.equals("false"))
                    Toast.makeText(getActivity(),"Cooper,Cooper, two Coopers",Toast.LENGTH_SHORT).show();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Parent.ReplaceFragment(new RegistrationFragment(),"RegistrationFragment");
            }
        });

        return root;
    }

    @Override
    public void onAttach(Context context) {
        this.Parent = (EntranceActivity)context;
        super.onAttach(context);
    }
    public String Entry(String login,String pass){
        dataBaseClass =new DataBaseClass();
        try {
            dataBaseClass.execute("login=" + login + "&password=" + pass, "login");
            return dataBaseClass.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return "false";
    }
}
