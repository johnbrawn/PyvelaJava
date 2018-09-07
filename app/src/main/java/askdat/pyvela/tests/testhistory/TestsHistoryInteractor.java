package askdat.pyvela.tests.testhistory;

import java.util.ArrayList;

public class TestsHistoryInteractor {

    OnDataLoadedListener listener;

    public TestsHistoryInteractor(OnDataLoadedListener listener) {
        this.listener = listener;
    }

    public void getItems() {
        listener.onDataLoaded(createArrayList());
    }

    private ArrayList<TestInfo> createArrayList() {

        ArrayList<TestInfo> data = new ArrayList<>();
        data.add(new TestInfo("UNT", 102));
        data.add(new TestInfo("Chemistry", 33));
        data.add(new TestInfo("Biology", 28));
        data.add(new TestInfo("UNT", 100));
        data.add(new TestInfo("UNT", 97));

        return data;
    }

    public interface OnDataLoadedListener {

        void onDataLoaded(ArrayList<TestInfo> data);
    }
}
