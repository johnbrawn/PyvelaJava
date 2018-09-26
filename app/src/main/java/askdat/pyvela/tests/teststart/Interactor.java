package askdat.pyvela.tests.teststart;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static askdat.pyvela.tests.Args.*;


import askdat.pyvela.tests.teststart.Connector.*;


public class Interactor implements ActivityListener {

    private final int UNT_SINGLE = 1;

    private final int UNT_FULL = 2;

    private final int COMPLICATED_EXERCISES_AMOUNT = 10;

    private final int SIMPLE_EXERCISES_AMOUNT = 20;

    private final int UNT_SUBJECTS_AMOUNT = 5;

    private List<Exercise[]> mExercises;

    private int[] mSubjectsId;

    private int mCurrentSubject;

    private InteractorListener mInteractorListener;

    private LinkedList<ExerciseFragment> mFragmentsSpotter;

    public Interactor(@NonNull int[] subjectsId) {

        mFragmentsSpotter = new LinkedList<>();

        mSubjectsId = subjectsId;

        // Check the length of subjectsId
        if (subjectsId.length == 1 || subjectsId.length == 5) {

            mExercises = new ArrayList<>();

            // Populate the mExercises
            for (int i = 0; i < subjectsId.length; i++) {

                int id = subjectsId[i];

                if (isCompulsory(id)) {
                    mExercises.add(newExercisesArray(SIMPLE_EXERCISES_AMOUNT, id));
                } else if (isElective(id)) {
                    mExercises.add(newExercisesArray(SIMPLE_EXERCISES_AMOUNT + COMPLICATED_EXERCISES_AMOUNT, id));
                } else {
                    throw new RuntimeException("Interactor: subjectsId[" + String.valueOf(id) + "] is invalid");
                }
            }
        } else {
            throw new RuntimeException("Interactor subjectsId.length = " + String.valueOf(subjectsId.length) + " . Invalid");
        }
    }

    public void addListener(InteractorListener listener) {
        this.mInteractorListener = listener;
    }

    public String[] getSubjects() {

        String[] subjects = new String[mSubjectsId.length];
        for (int i = 0; i < mSubjectsId.length; i++) {
            subjects[i] = SUBJECTS[i];
        }
        subjects[0] = "Математикалық сауаттылық";
        return subjects;
    }

    public int getCurrentAmountOfExercises() {
        return mExercises.get(mCurrentSubject).length;
    }

    public String[] getExercise(int exercisePos) {
        return mExercises.get(mCurrentSubject)[exercisePos].getExercise();
    }

    public int[] getSelectedAnswersIndexes(int exercisePos) {
        return mExercises.get(mCurrentSubject)[exercisePos].getSeletedIndexes();
    }

    public void select(int exercisePos, int answerPos) {
        mExercises.get(mCurrentSubject)[exercisePos].select(answerPos);
    }

    public void deselect(int exercisePos, int answerPos) {
        mExercises.get(mCurrentSubject)[exercisePos].deselect(answerPos);
    }

    public boolean isFull(int exercisePos) {
        return mExercises.get(mCurrentSubject)[exercisePos].isFull();
    }

    public boolean isSelected(int exercisePos, int answerPos) {
        return mExercises.get(mCurrentSubject)[exercisePos].isSelected(answerPos);
    }

    public int getCurrentSubject() {
        return mCurrentSubject;
    }

    private boolean isCompulsory(int index) {
        return 0 <= index & index <= 2;
    }

    private boolean isElective(int index) {
        return 3 <= index & index <= 10;
    }

    private Exercise[] newExercisesArray(int length, int id) {

        Exercise[] exercises = new Exercise[length];

        if (id == MATH_LITERACY) {
            exercises[0] = new Exercise("Сандар белгілі бір заңдылық бойынша орналасқан." +
                "Сұрақ белгісінің орнындағы санды табыңыз: 289; 169; 109; 79; 64; ?",
                "50,5", "53,5", "48", "65,5", "56,5");

            exercises[1] = new Exercise("Құты мен стаканның жалпы сыйымдылығы құмыраның сыйымдылығына тең. " +
                "ұтының сыйымдылығы стакан мен кесенің  жалпы сыйымдылығына тең." +
                "Үш кесенің сыйымдылығы екі құмыраның сыйымдылығына тең. Кесенің сыйымдылығын табыңыз.",
                "7 стакан",
                "5 стакан",
                "3 стакан",
                "4 стакан",
                "6 стакан");

            exercises[2] = new Exercise("420 саны 5:7:9 тура пропорция бойынша бөлінген. Ең үлкен бөлігінен ең кіші бөлігінің айырмасын табыңыз.",
                "80",
                "110",
                "100",
                "70",
                "90");

            exercises[3] = new Exercise("Ағаштың берілген массасының 20%-ынан қағаз дайындалады. Оқушының бір дәптері 30 г, ал кітап одан 16 есе ауыр. Сыныпта 30 оқушының әрқайсысына 1 дәптерден және 1 кітаптан дайындау үшін барлығы неше кг ағаш қажет?",
                "67,2 кг",
                "76,5 кг",
                "75,2 кг",
                "70 кг",
                "70,8 кг");

            exercises[4] = new Exercise("Квадрат пен теңқабырғалы үшбұрыштың әрқайсысы бірдей n дөңгелектермен толтырылған (көршілес дөңгелектер өзара жанасқан). Егер үшбұрыштың ішінде 625 дөңгелек бар болса, онда квадраттың бір ғана қабырғасымен неше дөңгелек жанасып тұрғанын анықтаңыз.",
                "24",
                "25",
                "50",
                "23",
                "45");

            for (int i = 5; i < exercises.length; i++) {
                exercises[i] = new Exercise(getData());
            }
        }
        else if (id == READING_LITERACY) {

        }
        else {
            for (int i = 0; i < exercises.length; i++) {
                exercises[i] = new Exercise(getData());
            }
        }
        return exercises;
    }

    private String[] getData() {
        return new String[] {
                        "Question", "Answer1", "Answer2",
                        "Answer3", "Answer4", "Answer5"
        };
    }

    public void addFragment(ExerciseFragment fragment) {
        mFragmentsSpotter.add(fragment);
        Log.d(TAG, "Fragment Added: " + fragment.getPosition());
    }

    public void removeFragment(ExerciseFragment fragment) {
        mFragmentsSpotter.remove(fragment);
        Log.d(TAG, "Fragment Removed: " + fragment.getPosition());
    }

    private ExerciseFragment[] getFragments() {
        return mFragmentsSpotter.toArray(new ExerciseFragment[mFragmentsSpotter.size()]);
    }

    private boolean isFirstLaunch = true;

    /*
       You must assign new value to mCurrentSubject after outOfRange
       variable computation and before fragments update
       When you invoke fragments Update() method, it call mInteractor mehtod which use
       mCurrentSubject.
    */

    @Override
    public void onSpinnerItemSelected(int newSubjectIndex, int viewPagerCurrentPos) {

        if (isFirstLaunch) {
            isFirstLaunch = false;
        } else {

            boolean outOfRange = isCompulsory(newSubjectIndex) &&
                    isElective(mCurrentSubject) &&
                    viewPagerCurrentPos > SIMPLE_EXERCISES_AMOUNT;

            boolean extendRange = isCompulsory(mCurrentSubject) && isElective(newSubjectIndex);

            boolean changeCount = isCompulsory(newSubjectIndex) && isElective(mCurrentSubject);

            mCurrentSubject = newSubjectIndex;

            updateFragments();

            if (outOfRange) {
                mInteractorListener.onResetAdapter();
            }
            if (extendRange) {
                mInteractorListener.onExtendAdapter();
            }
            if (changeCount){
                mInteractorListener.onChangeCount();
            }

        }
    }

    public void updateFragments(){
        for (ExerciseFragment fragment: getFragments()) {
            if (fragment != null && fragment.getPosition() < getCurrentAmountOfExercises()) {
                fragment.update();
            }
        }
    }
}
