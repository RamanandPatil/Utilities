package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * 
 * @author rkpatil
 *
 */
public class FileUtils {

	public static final boolean DEBUG = true;

	/**
	 * 
	 * @param maxDepth
	 */
	public static void listFiles(int maxDepth) {
		listFiles(".", maxDepth);
	}

	/**
	 * 
	 * @param dir
	 */
	public static void listFiles(String dir) {
		listFiles(dir, Integer.MAX_VALUE);
	}

	/**
	 * 
	 * @param dir
	 * @param maxDepth
	 */
	public static void listFiles(String dir, int maxDepth) {
		try {
			Files.find(Paths.get(dir), maxDepth, (filePath, fileAttr) -> fileAttr.isRegularFile())
					.forEach(System.out::println);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param findString
	 * @param replaceString
	 */
	public static void renameFiles(String findString, String replaceString) {
		renameFiles(".", Integer.MAX_VALUE, findString, replaceString);
	}

	/**
	 * 
	 * @param dirOrExtension
	 * @param findString
	 * @param replaceString
	 */
	public static void renameFiles(String dirOrExtension, String findString, String replaceString) {
		if (dirOrExtension.length() > 1 && dirOrExtension.startsWith(".")) {
			renameFiles(".", dirOrExtension, Integer.MAX_VALUE, findString, replaceString);
		} else {
			renameFiles(dirOrExtension, "", Integer.MAX_VALUE, findString, replaceString);
		}
	}

	/**
	 * 
	 * @param dir
	 * @param maxValue
	 * @param findString
	 * @param replaceString
	 */
	private static void renameFiles(String dir, int maxValue, String findString, String replaceString) {
		renameFiles(".", "", Integer.MAX_VALUE, findString, replaceString);
	}

	/**
	 * 
	 * @param dir
	 * @param fileExtension
	 * @param findString
	 * @param replaceString
	 */
	public static void renameFiles(String dir, String fileExtension, String findString, String replaceString) {
		renameFiles(dir, fileExtension, Integer.MAX_VALUE, findString, replaceString);
	}

	/**
	 * 
	 * @param dir
	 * @param fileExtension
	 * @param maxDepth
	 * @param findString
	 * @param replaceString
	 */
	public static void renameFiles(String dir, String fileExtension, int maxDepth, String findString,
			String replaceString) {
		try {
			try (Stream<Path> stream = Files.find(Paths.get(dir), maxDepth,
					(path, attr) -> String.valueOf(path).endsWith(fileExtension))) {

				stream.map(String::valueOf).forEach(item -> {
					if (item.contains(findString)) {
						try {
							Path oldPath = new File(item).toPath();
							Path newPath = new File(item.replace(findString, replaceString)).toPath();
							if (DEBUG) {
								System.out.println("Old: " + oldPath);
								System.out.println("New: " + newPath);
							}
							// TODO: find out why Files.move(...) is not working for file extension.
							if (fileExtension.equals(findString)) {
								new File(oldPath.toString()).renameTo(new File(newPath.toString()));
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
	 * 
	 * @param dir
	 * @param currentExtension
	 * @param newExtension
	 */
	public static void changeExtension(String dir, String currentExtension, String newExtension) {
		renameFiles(dir, currentExtension, Integer.MAX_VALUE, currentExtension, newExtension);
	}

	/**
	 * 
	 * @param currentExtension
	 * @param newExtension
	 */
	public static void changeExtension(String currentExtension, String newExtension) {
		renameFiles(".", currentExtension, Integer.MAX_VALUE, currentExtension, newExtension);
	}

	/**
	 * 
	 * @param dir
	 * @param maxDepth
	 * @param currentExtension
	 * @param newExtension
	 */
	public static void changeExtension(String dir, int maxDepth, String currentExtension, String newExtension) {
		renameFiles(dir, currentExtension, maxDepth, currentExtension, newExtension);
	}

}
