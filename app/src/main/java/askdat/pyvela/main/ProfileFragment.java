package askdat.pyvela.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import askdat.pyvela.entrance.EntranceActivity;
import askdat.pyvela.R;
import askdat.pyvela.entrance.SplashActivity;

public class ProfileFragment extends Fragment {

    private ImageView profileImage;
    private MainActivity Parent;
    private Button exit;
    private SplashActivity splashActivity;

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
        splashActivity = new SplashActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.frag_profile,container,false);
        profileImage = root.findViewById(R.id.frag_profile_image);
        exit = root.findViewById(R.id.frag_profile_button);

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Parent.ReplaceFragment(new ImageChangeFragment());
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splashActivity.saveText();
                Intent intent = new Intent(getActivity(),EntranceActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }

}
