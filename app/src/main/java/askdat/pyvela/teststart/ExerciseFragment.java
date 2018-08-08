package askdat.pyvela.teststart;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import askdat.pyvela.R;
import askdat.pyvela.data.local.TestStartData;

public class ExerciseFragment extends Fragment{

    public static String ARG_EXERCISE_POS = "EXERCISE_POS";

    private int mPosition;

    private TextView mQuestion;

    private TestStartData mDataManager;

    @Override
    public void onAttach(Context context) {
        mDataManager = ((TestStartActivity)context).getDataManager();
        super.onAttach(context);
    }

    public static ExerciseFragment newInstance(int Position) {

        Bundle args = new Bundle();
        args.putInt(ARG_EXERCISE_POS, Position);
        ExerciseFragment fragment = new ExerciseFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mPosition = savedInstanceState.getInt(ARG_EXERCISE_POS, -1);
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =  inflater.inflate(R.layout.frag_test_start, container, false);

        mQuestion = root.findViewById(R.id.test_start_question);

        LinearLayout answersLayout = root.findViewById(R.id.test_start_answers);

        //Create and add Answer buttons
        for (int i = 0; i < 5; i++) {
            Button answer = getAnswerView();
            answer.setOnClickListener(OnAnswerClick);
            answersLayout.addView(answer);
        }
        if (mPosition > 20) {
            for (int i = 0; i < 3; i++) {
                Button answer = getAnswerView();
                answer.setOnClickListener(OnAnswerClick);
                answersLayout.addView(answer);
            }
        }

        return root;
    }

    private Button getAnswerView() {

        return (Button)getLayoutInflater().inflate(R.layout.answer, null);
    }

    private View.OnClickListener OnAnswerClick = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            if (view.isClickable()) {
                view.setClickable(false);
            }
            else {
                view.setClickable(true);
            }
        }
    };
}


