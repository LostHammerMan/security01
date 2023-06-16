package com.demo.security01.controller.admin;

import com.demo.security01.entity.User;
import com.demo.security01.model.Role;
import com.demo.security01.model.dto.admin.AdminUpdateDto;
import com.demo.security01.model.dto.paging.Criteria;
import com.demo.security01.model.dto.paging.Paging;
import com.demo.security01.repository.user.UserRepositoryCustomImpl;
import com.demo.security01.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
@Controller
public class AdminController {

    private final AdminService adminService;
    private final UserRepositoryCustomImpl userRepository;
//    private final PagingConversionService conversionService;

   /* @InitBinder
    public void init(WebDataBinder binder){
        String objectName = binder.getObjectName();

        if (objectName.equals("criteria")){
            binder.registerCustomEditor(String.class, new PropertyEditorSupport(){

                @Override
                public void setAsText(String text) throws IllegalArgumentException {
                    conversionService.doConvert(text);
                    log.info("convertedValue = {}", text);
                    setValue(text);
                }
            });
        }
    }*/




//    private final AuthenticationManager authenticationManager;
//    private final UserRepository userRepository2;

//    private DispatcherServlet servlet;

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    /*@InitBinder
    public void init(WebDataBinder binder){
        binder.setConversionService(conversionService);
    }*/
    /*@InitBinder
    public void init(WebDataBinder binder){
        log.info("======= initBinder Called......============");
        String objectName = binder.getObjectName();
        log.info("conversionService = {}", conversionService);

        if (objectName.equals("criteria")){
            Criteria cri = new Criteria();
            conversionService.convert(cri.getPerPageNum(), Criteria.class);
        }


        if (objectName.equals("criteria")){
            if (binder.getConversionService() == null){
                binder.setConversionService(conversionService);
                log.info("converter called...." + binder.getConversionService().toString());

            }
        }
    }*/


    @GetMapping("/main")
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
//    public String allUser(Criteria cri, Model model) {
    public String allUser(@ModelAttribute Criteria cri, Model model) {
        log.info("========== userList called... ===========");
//        pagingConversionService.doConvert(String.valueOf(cri.getPerPageNum()));
       /* if (result.hasErrors()){
            cri.setPerPageNum(10);
        }*/
        log.info("cri ={}", cri);

        Paging paging = adminService.pagination(cri);
        List<User> findAllUser = adminService.allUser(cri);

        model.addAttribute("findAllUser", findAllUser);
        model.addAttribute("paging", paging);
        log.info("============ paging ================");

        return "admin/userList";
    }

    // 회원 수정 페이지
    @GetMapping("/user/{id}")
    public String findUser(@PathVariable int id, User user, int page, Model model, HttpServletResponse response) throws IOException {
//    public String findUser(@PathVariable int id, Role role, User user, Criteria cri, Model model, HttpServletResponse response) throws IOException {
//        User findUser = userRepository.findUser(user.getId());
        User findUser = adminService.userDetails(user);
        log.info("findUser={}", findUser);
        model.addAttribute("findUser", findUser);
        model.addAttribute("page", page);
        return "admin/updateForm";


    }

    // <div class>

    @GetMapping("/findAllUser")
    public @ResponseBody List<User> findAll(){
        return adminService.allUser(new Criteria());
//        return userRepository.findAllUser(new Criteria());

    }

    // 회원수정
    @PutMapping("/user/{id}")
    public String updateUser(@PathVariable int id, User user, AdminUpdateDto dto, @RequestParam("page") int page,@RequestParam("updateRole") String updateRole, Model model){
        log.info("=======updateUser called..=======");
        dto.setId(user.getId());
        dto.setRole(Enum.valueOf(Role.class, updateRole));
        log.info("dto ={}", dto);

        userRepository.updateUser(dto);

        // 시큐리티 세션 등록
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("user = {}", user);
//        model.addAttribute("page", cri.getPage());

        return "redirect:/admin/userList?page=" + page;
    }

    // 회원 삭제
    @DeleteMapping("/{id}")
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

    // 회원 삭제 후 리스트 다시 불러 오기
    @GetMapping("/getUserList")
    @ResponseBody
    public ResponseEntity<Object> getUserList(Criteria cri) {
        log.info("getUserList called...");
        int userListCnt = userRepository.userListCnt(cri);
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
