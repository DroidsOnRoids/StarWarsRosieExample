package pl.droidsonroids.rosieexample;

import android.content.Context;
import com.karumi.rosie.repository.datasource.paginated.InMemoryPaginatedCacheDataSource;
import com.karumi.rosie.repository.datasource.paginated.PaginatedCacheDataSource;
import com.karumi.rosie.time.TimeProvider;
import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import pl.droidsonroids.rosieexample.model.Person;
import pl.droidsonroids.rosieexample.repository.api.ApiClient;
import pl.droidsonroids.rosieexample.view.MainActivity;

import static java.util.concurrent.TimeUnit.MINUTES;

@Module(library = true,
        complete = false,
        injects = {
                StarWarsApplication.class, MainActivity.class
        })
public class StarWarsModule {

    private static final long PERSON_IN_MEMORY_CACHE_TTL = MINUTES.toMillis(5);
    private final StarWarsApplication application;

    public StarWarsModule(final StarWarsApplication application) {
        this.application = application;
    }

    @Provides
    public Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    public Picasso providePicasso(Context context) {
        return Picasso.with(context);
    }

    @Provides
    @Singleton
    public ApiClient.SwapiClient provideGithubClient() {
        return ApiClient.create();
    }

    @Provides
    @Singleton
    public PaginatedCacheDataSource<Long, Person> providePersonPageInMemoryCache() {
        return new InMemoryPaginatedCacheDataSource<>(new TimeProvider(), PERSON_IN_MEMORY_CACHE_TTL);
    }
}
