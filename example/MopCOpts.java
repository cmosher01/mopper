import nu.mine.mosher.mopper.Optional;

import java.nio.charset.Charset;

@SuppressWarnings("unused")
public class MopCOpts {
    // options with defaults
    public int verbosity = 3;
    public int width = 60;
    // option with no default (null)
    public Charset charset;
    public Integer siz;
    public String sizUnits;

    // alias
    public void v() {
        verbose();
    }

    // verbose (more v's for more verbose)
    public void verbose() {
        this.verbosity++;
    }

    // option with a (required) value, and error checking
    public void w(final String width) {
        try {
            this.width = Integer.parseInt(width);
        } catch (final Throwable e) {
            throw new IllegalArgumentException("invalid width: "+width);
        }
        if (this.width <= 0) {
            throw new IllegalArgumentException("width specified as " + width + ", but must be greater than 0");
        }
    }

    // option with an optional value
    public void encoding(@Optional final String enc) {
        if (enc.isEmpty()) {
            // if option is given, without a value:
            this.charset = Charset.forName("windows-1252");
        } else {
            // if option is given with a value:
            this.charset = Charset.forName(enc);
        }
    }

    // option with one required value and one optional value
    public void size(final String n, @Optional final String units) {
        this.siz = Integer.valueOf(n);
        if (units.isEmpty()) {
            this.sizUnits = "inches";
        } else {
            this.sizUnits = units;
        }
    }
}
