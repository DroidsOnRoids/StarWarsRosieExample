package pl.droidsonroids.rosieexample.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.repository.PaginatedCollection;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import pl.droidsonroids.rosieexample.PaginatedCollectionUtils;
import pl.droidsonroids.rosieexample.domain.GetPeople;
import pl.droidsonroids.rosieexample.model.Person;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class MainPresenterTest {

    @Mock UseCaseHandler useCaseHandler;
    @Mock GetPeople getPeople;

    private MainPresenter mainPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mainPresenter = new MainPresenter(useCaseHandler, getPeople);
    }

    @Test
    public void testUpdate_emptyList() throws Exception {
        //given
        PaginatedCollection<Person> emptyList = PaginatedCollectionUtils.createPaginatedCollection(new ArrayList<>());
        when(getPeople.getAllPeopleInCache()).thenReturn(emptyList);

        //when
        mainPresenter.update();

        //then
        verify(getPeople).getAllPeopleInCache();
        //impossible to test
        //verify(getPeople).getPeople(any());
        //assertThat(mainPresenter.offset).isEqualTo(Constants.DEFAULT_LIMIT);
    }
}