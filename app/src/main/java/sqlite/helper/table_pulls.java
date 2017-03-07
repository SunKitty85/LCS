package sqlite.helper;

/**
 * Created by moltox on 06.03.2017.
 */

public class table_pulls {
    int id;
    int pullDateTime;

    public table_pulls() {}

    public table_pulls(int pullDateTime)  {
        this.pullDateTime = pullDateTime;
    }

    public table_pulls(int id, int pullDateTime)  {
        this.id = id;
        this.pullDateTime = pullDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPullDateTime() {
        return pullDateTime;
    }

    public void setPullDateTime(int pullDateTime) {
        this.pullDateTime = pullDateTime;
    }





}
