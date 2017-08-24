package nu.mine.mosher.mopper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        System.out.println("-------------- \""+input+"\"  -->  "+a);
        System.out.flush();

        assertEquals(ind, a.ind());
        assertEquals(opt, a.opt());
        assertEquals(val, a.val());
        assertEquals(rst, a.rst());
        assertEquals(trm, a.trm());
        assertEquals(pxt, a.pxt());
        assertEquals(pvn, a.pvn());
        assertEquals(false, a.terminated());

        a.terminate();

        assertEquals("", a.ind());
        assertEquals("", a.opt());
        assertEquals(input, a.val());
        assertEquals("", a.rst());
        assertEquals(true, a.trm()); // ?
        assertEquals(true, a.pxt()); // ?
        assertEquals(false, a.pvn());
        assertEquals(true, a.terminated());
    }

    @Test
    void shortOpt() {
        is("-a","-", "a", "","", false, false, false);
    }

    @Test
    void longOpt() {
        is("--aa","--", "aa", "","", false, false, false);
    }

    @Test
    void singleDashOpt() {
        is("-","", "", "-","", false, true, false);
    }

    @Test
    void doubleDashOpt() {
        is("--","--", "", "","", true, true, false);
    }

    @Test
    void multiShortOpt() {
        is("-abc","-", "a", "","bc", false, false, false);
    }

    @Test
    void value() {
        is("v","", "", "v","", false, true, false);
    }

    @Test
    void longOptEmptyArg() {
        is("--aa=","--", "aa", "","", false, false, false);
    }

    @Test
    void longOptArg() {
        is("--aa=v","--", "aa", "v","", false, false, false);
    }

    @Test
    void vendorArg() {
        is("aa=v","", "aa", "v","", false, false, false);
    }

    @Test
    void vendorOpt() {
        is("-W","-", "W", "","", false, false, true);
    }
}
