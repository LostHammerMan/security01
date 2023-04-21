package com.demo.security01.controller;

import com.demo.security01.entity.User;
import com.demo.security01.repository.UserRepositoryCustomImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AdminController {

    private final UserRepositoryCustomImpl userRepository;

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String admin(){
        log.info("admin controller called......");
        return "admin/admin";
    }

    @GetMapping("/users")
    public String allUser(Model model){
        List<User> findAllUser = userRepository.findAllUser();
        log.info("모든 유저 = {}", findAllUser);
        model.addAttribute("findAllUser", findAllUser);
        return "admin/users";
    }

    @GetMapping("/user/{id}")
    public String findUser(@PathVariable int id, Model model, User user){
        User findUser = userRepository.findUser(user.getId());
        log.info("findUser={}", findUser);
        model.addAttribute("findUser", findUser);
        return "admin/findUser";
    }

   /* @GetMapping("/findAllUser")
    public @ResponseBody List<User> findAll(){
        return userRepositoryCustom.findAllUser();

    }*/
}
