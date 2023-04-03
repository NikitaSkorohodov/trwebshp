

package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Shuse implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic(fetch = FetchType.EAGER, optional = true)
    @Column()
    private String shuseName;
    @OneToMany
    private List<Firm> firms;
    private int prise;
    private int quantity;
    private Cover cover;

    public Shuse() {
        firms = new ArrayList<>();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getShuseName() {
        return shuseName;
    }

    public void setShuseName(String shuseName) {
        this.shuseName = shuseName;
    }

    public List<Firm> getFirm() {
        return firms;
    }

    public void setFirm(List<Firm> firms) {
        this.firms = firms;
    }
    
    public void addFirm(Firm firm){
        firms.add(firm);
    }
    
    public int prise() {
        return prise;
    }
    public void removeFirm(int numberOfFirm){
        firms.remove(numberOfFirm);
    }
    public void setPrise(int prise) {
        this.prise = prise;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Book{" 
                + "shuseName=" + shuseName 
                + ", firms=" + Arrays.toString(firms.toArray())
                + ", prise=" + prise 
                + ", quantity=" + quantity 
                + ", cover=" + cover.getUrl()
                + '}';
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    
    
}
