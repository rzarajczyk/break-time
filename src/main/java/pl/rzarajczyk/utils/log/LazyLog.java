package pl.rzarajczyk.utils.log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Rafal
 */
public class LazyLog implements Log {

    private Class<?> clazz;
    private Log instance;

    public LazyLog(Class<?> clazz) {
        this.clazz = clazz;
    }

    private Log get() {
        if (instance == null) {
            instance = LogFactory.getLog(clazz);
        }
        return instance;
    }

    @Override
    public void warn(Object o, Throwable thrwbl) {
        get().warn(o, thrwbl);
    }

    @Override
    public void warn(Object o) {
        get().warn(o);
    }

    @Override
    public void trace(Object o, Throwable thrwbl) {
        get().trace(o, thrwbl);
    }

    @Override
    public void trace(Object o) {
        get().trace(o);
    }

    @Override
    public boolean isWarnEnabled() {
        return get().isWarnEnabled();
    }

    @Override
    public boolean isTraceEnabled() {
        return get().isTraceEnabled();
    }

    @Override
    public boolean isInfoEnabled() {
        return get().isInfoEnabled();
    }

    @Override
    public boolean isFatalEnabled() {
        return get().isFatalEnabled();
    }

    @Override
    public boolean isErrorEnabled() {
        return get().isErrorEnabled();
    }

    @Override
    public boolean isDebugEnabled() {
        return get().isDebugEnabled();
    }

    @Override
    public void info(Object o, Throwable thrwbl) {
        get().info(o, thrwbl);
    }

    @Override
    public void info(Object o) {
        get().info(o);
    }

    @Override
    public void fatal(Object o, Throwable thrwbl) {
        get().fatal(o, thrwbl);
    }

    @Override
    public void fatal(Object o) {
        get().fatal(o);
    }

    @Override
    public void error(Object o, Throwable thrwbl) {
        get().error(o, thrwbl);
    }

    @Override
    public void error(Object o) {
        get().error(o);
    }

    @Override
    public void debug(Object o, Throwable thrwbl) {
        get().debug(o, thrwbl);
    }

    @Override
    public void debug(Object o) {
        get().debug(o);
    }

    @Override
    public String toString() {
        return get().toString();
    }

    @Override
    public int hashCode() {
        return get().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return get().equals(obj);
    }
}
