package kavya.sample.testapplication.network;

import java.util.ArrayList;
import java.util.List;

import kavya.sample.testapplication.pojo.ImageAsset;
import kavya.sample.testapplication.pojo.ImageData;
import kavya.sample.testapplication.pojo.ImageItem;
import rx.Observable;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class ImageDataToPreviewTransformer
        implements Observable.Transformer<ImageData, List<ImageItem>> {

    @Override
    public Observable<List<ImageItem>> call(Observable<ImageData> imageDataObservable) {
        return imageDataObservable
                .map(ImageData::data)
                .flatMap(assets -> {
                    final List<ImageItem> items = new ArrayList<>(assets.size());
                    for (ImageAsset imageAsset : assets) {
                        items.add(imageAsset.assets().preview());
                    }
                    return Observable.just(items);
                });
    }
}
