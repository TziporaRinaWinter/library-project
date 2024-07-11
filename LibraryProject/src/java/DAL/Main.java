/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ווינטר צפורה רינה
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Transaction trx = null;
        try {
            //write to SQL_DB
//            Date d = new Date(2022,12,28);
            Customer c = new Customer("dina","8795");
//            Book b = new Book(1, "machane_hakaitz","yael", Category.ADULT);
//            Item m = new Magazine(d, 0, "Mishpacha");
            Session session = HibernateUtil.getSessionFactory().openSession();
            trx = session.beginTransaction();
            session.save(c);
//            session.save(b);
//            session.save(m);
            trx.commit();
            //read from SQL_DB
            Customer c1 = (Customer) session.get(Customer.class, 1);
            System.out.println(c1);
            
        } catch (HibernateException e) {
            trx.rollback();
            System.out.println("oooppppssssssssss");
        }
    }
}
