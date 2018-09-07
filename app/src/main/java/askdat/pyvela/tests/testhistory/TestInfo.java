package askdat.pyvela.tests.testhistory;

public class TestInfo {

    private String Title;

    private String Score;

    public TestInfo(String title, String score) {
        Title = title;
        Score = score;
    }

    public TestInfo(String title, int score) {
        Title = title;
        Score = String.valueOf(score);
    }


    public String getTitle() {
        return Title;
    }

    public String getScore() {
        return Score;
    }
}
