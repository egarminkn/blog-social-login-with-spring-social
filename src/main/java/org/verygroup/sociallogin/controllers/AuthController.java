package org.verygroup.sociallogin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @PostMapping("/auth")
    public String auth(@RequestParam("providerId") String providerId,
                       @RequestParam("redirect_uri") String redirect_uri,
                       @RequestParam(value = "scope", required = false) String scope,
                       Model model) {

        model.addAttribute("redirect_uri", redirect_uri);

        return "forward:connect/" + providerId + "?redirect_uri=" + redirect_uri;
    }

}