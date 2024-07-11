/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author ווינטר צפורה רינה
 */
@Entity
@DiscriminatorValue("magazine")
public class Magazine extends Item{
    
    private Date date;

    public Magazine() {
        
    }

    public Magazine(Date date, int id, String name) {
        super(id, name);
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return super.toString()+"Magazine{" + "date=" + date + '}';
    }
    

   

    
    
}
