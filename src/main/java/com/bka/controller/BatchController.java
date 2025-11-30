package com.bka.controller;

import com.bka.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test-batch")
public class BatchController {
    private PersonDao personDao;

    @Autowired
    public BatchController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String batch() {
        return "/test-batch/batch";
    }

    @PostMapping("/with")
    public String with() {
        personDao.with();
        return "redirect:/people";
    }

    @PostMapping("/without")
    public String without() {
        personDao.without();
        return "redirect:/people";
    }
}
