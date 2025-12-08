package com.bka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.bka.entity.Person;
import com.bka.service.PeopleService;

import javax.validation.Valid;

/**
 * @author Neil Alishev
 */
@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people/list";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/id";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";

        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}



//package com.bka.controller;
//
//import com.bka.service.PersonService;
//import com.bka.dto.PersonDto;
//
//import javax.validation.Valid;
//
////import com.bka.util.PersonValidator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/people")
//public class PeopleController {
//    private final PersonService service;
////    private PersonValidator validator;
//
//    @Autowired
//    public PeopleController(PersonService service) {
//        System.out.println("Inside PeopleController");
//        this.service = service;
////        this.validator = validator;
//    }
//
//    @GetMapping
//    public String getPeople(Model model) {
//        List<PersonDto> l = service.getAllPeople();
//        model.addAttribute("people", l);
//        System.out.println(l);
//        return "people/list";
//    }
//
//    @GetMapping("/{id}")
//    public String getPerson(@PathVariable("id") Long id, Model model) {
//        PersonDto person = service.getPerson(id);
//        model.addAttribute("person", person);
//        return "people/id";
//    }
//
//    @GetMapping("/new")
//    public String getNewPerson(Model model) {
//        model.addAttribute("person", new PersonDto());
//        return  "people/new";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String getEditPerson(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("person", service.getPerson(id));
//         return "people/edit";
//    }
//
//    @PatchMapping("/{id}")
//    public String editPerson(@PathVariable("id") Long id, @ModelAttribute("person") @Valid PersonDto person,
//                             BindingResult result) {
////        validator.validate(person, result);
//        if (result.hasErrors()) {
//            return "people/edit";
//        }
//        service.updatePerson(id, person);
//        return "redirect:/people";
//    }
//
//    @PostMapping
//    public String addPerson(@ModelAttribute("person") @Valid PersonDto person,  BindingResult result) {
////        validator.validate(person, result);
//        if (result.hasErrors()){
//            return "people/new";
//        }
//        service.addPerson(person);
//        return "redirect:/people";
//    }
//
//
//    @DeleteMapping("/{id}")
//    public String deletePerson(@PathVariable("id") Long id) {
//        service.removePerson(id);
//        return  "redirect:/people";
//    }
//
//}
