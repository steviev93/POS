package Models;

public class Title {
    private int TitleId;
    private String TitleName;

    public Title(int titleId, String titleName) {
        TitleId = titleId;
        TitleName = titleName;
    }

    public int getTitleId() {
        return TitleId;
    }

    public void setTitleId(int titleId) {
        TitleId = titleId;
    }

    public String getTitleName() {
        return TitleName;
    }

    public void setTitleName(String titleName) {
        TitleName = titleName;
    }
}
