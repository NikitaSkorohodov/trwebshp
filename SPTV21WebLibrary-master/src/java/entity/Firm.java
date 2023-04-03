/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Firm implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firmname;
    
    private int birthYear;
    @OneToMany
    private List<Shuse> shuse;
    
    /**
     *
     */
    public Firm() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getFirmname() {
        return firmname;
    }

    public void setFirmname(String firstname) {
        this.firmname = firstname;
    }

    @Override
    public String toString() {
        return "Firm{" + "id=" + id + ", firmname=" + firmname + ", birthYear=" + birthYear + ", books=" + shuse + '}';
    }

    

    public List<Shuse> getShuses() {
        return shuse;
    }

    public void setBooks(List<Shuse> shuse) {
        this.shuse = shuse;
    }


    

}
