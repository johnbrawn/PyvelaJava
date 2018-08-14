package askdat.pyvela.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import askdat.pyvela.SharedPrefsClass;
import askdat.pyvela.data.local.ImageChangeData;
import askdat.pyvela.entrance.EntranceActivity;
import askdat.pyvela.R;
import askdat.pyvela.entrance.SplashActivity;

public class  ProfileFragment extends Fragment {

    private ImageView profileImage;
    private MainActivity Parent;
    private SharedPrefsClass sharedPrefsClass;
    private DialogFragment dialogFragment;
    private TextView textView;
    private Bundle bundle;

    @Override
    public void onAttach(Context context) {
        Log.d("NUrlan","onCreateV");
        this.Parent = (MainActivity)context;
        super.onAttach(context);
    }

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("NUrlan","onCreate");
        super.onCreate(savedInstanceState);
        ImageChangeData.getInstance();
        sharedPrefsClass = new SharedPrefsClass();
        dialogFragment = new ChangeNameDialogFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        Log.d("NUrlan","onCreateView");

        View root=inflater.inflate(R.layout.frag_profile,container,false);
        profileImage = root.findViewById(R.id.frag_profile_image);
        textView = root.findViewById(R.id.frag_profile_name);

        sharedPrefsClass.appPrefs(getActivity());
        int images_id = sharedPrefsClass.sharedPrefs.getInt("change_photo",0);
        String name = sharedPrefsClass.sharedPrefs.getString("name","Change");

        textView.setText(name);

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
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFragment.show(getFragmentManager(),"dialog");
            }
        });
        return root;
    }
}
