package pl.rzarajczyk.utils.application;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafalz
 */
public class Version implements Comparable<Version> {

    private List<Integer> parts;
    private String postfix;
    
    public Version(String version) throws NumberFormatException {
        version = version.trim();
        int dash = version.indexOf("-");
        if ( dash >= 0 ) {
            this.postfix = version.substring(dash+1);
            version = version.substring(0, dash);
        }
        String [] splitted = version.split("\\.");
        parts = new ArrayList<Integer>(splitted.length);
        for ( int i=0; i<splitted.length; i++ ) {
            int value = Integer.parseInt(splitted[i]);
            this.parts.add(value);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for ( int part : parts ) {
            result.append(part);
            result.append(".");
        }
        if ( result.length() > 0 ) {
            result.deleteCharAt(result.length()-1);
        }
        if ( postfix != null ) {
            result.append("-");
            result.append(postfix);
        }
        return result.toString();
    }
    
    public int getPart(int index) {
        return parts.get(index);
    }
    
    public int getPartsCount() {
        return parts.size();
    }
    
    public int [] toArray() {
        return toArray(parts.size());
    }
    
    public int [] toArray(int length) {
        length = Math.min(length, parts.size());
        int [] result = new int[length];
        for ( int i=0; i<result.length; i++ ) {
            result[i] = i<parts.size() ? parts.get(i) : 0;
        }
        return result;  
    }

    @Override
    public int compareTo(Version t) {
        int length = Math.max(this.getPartsCount(), t.getPartsCount());
        int [] myParts = this.toArray(length);
        int [] tParts = t.toArray(length);
        assert myParts.length == tParts.length;
        for ( int i=0; i<myParts.length; i++ ) {
            if ( myParts[i] > tParts[i] ) {
                return 1;
            }
            if ( myParts[i] < tParts[i] ) {
                return -1;
            }
        }
        return 0;
    }
}
