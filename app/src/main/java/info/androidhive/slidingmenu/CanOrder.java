package info.androidhive.slidingmenu;

/**
 * Created by Rohan on 4/10/2016.
 */
public class CanOrder {
    private String timestamp;
    private String place;
    private String idli;
    private String dosa;
    private String name;
    private String vada;
    private String roll;
    private String hakka;
    private String samosa;
    private String burger;
    private String mobileno;
    private String chowmein;
    private String momos;

    public CanOrder(String timestamp, String name, String place, String idli, String dosa,String vada, String roll, String hakka, String  samosa, String burger, String mobileno,String momos, String chowmein)
    {
        this.settimestamp(timestamp);
        this.setname(name);
        this.setplace(place);
        this.setidli(idli);
        this.setdosa(dosa);
        this.setvada(vada);
        this.sethakka(hakka);
        this.setroll(roll);
        this.setsamosa(samosa);
        this.setburger(burger);
        this.setchowmein(chowmein);
        this.setmomos(momos);
        this.setmobileno(mobileno);

    }

    public String gettimestamp() {
        return timestamp;
    }
    public String getname() {
        return name;
    }
    public String getplace() {
        return place;
    }
    public String getidli() {
        return idli;
    }
    public String getvada() {
        return vada;
    }

    public String getdosa() {
        return dosa;
    }
    public String gethakka() {
        return hakka;
    }
    public String getsamosa() {
        return samosa;
    }
    public String getmobileno() {
        return mobileno;
    }
    public String getburger() {
        return burger;
    }
    public String getchowmein() {
        return chowmein;
    }
    public String getroll() {
        return roll;
    }
    public String getmomos() {
        return momos;
    }


    public void settimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setname (String name) {
        this.name = name;
    }
    public void setplace (String place) {
        this.place = place;
    }
    public void setidli (String idli) {
        this.idli = idli;
    }
    public void setdosa (String dosa) {
        this.dosa = dosa;
    }
    public void setvada (String vada) {
        this.vada = vada;
    }
    public void setroll (String roll) {
        this.roll = roll;
    }
    public void sethakka (String hakka) {
        this.hakka = hakka;
    }
    public void setsamosa (String samosa) {
        this.samosa = samosa;
    }
    public void setburger (String burger) {
        this.burger = burger;
    }
    public void setmobileno (String mobileno) {
        this.mobileno = mobileno;
    }
    public void setchowmein (String chowmein) {
        this.chowmein = chowmein;
    }
    public void setmomos (String momos) {
        this.momos = momos;
    }

}


