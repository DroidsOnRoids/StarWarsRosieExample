package pl.droidsonroids.rosieexample.repository;

import com.karumi.rosie.repository.PaginatedRosieRepository;
import com.karumi.rosie.repository.datasource.paginated.PaginatedCacheDataSource;
import javax.inject.Inject;
import pl.droidsonroids.rosieexample.model.Person;

public class PeopleRepository extends PaginatedRosieRepository<Long, Person> {

    @Inject
    public PeopleRepository(PeopleApiDataSource apiDataSource, PaginatedCacheDataSource<Long, Person> cacheDataSource) {
        //data to and from cache (they are readable and writable)
        addCacheDataSources(cacheDataSource);
        addPaginatedCacheDataSources(cacheDataSource);

        //data to api (only readable)
        addReadableDataSources(apiDataSource);
        addPaginatedReadableDataSources(apiDataSource);
    }
}
