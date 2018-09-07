package askdat.pyvela.tests.teststart;

import java.util.ArrayList;

import askdat.pyvela.tests.Args;

public class Exercise {

    private String[] mData;

    private boolean[] mSelects;

    public Exercise(String[] data) {

        mData = data;
        mSelects = new boolean[data.length - Args.QUESTION_AMOUNT];
    }

    public String[] getExercise() {
        return mData;
    }

    public void select(int pos) {
        mSelects[pos] = true;
    }

    public void deselect(int pos) {
        mSelects[pos] = false;
    }

    public boolean isFull() {

        int count = getCount();

        if (isSimple()) {
            return count == Args.SIMPLE_ANSWERS_MAX_SELECTS;
        }
        else if(isComplicated()) {
            return count == Args.COMPLICATED_ANSWERS_MAX_SELECTS;
        }
        else {
            throw new RuntimeException("Exercise is not simple or complicated.");
        }

    }

    public boolean isSelected(int pos) {
        return mSelects[pos];
    }

    public int[] getSeletedIndexes() {

        int last = 0;
        int[] indexes = new int[getCount()];
        for (int i = 0; i < mSelects.length; i++) {
            if (mSelects[i]){
                indexes[last++] = i;
            }
        }
        return indexes;
    }

    private int getCount() {
        int count = 0;
        for (boolean value: mSelects ) {
            if (value) {
                count++;
            }
        }
        return count;
    }

    private boolean isSimple() {
        return mSelects.length == Args.SIMPLE_ANSWERS_AMOUNT;
    }

    private boolean isComplicated() {
        return mSelects.length == Args.COMPLICATED_ANSWERS_AMOUNT;
    }
}
