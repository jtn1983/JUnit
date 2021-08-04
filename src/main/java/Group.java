public enum Group {
    WORK("Work"),
    HOME("Home"),
    FAMILY("Family");

    private String title;

    Group(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static boolean contains (String str) {
        for (Group group : values()){
            if (group.title.equals(str)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return title;
    }
}