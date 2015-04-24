package pl.rzarajczyk.utils.resources;

import com.google.common.collect.Lists;
import java.io.File;
import java.util.List;

/**
 *
 * @author Rafal
 */
class DirResourceManager extends AbstractResourceManager {

    private File dir;

    public DirResourceManager(File dir) {
        this.dir = dir;
    }
    
    @Override
    protected List<String> getContents() {
        return getContents(this.dir, "");
    }
    
    private List<String> getContents(File currentDir, String prefix) {
        List<String> result = Lists.newArrayList();
        for ( File file : currentDir.listFiles() ) {
            if ( file.isFile() ) {
                result.add(prefix + file.getName());
            } else if ( file.isDirectory() ) {
                result.add( prefix + file.getName()+ "/" );
                result.addAll( getContents(file, prefix + file.getName() + "/") );
            } else {
                // nothing - ignore
            }
        }
        return result;
    }
    
    
    
}
