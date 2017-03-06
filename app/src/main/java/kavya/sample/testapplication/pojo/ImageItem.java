package kavya.sample.testapplication.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class ImageItem {

    @SerializedName("url")
    private String imageUrl;

    public ImageItem(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String imageUrl() {
        return imageUrl;
    }
}
