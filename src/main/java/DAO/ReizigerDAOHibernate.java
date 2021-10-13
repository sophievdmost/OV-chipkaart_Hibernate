package DAO;

import domein.Reiziger;
import interfaces.ReizigerDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class ReizigerDAOHibernate implements ReizigerDAO {
    private Session session;

    public ReizigerDAOHibernate(Session session){
        this.session = session;
    }


    @Override
    public boolean save(Reiziger reiziger) throws SQLException {
        try{
            Transaction trans = session.beginTransaction();
            session.save(reiziger);
            trans.commit();
            return true;
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try{
            Transaction trans = session.beginTransaction();
            session.update(reiziger);
            trans.commit();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try{
            Transaction trans = session.beginTransaction();
            session.delete(reiziger);
            trans.commit();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Reiziger findByID(int id) {
        try{
            Transaction trans = session.beginTransaction();
            Reiziger reiziger = session.createQuery("FROM Reiziger where reiziger_id ="  + id, Reiziger.class).getSingleResult();
            trans.commit();
            return reiziger;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }



    @Override
    public List<Reiziger> findAll() {
        try{
            Transaction trans = session.beginTransaction();
            List<Reiziger> reizigers = session.createQuery("From Reiziger ", Reiziger.class).getResultList();
            trans.commit();
            return reizigers;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
