package pl.droidsonroids.rosieexample.repository;

import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.EmptyPaginatedReadableDataSource;
import com.karumi.rosie.repository.datasource.paginated.Page;
import java.util.List;
import javax.inject.Inject;
import pl.droidsonroids.rosieexample.Constants;
import pl.droidsonroids.rosieexample.model.People;
import pl.droidsonroids.rosieexample.model.Person;
import pl.droidsonroids.rosieexample.repository.api.ApiClient;

public class PeopleApiDataSource extends EmptyPaginatedReadableDataSource<Long, Person> {

    @Inject
    public PeopleApiDataSource() {
    }

    @Override
    public PaginatedCollection<Person> getPage(final Page page) throws Exception {
        int currentPage = Math.round(page.getOffset() / 10) + 1;

        ApiClient.SwapiClient client = ApiClient.create();
        People people = client.getPeople(currentPage);
        List<Person> personList = people.getPersonList();
        PaginatedCollection<Person> repositoryPaginatedCollection = new PaginatedCollection<>(personList);
        repositoryPaginatedCollection.setPage(page);
        repositoryPaginatedCollection.setHasMore(
                currentPage * Constants.DEFAULT_LIMIT + personList.size() < people.getCount());
        return repositoryPaginatedCollection;
    }
}
