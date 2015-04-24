package pl.rzarajczyk.utils.junit;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import pl.rzarajczyk.utils.system.os.OperatingSystem;
import pl.rzarajczyk.utils.system.os.OperatingSystemType;

/**
 *
 * @author Rafal
 */
public class OsAwareTestRunner extends BlockJUnit4ClassRunner {

    private RunOnlyOn classAnnotation;
    private OperatingSystemType currentOs;
    
    public OsAwareTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
        classAnnotation = null;
        if ( klass.isAnnotationPresent(RunOnlyOn.class) ) {
            classAnnotation = klass.getAnnotation(RunOnlyOn.class);
        }
        currentOs = OperatingSystem.determine();
    }

    void setCurrentOs(OperatingSystemType currentOs) {
        this.currentOs = currentOs;
    }

    @Override
    protected List<FrameworkMethod> computeTestMethods() {
        if ( ! validate(classAnnotation) ) {
            return Collections.emptyList();
        }
        List<FrameworkMethod> list = super.computeTestMethods();
        Iterator<FrameworkMethod> iterator = list.iterator();
        while ( iterator.hasNext() ) {
            FrameworkMethod method = iterator.next();
            RunOnlyOn annotation = method.getAnnotation(RunOnlyOn.class);
            if ( !validate(annotation) ) {
                iterator.remove();
            }
        }
        return list;
    }    
    
    boolean validate(RunOnlyOn annotation) {
        if ( annotation == null ) {
            return true;
        }
        OperatingSystemType [] required = annotation.value();
        return Arrays.asList(required).contains(currentOs);
    }
}
