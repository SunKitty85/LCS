package sqlite.helper;

/**
 * Created by moltox on 06.03.2017.
 */

public class Cards_done {
    int id;
    int card_id;
    int Done_DateTime;
    boolean Correct;

    // constructors ...
    public Cards_done()  {}

    public Cards_done(int card_id, int Done_DateTime, boolean Correct)  {
        this.card_id = card_id;
        this.Done_DateTime = Done_DateTime;
        this.Correct = Correct;
    }

    public Cards_done(int id, int card_id, int Done_DateTime, boolean Correct)  {
        this.id = id;
        this.card_id = card_id;
        this.Done_DateTime = Done_DateTime;
        this.Correct = Correct;
    }


    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCard_id() {
        return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    public int getDone_DateTime() {
        return Done_DateTime;
    }

    public void setDone_DateTime(int done_DateTime) {
        Done_DateTime = done_DateTime;
    }

    public boolean isCorrect() {
        return Correct;
    }

    public void setCorrect(boolean correct) {
        Correct = correct;
    }
}
