package pl.droidsonroids.rosieexample;

import android.support.annotation.NonNull;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import java.util.Collection;
import pl.droidsonroids.rosieexample.model.Person;

public class PaginatedCollectionUtils {

    @NonNull
    public static PaginatedCollection<Person> createPaginatedCollection(final Collection<Person> emptyCacheList) {
        PaginatedCollection<Person> expectedPeopleList = new PaginatedCollection<>(emptyCacheList);
        expectedPeopleList.setPage(Page.withOffsetAndLimit(0, emptyCacheList.size()));
        expectedPeopleList.setHasMore(true);
        return expectedPeopleList;
    }

}
