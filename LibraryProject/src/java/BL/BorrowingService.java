/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import DAL.Borrowing;
import DAL.Customer;
import DAL.HibernateUtil;
import DAL.Item;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author The user
 */
public class BorrowingService {
    private Transaction trx = null;
    //private Session session = HibernateUtil.getSessionFactory().openSession();
    public void addBorrowing(Customer c,int bookID){
        try {
            //write to SQL_DB
            Session session = HibernateUtil.getSessionFactory().openSession();
            Borrowing b = new Borrowing(c,bookID,new Date(),false);
            trx = session.beginTransaction();
            session.save(b);
            trx.commit();
            session.close();
        } catch (HibernateException e) {
            trx.rollback();
            System.out.println(e);
            System.out.println("oooppppssssssssss borrowing service - add");
        }
    }
    public void update(int customerID, int bookID){
        
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query q = session.createQuery("select b.id from Borrowing b where b.bookId=:bookID and b.customerId.id=:customerID and b.isReturn=false");
            q.setParameter("bookID", bookID);
            q.setParameter("customerID", customerID);
            List<Integer> l = q.list();
            int id = l.get(0);
            System.out.println(id);
            q = session.createQuery("update Borrowing set isReturn=:isreturn where id = :ID");
            q.setParameter("isreturn", true);
            q.setParameter("ID", id);
            int status = q.executeUpdate();
            System.out.println("the status code of return item (=update): "+status);
            trx = session.beginTransaction();
            trx.commit();
            session.close();
        } catch (HibernateException e) {
            trx.rollback();
            System.out.println("oooppppssssssssss borrowing service - update");
        }
    }
    public List<Borrowing> findCustomerBorrowing(int customerId){
        List<Borrowing> b=null;
        CustomerService cs=new CustomerService();
        ItemService is=new ItemService();
        Customer c=cs.getById(customerId);
        if(c==null){
            return null;
        }
        try{
            Session session=HibernateUtil.getSessionFactory().openSession();
           
            String hql = "from Borrowing b where b.customerId.id=:customerID";
            Query q=session.createQuery(hql);
            q.setParameter("customerID", c.getId());
            b=q.list();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return b;
    }
//    public Item getItem(int id){
//        Borrowing b=null;
//        Item bookI = null;
//        try{
//           Session session=HibernateUtil.getSessionFactory().openSession();
//           Query q= session.createQuery("FROM Borrowing WHERE id = :borrowID");
//           q.setParameter("borrowID", id);
//           b=(Borrowing)q.uniqueResult();
//           int book = b.getBookId();
//           ItemService i = new ItemService();
//           bookI = i.getById(book);
//           
//        }
//        catch(Exception e){
////            t.rollback();
//            e.printStackTrace();
//        }
//        return bookI;
//    }
}