import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RenameYTDLFiles {
    public static void main(String[] args) {
        String pathname = "Y:\\Channels\\edureka!";
        renameFiles(pathname, "^[0-9]+ - ");
    }

    public static void renameFiles(String pathname, String prefixToRemove) {
        File dir = new File(pathname);
        if (!dir.exists()) {
            throw new RuntimeException("Please provide Correct pathname");
        }

        if (dir.isDirectory()) {
            File[] files = dir.listFiles();

            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println(
                            "\nRecursive call into: " + file.getPath());
                    renameFiles(file.getPath(), prefixToRemove);
                }
                String fileName = file.getName();
                if (file.isFile()) {
                    Path path = file.toPath();
                    System.out.println("Path before renaming: " + path);

                    String newFileName = fileName
                            .replaceFirst(prefixToRemove, "");

                    Path newPath = Paths.get(dir.getPath(), newFileName);
                    try {
                        Files.move(path, newPath);
                        System.out.println("Path after renaming: " + newPath);
                    } catch (IOException e) {
                        System.err.println(
                                "IOException while renaming files: " +
                                e.getMessage());
                    }
                }
            }

        } else {

        }
    }

}
