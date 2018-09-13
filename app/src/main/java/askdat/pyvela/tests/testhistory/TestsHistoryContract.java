package askdat.pyvela.tests.testhistory;

import java.util.ArrayList;

public interface TestsHistoryContract {

    interface View {

        void showList(ArrayList<TestHistoryData> items);

        void showVoid(String message);
    }

    interface Presenter {

        void OnResume();

        void OnDestroy();
    }
}
