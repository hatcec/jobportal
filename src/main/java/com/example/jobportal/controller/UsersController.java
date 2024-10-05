package com.example.jobportal.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.jobportal.entities.Users;
import com.example.jobportal.entities.UsersType;
import com.example.jobportal.repository.UsersTypeRepository;
import com.example.jobportal.services.UsersService;
import com.example.jobportal.services.UsersTypeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.catalina.LifecycleState;
import org.apache.catalina.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {
    private  final UsersTypeService usersTypeService;
    private final UsersService usersService;

    public UsersController(UsersTypeService usersTypeService, UsersService usersService) {
        this.usersTypeService = usersTypeService;
        this.usersService=usersService;
    }
    @GetMapping ("register")
    public  String register(Model model){
        List<UsersType > usersTypes=usersTypeService.getAll();
        model.addAttribute("getAllTypes", usersTypes);
        model.addAttribute("user" , new Users());
        return "register";
    }
    @PostMapping("register/new")
    public  String userRegistiration(@Valid Users users, Model model){
        Optional<Users> optionalUsers= usersService.getUserByEmail(users.getEmail());
        if(optionalUsers.isPresent()){
            model.addAttribute("error", "Email already registered, try to login or register with other email");
            List<UsersType > usersTypes=usersTypeService.getAll();
            model.addAttribute("getAllTypes", usersTypes);
            model.addAttribute("user" , new Users());
            return "register";
        }
        usersService.addNew(users);
        return "dashboard";
    }
    @GetMapping("/login")
    public  String login(){
        return "login";
    }
    @GetMapping("/logout")
    public  String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }
}
