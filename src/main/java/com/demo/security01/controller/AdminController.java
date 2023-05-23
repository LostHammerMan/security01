package com.demo.security01.controller;

import com.demo.security01.entity.User;
import com.demo.security01.model.Role;
import com.demo.security01.model.dto.AdminUpdateDto;
import com.demo.security01.model.dto.paging.Criteria;
import com.demo.security01.model.dto.paging.Paging;
import com.demo.security01.repository.UserRepository;
import com.demo.security01.repository.UserRepositoryCustomImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AdminController {

    private final UserRepositoryCustomImpl userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository2;

    private DispatcherServlet servlet;

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    @Secured("ROLE_ADMIN")
    public String admin(){
        log.info("admin controller called......");
        return "admin/admin_main";
    }

    // 페이징 X 회원 목록
    /*@GetMapping("/users")
    public String allUser(Model model){
        List<User> findAllUser = userRepository.findAllUser();
        log.info("모든 유저 = {}", findAllUser);
        model.addAttribute("findAllUser", findAllUser);
        return "admin/users";
    }*/

    // 회원 목록 + 페이징
    @GetMapping("/userList")
    public String allUser(Criteria cri, Model model){

        // 전체 회원 수
        int userListCnt = userRepository.userListCnt();
        log.info("userListCnt = {}", userListCnt);

        // 페이징 객체
        Paging paging = new Paging();
        paging.setCri(cri);
        paging.setTotalCount(userListCnt);

        List<User> findAllUser = userRepository.findAllUser(cri);
        log.info("모든 유저 = {}", findAllUser);
        model.addAttribute("findAllUser", findAllUser);
        model.addAttribute("paging", paging);
        log.info("============ paging ================");
        log.info("paging = {}", paging);
        return "admin/userList";
    }

    @GetMapping("/user/{id}")
    public String findUser(@PathVariable int id, Role role, User user, Model model, HttpServletResponse response) throws IOException {
        User findUser = userRepository.findUser(user.getId());
        log.info("findUser={}", findUser);
//        model.addAttribute("user_role", role.);
        model.addAttribute("findUser", findUser);
        return "admin/updateForm";
    }

    // <div class>

    @GetMapping("/findAllUser")
    public @ResponseBody List<User> findAll(){
        return userRepository.findAllUser(new Criteria());

    }

    // 회원수정
    @PutMapping("/user/{id}")
    public String updateUser(@PathVariable int id, User user, AdminUpdateDto dto, @RequestParam("updateRole") String updateRole){
        log.info("=======updateUser called..=======");
        dto.setId(user.getId());
        dto.setRole(Enum.valueOf(Role.class, updateRole));
        log.info("dto ={}", dto);

        userRepository.updateUser(dto);

        // 시큐리티 세션 등록
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("user = {}", user);

        return "redirect:/userList";
    }

    // 회원 삭제
    @DeleteMapping("/user/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteUser(@PathVariable int id){
        log.info("deleteUser called....");
        log.info("id= {}", id);
//        id = dto.getId();
        userRepository.delete(id);
        //List<User> userList = userRepository.findAllUser();
//        if (deleteSuccess == 1) {
//        } else {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
        Map<String, String> map = new HashMap<>();
        map.put("message", "delete success");

        return ResponseEntity.ok(map);
    }

    @DeleteMapping("/user2/{id}")
    @ResponseBody
    public ResponseEntity<Object> deleteUser2(@PathVariable List<Integer> id){
        log.info("deleteUser2 called....");
        log.info("id= {}", id);
        userRepository.deleteAll(id);
//        id = dto.getId();
//        for (Integer ids : id){
//            userRepository.delete(ids);
//        }
        //List<User> userList = userRepository.findAllUser();
//        if (deleteSuccess == 1) {
//        } else {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
        Map<String, String> map = new HashMap<>();
        map.put("message", "delete success");

        return ResponseEntity.ok(map);
    }

    // ajax 회원삭제 후 다시 목록 불러오기
    /*@GetMapping("/user/getUserList")
    @ResponseBody
    public ResponseEntity<Object> getUserList() {
        log.info("getUserList called...");



        Map<String, Object> map = new HashMap<>();
        List<User> userList = userRepository.findAllUserList();
        map.put("userList",  userList);

        log.info("{}", userList);

        return ResponseEntity.ok(map);
    }*/

    @GetMapping("/user/getUserList")
    @ResponseBody
    public ResponseEntity<Object> getUserList(Criteria cri) {
        log.info("getUserList called...");
        int userListCnt = userRepository.userListCnt();
        log.info("userListCnt = {}", userListCnt);

        // 페이징 객체
        /*Paging paging = new Paging();
        paging.setCri(cri);
        paging.setTotalCount(userListCnt);*/
/*
        List<User> findAllUser = userRepository.findAllUser(cri);
        log.info("모든 유저 = {}", findAllUser);
        model.addAttribute("findAllUser", findAllUser);
        model.addAttribute("paging", paging);
        log.info("============ paging ================");
        log.info("paging = {}", paging);*/



        Map<String, Object> map = new HashMap<>();
        List<User> userList = userRepository.findAllUser(cri);

        map.put("userList",  userList);

        log.info("{}", userList);

        return ResponseEntity.ok(map);
    }

    /*@DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id){
        boardService.deleteBoard(id);
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }*/



}
