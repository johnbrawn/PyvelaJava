package askdat.pyvela.tests.testhistory;

import java.util.ArrayList;

public class TestsHistoryInteractor {

    private Mediator mMediator;

    private ArrayList<TestHistoryData> mData;

    public TestsHistoryInteractor(Mediator mediator) {
        this.mMediator = mediator;
        mData = new ArrayList<>();

        ArrayList<String[]> data = new ArrayList<>();

        data.add(new String[]{
            "Math", "27/30"
        });
        data.add(new String[] {
            "History of Kazakhstan", "18/20"
        });
        data.add(new String[] {
            "Chemisty", "26/30"
        });


        mData = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            String subject = data.get(i)[0];
            String indicator = " " + String.valueOf(subject.charAt(0)) + " ";
            String score = data.get(0)[1];
            mData.add(new TestHistoryData(indicator, subject, score));
        }
    }

    public void getItems() {
        mMediator.onDataLoaded(mData);
    }

    public void isDataAvailable() {

        if (mData == null) {
            mMediator.onDataNotAvailable();
        }
        else {
            mMediator.onDataAvailable();
        }
    }

    public interface Mediator {

        void onDataLoaded(ArrayList<TestHistoryData> data);

        void onDataAvailable();

        void onDataNotAvailable();
    }
}
