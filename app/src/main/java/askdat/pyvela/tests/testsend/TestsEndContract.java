package askdat.pyvela.tests.testsend;

public interface TestsEndContract {

    interface View {

        void setPresenter(Presenter presenter);

        void showResults(int maxScore, Result[] results);
    }

    interface Presenter {

        void onStart();

        void onDestroy();
    }
}
