package org.launchcode.models.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import org.launchcode.models.data.Cheese;


@Entity
public class Category {
            @Id
            @GeneratedValue
            private int id;

            @OneToMany
            @JoinColumn(name="category_id")
            private List<Cheese> cheeses = new ArrayList<>();

            @NotNull
            @Size(min=3,max=15)
            private String name;

            public void Category(){

            }

            public void Category(String name){
                this.name=name;
            }

            public int getId(){return id;}

            public String getName(){return this.name;}

            public void setName(String name){this.name = name;}

        }