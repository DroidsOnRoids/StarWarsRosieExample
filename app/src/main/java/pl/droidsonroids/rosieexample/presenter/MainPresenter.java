package pl.droidsonroids.rosieexample.presenter;

import com.karumi.rosie.domain.usecase.UseCaseHandler;
import com.karumi.rosie.domain.usecase.annotation.Success;
import com.karumi.rosie.domain.usecase.callback.OnSuccessCallback;
import com.karumi.rosie.repository.PaginatedCollection;
import com.karumi.rosie.repository.datasource.paginated.Page;
import com.karumi.rosie.view.RosiePresenter;
import javax.inject.Inject;
import pl.droidsonroids.rosieexample.Constants;
import pl.droidsonroids.rosieexample.domain.GetPeople;
import pl.droidsonroids.rosieexample.model.Person;
import pl.droidsonroids.rosieexample.view.MainView;

public class MainPresenter extends RosiePresenter<MainView> {

    private final GetPeople getPeople;

    int offset = 0;

    @Inject
    public MainPresenter(final UseCaseHandler useCaseHandler, final GetPeople getPeople) {
        super(useCaseHandler);
        this.getPeople = getPeople;
    }

    @Override
    protected void update() {
        super.update();

        PaginatedCollection<Person> allPeopleInCache = getPeople.getAllPeopleInCache();
        if (allPeopleInCache.getPage().getLimit() == 0) {
            loadPeople();
        } else {
            getView().clearPeople();
            showPeople(allPeopleInCache);
            offset = allPeopleInCache.getItems().size();
        }
    }

    private void showPeople(final PaginatedCollection<Person> people) {
        getView().showPeople(people.getItems());
        getView().showHasMore(people.hasMore());
    }

    private void loadPeople() {
        createUseCaseCall(getPeople)
                .args(Page.withOffsetAndLimit(offset, Constants.DEFAULT_LIMIT))
                .onSuccess(new OnSuccessCallback() {
                    @Success public void onRepositoriesLoaded(PaginatedCollection<Person> people) {
                        showPeople(people);
                        offset = people.getPage().getOffset() + Constants.DEFAULT_LIMIT;
                    }
                })
                .onError(error -> {
                    getView().showError(error);
                    return false;
                })
                .execute();
    }

    public void onLoadMore() {
        loadPeople();
    }
}
