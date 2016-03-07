package pl.droidsonroids.rosieexample.domain;

import com.karumi.rosie.domain.usecase.RosieUseCase;
import com.karumi.rosie.domain.usecase.annotation.UseCase;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.repository.policy.ReadPolicy;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;
import pl.droidsonroids.rosieexample.model.Person;
import pl.droidsonroids.rosieexample.repository.PeopleRepository;

public class GetPeople extends RosieUseCase {

    private final PeopleRepository peopleRepository;

    @Inject
    public GetPeople(final PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public PaginatedCollection<Person> getAllPeopleInCache() {
        Collection<Person> allPeople;
        try {
            allPeople = peopleRepository.getAll(ReadPolicy.CACHE_ONLY);
        } catch (Exception e) {
            allPeople = new ArrayList<>();
        }

        if (allPeople == null) {
            allPeople = new ArrayList<>();
        }

        Page page = Page.withOffsetAndLimit(0, allPeople.size());
        PaginatedCollection<Person> allPeoplePaginated = new PaginatedCollection<>(allPeople);
        allPeoplePaginated.setPage(page);
        allPeoplePaginated.setHasMore(true);

        return allPeoplePaginated;
    }

    @UseCase
    public void getPeople(Page page) throws Exception {
        PaginatedCollection<Person> repositories = peopleRepository.getPage(page);
        notifySuccess(repositories);
    }
}
