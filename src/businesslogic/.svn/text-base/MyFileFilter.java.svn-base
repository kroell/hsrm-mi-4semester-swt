package businesslogic;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * ImageFilter.java
 * 
 * 
 * Filter der beim Oeffnen eines JFileChoosers verwendet werden kann
 * um nach vorgegebenen Extensions (zB .jpg) Dateien zu filtern.
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmiz
 *
 */
public class MyFileFilter extends FileFilter {
    
    private String[] extensions;
    private String description;

    // Konstruktor
    public MyFileFilter(String description, String[] extensions) {
        this.description = description;
        this.extensions = extensions;

        // Um Gross- und Kleinschreibungsprobleme zu umgehen
        for (int i = 0, len = extensions.length; i < len; i++) {
            extensions[i].toLowerCase();
        }
    }

    // Abgleich um ein File akzeptiert wird oder nicht    
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        } else {
            String path = f.getAbsolutePath().toLowerCase();
            for (int i = 0, len = extensions.length; i < len; i++) {
                if ((path.endsWith(extensions[i]))) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getDescription() {
        return description;
    } 
}
