package askdat.pyvela.teststart;

import android.widget.Button;
import android.widget.LinearLayout;

import askdat.pyvela.IBasePresenter;
import askdat.pyvela.IBaseView;

public interface ITestStartContract {

    interface IView extends IBaseView<IPresenter>{

        void showQuestion(String content);

        void showAnswer(String content);

        void showSelectedAnswer(int id);

    }

    interface IPresenter extends IBasePresenter{

        void onCreateView(int Position, LinearLayout root);

        void onTestComplete();

        void onDestroyView();

        Button getAnswerView();

    }
}
