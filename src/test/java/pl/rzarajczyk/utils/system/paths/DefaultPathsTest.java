package pl.rzarajczyk.utils.system.paths;

/**
 *
 * @author Rafal
 */
public class DefaultPathsTest extends PathsTestFramework {

    @Override
    protected Paths getInstance() {
        return new DefaultPaths();
    }
    
    // only one test inherited from PathsTestFramework
    
}
