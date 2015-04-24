package pl.rzarajczyk.utils.junit;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import pl.rzarajczyk.utils.system.os.OperatingSystemType;

/**
 *
 * @author Rafal
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RunOnlyOn {
    OperatingSystemType [] value();
}
