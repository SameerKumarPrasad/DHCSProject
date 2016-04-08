package info.androidhive.slidingmenu;

/**
 * Created by ubuntu on 8/4/16.
 */
public class CReview {
private String ctimeStamp;
private String cr;

    public CReview(String ctimeStamp, String cr)
    {
        this.setCtimesatamp(ctimeStamp);
        this.setCr(cr);

    }

    public String getCtimeStamp() {
        return ctimeStamp;
    }

    public void setCtimesatamp(String ctimeStamp) {
        this.ctimeStamp = ctimeStamp;
    }

    public String getCr() {
        return cr;
    }

    public void setCr (String cr) {
        this.cr = cr;
    }


}

