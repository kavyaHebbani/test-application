package kavya.sample.testapplication.presenters;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import kavya.sample.categorylibrary.model.CategoryDataModel;
import kavya.sample.testapplication.TestSchedulerProvider;
import kavya.sample.testapplication.fragments.CategoriesFragment;
import kavya.sample.testapplication.network.ApiService;
import kavya.sample.testapplication.pojo.ImageItem;

import static junit.framework.Assert.assertTrue;
import static rx.Observable.just;

/**
 * Created by ksreeniv on 06/03/17.
 */
public class CategoriesPresenterTest {

    @Mock
    ApiService mApiService;

    @Mock
    CategoryDataModel mCategoryDataModel;

    @Mock
    CategoriesFragment mCategoriesFragment;

    CategoriesPresenter mPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mPresenter = new CategoriesPresenter(mApiService, new TestSchedulerProvider(),
                                             mCategoryDataModel);
    }

    @Test
    public void testBind_loadsImages() {
        List<ImageItem> dummyList = Arrays.asList(new ImageItem("abc"));
        Mockito.when(mApiService.getCategories()).thenReturn(just(dummyList));

        mPresenter.bind(mCategoriesFragment);

        Mockito.verify(mCategoriesFragment).updateImages(dummyList);
    }

    @Test
    public void testIsNewUser_callsMethod() {
        Mockito.when(mCategoryDataModel.isFirstTimeUser()).thenReturn(true);

        assertTrue(mPresenter.isNewUser());

        Mockito.verify(mCategoryDataModel).isFirstTimeUser();
    }

}
