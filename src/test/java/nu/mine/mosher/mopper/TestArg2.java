package nu.mine.mosher.mopper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("WeakerAccess")
class TestArg2 {
    private static void is(
        final String input,
        final String ind,
        final String opt,
        final String val,
        final String rst,
        final boolean trm,
        final boolean pxt,
        final boolean pvn) {

        final Arg2 a = new Arg2(input);
        System.out.println(input+"  -->  "+a);
        System.out.flush();

        assertEquals(ind, a.ind());
        assertEquals(opt, a.opt());
        assertEquals(val, a.val());
        assertEquals(rst, a.rst());
        assertEquals(trm, a.trm());
        assertEquals(pxt, a.pxt());
        assertEquals(pvn, a.pvn());
        assertFalse(a.terminated());

        a.terminate();

        assertEquals("", a.ind());
        assertEquals("", a.opt());
        assertEquals(input, a.val());
        assertEquals("", a.rst());
        assertTrue(a.trm()); // ?
        assertTrue(a.pxt()); // ?
        assertFalse(a.pvn());
        assertTrue(a.terminated());
    }

    @Test
    @DisplayName("-a")
    void shortOpt() {
        is("-a","-", "a", "","", false, false, false);
    }

    @Test
    @DisplayName("--aa")
    void longOpt() {
        is("--aa","--", "aa", "","", false, false, false);
    }

    @Test
    @DisplayName("-")
    void singleDashOpt() {
        is("-","", "", "-","", false, true, false);
    }

    @Test
    @DisplayName("--")
    void doubleDashOpt() {
        is("--","--", "", "","", true, true, false);
    }

    @Test
    @DisplayName("-abc")
    void multiShortOpt() {
        is("-abc","-", "a", "","bc", false, false, false);
    }

    @Test
    @DisplayName("v")
    void value() {
        is("v","", "", "v","", false, true, false);
    }

    @Test
    @DisplayName("=")
    void valueEq() {
        is("=","", "", "=","", false, true, false);
    }

    @Test
    @DisplayName("=v")
    void valueEqV() {
        is("=v","", "", "=v","", false, true, false);
    }

    @Test
    @DisplayName("aa=")
    void valueOEq() {
        is("aa=","", "aa", "","", false, false, false);
    }

    @Test //???
    @DisplayName("--=v")
    void doubleDashEqV() {
        is("--=v","--", "", "v","", false, false, false);
    }

    @Test //???
    @DisplayName("--=")
    void doubleDashEq() {
        is("--=","--", "", "","", false, false, false);
    }

    @Test //???
    @DisplayName("-W=v")
    void vendorOptEqV() {
        is("-W=v","", "", "v","", false, false, false);
    }

    @Test //???
    @DisplayName("-W=")
    void vendorOptEq() {
        is("-W=","", "", "","", false, false, false);
    }

    @Test
    @DisplayName("--aa=")
    void longOptEmptyArg() {
        is("--aa=","--", "aa", "","", false, false, false);
    }

    @Test
    @DisplayName("--aa=v")
    void longOptArg() {
        is("--aa=v","--", "aa", "v","", false, false, false);
    }

    @Test
    @DisplayName("aa=v")
    void vendorArg() {
        is("aa=v","", "aa", "v","", false, false, false);
    }

    @Test
    @DisplayName("-W")
    void vendorOpt() {
        is("-W","-", "W", "","", false, false, true);
    }

    @Test
    @DisplayName("-Waa=v")
    void vendorOptNoSpace() {
        is("-Waa=v","", "aa", "v","", false, false, false);
    }

    @Test
    @DisplayName("<space>")
    void space() {
        is(" ","", "", " ","", false, true, false);
    }


    @Test
    void beforeEq() {
        assertBeforeEq("", "");
        assertBeforeEq("=", "");
        assertBeforeEq("=v", "");
        assertBeforeEq("k", "k");
        assertBeforeEq("k=", "k");
        assertBeforeEq("k=v", "k");
    }

    void assertBeforeEq(final String in, final String expected) {
        assertEquals(expected, Arg2.beforeEq(in));
    }

    @Test
    void afterEq() {
        assertAfterEq("", "");
        assertAfterEq("=", "");
        assertAfterEq("=v", "v");
        assertAfterEq("k", "");
        assertAfterEq("k=", "");
        assertAfterEq("k=v", "v");
    }

    void assertAfterEq(final String in, final String expected) {
        assertEquals(expected, Arg2.afterEq(in));
    }
}
