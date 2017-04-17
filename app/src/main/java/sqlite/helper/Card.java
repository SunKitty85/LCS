package sqlite.helper;

/**
 * Created by moltox on 06.03.2017.
 */

public class Card {
    int id;
    String question;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    String releaseDate;
    String category_id;

    // constructors ...
    public Card()  {};

    /*
    public Card(String question, String answer1, String answer2, String answer3, String answer4, String releaseDate,String category_id)  {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.releaseDate = releaseDate;
        this.category_id = category_id;
    }
    */

    public Card(int id, String question, String answer1, String answer2, String answer3, String answer4, String releaseDate,String category_id)  {
        this.id = id;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.releaseDate = releaseDate;
        this.category_id = category_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCategory_id()  {return category_id;}

    public void setCategory_id(String category_id)  {this.category_id = category_id;}

}
