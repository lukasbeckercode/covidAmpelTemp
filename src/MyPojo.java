import java.util.Arrays;

public class MyPojo {
    private Warnstufen[] warnstufen;

    private String kw;

    public Warnstufen[] getWarnstufen() {
        return warnstufen;
    }


    public String getKw() {
        return kw;
    }


    @Override
    public String toString() {
        return "ClassPojo [warnstufen = " + Arrays.toString(warnstufen) + ", kw = " + kw + "]";
    }
}
