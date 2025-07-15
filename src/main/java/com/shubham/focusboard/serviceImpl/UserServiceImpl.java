package com.shubham.focusboard.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.shubham.focusboard.enties.User;
import com.shubham.focusboard.exception.ReqProcessingException;
import com.shubham.focusboard.repository.UserRepository;
import com.shubham.focusboard.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) throws ReqProcessingException {
        try {
            logger.info("Registering user with loginId: {}", user.getLoginId());
            if (userRepository.findByLoginId(user.getLoginId()).isPresent()) {
                throw new ReqProcessingException("Login ID already exists");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            if (user.getIsActive() == null) {
                user.setIsActive("Y");
            }
            return userRepository.save(user);
        } catch (Exception e) {
            logger.error("Error in registerUser", e);
            throw new ReqProcessingException("Error in registerUser", e);
        }
    }

    @Override
    public User authenticateUser(String loginId, String password) throws ReqProcessingException {
        try {
            logger.info("Authenticating user with loginId: {}", loginId);
            User user = userRepository.findByLoginId(loginId)
                    .orElseThrow(() -> new ReqProcessingException("Invalid loginId or password"));

            if (!"true".equalsIgnoreCase(user.getIsActive())) {
                throw new ReqProcessingException("User is not active");
            }

            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new ReqProcessingException("Invalid loginId or password");
            }

            return user;

        } catch (Exception e) {
            logger.error("Error in authenticateUser", e);
            throw new ReqProcessingException("Error in authenticateUser", e);
        }
    }

    @Override
    public User findUserByLoginId(String loginId) throws ReqProcessingException {
        try {
            logger.info("Finding user by loginId: {}", loginId);
            return userRepository.findByLoginId(loginId)
                    .orElseThrow(() -> new ReqProcessingException("User not found with loginId: " + loginId));
        } catch (Exception e) {
            logger.error("Error in findUserByLoginId", e);
            throw new ReqProcessingException("Error in findUserByLoginId", e);
        }
    }

}
