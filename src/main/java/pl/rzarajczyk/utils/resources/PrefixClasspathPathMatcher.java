package pl.rzarajczyk.utils.resources;

/**
 *
 * @author Rafal
 */
class PrefixClasspathPathMatcher implements ClasspathPathMatcher {

    private String prefix;

    public PrefixClasspathPathMatcher(String prefix) {
        this.prefix = prefix;
    }
    
    @Override
    public boolean matches(String path) {
        return path.startsWith(prefix);
    }
    
    
    
}
