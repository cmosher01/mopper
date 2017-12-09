# Mopper

Mopper is a command-line option processor for Java programs.

This software is distributed under the
[GPLv3](http://www.gnu.org/licenses/gpl-3.0-standalone.html)
license.

Include as dependency in gradle:

```groovy
repositories {
    maven {
        url 'http://mosher.mine.nu/nexus/repository/maven-public/'
    }
}

dependencies {
    compile group: 'nu.mine.mosher.mopper', name: 'mopper', version: 'latest.integration'
}
```

Example:

```java
import nu.mine.mosher.mopper.ArgParser;

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
        // each method becomes a command line option
        public void f(String val) {
            foo = val;
        }
        public void x() {
            System.err.println("(-x processed)");
        }
    }
}
```

```shell
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
```

More examples are available in the [example](example) directory.
