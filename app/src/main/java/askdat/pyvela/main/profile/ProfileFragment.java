package askdat.pyvela.main.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import askdat.pyvela.SharedPrefsClass;
import askdat.pyvela.data.local.ImageChangeData;
import askdat.pyvela.R;
import askdat.pyvela.main.Main2Activity;
import askdat.pyvela.main.MainActivity;

public class  ProfileFragment extends Fragment implements ChangeNameDialogFragment.onChangeName{

    private ImageView profileImage;
    private MainActivity Parent;
    private SharedPrefsClass sharedPrefsClass;
    private DialogFragment dialogFragment;
    private TextView name;
    private Bundle bundle;
    private Button edit;

    @Override
    public void onAttach(Context context) {
        this.Parent = (MainActivity)context;
        super.onAttach(context);
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageChangeData.getInstance();
        sharedPrefsClass = new SharedPrefsClass();
        dialogFragment = new ChangeNameDialogFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        dialogFragment.setTargetFragment(ProfileFragment.this, 1);

        View root=inflater.inflate(R.layout.frag_profile,container,false);
        profileImage = root.findViewById(R.id.frag_profile_image);
        name = root.findViewById(R.id.frag_profile_name);
        edit = root.findViewById(R.id.frag_edit);
        LinearLayout linearLayout = root.findViewById(R.id.frag_profile_linear);

        sharedPrefsClass.appPrefs(getActivity());
        int images_id = sharedPrefsClass.sharedPrefs.getInt("change_photo",0);
        String fullName = sharedPrefsClass.sharedPrefs.getString("name","Change Name");
        //String[] splitFullName = fullName.split(" ");
        name.setText(fullName);

        int[] images = ImageChangeData.instance.Images;
        profileImage.setImageResource(images[images_id]);

        //Change Photo
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Parent.ReplaceFragment(new ImageChangeFragment(),"ImageChangeFragment");
            }
        });

        //Change name
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment.show(getFragmentManager(),"dialog");
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Main2Activity.class);
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onChange(String Name) {
        name.setText(Name);
    }
}
