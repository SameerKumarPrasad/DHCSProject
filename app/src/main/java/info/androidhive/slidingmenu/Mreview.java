package info.androidhive.slidingmenu;


public class Mreview {
    private String mtimeStamp;
    private String mlunch;
    private String mbreakfast;
    private String msnacks;
    private String mdinner;

    public Mreview(String mtimeStamp, String mbreakfast, String mlunch, String msnacks, String mdinner)
    {
        this.setMtimesatamp(mtimeStamp);
        this.setMbreakfast(mbreakfast);
        this.setMluch(mlunch);
        this.setMsnacks(msnacks);
        this.setMdinner(mdinner);

    }

    public String getMtimeStamp() {
        return mtimeStamp;
    }

    public void setMtimesatamp(String mtimeStamp) {
        this.mtimeStamp = mtimeStamp;
    }

    public String getMbreakfast() {
        return mbreakfast;
    }

    public void setMbreakfast(String mbreakfast) {
        this.mbreakfast = mbreakfast;
    }

    public String getMlunch() {
        return mlunch;
    }

    public void setMluch(String mlunch) {
        this.mlunch = mlunch;
    }

    public String getMsnacks() {
        return msnacks;
    }

    public void setMsnacks(String msnacks) {
        this.msnacks = msnacks;
    }

    public String getMdinner() {
        return mdinner;
    }

    public void setMdinner(String mdinner) {
        this.mdinner = mdinner;
    }


}
