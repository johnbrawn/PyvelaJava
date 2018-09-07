package askdat.pyvela.tests.teststart;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import askdat.pyvela.tests.Args;

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
                    mExercises.add(newExercisesArray(SIMPLE_EXERCISES_AMOUNT));
                } else if (isElective(id)) {
                    mExercises.add(newExercisesArray(SIMPLE_EXERCISES_AMOUNT + COMPLICATED_EXERCISES_AMOUNT));
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
            subjects[i] = Args.SUBJECTS[i];
        }
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

    private Exercise[] newExercisesArray(int length) {
        Exercise[] exercises = new Exercise[length];
        for (int i = 0; i < exercises.length; i++) {
            exercises[i] = new Exercise(getData());
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
        Log.d(Args.TAG, "Fragment Added: " + fragment.getPosition());
    }

    public void removeFragment(ExerciseFragment fragment) {
        mFragmentsSpotter.remove(fragment);
        Log.d(Args.TAG, "Fragment Removed: " + fragment.getPosition());
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
