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
import askdat.pyvela.R;
import askdat.pyvela.data.remote.DataBaseClass;
import askdat.pyvela.main.MainActivity;
import askdat.pyvela.main.RegistrationFragment;

public class AuthorizationFragment extends Fragment {
    private static final String TAG = "myLogs";
    private EntranceActivity Parent;
    private SplashActivity splashActivity;
    private boolean check;
    private Button signIn, signUp;
    private EditText login, pass;
    private DataBaseClass dataBaseClass;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBaseClass =new DataBaseClass();
        splashActivity = new SplashActivity();
        Parent = new EntranceActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_authorization,container,false);
        signIn = root.findViewById(R.id.auth_sign_in);
        signUp = root.findViewById(R.id.auth_sign_up);
        login = root.findViewById(R.id.auth_login);
        pass = root.findViewById(R.id.auth_password);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean status = Entry((login.getText()).toString(),(pass.getText()).toString());
                if (status) {
                    splashActivity.appPreferences(getActivity());
                    splashActivity.saveText(true);
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
                Toast.makeText(getActivity(),"Cooper,Cooper, two Coopers",Toast.LENGTH_SHORT).show();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Parent.ReplaceFragment(new RegistrationFragment());
                Toast.makeText(getActivity(),"Cooper,Cooper, two Coopers",Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    @Override
    public void onAttach(Context context) {
        this.Parent = (EntranceActivity)context;
        super.onAttach(context);
    }
    public boolean Entry(String login,String pass){
        dataBaseClass.execute("login="+ login+"&password="+pass);
        return true;
    }
}
