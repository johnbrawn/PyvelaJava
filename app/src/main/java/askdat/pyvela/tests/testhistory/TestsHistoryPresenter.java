package askdat.pyvela.tests.testhistory;

import askdat.pyvela.data.remote.TestStartData;
import askdat.pyvela.tests.testhistory.TestsHistoryInteractor.Mediator;
import java.util.ArrayList;

public class TestsHistoryPresenter implements TestsHistoryContract.Presenter {

    private TestsHistoryContract.View mView;
    private TestsHistoryInteractor mInteractor;

    public TestsHistoryPresenter(TestsHistoryContract.View view) {
        mView = view;
        mInteractor = new TestsHistoryInteractor(new Mediator() {

            @Override
            public void onDataLoaded(ArrayList<TestHistoryData> data) {
                mView.showList(data);
            }

            @Override
            public void onDataAvailable() {
                mInteractor.getItems();
            }

            @Override
            public void onDataNotAvailable() {
                mView.showVoid("No completed tests");
            }
        });
    }

    @Override
    public void OnResume() {
        mInteractor.isDataAvailable();
    }

    @Override
    public void OnDestroy() {
        mView = null;
    }
}
