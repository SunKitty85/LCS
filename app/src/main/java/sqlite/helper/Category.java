package sqlite.helper;

/**
 * Created by moltox on 12.03.2017.
 */

public class Category {
    int id;
    String category;
    String pic_filename;

    public Category()  {};

    public Category(String category,String pic_filename)  {
        this.category = category;
        this.pic_filename = pic_filename;
    }

    public Category(int id, String category,String pic_filename)  {
        this.id = id;
        this.category = category;
        this.pic_filename = pic_filename;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPic_filename() {
        return pic_filename;
    }

    public void setPic_filename(String pic_filename) {
        this.pic_filename = pic_filename;
    }
}
