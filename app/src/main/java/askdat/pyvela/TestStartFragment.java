package askdat.pyvela;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class TestStartFragment extends Fragment {
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.frag_test_start, container, false);
        button = root.findViewById(R.id.test_answer1);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String s = button.getText().toString() + "CHEBUREK";
                button.setText(s);
            }
        });
        return root;
    }
}
