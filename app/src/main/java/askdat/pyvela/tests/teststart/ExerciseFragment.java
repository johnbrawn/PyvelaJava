package askdat.pyvela.tests.teststart;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import askdat.pyvela.R;
import askdat.pyvela.tests.Args;

public class ExerciseFragment extends Fragment {

    public static String ARG_EXERCISE_POS = "EP";

    private int mPosition;

    private TextView mQuestion;

    private Button[] mAnswers;

    private Interactor mInteractor;

    public static ExerciseFragment newInstance(int Position, Interactor interactor) {

        Bundle args = new Bundle();
        args.putInt(ARG_EXERCISE_POS, Position);
        ExerciseFragment fragment = new ExerciseFragment();
        fragment.setArguments(args);

        fragment.mInteractor = interactor;

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        Bundle args = getArguments();

        if (args !=  null) {
            mPosition = args.getInt(ARG_EXERCISE_POS);
        } else {
            throw new RuntimeException("ExerciseFragment: getArguments() returned null");
        }

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mInteractor.addFragment(this);

        View root =  inflater.inflate(R.layout.frag_exercise, container, false);

        mQuestion = root.findViewById(R.id.test_start_question);
        LinearLayout answersLayout = root.findViewById(R.id.test_start_answers);

        mAnswers = new Button[5];

        for (int i = 0; i < 5; i++) {
            Button answer = getAnswerView();
            mAnswers[i] = answer;
            answer.setOnClickListener(OnAnswerClick);
            answersLayout.addView(answer);
        }
        return root;
    }

    @Override
    public void onStart() {

        populateFragment(false);
        super.onStart();
    }

    @Override
    public void onDestroyView() {

        mInteractor.removeFragment(this);
        super.onDestroyView();
    }


    private Button getAnswerView() {

        Button answer = (Button)getLayoutInflater().inflate(R.layout.temp_answer, null);
        answer.setTextColor(Color.BLACK);
        float scale = getResources().getDisplayMetrics().density;
        int dp = (int) (5*scale + 0.5f);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(dp, dp, dp, dp);
        answer.setLayoutParams(lp);

        return answer;
    }


    private View.OnClickListener OnAnswerClick = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

        int answerPos = getAnswerPos(view);

        // if view is selected - unselect
        if (mInteractor.isSelected(mPosition, answerPos))
        {
            mInteractor.deselect(mPosition, answerPos);
            setUnselected(view);
        }
        else
        {
            if (isSimple())
            {
                if (!mInteractor.isFull(mPosition))
                {
                    mInteractor.select(mPosition, answerPos);
                    setSelected(view);
                }
                else
                {
                    int[] indexes = mInteractor.getSelectedAnswersIndexes(mPosition);
                    for (int ind: indexes)
                    {
                        mInteractor.deselect(mPosition, ind);
                        setUnselected(mAnswers[ind]);
                    }
                    mInteractor.select(mPosition, answerPos);
                    setSelected(view);
                }
            }
            else
            {
                showMessage();
            }
        }
        }
    };

    private void setSelected(View view) {
        Button button = (Button)view;
        button.setTextColor(Color.WHITE);
        button.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC);
    }

    private void setUnselected(View view) {
        Button button = ((Button) view);
        button.setTextColor(Color.BLACK);
        button.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC);
    }

    public int getPosition() {
        return mPosition;
    }

    private int getAnswerPos(View view) {
        for (int i = 0; i < mAnswers.length; i++) {
            if (mAnswers[i] == view) {
                return i;
            }
        }
        return -1;
    }

    private boolean isComplicated() {
        return 20 < mPosition && mPosition < 31;
    }

    private boolean isSimple() {
        return -1 < mPosition && mPosition < 21;
    }

    private void showMessage() {
        mQuestion.setText("UNSELECT");
    }

    private void populateFragment(boolean isUpdate) {

        // populate fields
        String[] exercise = mInteractor.getExercise(mPosition);
        mQuestion.setText(exercise[0]);

        for (int i = 0; i < 5; i++) {
            mAnswers[i].setText(exercise[i+1]);
        }

        if (isUpdate) {
            for (Button answer: mAnswers) {
                setUnselected(answer);
            }
        }

        // get selected answer from
        int[] indexes = mInteractor.getSelectedAnswersIndexes(mPosition);
        for (int i: indexes) {
            setSelected(mAnswers[i]);
        }
    }

    public void update() {
        populateFragment(true);
    }
}





