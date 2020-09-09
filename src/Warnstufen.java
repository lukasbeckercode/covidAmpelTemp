

public class Warnstufen {
    private String warnstufe;

    private String gkz;

    private String name;

    private String region;

    public String getWarnstufe() {
        return warnstufe;
    }

    public String getGkz() {
        return gkz;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }


    @Override
    public String toString() {
        return warnstufe + "---" + name;
    }
}
