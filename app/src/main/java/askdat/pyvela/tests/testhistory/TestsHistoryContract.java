package askdat.pyvela.tests.testhistory;

import java.util.ArrayList;

public interface TestsHistoryContract {

    interface View {

        void showList(ArrayList<TestInfo> items);
    }

    interface Presenter {

        void OnResume();

        void OnDestroy();
    }
}
