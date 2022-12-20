package com.setproject.domain.user;

import com.setproject.common.advice.exception.AuthAccessDeniedException;
import com.setproject.common.advice.exception.UserAlreadyExistException;
import com.setproject.common.advice.exception.UserNotFoundException;
import com.setproject.common.model.jenum.EGender;
import com.setproject.common.model.jenum.ERole;
import com.setproject.domain.user.entity.User;
import com.setproject.domain.user.entity.UserRepository;
import com.setproject.domain.user.entity.dto.LoginReqDto;
import com.setproject.domain.user.entity.dto.UserListResDto;
import com.setproject.domain.user.entity.dto.UserReqDto;
import com.setproject.domain.user.entity.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserService {
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

    @Transactional
    public void postUser(UserReqDto dto, HttpServletResponse response) {
        try {
            if (userRepository.findByEmailAndIsdel(dto.getEmail(), false).isPresent())
                throw new UserAlreadyExistException();
            User user = User.builder()
                    .email(dto.getEmail())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .name(dto.getName())
                    .cellphone(dto.getCellphone())
                    .gender(EGender.valueOf(dto.getGender()))
                    .isdel(false)
                    .build();
    //        Role role = Role.builder()
    //                .roleName("USER")
    //                .build();
            user.setRole(ERole.ROLE_USER);
            userRepository.save(user);
            response.sendRedirect("/v1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Transactional
    public void login(LoginReqDto dto, HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = userRepository.findByEmailAndIsdel(dto.getEmail(), false).orElseThrow(UserNotFoundException::new);
            if (!passwordEncoder.matches(dto.getPassword(), user.getPassword()))
                throw new AuthAccessDeniedException();
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(3600);
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("userEmail", user.getEmail());
            session.setAttribute("userName", user.getName());
            session.setAttribute("userRole", user.getRole());
            session.setAttribute("isLogin", true);
            response.sendRedirect("/v1/home");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().invalidate();
            response.sendRedirect("/v1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional(readOnly = true)
    public UserListResDto getUserList(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return new UserListResDto(users);
    }

    @Transactional
    public Long updateUser(Long id, UserUpdateDto dto) {
        User user = userRepository.findByUserIdAndIsdel(id, false).orElseThrow(UserNotFoundException::new);
        user.setUpdate(dto);
        return user.getUserId();
    }

    @Transactional
    public Long delUser(Long id) {
        User user = userRepository.findByUserIdAndIsdel(id, false).orElseThrow(UserNotFoundException::new);
        user.setDel();
        return user.getUserId();
    }

}
