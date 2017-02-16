package org.verygroup.sociallogin.controllers;

import org.verygroup.sociallogin.controllers.util.SocialControllerUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    private SocialControllerUtil util;

    /**
     * Если вход через соц.сеть был отменен или прошел неудачно
     */
    @GetMapping("/signin")
    public String signin(HttpServletRequest request, Principal currentUser, Model model) {
        return "forward:/index";
    }

    @GetMapping("/")
    public String home(HttpServletRequest request, Principal currentUser, Model model) {
        return "forward:/index";
    }

    @GetMapping("/index")
    public String index(HttpServletRequest request, Principal currentUser, Model model) {
        util.setModel(request, currentUser, model);
        return "index";
    }

    @GetMapping("/author")
    public String author(HttpServletRequest request, Principal currentUser, Model model) {
        util.setModel(request, currentUser, model);
        return "author";
    }

    @GetMapping("/author-pa")
    public String authorPa(HttpServletRequest request, Principal currentUser, Model model) {
        util.setModel(request, currentUser, model);
        return "author-pa";
    }

}