package DAO;

import domein.Adres;
import domein.Reiziger;
import interfaces.AdresDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class AdresDAOHibernate implements AdresDAO {
    private Session session;

    public AdresDAOHibernate( Session session){
        this.session = session;
    }

    @Override
    public boolean save(Adres adres) {
        try{
            Transaction transaction =this.session.beginTransaction();
            session.save(adres);
            transaction.commit();
            return true;
    }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Adres adres) {
        try{
            Transaction trans = session.beginTransaction();
            session.update(adres);
            trans.commit();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Adres adres) {
        try {
            Transaction trans = session.beginTransaction();
            session.delete(adres);
            trans.commit();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        try {
            Transaction trans = session.beginTransaction();
            Adres adres = session.createQuery("FROM Adres WHERE reiziger_id =" + reiziger.getReiziger_Id(), Adres.class).getSingleResult();
            trans.commit();
            return adres;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Adres> findAll() {
        try{
            Transaction trans = session.beginTransaction();
            List<Adres> adres = session.createQuery("FROM Adres", Adres.class).getResultList();
            List<Adres> adreslijst = new ArrayList<>(adres);
            trans.commit();
            return adreslijst;

        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
