package pl.droidsonroids.rosieexample.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import butterknife.Bind;
import com.karumi.rosie.view.Presenter;
import com.karumi.rosie.view.RosieActivity;
import com.karumi.rosie.view.paginated.ScrollToBottomListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import pl.droidsonroids.rosieexample.R;
import pl.droidsonroids.rosieexample.model.Person;
import pl.droidsonroids.rosieexample.presenter.MainPresenter;

public class MainActivity extends RosieActivity implements MainView {

    @Bind(R.id.recycler_view) RecyclerView recyclerView;
    @Bind(R.id.toolbar) Toolbar toolbar;

    @Inject @Presenter MainPresenter mainPresenter;

    private List<Person> peopleList;
    private PeopleAdapter peopleAdapter;
    private ScrollToBottomListener loadMoreListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar();
        initializeRecyclerView();
    }

    private void setToolbar() {
        toolbar.setTitle("Rosie Example - Star Wars");
    }

    private void initializeRecyclerView() {
        //peopleList = new PeopleAdapteeCollection();
        //RendererBuilder<Person> rendererBuilder = new PersonRendererBuilder();
        //peopleAdapter = new RVRendererAdapter<>(getLayoutInflater(), rendererBuilder, peopleList);

        peopleList = new ArrayList<>();
        peopleAdapter = new PeopleAdapter(this, peopleList);

        recyclerView.setAdapter(peopleAdapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        loadMoreListener = new ScrollToBottomListener(layoutManager, mainPresenter::onLoadMore);
        recyclerView.addOnScrollListener(loadMoreListener);
    }

    private void showLoading() {

    }

    private void hideLoading() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void showPeople(final Collection<Person> people) {
        peopleList.addAll(people);
        removeEmptyPerson();
        peopleAdapter.notifyDataSetChanged();
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    private void removeEmptyPerson() {
        peopleList.removeAll(Collections.singleton(null));
    }

    @Override
    public void showError(final Error error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearPeople() {
        peopleAdapter.clear();
    }

    @Override
    public void showHasMore(final boolean hasMore) {
        if (hasMore) {
            peopleList.add(null);
            peopleAdapter.notifyItemInserted(peopleList.size());
        }
        loadMoreListener.setIsProcessing(false);
        loadMoreListener.setIsEnabled(hasMore);
    }
}
