import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FolderRenamer {
    public static void main(java.lang.String[] args) {

        //working folder
        String dir = "D:\\Learning\\Spring\\spring-and-hibernate-for"
                     + "-beginners\\spring-hibernate-source-code-v27";
        //recursively list files before renaming
        // listDirectories(dir);
        //rename files - replace text in the name with song.text
        renameFiles(dir, "solution-code-", "");
        //recursively list files after renaming
        // listDirectories(dir);
    }


    public static void listDirectories(String rootDir) {
        try {
            Files.find(Paths.get(rootDir),
                       Integer.MAX_VALUE,
                       (filePath, fileAttr) -> fileAttr.isDirectory())
                 .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void renameFiles(String dir, String replace,
                                   String replaceBy) {
        if (!dir.contains(replace)) {
            return;
        }
        try {
            try (Stream<Path> stream =
                         Files.find(Paths.get(dir), 3,
                                    (path, attr) -> attr.isDirectory())) {
                stream.map(String::valueOf)
                      .forEach(src -> {
                                   try {
                                       String dest =
                                               src.replace(replace, replaceBy);
                                       Files.move(Path.of(src), Path.of(dest));
                                   } catch (IOException e) {
                                       e.printStackTrace();
                                   }
                               }
                      );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
