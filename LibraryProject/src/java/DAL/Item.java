/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author ווינטר צפורה רינה
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;
    protected String name;
    
    public Item() {
    }
    
    public Item(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name=" + name + '}';
    }
}
