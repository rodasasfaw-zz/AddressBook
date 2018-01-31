package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    PeopleRepository peopleRepository;

   @RequestMapping("/")
    public String listpeople(Model model){
        model.addAttribute("peoples", peopleRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String personform(Model model){
        model.addAttribute("people", new People());
        return "personform";
    }
    @PostMapping("/process")
    public String processForm(@Valid People people, BindingResult result){
        if(result.hasErrors()){
            return "personform";
        }
        peopleRepository.save(people);

        return "redirect:/";
    }
    @RequestMapping("/detail/{id}")
    public String showJob(@PathVariable("id") long id, Model model){
        model.addAttribute("people", peopleRepository.findOne(id));
        return "show";
    }
    @RequestMapping("/delete/{id}")
    public String delJob(@PathVariable("id") long id){
        peopleRepository.delete(id);
        return "redirect:/";
    }
}
