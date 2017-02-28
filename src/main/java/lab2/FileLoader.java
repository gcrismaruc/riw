package lab2;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gheorghe on 2/23/2017.
 */
public class FileLoader {

    private String directoryName;

    public FileLoader(String directoryName){
        this.directoryName = directoryName;
    }

    public List<File> getFiles(String directoryName) throws IOException {

        List<Path> paths = new ArrayList<Path>();
        try{
            DirectoryStream<Path> stream = Files.newDirectoryStream(directoryName, "*.{c,h,cpp,hpp,java,html}");

            for (Path entry: stream) {
                paths.add(entry);
            }
        } catch (DirectoryIteratorException ex) {
            // I/O error encounted during the iteration, the cause is an IOException
            throw ex.getCause();
        }

        List<File> files = new ArrayList<File>();

        for(Path path : paths){
            System.out.println(path);
            files.add(path.toFile());
        }

        return files;
    }
}
