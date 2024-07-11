/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author The user
 */
@Entity
public class Borrowing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "id")
    private Customer customerId;
    private int bookId;
    private Date date;
    private boolean isReturn;

    public Borrowing() {
    }

    public Borrowing(Customer customerId, int bookId, Date date, boolean isReturn) {
        this.customerId = customerId;
        this.bookId = bookId;
        this.date = date;
        this.isReturn = isReturn;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isIsReturn() {
        return isReturn;
    }

    public void setIsReturn(boolean isReturn) {
        this.isReturn = isReturn;
    }

    @Override
    public String toString() {
        return "Lending{" + "id=" + id + ", customerId=" + customerId + ", bookId=" + bookId + ", date=" + date + ", isReturn=" + isReturn + '}';
    }
    
    
    
}