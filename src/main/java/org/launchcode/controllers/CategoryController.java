package org.launchcode.controllers;

import org.launchcode.models.data.Category;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.Errors;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.launchcode.models.CategoryDao;


@Controller
@RequestMapping("category")
public class CategoryController {
            @Autowired
            private CategoryDao categoryDao;

            @RequestMapping(value="")
            public String index(Model model){
                model.addAttribute("title","Categories");
                model.addAttribute("categories",categoryDao.findAll());

                return "category/index";

        }

            @RequestMapping(value="add", method=RequestMethod.GET)
            public String displayAddCategoryForm(Model model){
                model.addAttribute("title","Add Category");
                model.addAttribute(new Category());
                return "category/add";
        }

            @RequestMapping(value="add", method=RequestMethod.POST)
            public String processAddCategoryForm(Model model, @ModelAttribute @Valid Category newCategory, Errors errors){
                if(errors.hasErrors()){
                    model.addAttribute("title", "Add Category");
                    return "category/add";
                }
                else{
                    categoryDao.save(newCategory);
                    return "redirect:";
                }
        }
}