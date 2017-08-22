import nu.mine.mosher.mopper.ArgParser;

/*
$ javac -cp example/optvalue:src/main/java example/optvalue/Foobar.java
$ java  -cp example/optvalue:src/main/java Foobar -x
(-x processed)
-f not present
$ java  -cp example/optvalue:src/main/java Foobar -f -x
-f present, with value: -x
$ java  -cp example/optvalue:src/main/java Foobar -f bar -x
(-x processed)
-f present, with value: bar
$ java  -cp example/optvalue:src/main/java Foobar -f
-f present, but without a value
*/
public class Foobar {
    public static void main(String[] args) {
        FoobarOpts opts = new ArgParser<>(new FoobarOpts()).parse(args);
        if (opts.foo == null) {
            System.err.println("-f not present");
        } else if (opts.foo.isEmpty()) {
            System.err.println("-f present, but without a value");
        } else {
            System.err.println("-f present, with value: "+opts.foo);
        }
        System.err.flush();
    }

    public static class FoobarOpts {
        public String foo;
        public void f(String val) {
            foo = val;
        }
        public void x() {
            System.err.println("(-x processed)");
        }
    }
}
