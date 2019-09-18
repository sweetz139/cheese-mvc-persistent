package org.launchcode.models.forms;

import org.launchcode.models.*;
import org.launchcode.models.data.Menu;
import org.launchcode.models.data.Cheese;

import javax.validation.constraints.NotNull;



public class AddMenuItemForm{
    private Menu menu;
    private Iterable<Cheese> cheeses;

    @NotNull
    private int menuId;

    @NotNull
    private int cheeseId;

    public int getCheeseId(){return this.cheeseId;}

    public void setCheeseId(int cheeseId){this.cheeseId = cheeseId;}

    public int getMenuId(){return this.menuId;}

    public void setMenuId(int menuId){this.menuId = menuId;}

    public Menu getMenu(){
        return this.menu;
    }

    void setMenu(Menu menu){this.menu = menu;}

    public Iterable<Cheese> getCheeses(){return this.cheeses;}

    public void setCheeses(Iterable<Cheese> cheeses){this.cheeses = cheeses;}

    public AddMenuItemForm(){

    }

    public AddMenuItemForm(Menu menu, Iterable<Cheese> cheeses){
        this.menu = menu;
        this.cheeses = cheeses;
    }
}