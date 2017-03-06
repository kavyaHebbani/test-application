package kavya.sample.testapplication.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class ImageData {

    @SerializedName("data")
    private List<ImageAsset> data = new ArrayList<>();

    public ImageData(List<ImageAsset> data) {
        this.data = data;
    }

    public Collection<ImageAsset> data() {
        return data;
    }
}
