package info.androidhive.slidingmenu;

/**
 * Created by ubuntu on 10/4/16.
 */
public class UAnnouncement {

    private String UATimeStamp;
    private String UA;

    public UAnnouncement(String UATimeStamp, String UA)
    {
        this.setUATimesatamp(UATimeStamp);
        this.setUA(UA);

    }

    public String getUATimeStamp() {
        return UATimeStamp;
    }

    public void setUATimesatamp(String UATimeStamp) {
        this.UATimeStamp = UATimeStamp;
    }

    public String getUA() {
        return UA;
    }

    public void setUA (String UA) {
        this.UA = UA;
    }

}
