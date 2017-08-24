package nu.mine.mosher.mopper;

public class Arg2 {
    private final String org;

    private boolean terminated;

    private String ind = "";
    private String opt = "";
    private String val = "";
    private String rst = "";
    private boolean trm;
    private boolean pxt;
    private boolean pvn;



    public Arg2(final String arg) {
        this.org = arg;
        parse();
    }

    public String ind() {
        return this.ind;
    }

    public String opt() {
        return this.opt;
    }

    public String val() {
        return this.val;
    }

    public String rst() {
        return this.rst;
    }

    public boolean trm() {
        return this.trm;
    }

    public boolean pxt() {
        return this.pxt;
    }

    public boolean pvn() {
        return this.pvn;
    }

    public boolean terminated() {
        return this.terminated;
    }



    public void terminate() {
        this.terminated = true;
        this.val = org;
        this.ind = this.opt = this.rst = "";
    }



    private void parse() {
        this.ind = this.org.substring(0,1);
        this.opt = this.org.substring(1);
    }



    @Override
    public String toString() {
        String s = "/";
        if (this.ind.equals("--")) {
            s += this.ind + this.opt + "=" + this.val;
            if (!this.rst.isEmpty()) {
                s += " [" + this.rst + "]";
            }
        } else if (this.ind.equals("-")) {
            s += this.ind + this.opt;
            if (!this.val.isEmpty()) {
                s += " " + this.val;
            }
            if (!this.rst.isEmpty()) {
                s += " [" + this.rst + "]";
            }
        } else {
            s += "[" + this.org + "]";
        }
        s += "/ {" + this.ind + "|" + this.opt + "|" + this.val + "|" + this.rst + "}";
        return s;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Arg2)) {
            return false;
        }
        final Arg2 that = (Arg2)obj;
        return this.org.equals(that.org) && (this.terminated == that.terminated);
    }

    @Override
    public int hashCode() {
        return this.org.hashCode() * 31 + (this.terminated ? 31 : 0);
    }
}
