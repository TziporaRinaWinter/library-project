/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author ווינטר צפורה רינה
 */
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String userName;
    private String password;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID")
    List<Borrowing> myborrowing;

    public Customer() {
    }

    public Customer(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    @Override
    public String toString(){
        return "Customer:  id: "+this.id+", user name: "+this.userName;
    }
    
}
