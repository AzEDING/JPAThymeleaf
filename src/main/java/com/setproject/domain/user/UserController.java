package com.setproject.domain.user;

import com.setproject.domain.user.entity.dto.LoginReqDto;
import com.setproject.domain.user.entity.dto.UserListResDto;
import com.setproject.domain.user.entity.dto.UserReqDto;
import com.setproject.domain.user.entity.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1")
public class UserController {
    private final UserService userService;

    @GetMapping(value = "")
    public ModelAndView getLogin() {
        LoginReqDto loginReqDto = new LoginReqDto();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("idPw", loginReqDto);
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping(value = "/signup")
    public ModelAndView getSignup() {
        UserReqDto userReqDto = new UserReqDto();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("signupData", userReqDto);
        modelAndView.setViewName("signup");
        return modelAndView;
    }

//    @GetMapping(value = "/logout")
//    public void logout(HttpServletRequest request, HttpServletResponse response) {
//        try {
////            request.getSession().removeAttribute("JSESSIONID");
//            Cookie[] cookies = request.getCookies();
//            if (cookies != null) {
//                for (int i = 0; i < cookies.length; i++) {
//                    cookies[i].setMaxAge(0);
//                    response.addCookie(cookies[i]);
//                }
//                response.sendRedirect("");
//            }
//        }catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//}

    @PostMapping(value = "/signup")
    public void signup(@ModelAttribute("signupData") UserReqDto userReqDto, HttpServletResponse response) {
        userService.postUser(userReqDto, response);
    }
    
    @PostMapping(value = "/login")
    public void login(@ModelAttribute("idPw") LoginReqDto loginReqDto, HttpServletRequest request, HttpServletResponse response) throws Exception {
        userService.login(loginReqDto, request, response);
    }

    @GetMapping(value = "/admin/user")
    public UserListResDto getUserList(@PageableDefault(sort = "user_id", direction = Sort.Direction.DESC) Pageable pageable) {
        return userService.getUserList(pageable);
    }

    @PutMapping(value = "/user/{id}")
    public Long putUser(@PathVariable Long id, UserUpdateDto dto) {
        return userService.updateUser(id, dto);
    }

    @DeleteMapping(value = "/user/{id}")
    public Long delUser(@PathVariable Long id) {
        return userService.delUser(id);
    }

}
