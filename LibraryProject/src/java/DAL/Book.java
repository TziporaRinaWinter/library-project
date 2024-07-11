/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import javax.persistence.*;

/**
 *
 * @author ווינטר צפורה רינה
 */
@Entity
@DiscriminatorValue("book")
public class Book extends Item{
    
    private String author;
    private Category category;

    public Book() {
    }

    public Book(int id, String name,String author, Category category) {
        super(id, name);
        this.author = author;
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return super.toString()+"Book{" + "author=" + author + ", category=" + category + '}';
    }
    
}
