package org.launchcode.controllers;

import org.launchcode.models.data.Cheese;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.Menu;
import org.launchcode.models.data.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;

import org.launchcode.models.forms.*;

@Controller
@RequestMapping(value="menu")
public class MenuController{

    @Autowired
    private CheeseDao cheeseDao;


    @Autowired
    private MenuDao menuDao;

    @RequestMapping(value="")
    public String index(Model model){
        model.addAttribute("title","Menus");
        model.addAttribute("menus",menuDao.findAll());
        return "menu/index";
    }

    @RequestMapping(value="add", method=RequestMethod.GET)
    public String displayAddMenuForm(Model model){
        model.addAttribute("title", "Add Menu");
        model.addAttribute(new Menu());
        return "menu/add";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    public String processAddMenuForm(Model model, @ModelAttribute @Valid Menu menu, Errors errors){
        if(errors.hasErrors()){
            model.addAttribute("title", "Add Menu");
            return "menu/add";
        }
        else{
            menuDao.save(menu);
            return "redirect:view/"+ menu.getId();
        }

    }

    @RequestMapping(value="/view/{menuId}", method=RequestMethod.GET)
    public String viewMenu(@PathVariable int menuId, Model model){
        Menu menu = menuDao.findOne(menuId);

        model.addAttribute("title",menu.getName());
        model.addAttribute("menu", menu);
        //model.addAttribute("menuId",menu.getId());
        return "menu/view";
    }

    @RequestMapping(value="/add-item/{menuId}", method= RequestMethod.GET)
    public String addMenuItem(Model model,@PathVariable int menuId){
        Menu menu = menuDao.findOne(menuId);
        AddMenuItemForm form = new AddMenuItemForm(menu,cheeseDao.findAll());
        model.addAttribute("form",form);
        model.addAttribute("title", "add item to menu: "+ menu.getName());


        return "menu/add-item";
    }

    @RequestMapping(value="/add-item/{menuId}",method = RequestMethod.POST)
    public String processAddMenuItem(Model model, @ModelAttribute @Valid AddMenuItemForm form,@PathVariable int menuId, Errors errors){
       Menu menu = menuDao.findOne(menuId);
       if(errors.hasErrors()){
           model.addAttribute("form", form);
           return "menu/add-item";
       }
       Cheese cheese = cheeseDao.findOne(form.getCheeseId());
       menu.addItem(cheese);
       menuDao.save(menu);
       return "redirect:/menu/view/" + menu.getId();
    }
}