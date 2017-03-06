package kavya.sample.testapplication.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class ImagePreview {

    @SerializedName("preview")
    private ImageItem preview;

    public ImagePreview(ImageItem preview) {
        this.preview = preview;
    }

    public ImageItem preview() {
        return preview;
    }

}
