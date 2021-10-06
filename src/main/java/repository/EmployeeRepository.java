package repository;

import models.Employees;
import utils.HibernateSessionFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Iterator;
import java.util.List;

public class EmployeeRepository {
    public Employees getByUsername(String username)    {
        Session session = null;
        Transaction tx = null;
        Employees pass = null;

        try {
            session = HibernateSessionFactory.getSession();
            tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employees> cq = cb.createQuery(Employees.class);
            Root<Employees> rootEntry = cq.from(Employees.class);
            CriteriaQuery<Employees> all = cq.select(rootEntry).where(cb.equal(rootEntry.get("employee_username"), username));

            TypedQuery<Employees> allQuery = session.createQuery(all);
            pass = allQuery.getSingleResult();
            tx.commit();
        }
        catch(HibernateException e) {
            tx.rollback();
            e.printStackTrace();
        }
        finally{
            session.close();
        }
        return pass;
    }
}
