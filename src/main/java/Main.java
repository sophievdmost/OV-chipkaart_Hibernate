

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
//        testFetchAll();
//        setTestData();
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


    private static void testDAOHibernate() throws SQLException {
        AdresDAOHibernate adao = new AdresDAOHibernate(getSession());
        OVChipkaartDAOHibernate ovdao = new OVChipkaartDAOHibernate(getSession());
        ProductDAOHibernate pdao = new ProductDAOHibernate(getSession());
        ReizigerDAOHibernate rdao = new ReizigerDAOHibernate(getSession());


        Reiziger reizigertest = new Reiziger(69, "S", "van der", "Most", java.sql.Date.valueOf("2002-11-06"));
        Adres adres = new Adres(10, "1", "3971VP", 69, "van heemstrastraat", "driebergen");
        OVChipkaart ovChipkaart = new OVChipkaart(69699, java.sql.Date.valueOf("2030-11-19"), 2, 1000, 69);
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
        System.out.println("Na de update "+ rdao.findAll()+ "\n");

        ///adres
        System.out.println("--------------[TEST ADRES]----------------");

        //save en findall
        System.out.println("---[TEST]--- save + findall");
        System.out.println("test findall1 " + adao.findAll());
//        adres.setReiziger(null);
        adao.save(adres);
        System.out.println("test findall2" + adao.findAll());

        //update + findbyreiziger
        System.out.println("---[TEST]--- update + findByReiziger");
        System.out.println(rdao.findAll());
        adres.setHuisnummer("2");
        adao.update(adres);
        System.out.println(adao.findByReiziger(reizigertest));

        //delete
        System.out.println("---[TEST]--- delete");
        System.out.println(adao.findAll());
        adao.delete(adres);
        System.out.println(adao.findAll()+ "\n");

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
        System.out.println(pdao.findAll() + "\n");

        //findByOvchimp
        ///insert test findbyov
        System.out.println("---[TEST]--- find by ov chipkaart");
        OVChipkaart ovChipkaart1 = new OVChipkaart(66666,java.sql.Date.valueOf("2030-11-19"), 1, 70, 69 );
        List<Product> testprod1 = new ArrayList<>();
        testprod1.add(product);
        ovChipkaart1.setProducten(testprod1);
        ovdao.save(ovChipkaart1);
        System.out.println(pdao.findByOvchipkaart(ovChipkaart1));
        ovdao.delete(ovChipkaart1);


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
        System.out.println(ovdao.findbyReiziger(reizigertest) + "\n");

        ///delete alles

        System.out.println("--------------[TEST DELETE]----------------");

        /// ovchipkaart
        System.out.println("---[TEST]--- ovchip");
        System.out.println(ovdao.findAll());
        ovdao.delete(ovChipkaart);



        System.out.println(ovdao.findAll());

        //product
        System.out.println("---[TEST]--- product");
        System.out.println(pdao.findAll());
        pdao.delete(product);
        System.out.println(pdao.findAll());

        //reiziger
        System.out.println("---[TEST]--- reiziger");
        System.out.println(rdao.findAll());
        rdao.delete(reizigertest);
        System.out.println(rdao.findAll());
  }
}
