package org.launchcode.models.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToMany;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import org.launchcode.models.data.Cheese;

@Entity
public class Menu{

    @NotNull
    @Size(min=3, max=15)
    private String name;

    @Id
    @GeneratedValue
    private int id;

    @ManyToMany
    private List<Cheese> cheeses;

    public String getName(){return this.name; }
    public void setName(String name){this.name = name;}

    public int getId(){ return this.id;}

    public void addItem(Cheese item){
        cheeses.add(item);
    }

    public List<Cheese> getCheeses(){
        return cheeses;
    }

    public Menu(){}

    public Menu(String name){
        this.name = name;
    }
}