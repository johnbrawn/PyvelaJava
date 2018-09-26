package askdat.pyvela.tests.testsend;

public class TestsEndPresenter implements TestsEndContract.Presenter {

    private TestsEndContract.View mView;

    public TestsEndPresenter(TestsEndContract.View view) {

        mView = view;
    }


    @Override
    public void onStart() {

        Result[] results = new Result[] {
            new Result("Математикалық сауаттылық", 18, 20),
            new Result("Оқу сауаттылығы", 14, 20),
            new Result("Қазақстан тарихы", 15, 20),
            new Result("Математика", 36, 40),
            new Result("Физика", 31, 40)
        };

        mView.showResults(114, results);
    }

    @Override
    public void onDestroy() {
        mView = null;
    }
}
