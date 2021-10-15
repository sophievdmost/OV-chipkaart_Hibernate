package DAO;

import domein.OVChipkaart;
import domein.Product;
import interfaces.ProductDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ProductDAOHibernate implements ProductDAO {
    private Session session;

    public ProductDAOHibernate(Session session){
        this.session = session;
    }

    @Override
    public boolean save(Product product) {
        try{
            Transaction trans = session.beginTransaction();
            session.save(product);
            trans.commit();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Product product) {
        try {
            Transaction trans = session.beginTransaction();
            session.update(product);
            trans.commit();
            return true;

        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Product product) {
        try{
            Transaction trans = session.beginTransaction();
            session.delete(product);
            trans.commit();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Product> findByOvchipkaart(OVChipkaart ovChipkaart) {
        try{
            Transaction trans = session.beginTransaction();
            List<Product> alleProducten = session.createQuery("FROM Product", Product.class).getResultList();
            List<Product> ovProducten = new ArrayList<>();
            for(Product product : alleProducten){
//                System.out.println(product);
//                System.out.println(alleProducten);
                if(ovChipkaart.getProducten().contains(product)){
                    ovProducten.add(product);
                }
            }
            trans.commit();
            return ovProducten;
        }catch (Exception e){
            e.printStackTrace();
            return null;
    }
    }

    @Override
    public List<Product> findAll() {
        try{
            Transaction trans = session.beginTransaction();
            List<Product> producten = session.createQuery("FROM Product ", Product.class).getResultList();
            trans.commit();
            return producten;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
