package askdat.pyvela.tests.testhistory;

import askdat.pyvela.tests.testhistory.TestsHistoryInteractor.OnDataLoadedListener;
import java.util.ArrayList;

public class TestsHistoryPresenter implements TestsHistoryContract.Presenter {

    private TestsHistoryContract.View mView;
    private TestsHistoryInteractor mInteractor;

    public TestsHistoryPresenter(TestsHistoryContract.View view) {
        mView = view;
        mInteractor = new TestsHistoryInteractor(listener);
    }

    @Override
    public void OnResume() {
        mInteractor.getItems();
    }

    @Override
    public void OnDestroy() {
        mView = null;
    }

    OnDataLoadedListener listener = new OnDataLoadedListener() {
        @Override
        public void onDataLoaded(ArrayList<TestInfo> data) {
            mView.showList(data);
        }
    };
}
