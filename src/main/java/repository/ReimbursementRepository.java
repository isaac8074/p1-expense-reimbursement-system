package repository;

import models.Reimbursement;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;

public class ReimbursementRepository {

    public List<Reimbursement> findAll()    {
        Session session = null;
        Transaction tx = null;
        List<Reimbursement> lists = null;

        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Reimbursement> cq = cb.createQuery(Reimbursement.class);
            Root<Reimbursement> rootEntry = cq.from(Reimbursement.class);
            CriteriaQuery<Reimbursement> all = cq.select(rootEntry);

            TypedQuery<Reimbursement> allQuery = session.createQuery(all);
            lists = allQuery.getResultList();
            tx.commit();
        }
        catch(HibernateException e){
            tx.rollback();
            e.printStackTrace();
        }
        finally{
            session.close();
        }
        return lists;
    }

    public List<Reimbursement> findName(String name)    {
        Session session = null;
        Transaction tx = null;
        List<Reimbursement> lists = null;

        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Reimbursement> cq = cb.createQuery(Reimbursement.class);
            Root<Reimbursement> rootEntry = cq.from(Reimbursement.class);
            CriteriaQuery<Reimbursement> all = cq.select(rootEntry).where(cb.equal(rootEntry.get("employee_name"), name));

            TypedQuery<Reimbursement> allQuery = session.createQuery(all);
            lists = allQuery.getResultList();
            tx.commit();
        }
        catch(HibernateException e){
            tx.rollback();
            e.printStackTrace();
        }
        finally{
            session.close();
        }
        return lists;
    }

    public void save(Reimbursement reimbursement)   {
        Session session = null;
        Transaction tx = null;

        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            session.save(reimbursement);
            tx.commit();
        }
        catch(HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        }
        finally{
            session.close();
        }
    }

    public void approve(int id)   {
        Session session = null;
        Transaction tx = null;
        Reimbursement notify = null;

        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Reimbursement> cq = cb.createQuery(Reimbursement.class);
            Root<Reimbursement> rootEntry = cq.from(Reimbursement.class);
            CriteriaQuery<Reimbursement> all = cq.select(rootEntry).where(cb.equal(rootEntry.get("reimbursement_id"), id));

            TypedQuery<Reimbursement> allQuery = session.createQuery(all);
            notify = allQuery.getSingleResult();

            notify.setReimbursement_status("Approved");

            session.saveOrUpdate(notify);
            tx.commit();
        }
        catch(HibernateException e){
            tx.rollback();
            e.printStackTrace();
        }
        finally{
            session.close();
        }
    }

    public void deny(int id)    {
        Session session = null;
        Transaction tx = null;
        Reimbursement notify = null;

        try{
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Reimbursement> cq = cb.createQuery(Reimbursement.class);
            Root<Reimbursement> rootEntry = cq.from(Reimbursement.class);
            CriteriaQuery<Reimbursement> all = cq.select(rootEntry).where(cb.equal(rootEntry.get("reimbursement_id"), id));

            TypedQuery<Reimbursement> allQuery = session.createQuery(all);
            notify = allQuery.getSingleResult();

            notify.setReimbursement_status("Denied");

            session.saveOrUpdate(notify);
            tx.commit();
        }
        catch(HibernateException e){
            tx.rollback();
            e.printStackTrace();
        }
        finally{
            session.close();
        }
    }
}
