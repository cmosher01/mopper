import nu.mine.mosher.mopper.ArgParser;

/*
$ javac -cp example/alias:src/main/java example/alias/Foobar.java
$ java  -cp example/alias:src/main/java Foobar
$ java  -cp example/alias:src/main/java Foobar -f
foo
$ java  -cp example/alias:src/main/java Foobar --foo
foo
*/
public class Foobar {
    public static void main(String[] args) {
        FoobarOpts opts = new ArgParser<>(new FoobarOpts()).parse(args);

        if (opts.foo) {
            System.err.println("foo");
        }
        System.err.flush();
    }

    public static class FoobarOpts {
        public boolean foo;

        // This is the -f option (alias for --foo option)
        public void f() {
            foo();
        }

        // This is the --foo option
        public void foo() {
            this.foo = true;
        }
    }
}
