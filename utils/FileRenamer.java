import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileRenamer {
	public static void main(String[] args) {
		String pathname = "D:\\Downloads";
		renameFiles(pathname, "Watch", "Full Online ");
	}

	public static void renameFiles(String pathname, String prefixToRemove, String suffixToRemove) {

		File dir = new File(pathname);
		if (!dir.exists()) {
			throw new RuntimeException("Please provide Correct pathname");
		}

		if (dir.isDirectory()) {
			File[] files = dir.listFiles();

			for (File file : files) {
				String fileName = file.getName();
				if (file.isFile() && fileName.startsWith(prefixToRemove) && fileName.contains(suffixToRemove)) {
					Path path = file.toPath();
					System.out.println("Path before renaming:    " + path);

					String newFileName = fileName.replaceAll(prefixToRemove + "[ -]", "");
					newFileName = newFileName.replaceAll("[ -]" + suffixToRemove + "[- 0-9A-Za-z.()]*.mp4", ".mp4");

					Path newPath = Paths.get(dir.getPath(), newFileName);

					try {
						Files.move(path, newPath);
						System.out.println("Path after renaming: " + newPath);
					} catch (IOException e) {
						System.err.println("IOException while renaming files: " + e.getMessage());
					}
				}
			}

		} else {

		}
	}

}
