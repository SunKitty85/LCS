package sqlite.helper;

import static java.sql.Types.NULL;

/**
 * Created by moltox on 06.03.2017.
 */

public class Cards_done {
    long id;
    long card_id;
    long Done_DateTime_Correct;
    long Done_DateTime_Incorrect;

    // constructors ...
    public Cards_done()  {}

    public Cards_done(long card_id,boolean isCorrect)  {
        this.card_id = card_id;
        long timestamp = System.currentTimeMillis()/1000;
        if (isCorrect)  {
            this.Done_DateTime_Correct = timestamp;
            this.Done_DateTime_Incorrect = NULL;
        }  else  {
            this.Done_DateTime_Incorrect = timestamp;
            this.Done_DateTime_Correct = NULL;
        }
    }

    public Cards_done(long card_id,long timestamp,boolean isCorrect)  {
        this.card_id = card_id;
        if (isCorrect)  {
            this.Done_DateTime_Correct = timestamp;
            this.Done_DateTime_Incorrect = NULL;
        }  else  {
            this.Done_DateTime_Incorrect = timestamp;
            this.Done_DateTime_Correct = NULL;
        }
    }

    public Cards_done(long card_id, long Done_DateTime_Correct, long Done_DateTime_Incorrect)  {
        this.card_id = card_id;
        this.Done_DateTime_Correct = Done_DateTime_Correct;
        this.Done_DateTime_Incorrect = Done_DateTime_Incorrect;
    }

    public Cards_done(long id, long card_id, long Done_DateTime_Correct, long Done_DateTime_Incorrect)  {
        this.id = id;
        this.card_id = card_id;
        this.Done_DateTime_Correct = Done_DateTime_Correct;
        this.Done_DateTime_Incorrect = Done_DateTime_Incorrect;
    }


    // getters and setters
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCard_id() {
        return card_id;
    }

    public void setCard_id(long card_id) {
        this.card_id = card_id;
    }

    public long getDone_DateTime_Correct() {
        return Done_DateTime_Correct;
    }

    public void setDone_DateTime_Correct(long done_DateTime_Correcte) {
        this.Done_DateTime_Correct = done_DateTime_Correcte;
    }

    public long getDone_DateTime_Incorrect() {
        return Done_DateTime_Incorrect;
    }

    public void setDone_DateTime_Incorrect(long done_DateTime_Incorrect)
    {
        this.Done_DateTime_Incorrect = done_DateTime_Incorrect;
    }
}
