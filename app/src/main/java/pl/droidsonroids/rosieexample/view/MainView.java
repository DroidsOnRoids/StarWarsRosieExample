package pl.droidsonroids.rosieexample.view;

import com.karumi.rosie.view.RosiePresenter;
import java.util.Collection;
import pl.droidsonroids.rosieexample.model.Person;

public interface MainView extends RosiePresenter.View {
    void showPeople(final Collection<Person> repositories);
    void showError(Error error);
    void clearPeople();
    void showHasMore(boolean hasMore);
}
