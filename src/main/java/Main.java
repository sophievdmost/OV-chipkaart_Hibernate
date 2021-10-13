

import domein.Adres;
import domein.OVChipkaart;
import domein.Product;
import domein.Reiziger;


import DAO.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Testklasse - deze klasse test alle andere klassen in deze package.
 *
 * System.out.println() is alleen in deze klasse toegestaan (behalve voor exceptions).
 *
 * @author tijmen.muller@hu.nl
 */
public class Main {
    // Creëer een factory voor Hibernate sessions.
    private static final SessionFactory factory;

    static {
        try {
            // Create a Hibernate session factory
            factory = new Configuration().configure().buildSessionFactory();

        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Retouneer een Hibernate session.
     *
     * @return Hibernate session
     * @throws HibernateException
     */
    private static Session getSession() throws HibernateException {
        return factory.openSession();
    }

    public static void main(String[] args) throws SQLException {
        testFetchAll();
        setTestData();
        testDAOHibernate();
    }

    /**
     * P6. Haal alle (geannoteerde) entiteiten uit de database.
     */
    private static void testFetchAll() {
        Session session = getSession();
        try {
            Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                Query query = session.createQuery("from " + entityType.getName());

                System.out.println("[Test] Alle objecten van type " + entityType.getName() + " uit database:");
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
                System.out.println();
            }
        } finally {
            session.close();
        }
    }
    private static void setTestData() {
        //Create reizigers
       Reiziger r1 = new Reiziger(6, "s", null, "Most", java.sql.Date.valueOf("2002-11-07" ));
        Reiziger r2 = new Reiziger(7, "s", null, "boom", java.sql.Date.valueOf("2002-11-05" ));
        Reiziger r3 = new Reiziger(8, "s", null, "kloom", java.sql.Date.valueOf("2002-11-10" ));
        Reiziger r4 = new Reiziger(9, "s", null, "ha", java.sql.Date.valueOf("2002-11-01" ));
        Reiziger r5 = new Reiziger(10, "s", null, "Mt", java.sql.Date.valueOf("2002-11-02" ));

        //Create adressen
        Adres a1 = new Adres(11, "3791VP", "1", "Heemstrastraat", "Driebergen", 6);
        Adres a2 = new Adres(12, "3791VP", "2", "Heemstrastraat", "Driebergen", 7);
        Adres a3 = new Adres(13, "3791VP", "3", "Heemstrastraat", "Driebergen", 8);
        Adres a4 = new Adres(14, "3791VP", "4", "Heemstrastraat", "Driebergen", 9);
        Adres a5 = new Adres(15, "3791VP", "5", "Heemstrastraat", "Driebergen", 10);

        //Create ovchipkaarten
        OVChipkaart o1 = new OVChipkaart(69999, java.sql.Date.valueOf("2030-11-19"), 2, 1000, r1);
        OVChipkaart o2 = new OVChipkaart(66666, java.sql.Date.valueOf("2030-11-18"), 2, 1000, r2);
        OVChipkaart o3 = new OVChipkaart(70000, java.sql.Date.valueOf("2030-11-17"), 2, 1000, r3);
        OVChipkaart o4 = new OVChipkaart(11111, java.sql.Date.valueOf("2030-11-16"), 2, 1000, r4);
        OVChipkaart o5 = new OVChipkaart(12122, java.sql.Date.valueOf("2030-11-15"), 2, 1000, r5);
        OVChipkaart o6 = new OVChipkaart(12123, java.sql.Date.valueOf("2030-11-14"), 2, 1000, r4);
        OVChipkaart o7 = new OVChipkaart(12133, java.sql.Date.valueOf("2030-11-13"), 2, 1000, r3);
        OVChipkaart o8 = new OVChipkaart(12144, java.sql.Date.valueOf("2030-11-12"), 2, 1000, r2);
        OVChipkaart o9 = new OVChipkaart(12155, java.sql.Date.valueOf("2030-11-11"), 2, 1000, r5);
        OVChipkaart o10 = new OVChipkaart(12162, java.sql.Date.valueOf("2030-11-10"), 2, 1000, r1);


        //Create producten
        Product p1 = new Product(61, "TEST PRODUCT1", "TEST BESCHRIJVINGEN VOOR PRODUCT1", 11);
        Product p2 = new Product(62, "TEST PRODUCT2", "TEST BESCHRIJVINGEN VOOR PRODUCT2", 12);
        Product p3 = new Product(63, "TEST PRODUCT3", "TEST BESCHRIJVINGEN VOOR PRODUCT3", 13);
        Product p4 = new Product(64, "TEST PRODUCT4", "TEST BESCHRIJVINGEN VOOR PRODUCT4", 14);
        Product p5 = new Product(65, "TEST PRODUCT5", "TEST BESCHRIJVINGEN VOOR PRODUCT5", 15);

        //voeg producten toe
        List<Product> producten1 = new ArrayList<>();
        List<Product> producten2 = new ArrayList<>();
        List<Product> producten3 = new ArrayList<>();
        List<Product> producten4 = new ArrayList<>();
        List<Product> producten5 = new ArrayList<>();

        producten1.add(p1);
        producten2.add(p2);
        producten3.add(p3);
        producten4.add(p4);
        producten5.add(p5);

        o1.setProducten(producten1);
        o2.setProducten(producten2);
        o3.setProducten(producten3);
        o4.setProducten(producten4);
        o5.setProducten(producten5);
        o6.setProducten(producten1);
        o7.setProducten(producten2);
        o8.setProducten(producten3);
        o9.setProducten(producten4);
        o10.setProducten(producten5);


        //Persisteer alles
        Session session = factory.openSession();
        session.save(r1);
        session.save(r2);
        session.save(r3);
        session.save(r4);
        session.save(r5);
        session.save(o1);
        session.save(o2);
        session.save(o3);
        session.save(o4);
        session.save(o5);
        session.save(o6);
        session.save(o7);
        session.save(o8);
        session.save(o9);
        session.save(o10);
        session.save(a1);
        session.save(a2);
        session.save(a3);
        session.save(a4);
        session.save(a5);
        session.close();
    }

    private static void testDAOHibernate() throws SQLException {
        AdresDAOHibernate adao = new AdresDAOHibernate(getSession());
        OVChipkaartDAOHibernate ovdao = new OVChipkaartDAOHibernate(getSession());
        ProductDAOHibernate pdao = new ProductDAOHibernate(getSession());
        ReizigerDAOHibernate rdao = new ReizigerDAOHibernate(getSession());


        Reiziger reizigertest = new Reiziger(69, "S", "van der", "Most", java.sql.Date.valueOf("2002-11-06"));
        Adres adres = new Adres(10, "3791VP", "1", "Heemstrastraat", "Driebergen", 69);
        OVChipkaart ovChipkaart = new OVChipkaart(69699, java.sql.Date.valueOf("2030-11-19"), 2, 1000, reizigertest);
        Product product = new Product(60, "TEST PRODUCT", "TEST BESCHRIJVINGEN VOOR PRODUCT", 10);

        System.out.println("--------------[TEST REIZIGER]----------------");
        ///save + findall
        System.out.println("---[TEST]--- save + findall");
        System.out.println(rdao.findAll());
        rdao.save(reizigertest);
        System.out.println(rdao.findAll());

        ///update + findbyID
        System.out.println("---[TEST]--- update + findByID");
        System.out.println(rdao.findByID(reizigertest.getReiziger_Id()));
        System.out.println("Voor de update " + rdao.findAll());
        reizigertest.setAchternaam("barnes");
        rdao.update(reizigertest);
        System.out.println("Na de update "+ rdao.findAll());



        ///adres
        System.out.println("--------------[TEST ADRES]----------------");

        //save en findall
        System.out.println("---[TEST]--- save + findall");
        System.out.println(adao.findAll());
        adao.save(adres);
        System.out.println(adao.findAll());

        //update + findbyreiziger
        System.out.println("---[TEST]--- update + findByReiziger");
        System.out.println(rdao.findAll());
        adres.setHuisnummer("2");
        adao.update(adres);
        System.out.println(adao.findByReiziger(reizigertest));

        //delete
        System.out.println("---[TEST]--- delete");
        adao.delete(adres);
        System.out.println(adao.findAll());

        //product
        System.out.println("--------------[TEST PRODUCT]----------------");

        //save + findall
        System.out.println("---[TEST]--- save + findAll");
        System.out.println(pdao.findAll());
        pdao.save(product);
        System.out.println(pdao.findAll());

        //update
        System.out.println("---[TEST]--- update");
        System.out.println(pdao.findAll());
        product.setNaam("UPDATETEST");
        pdao.update(product);
        System.out.println(pdao.findAll());

        //findByOvchimp
        ///insert test findbyov

        //ovchipkaart
        System.out.println("--------------[TEST OVCHIPKAART]----------------");

        //save
        System.out.println("---[TEST]--- save + findAll");
        System.out.println(ovdao.findAll());
        ovdao.save(ovChipkaart);
        System.out.println(ovdao.findAll());

        //update
        System.out.println("---[TEST]--- update");
        ovChipkaart.setSaldo(50);
        ovdao.update(ovChipkaart);
        System.out.println(ovdao.findAll());

        //product toevoegen
        System.out.println("---[TEST]--- product toevoegen");
        List<Product> testprod = new ArrayList<>();
        testprod.add(product);
        ovChipkaart.setProducten(testprod);
        ovdao.update(ovChipkaart);
        System.out.println(ovdao.findbyReiziger(reizigertest));

        ///delete alles

        System.out.println("--------------[TEST DELETE]----------------");

        /// ovchipkaart
        System.out.println("---[TEST]--- ovchip");
        ovdao.delete(ovChipkaart);
        System.out.println(ovdao.findAll());

        //product
        System.out.println("---[TEST]--- product");
        pdao.delete(product);
        System.out.println(pdao.findAll());

        //reiziger
        System.out.println("---[TEST]--- reiziger");
        rdao.delete(reizigertest);
        System.out.println(rdao.findByID(reizigertest.getReiziger_Id()));
    }
}
