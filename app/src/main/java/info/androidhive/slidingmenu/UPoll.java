package info.androidhive.slidingmenu;

/**
 * Created by ubuntu on 11/4/16.
 */
public class UPoll {


    private String UPTimeStamp;
    private String UP;
    private String op1;
    private String op2;
    private String op3;
    private String op4;

    public UPoll(String UPTimeStamp, String UP,String op1,String op2,String op3,String op4)
    {
        this.setUPTimesatamp(UPTimeStamp);
        this.setUP(UP);
        this.setOP1(op1);
        this.setOP2(op2);
        this.setOP3(op3);
        this.setOP4(op4);
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

    public String getOP1() {
        return op1;
    }

    public void setOP1 (String op1) {
        this.op1 = op1;
    }

    public String getOP2() {
        return op2;
    }

    public void setOP2 (String op2) {
        this.op2 = op2;
    }

    public String getOP3() {
        return op3;
    }

    public void setOP3 (String op3) {
        this.op3 = op3;
    }

    public String getOP4() {
        return op4;
    }

    public void setOP4 (String op4) {
        this.op4 = op4;
    }



}
