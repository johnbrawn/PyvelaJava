package askdat.pyvela.teststart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import askdat.pyvela.R;
import askdat.pyvela.data.IExerciseDataSource;
import askdat.pyvela.data.local.TestStartData;

public class TestStartPresenter implements ITestStartContract.IPresenter {

    private int STANDART_EXERCISE_RANGE = 20;

    private ITestStartContract.IView mView;

    private TestStartData mData;

    private LayoutInflater inflater;

    public TestStartPresenter(ITestStartContract.IView view) {

        this.mView = view;
        this.mData = TestStartData.newInstance();
    }

    @Override
    public void onCreateView(int position, LinearLayout layout) {

        if (position <= STANDART_EXERCISE_RANGE) {

            for (int i = 0; i < 5; i++) {
                Button answer = getAnswerView();
                answer.setOnClickListener(AnswerListener);
            }
        } else {
            for (int i = 0; i < 5; i++) {
                Button answer = getAnswerView();
                answer.setOnClickListener(AnswerListener);
            }
        }
    }

    @Override
    public void onTestComplete () {

    }

    @Override
    public void onDestroyView () {

    }

    @Override
    public Button getAnswerView () {
        return (Button) inflater.inflate(R.layout.answer, null);

    }

    @Override
    public void start () {

    }


    private View.OnClickListener AnswerListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
}