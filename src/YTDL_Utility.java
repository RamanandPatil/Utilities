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
            " -o '%(playlist)s/%(title)s.%(ext)s'";
    public static final String OUTPUT_TITLE_ONLY =
            " -o '%(playlist)s/%(title)s.%(ext)s'";
    public static final String RESTRICT_FILENAMES = " --restrict-filenames";

    // Use only if in simulation mode,
    // Note: Providing this option will activate simulation mode
    public static final String GET_FILENAME = " --get-filename";

    public static void main(String[] args) {

    }
}
