import nu.mine.mosher.mopper.ArgParser;

public class MopC {
    public static void main(String[] args) {
        run(new ArgParser<>(new MopCOptsSub()).parse(args));
    }

    private static void run(MopCOptsSub opts) {
        System.err.println("verbosity: " + Integer.toString(opts.verbosity));
        System.err.println("width: " + Integer.toString(opts.width));
        System.err.println("files: " + opts.files);
        if (opts.charset != null) {
            System.err.println("charset: " + opts.charset);
        }
    }
}
