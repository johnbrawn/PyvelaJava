package askdat.pyvela.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.SupportActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import askdat.pyvela.R;

public class UNT_FULL extends Fragment{

    ChooseDialog  dialog;

    FragmentTransaction ftrans;

    public TextView subject4;

    public TextView subject5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        
        View root =inflater.inflate(R.layout.unt_full,parent,false);

        subject4=(TextView)root.findViewById(R.id.textView4);
        subject5=(TextView)root.findViewById(R.id.textView5);
        ImageView choose=(ImageView)root.findViewById(R.id.chooseSubject);
        dialog = new ChooseDialog();

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ftrans = getFragmentManager().beginTransaction();
                dialog.show(getFragmentManager(),"asd");
                Toast.makeText(getActivity(),"Asdad",Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
}