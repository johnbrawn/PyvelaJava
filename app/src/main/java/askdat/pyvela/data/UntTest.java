package askdat.pyvela.data;

import android.util.SparseArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UntTest {

    private Map<Integer, ArrayList<String[]>> exercises;

    public UntTest(int subject) {

        exercises = new HashMap<>();

        ArrayList<String[]> data = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            data.add(new String[6]);
        }

        if (subject > Subject.HISTORY_OF_KAZAKHSTAN) {
            for (int i = 0; i < 10; i++) {
                data.add(new String[9]);
            }
        }

        exercises.put(subject, data);
    }

    public UntTest(byte subject1 ,byte subject2) {

        ArrayList<String[]> data = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 20; k++) {
                data.add(new String[6]);
            }
            exercises.put(i, data);
        }

        for (int i = 0; i < 2; i++) {
            for (int k = 0; k < 20; k++) {
                data.add(new String[6]);
            }
            for (int j = 0; j < 10; j++) {
                data.add(new String[9]);
            }
            exercises.put(i, data);
        }

    }



    public static class Subject {

        public static byte MATH_LITERACY = 0;
        public static byte READING_LITERACY = 1;
        public static byte HISTORY_OF_KAZAKHSTAN = 2;

        public static byte MATH = 3;
        public static byte PHYSICS = 4;
        public static byte BIOLOGY = 5;
        public static byte CHEMISTRY = 6;
        public static byte GEOGRAPHY = 7;
    }
}
