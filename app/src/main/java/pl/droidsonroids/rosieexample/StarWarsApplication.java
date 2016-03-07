package pl.droidsonroids.rosieexample;

import com.karumi.rosie.application.RosieApplication;
import java.util.Collections;
import java.util.List;

public class StarWarsApplication extends RosieApplication {

    @Override
    protected List<Object> getApplicationModules() {
        return Collections.singletonList((Object) new StarWarsModule(this));
    }
}
