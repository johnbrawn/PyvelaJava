package askdat.pyvela.tests.testhistory;

public class TestHistoryData {

    private String mIndicator;

    private String mTitle;

    private String mScore;

    public TestHistoryData(String indicator, String title, String score) {
        mIndicator = indicator;
        mTitle = title;
        mScore = score;
    }

    public TestHistoryData(String indicator, String title, int score) {
        this(indicator, title, String.valueOf(score));
    }

    public String getIndicator() {
        return mIndicator;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getScore() {
        return mScore;
    }
}
