package askdat.pyvela.data.local;

public class TestStartData{

    private byte UNT_SINGLE = -1;

    private byte UNT_FULL = -2;

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

}
