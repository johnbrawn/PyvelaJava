package askdat.pyvela.data;

public interface IExerciseDataSource {

    interface ILoadExercisesCallback {

        void onDataLoaded(Exercise exercise);

        void onDataNotAvailable(boolean isAvailable);
    }

    interface IGetExercisesCallback {

        void onDataTransfered(Exercise exercise);

        void onDataNotAvialable(boolean isAvailable);
    }

    void loadExercises(ILoadExercisesCallback callback);

    void getExercises(IGetExercisesCallback callback);

    interface IGetSelectedAnswerCallback {

        void onDataTransfered(int id);
    }

    void addSelectedAnswer(int id);

    void complete();


}


