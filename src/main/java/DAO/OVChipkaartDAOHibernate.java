package DAO;

import domein.OVChipkaart;
import domein.Reiziger;
import interfaces.OVChipkaartDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class OVChipkaartDAOHibernate implements OVChipkaartDAO {
    private Session session;

    public OVChipkaartDAOHibernate(Session session){
        this.session = session;
    }


    @Override
    public boolean save(OVChipkaart ov) throws SQLException {
        try{
            Transaction trans = session.beginTransaction();
            session.save(ov);
            trans.commit();
            return true;

        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(OVChipkaart ovChipkaart) {
        try {
            Transaction trans = session.beginTransaction();
            session.save(ovChipkaart);
            trans.commit();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(OVChipkaart ovChipkaart) {
        try{
            Transaction trans = session.beginTransaction();
            session.delete(ovChipkaart);
            trans.commit();
            return true;


        }catch(Exception e){
            e.printStackTrace();
        return false;}
    }

    @Override
    public OVChipkaart findByID(int kaart_nummer) {
        try{
            Transaction trans = session.beginTransaction();
            OVChipkaart chip = session.createQuery("FROM OVChipkaart WHERE kaart_nummer = " + kaart_nummer, OVChipkaart.class).getSingleResult();
            trans.commit();
            return chip;

        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<OVChipkaart> findAll() {
        try{
            Transaction trans = session.beginTransaction();
            List ovChipkaarten = session.createQuery(" FROM OVChipkaart ").getResultList();
            trans.commit();
            return ovChipkaarten;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<OVChipkaart> findbyReiziger(Reiziger reiziger) {
        try{
            Transaction trans = session.beginTransaction();
            List<OVChipkaart> ovchips = session.createQuery("FROM OVChipkaart WHERE reiziger_id =" + reiziger.getReiziger_Id(), OVChipkaart.class).getResultList();
            trans.commit();
            return ovchips;

        }
        catch (Exception e){
            e.printStackTrace();
            return null;
    }}
}
