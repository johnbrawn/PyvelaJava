package askdat.pyvela.teststart;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import askdat.pyvela.R;
import askdat.pyvela.data.Exercise;

/**
 * A placeholder fragment containing a simple view.
 */
public class TestStartFragment extends Fragment implements ITestStartContract.IView{

    public static String ARG_EXERCISE_POS = "EXERCISE_POS";

    private int mPosition;

    private ITestStartContract.IPresenter mPresenter;

    private TextView mQuestion;

    private Button[] mAnswers;

    public static TestStartFragment newInstance(int Position) {
        Bundle args = new Bundle();
        args.putInt(ARG_EXERCISE_POS, Position);
        TestStartFragment fragment = new TestStartFragment();
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

        LinearLayout AnswersLayout = root.findViewById(R.id.test_start_answers);

        mPresenter.onCreateView(mPosition, mAnswersLayout);

        return root;
    }

    @Override
    public void showQuestion(String content) {

    }

    @Override
    public void showAnswer(String content) {

    }

    @Override
    public void showSelectedAnswer(int id) {

    }


    @Override
    public void setPresenter(ITestStartContract.IPresenter presenter) {
        this.presenter = presenter;
    }
}


