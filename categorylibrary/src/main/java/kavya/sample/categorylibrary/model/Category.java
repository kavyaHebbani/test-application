package kavya.sample.categorylibrary.model;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class Category {

    private int _id;

    private String mName;

    private int mNumberOfClicks;

    public Category(String name, int numberOfClicks) {
        mName = name;
        mNumberOfClicks = numberOfClicks;
    }

    public Category(int id, String name, int numberOfClicks) {
        _id = id;
        mName = name;
        mNumberOfClicks = numberOfClicks;
    }

    public int getId() {
        return _id;
    }

    public String getName() {
        return mName;
    }

    public int getNumberOfClicks() {
        return mNumberOfClicks;
    }
}
