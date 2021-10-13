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

    public Adres(){

    }

    public Adres(int id, String postcode, String huisnummer, String straat, String woonplaats) {
        this.adres_id = id;
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.straat=straat;
        this.woonplaats=woonplaats;

    }

    @OneToOne
    @JoinColumn(name = "adres_id")
    private Reiziger reiziger;

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
        return reiziger.getId();
    }

    public void setReisigerid(int reisigerid) {
        reiziger.setId(reisigerid);
    }




    @Override
    public String toString() {
        return "Adres{" +
                "id=" + adres_id +
                ", postcode='" + postcode + '\'' +
                ", huisnummer='" + huisnummer + '\'' +
                ", straat='" + straat + '\'' +
                ", woonplaats='" + woonplaats + '\'' +
                ", reisigerid=" + reiziger.getId() +
                '}';
    }
}

