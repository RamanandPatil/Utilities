import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileRename {
    public static void main(String[] args) throws IOException {

        //working folder
        String dir = "D:\\Downloads\\20181216";
        //recursively list files before renaming
        listFiles(dir);
        //rename files - replace text in the name with song.text
        renameFiles(dir, "[Smtebooks.Us] ", "");
        //recursively list files after renaming
        listFiles(dir);
    }


    public static void listFiles(String dir) {
        try {
            Files.find(Paths.get(dir), Integer.MAX_VALUE,
                       (filePath, fileAttr) -> fileAttr.isRegularFile())
                 .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void renameFiles(String dir, String replace, String replaceBy)
            throws IOException {
        try (Stream<Path> stream = Files.find(Paths.get(dir), 3,
                                              (path, attr) ->
                                                      String.valueOf(path)
                                                            .endsWith(".Pdf"))) {
            stream.map(String::valueOf).forEach(item -> {
                try {
                    Files.move(new File(item).toPath(),
                               new File(item.replace(replace, replaceBy))
                                       .toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
