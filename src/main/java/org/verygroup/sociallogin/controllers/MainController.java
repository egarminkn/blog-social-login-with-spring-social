package org.verygroup.sociallogin.controllers;

import org.verygroup.sociallogin.controllers.util.SocialControllerUtil;
import org.verygroup.sociallogin.dao.DataDao;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by magnus on 18/08/14.
 */
@Controller
public class MainController {

    private static final Logger LOG = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private DataDao dataDao;

    @Autowired
    private SocialControllerUtil util;

    @RequestMapping("/")
    public String home(HttpServletRequest request, Principal currentUser, Model model) {
        util.setModel(request, currentUser, model);
        return "home";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Principal currentUser, Model model) {
        util.setModel(request, currentUser, model);
        return "login";
    }

    @PostMapping("/update")
    public String update(HttpServletRequest request,
                         Principal currentUser,
                         Model model,
                         @RequestParam String data) {
        LOG.debug("Update data to: {}", data);
        String userId = currentUser.getName();
        dataDao.setDate(userId, data);

        util.setModel(request, currentUser, model);
        return "home";
    }

    @PostMapping("/updateStatus")
    public String updateStatus(WebRequest webRequest,
                               HttpServletRequest request,
                               Principal currentUser,
                               Model model,
                               @RequestParam String status) {
        MultiValueMap<String, Connection<?>> cmap = connectionRepository.findAllConnections();
        LOG.error("cs.size = {}", cmap.size());
        Set<Map.Entry<String, List<Connection<?>>>> entries = cmap.entrySet();
        for (Map.Entry<String, List<Connection<?>>> entry : entries) {
            for (Connection<?> c : entry.getValue()) {
                LOG.debug("Updates {} with the status '{}'", entry.getKey(), status);
                c.updateStatus(status);
            }
        }
        return "home";
    }

    // ----------------------------------------
    // Добавил:
    // ----------------------------------------

//    @GetMapping("/")
//    public String home(HttpServletRequest request, Principal currentUser, HttpSession session, Model model) {
//        return "forward:/login-1";
//    }

    @GetMapping("/login-1")
    public String login1(HttpServletRequest request, Principal currentUser, HttpSession session, Model model) {
        util.setModel(request, currentUser, model);
        return "login-1";
    }

    @GetMapping("/login-2")
    public String login2(HttpServletRequest request, Principal currentUser, HttpSession session, Model model) {
        util.setModel(request, currentUser, model);
        return "login-2";
    }

}