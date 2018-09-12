package askdat.pyvela.main.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import askdat.pyvela.R;
import askdat.pyvela.main.MainActivity;

public class ChooseFragment extends Fragment {

    private MainActivity parent;


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


       @Override
  public void onAttach(Context context) {
           parent = (MainActivity) context;
           super.onAttach(context);
       }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.frag_test_choose, container, false);

//        toolbar = (Toolbar)root. findViewById(R.id.toolbar);
//        parent.setSupportActionBar(toolbar);
//        ActionBar actionBar =parent.getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) root.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout)root. findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        
        return root;
    }
    private void setupViewPager(ViewPager viewPager) {
        ChooseFragmentViewPager adapter = new ChooseFragmentViewPager(parent.getSupportFragmentManager());
        adapter.addFragment(new EntFragment(), "Ent");
        adapter.addFragment(new VoudFragment(), "Voud");

        viewPager.setAdapter(adapter);
    }
}
