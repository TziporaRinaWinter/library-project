/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import DAL.Customer;
import DAL.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author The user
 */
public class CustomerService {
    private Transaction trx = null;
    //private Session session = HibernateUtil.getSessionFactory().openSession();
    public void addCustomer(String name,String password){
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            if(isCorrect(name, password)){
                System.out.println("ops exist user with this name-password");
                trx.rollback();
            }
            Customer c = new Customer(name,password);
            trx = session.beginTransaction();
            session.save(c);
            trx.commit();
            session.close();
        } catch (HibernateException e) {
            trx.rollback();
            System.out.println("oooppppssssssssss customer service");
        }
    }
    public Customer getById(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Customer c1 = (Customer) session.get(Customer.class, id);
        System.out.println(c1);
        session.close();
        return c1;
    }
    public Customer getByName(String name){
        Customer c=null;
        try{
           Session session=HibernateUtil.getSessionFactory().openSession();
           Query q= session.createQuery("from Customer c where c.userName= :name");
           q.setParameter("name", name);
           c=(Customer)q.uniqueResult();
        }
        catch(Exception e){
//            t.rollback();
            e.printStackTrace();
        }
        return c;
    }
    public boolean isCorrect(String name, String password){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query qry = session.createQuery("from Customer c where c.userName= :name");
        qry.setParameter("name", name);
        List<Customer> l = qry.list();
        session.close();
        for(Customer c : l){
            if(c.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}
