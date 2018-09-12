package askdat.pyvela.main.home;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import askdat.pyvela.R;

public class StartTestDialog extends DialogFragment {
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup parent, final Bundle savedInstanceState) {
        super.onCreateView(inflater, parent, savedInstanceState);
        getDialog().setTitle("Are you sure");
        View root = inflater.inflate(R.layout.dialog_start_test, parent, false);
        return root;
    }
}
