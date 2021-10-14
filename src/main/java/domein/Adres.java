package domein;


import javax.persistence.*;

@Entity
@Table(name = "adres")
public class Adres {

    @Id
    private int adres_id;

    @Column
    private String postcode;
    private String huisnummer;
    private String straat;
    private String woonplaats;
    private int reiziger_id;

    @OneToOne
    @JoinColumn(name = "adres_id")
    private Reiziger reiziger;

    public Adres(){

    }

    public Adres(int id,String huisnummer, String postcode, int reiziger_id, String straat, String woonplaats ) {
        this.adres_id = id;
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.straat=straat;
        this.woonplaats=woonplaats;
        this.reiziger_id = reiziger_id;

    }



    public int getAdres_id() {
        return adres_id;
    }

    public void setAdres_id(int id) {
        this.adres_id = id;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }


//    public int getReizigerID() {
//        return reiziger.getReiziger_Id();
//    }
//
//    public void setReizigerID(int reisigerid) {
//        reiziger.setReiziger_Id(reisigerid);
//    }


    public int getReizigerID() {
        return reiziger_id;
    }

    public void setReizigerID(int reiziger_id) {
        this.reiziger_id = reiziger_id;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public int getReisigerid() {
        return reiziger.getReiziger_Id();
    }

    public void setReisigerid(int reisigerid) {
        reiziger.setReiziger_Id(reisigerid);
    }


    @Override
    public String toString() {
        return "Adres{" +
                "adres_id=" + adres_id +
                ", postcode='" + postcode + '\'' +
                ", huisnummer='" + huisnummer + '\'' +
                ", straat='" + straat + '\'' +
                ", woonplaats='" + woonplaats + '\'' +
                  "reisigerid=" + reiziger_id +
                '}';
    }
}

