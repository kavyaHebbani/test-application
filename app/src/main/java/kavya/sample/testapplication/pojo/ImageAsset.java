package kavya.sample.testapplication.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class ImageAsset {

    @SerializedName("assets")
    private ImagePreview assets;

    public ImageAsset(ImagePreview assets) {
        this.assets = assets;
    }

    public ImagePreview assets() {
        return assets;
    }
}
