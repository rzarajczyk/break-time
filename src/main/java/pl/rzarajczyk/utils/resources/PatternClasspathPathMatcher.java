package pl.rzarajczyk.utils.resources;

import java.util.regex.Pattern;

/**
 *
 * @author Rafal
 */
public class PatternClasspathPathMatcher implements ClasspathPathMatcher {

    private Pattern pattern;

    public PatternClasspathPathMatcher(Pattern pattern) {
        this.pattern = pattern;
    }
    
    public PatternClasspathPathMatcher(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }
    
    @Override
    public boolean matches(String path) {
        return pattern.matcher(path).matches();
    }
    
    
    
}
