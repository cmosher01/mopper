package nu.mine.mosher.mopper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("WeakerAccess")
public class TestMopper {
    public static class T1 {
        public String update;
        public boolean x;

        public void u(final String value) {
            update(value);
        }

        public void update(final String value) {
            this.update = value;
        }

        public void x() {
            this.x = true;
        }
    }

    @Test
    void shortBoolean() {
        final T1 o = new T1();
        final ArgParser<T1> uut = new ArgParser<>(o);

        assertFalse(o.x);
        uut.parse("-x");
        assertTrue(o.x);
    }

    @Test
    void shortValue() {
        final T1 o = new T1();
        final ArgParser<T1> uut = new ArgParser<>(o);

        assertNull(o.update);
        uut.parse("-u", "FOO");
        assertEquals("FOO", o.update);
    }

    @Test
    void shortMissingRequiredValue() {
        final T1 o = new T1();
        final ArgParser<T1> uut = new ArgParser<>(o);

        assertNull(o.update);
        uut.parse("-u");
        assertNull(o.update);
    }

    @Test
    void none() {
        final T1 o = new T1();
        final ArgParser<T1> uut = new ArgParser<>(o);

        assertFalse(o.x);
        assertNull(o.update);
        uut.parse();
        assertFalse(o.x);
        assertNull(o.update);
    }

    @Test
    void bug_1() {
        final T1 o = new T1();
        final ArgParser<T1> uut = new ArgParser<>(o);

        assertNull(o.update);
        assertFalse(o.x);
        uut.parse("--update=FOO", "-x");
        assertEquals("FOO", o.update);
        assertTrue(o.x);
    }

    @Test
    void bug_2() {
        final T1 o = new T1();
        final ArgParser<T1> uut = new ArgParser<>(o);

        assertNull(o.update);
        uut.parse("-u", "");
        assertEquals("", o.update);
    }
}
