package askdat.pyvela.tests.teststart;

public interface Connector {

    interface InteractorListener {

        void onExtendAdapter();

        void onResetAdapter();

        void onChangeCount();
    }

    interface ActivityListener {

        void onSpinnerItemSelected(int newSubject, int viewPagerCurrentPos);
    }
}
