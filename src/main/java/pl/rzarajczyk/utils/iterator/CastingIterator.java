package pl.rzarajczyk.utils.iterator;

import java.util.Iterator;

/**
 *
 * @author rafalz
 */
public class CastingIterator<S, T> implements Iterator<T> {
    
    private Iterator<S> source;

    public CastingIterator(Iterator<S> source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return source.toString();
    }

    @Override
    public int hashCode() {
        return source.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return source.equals(o);
    }

    @Override
    public void remove() {
        source.remove();
    }

    @Override
    public T next() {
        return (T) source.next();
    }

    @Override
    public boolean hasNext() {
        return source.hasNext();
    }
    
}
