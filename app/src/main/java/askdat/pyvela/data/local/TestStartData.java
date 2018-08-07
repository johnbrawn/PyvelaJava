package askdat.pyvela.data.local;


import askdat.pyvela.data.IExerciseDataSource;

public class TestStartData implements IExerciseDataSource {

    private static TestStartData instance;

    protected TestStartData() {

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


    @Override
    public void loadExercises(ILoadExercisesCallback callback) {
    }

    @Override
    public void getExercises(IGetExercisesCallback callback) {

    }

    @Override
    public void addSelectedAnswer(int id) {

    }

    @Override
    public void complete() {

    }
}
