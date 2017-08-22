import java.io.File;
import java.util.ArrayList;

// inherits options from super-class
@SuppressWarnings("unused")
public class MopCOptsSub extends MopCOpts {
    public ArrayList<File> files = new ArrayList<>(2);

    // non-option arguments call this method
    public void __(final String unnamed) {
        this.files.add(new File(unnamed));
    }

    public void v() {
        super.v();
        System.out.println("sub v");
    }
}
