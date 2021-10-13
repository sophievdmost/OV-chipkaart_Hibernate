package domein;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "ov_chipkaart")
public class OVChipkaart {
    @Id
    private int kaart_nummer;

    @Column
    private Date geldig_tot;
    private int klasse;
    private int saldo;
    private int reiziger_id;

    @ManyToOne
    @JoinColumn(name = "reiziger_id", insertable = false, updatable = false)
    private Reiziger reiziger;

    @ManyToMany
    @JoinTable(name = "ov_chipkaart_product", joinColumns = {@JoinColumn(name = "kaart_nummer")},
            inverseJoinColumns = {@JoinColumn(name = "product_nummer")})
    private List<Product > producten;

    public OVChipkaart(){

    }

    public OVChipkaart(int kaart_nummer, Date geldig_tot, int klasse, int saldo, Reiziger reiziger) {
        this.kaart_nummer = kaart_nummer;
        this.geldig_tot = geldig_tot;
        this.klasse = klasse;
        this.saldo= saldo;
        this.reiziger = reiziger;
    }

    public int getKaart_nummer() {
        return kaart_nummer;
    }

    public void setKaart_nummer(int kaart_nummer) {
        this.kaart_nummer = kaart_nummer;
    }

    public Date getGeldig_tot() {
        return geldig_tot;
    }

    public void setGeldig_tot(Date geldig_tot) {
        this.geldig_tot = geldig_tot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getReisigerid() {
        return reiziger.getId();
    }

    public void setReisigerid(int reisigerid) {
        reiziger.setId(reisigerid);
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public List<Product> getProducten() {
        return producten;
    }

    public void setProducten(List<Product> producten) {
        this.producten = producten;
    }

    @Override
    public String toString() {
        return "OVChipkaart{" +
                "kaart_nummer=" + kaart_nummer +
                ", geldig_tot=" + geldig_tot +
                ", klasse=" + klasse +
                ", saldo=" + saldo +
                ", reiziger=" + reiziger +
                '}';
    }
}
