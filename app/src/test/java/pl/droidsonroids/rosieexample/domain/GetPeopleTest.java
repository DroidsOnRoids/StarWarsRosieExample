package pl.droidsonroids.rosieexample.domain;

import android.support.annotation.NonNull;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.policy.ReadPolicy;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import pl.droidsonroids.rosieexample.PaginatedCollectionUtils;
import pl.droidsonroids.rosieexample.model.Person;
import pl.droidsonroids.rosieexample.repository.PeopleRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class GetPeopleTest {

    private static final String PERSON_NAME = "person_name";
    private static final Person.Gender PERSON_GENDER = Person.Gender.MALE;
    private static final String PERSON_BIRTH_YEAR = "birth_year";
    private static final String PERSON_URL = "person_url";

    @Mock PeopleRepository peopleRepository;

    private GetPeople getPeople;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        getPeople = new GetPeople(peopleRepository);
    }

    @Test
    public void testGetAllPeopleInTheCache_cacheIsEmpty() throws Exception {
        //given
        Collection<Person> emptyCacheList = new ArrayList<>();
        PaginatedCollection<Person> expectedPeopleList = PaginatedCollectionUtils.createPaginatedCollection(
                emptyCacheList);
        when(peopleRepository.getAll(ReadPolicy.CACHE_ONLY)).thenReturn(emptyCacheList);

        //when
        PaginatedCollection<Person> actualPeopleList = getPeople.getAllPeopleInCache();

        //then
        assertThatPaginatedListAreEqual(actualPeopleList, expectedPeopleList);
    }

    @Test
    public void testGetAllPeopleInTheCache_cacheIsNotEmpty() throws Exception {
        //given
        Collection<Person> nonEmptyCacheList = new ArrayList<>();
        nonEmptyCacheList.add(new Person(PERSON_NAME, PERSON_GENDER, PERSON_BIRTH_YEAR, PERSON_URL));
        PaginatedCollection<Person> expectedPeopleList = PaginatedCollectionUtils.createPaginatedCollection(
                nonEmptyCacheList);
        when(peopleRepository.getAll(ReadPolicy.CACHE_ONLY)).thenReturn(nonEmptyCacheList);

        //when
        PaginatedCollection<Person> actualPeopleList = getPeople.getAllPeopleInCache();

        //then
        assertThatPaginatedListAreEqual(actualPeopleList, expectedPeopleList);
    }

    public void assertThatPaginatedListAreEqual(PaginatedCollection actualList, PaginatedCollection expectedList) {
        assertThat(actualList.getItems(), is(expectedList.getItems()));
        assertThat(actualList.getPage().getLimit(), is(expectedList.getPage().getLimit()));
        assertThat(actualList.getPage().getOffset(), is(expectedList.getPage().getOffset()));
    }
}