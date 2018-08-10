package askdat.pyvela.data.local;

import android.util.SparseArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UntTest {

    private Map<Integer, ArrayList<String[]>> exercises;

    private Map<Integer, Integer[]> answers;

    private Map<Integer, Integer[][]> answersExtended;

    public UntTest(int subject) {

        exercises = new HashMap<>();
        answers = new HashMap<>();

        if (subject > Subject.HISTORY_OF_KAZAKHSTAN) {
            exercises.put(subject, getComplexSubject());
            answers.put(subject, getStandartsAnswers());
            answersExtended.put(subject, getExtendedAnswers());
        }
        else {
            exercises.put(subject, getStandartSubject());
            answers.put(subject, getStandartsAnswers());
        }
    }

    public UntTest(int subject1 ,int subject2) {

        exercises = new HashMap<>();

        for (int i = 0; i < 3; i++) {
            exercises.put(i, getStandartSubject());
            answers.put(i, getStandartsAnswers());
        }
        exercises.put(subject1, getComplexSubject());
        exercises.put(subject2, getComplexSubject());
        answers.put(subject1, getStandartsAnswers());
        answers.put(subject2, getStandartsAnswers());
        answersExtended.put(subject1, getExtendedAnswers());
        answersExtended.put(subject2, getExtendedAnswers());
    }

    private ArrayList<String[]> getStandartSubject() {

        ArrayList<String[]> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(new String[6]);
        }
        return data;
    }

    private ArrayList<String[]> getComplexSubject() {

        ArrayList<String[]> data = getStandartSubject();
        for (int i = 0; i < 10; i++) {
            data.add(new String[9]);
        }
        return data;
    }

    private Integer[] getStandartsAnswers() {
        return new Integer[20];
    }

    private Integer[][] getExtendedAnswers() {
        return new Integer[30][3];
    }

    public String[] getExercise(int subject, int position) {
      return exercises.get(subject).get(position);
    }

    public int getSelectedAnswer(int subject, int position) {
        return answers.get(subject)[position];
    }

    public void setSelectedAnswer(int subject, int position) {
        answers.get(subject)[position] = 1;
    }

    public static class Subject {

        public static int MATH_LITERACY = 0;
        public static int READING_LITERACY = 1;
        public static int HISTORY_OF_KAZAKHSTAN = 2;

        public static int MATH = 3;
        public static int PHYSICS = 4;
        public static int BIOLOGY = 5;
        public static int CHEMISTRY = 6;
        public static int GEOGRAPHY = 7;
    }
}
