import java.io.File;
import java.io.IOException;

public class YTDL_Utility {
    public static final String YTDL = "youtube-dl"; // main command
    public static final String IGNORE_ERRORS = " -i"; // ignore errors
    public static final String DESCRIPTION = " --write-description";

    public static final String BEST_AV_FORMAT =
            " -f 'bestvideo[ext=mp4]+bestaudio[ext=m4a]/best[ext=mp4]/best'";


    // Output file name formats:
    public static final String OUTPUT_INDEX_TITLE =
            " -o '%(playlist_index)s - %(title)s.%(ext)s'";
    public static final String OUTPUT_PL_INDEX_TITLE =
            " -o '%(playlist_index)s - %(title)s.%(ext)s'";
    public static final String OUTPUT_PL_TITLE =
            "-o '%(playlist)s/%(title)s.%(ext)s'";
    public static final String OUTPUT_TITLE_ONLY =
            " -o '%(playlist)s/%(title)s.%(ext)s'";
    public static final String RESTRICT_FILENAMES = "--restrict-filenames";

    // Use only if in simulation mode,
    // Note: Providing this option will activate simulation mode
    public static final String GET_FILENAME = "--get-filename";


    // TODO: Take Channel URL from properties or as an argument
    public static String channelURL = "https://www.youtube" +
                                      ".com/c/IIECconnect/featured";
    private static String channelName;
    private static String videosURL;
    private static String playlistsURL;

    private static String currentDirectory = System.getProperty("user.dir");

    public static void main(String[] args) {
        try {
            initializeChannelData(channelURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param channel
     */
    private static void initializeChannelData(String channel)
            throws IOException {
        if (channel.endsWith("featured")) {
            int index = channel.lastIndexOf("/featured");
            channel = channel.substring(0, index);
        }
        channelURL = channel;
        channelName = channelURL.substring(channelURL.lastIndexOf("/") + 1);
        videosURL = channelURL + "/videos";
        playlistsURL = channelURL + "/playlists";

        System.out.println(channelName);
        System.out.println(channelURL);
        System.out.println(videosURL);
        System.out.println(playlistsURL);


        writeVideosInformation(videosURL);
        writePlaylistsInformation(playlistsURL);
    }

    private static void writePlaylistsInformation(String playlistsURL)
            throws IOException {
        String[] command = new String[]{"youtube-dl", RESTRICT_FILENAMES,
                                        GET_FILENAME, OUTPUT_PL_TITLE,
                                        playlistsURL,
                                        // "> PlaylistsInfo.txt 2>&1"
        };
        SystemCommandUtils.executeCommand(
                new File(currentDirectory, "PlaylistInfo.txt"), command);
    }

    private static void writeVideosInformation(String videosURL) {

    }


    public static int getIndexFromPlayList(String playlistName,
                                           String videoName) {

        return 0;
    }

    public static int getIndexFromVideos(String videoName) {
        return 0;
    }
}
