import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author rpatil
 */
public class FileUtils {

    public static final boolean DEBUG = false;

    /**
     * Method to list files from the current working directory.
     */
    public static void listFiles() {
        listFiles(".", 1, false);
    }

    /**
     * Method to list files/folders from the current working directory.
     *
     * @param showDir whether to list folders or not
     */
    public static void listFiles(boolean showDir) {
        listFiles(".", 1, showDir);
    }

    /**
     * Method to list files and folders from the current directory recursively
     * upto maxDepth
     *
     * @param maxDepth depth level of recursion
     */
    public static void listFiles(int maxDepth) {
        listFiles(".", maxDepth, true);
    }

    /**
     * Method to list files from the given directory and it's sub-directories
     * recursively till all the levels
     *
     * @param dir top level directory
     */
    public static void listFiles(String dir) {
        listFiles(dir, Integer.MAX_VALUE, true);
    }

    /**
     * Method to list files from the given directory and it's sub-directory upto
     * a given level of maxDepth
     *
     * @param dir      top directory
     * @param maxDepth depth of the recursion for sub-directories
     */
    public static void listFiles(String dir, int maxDepth, boolean showDir) {
        try {
            Files.find(Paths.get(dir), maxDepth,
                       (filePath, fileAttr) ->
                               fileAttr.isRegularFile() ||
                               (showDir && fileAttr.isDirectory()))
                 .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Rename all the files from the current directory and all sub-directories
     *
     * @param findString    the string name to be replaced from filename
     * @param replaceString the replacement string
     */
    public static void renameFiles(String findString, String replaceString) {
        renameFiles(".", Integer.MAX_VALUE, findString, replaceString);
    }

    /**
     * @param dirOrExtension
     * @param findString
     * @param replaceString
     */
    public static void renameFiles(String dirOrExtension, String findString,
                                   String replaceString) {
        if (dirOrExtension.length() > 1 && dirOrExtension.startsWith(".")) {
            renameFiles(".", dirOrExtension, Integer.MAX_VALUE, findString,
                        replaceString);
        } else {
            renameFiles(dirOrExtension, "", Integer.MAX_VALUE, findString,
                        replaceString);
        }
    }

    /**
     * @param dir
     * @param maxValue
     * @param findString
     * @param replaceString
     */
    private static void renameFiles(String dir, int maxValue, String findString,
                                    String replaceString) {
        renameFiles(".", "", Integer.MAX_VALUE, findString, replaceString);
    }

    /**
     * @param dir
     * @param fileExtension
     * @param findString
     * @param replaceString
     */
    public static void renameFiles(String dir, String fileExtension,
                                   String findString, String replaceString) {
        renameFiles(dir, fileExtension, Integer.MAX_VALUE, findString,
                    replaceString);
    }

    /**
     * @param dir
     * @param fileExtension
     * @param maxDepth
     * @param findString
     * @param replaceString
     */
    public static void renameFiles(String dir, String fileExtension,
                                   int maxDepth, String findString,
                                   String replaceString) {
        try {
            try (Stream<Path> stream = Files.find(Paths.get(dir), maxDepth,
                                                  (path, attr) -> String
                                                          .valueOf(path)
                                                          .endsWith(
                                                                  fileExtension))) {

                stream.map(String::valueOf).forEach(item -> {
                    if (item.contains(findString)) {
                        try {
                            Path oldPath = new File(item).toPath();
                            Path newPath = new File(
                                    item.replace(findString, replaceString))
                                    .toPath();
                            if (DEBUG) {
                                System.out.println("Old: " + oldPath);
                                System.out.println("New: " + newPath);
                            }
                            // TODO: find out why Files.move(...) is not
                            //  working for file extension.
                            if (fileExtension.equals(findString)) {
                                new File(oldPath.toString())
                                        .renameTo(new File(newPath.toString()));
                            } else {
                                Files.move(oldPath, newPath);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param dir
     * @param currentExtension
     * @param newExtension
     */
    public static void changeExtension(String dir, String currentExtension,
                                       String newExtension) {
        renameFiles(dir, currentExtension, Integer.MAX_VALUE, currentExtension,
                    newExtension);
    }

    /**
     * @param currentExtension
     * @param newExtension
     */
    public static void changeExtension(String currentExtension,
                                       String newExtension) {
        renameFiles(".", currentExtension, Integer.MAX_VALUE, currentExtension,
                    newExtension);
    }

    /**
     * @param dir
     * @param maxDepth
     * @param currentExtension
     * @param newExtension
     */
    public static void changeExtension(String dir, int maxDepth,
                                       String currentExtension,
                                       String newExtension) {
        renameFiles(dir, currentExtension, maxDepth, currentExtension,
                    newExtension);
    }

}
