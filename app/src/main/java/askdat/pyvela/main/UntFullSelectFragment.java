package askdat.pyvela.main;

        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import askdat.pyvela.R;
        import askdat.pyvela.SharedPrefsClass;

public class UntFullSelectFragment extends Fragment implements UntFullDialog.Replace{

    //Class
    SharedPrefsClass sharedPrefsClass;

    //Widgets
    private TextView subject4;
    private TextView subject5;
    private Button StartTest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefsClass = new SharedPrefsClass();}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.unt_full, parent, false);

        subject4 = (TextView) root.findViewById(R.id.textView4);
        subject5 = (TextView) root.findViewById(R.id.textView5);
        StartTest = (Button) root.findViewById(R.id.StartTest);
        ImageView choose = (ImageView) root.findViewById(R.id.chooseSubject);

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UntFullDialog dialog = new UntFullDialog();
                dialog.show(getFragmentManager(), "Dialog Show ");
                dialog.setTargetFragment(UntFullSelectFragment.this, 2);
                Toast.makeText(getActivity(), "Asdad", Toast.LENGTH_SHORT).show();
            }
        });
        sharedPrefsClass.appPrefs(getContext());


            StartTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CompleteRegistrationDialog registrationDialog = new CompleteRegistrationDialog();

                    if (sharedPrefsClass.sharedPrefs.getString("PhoneNumber", "asda") == "asda") {
                    registrationDialog.show(getFragmentManager(), "Dialogs show");
                    registrationDialog.setTargetFragment(UntFullSelectFragment.this, 2);
                }}
            });

            return root;
        }

    @Override
    public void ReplaceText(String text1, String text2) {
        subject4.setText(text1);
        subject5.setText(text2);
    }
}

