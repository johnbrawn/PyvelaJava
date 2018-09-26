package askdat.pyvela.tests.testsend;

public class Result {

    private String mSubject;

    private String mScore;

    public Result(String subject, int score, int maxScore) {

        if (maxScore >= score) {
            this.mSubject = subject;
            this.mScore = String.format("%d/#d", score, maxScore);
        }
        else {
            throw new RuntimeException(String.format("Score: %d, MaxScore: %d", score, maxScore));
        }
    }

    public String getSubject() {
        return mSubject;
    }

    public String getFirstChar() {
        return String.valueOf(mSubject.charAt(0));
    }

    public String getScore() {
        return mScore;
    }
}
