package info.androidhive.slidingmenu;

/**
 * Created by ubuntu on 11/4/16.
 */
public class UPoll {


    private String UPTimeStamp;
    private String UP;

    public UPoll(String UPTimeStamp, String UP)
    {
        this.setUPTimesatamp(UPTimeStamp);
        this.setUP(UP);

    }

    public String getUPTimeStamp() {
        return UPTimeStamp;
    }

    public void setUPTimesatamp(String UPTimeStamp) {
        this.UPTimeStamp = UPTimeStamp;
    }

    public String getUP() {
        return UP;
    }

    public void setUP (String UP) {
        this.UP = UP;
    }

}
