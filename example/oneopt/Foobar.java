import nu.mine.mosher.mopper.ArgParser;

/*
$ javac -cp example/oneopt:src/main/java example/oneopt/Foobar.java
$ java  -cp example/oneopt:src/main/java Foobar
$ java  -cp example/oneopt:src/main/java Foobar -f
foo
*/
public class Foobar {
    public static void main(String[] args) {
        // Parse given command line arguments into our FoobarOpts object
        FoobarOpts opts = new ArgParser<>(new FoobarOpts()).parse(args);

        // Use the options in the program
        if (opts.foo) {
            System.err.println("foo");
        }
        System.err.flush();
    }

    public static class FoobarOpts {
        public boolean foo;

        // All public methods define the program options.

        // This is the -f option
        public void f() {
            foo = true;
        }
    }
}
