import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class SystemCommandUtils {
    static String currentDirectory;

    public static void main(String[] args) {
        currentDirectory = System.getProperty("user.dir");
        try {
            executeCommand(new File(currentDirectory), "youtube-dl", "-h");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void executeCommand(File file, String... command)
            throws IOException {
        System.out.println(file.exists());
        System.out.println(file.isDirectory());
        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new IOException("Cannot create specified file!");
            }
        }
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.directory(file.getParentFile());
        pb.inheritIO();
        pb.redirectErrorStream(true);
        pb.redirectOutput(file);

        try {
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
            int exitVal = process.waitFor();
            if (exitVal != 0) {
                System.out
                        .println("Abnormal Behaviour! Something bad happened.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Something went wrong. Here are more details\n" +
                               e.getMessage());
        }
    }

}
