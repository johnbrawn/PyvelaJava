package askdat.pyvela.data;

public class Exercise {

    private String Question;

    private String[] Answers;

    public Exercise(String question, String[] answers) {
        Question = question;
        Answers = answers;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String[] getAnswers() {
        return Answers;
    }

    public void setAnswers(String[] answers) {
        Answers = answers;
    }
}
