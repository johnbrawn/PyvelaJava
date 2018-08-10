package askdat.pyvela.data.remote;

import askdat.pyvela.data.local.UntTest.Subject;
import askdat.pyvela.data.local.UntTest;

public class TestStartData{

    private static TestStartData instance;

    private TestStartData() {

    }

    public static TestStartData newInstance() {

        if (instance == null) {
            synchronized (TestStartData.class) {
                if (instance == null) {
                    instance = new TestStartData();
                    return instance;
                }
            }
        }
        return null;
    }

    public void loadUntFull() {

    }

    public void loadUntSingle(Subject subject1, Subject subject2) {

    }

}
