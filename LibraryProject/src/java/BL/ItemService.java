/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import DAL.Book;
import DAL.Category;
import DAL.HibernateUtil;
import DAL.Item;
import DAL.Magazine;
import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
/**
 *
 * @author The user
 */
public class ItemService {
    private Transaction trx = null;
    private Session session = HibernateUtil.getSessionFactory().openSession();
    
    public void addBook(String name, String author,Category c){
        try {
            //write to SQL_DB
            Book b = new Book(1,name,author,c);
            trx = session.beginTransaction();
            session.save(b);
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            System.out.println("oooppppssssssssss");
        }
    }
    public void addMagazine(String name,Date d){
        try {
            //write to SQL_DB
            Magazine m = new Magazine(d,1,name);
            trx = session.beginTransaction();
            session.save(m);
            trx.commit();
        } catch (HibernateException e) {
            trx.rollback();
            System.out.println("oooppppssssssssss");
        }
    }
    public Item getById(int id){
        Item it = (Item) session.get(Item.class, id);
        System.out.println(it);
        return it;
    }
}
