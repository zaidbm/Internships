package com.gestion.app.services;

import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestion.app.dao.UserRepository;
import com.gestion.app.entities.User;
import java.security.SecureRandom;

@Service
public class UserService {

    
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
            this.userRepository=userRepository;
}

    private final SecureRandom secureRandom = new SecureRandom();

    
    //@Autowired
    //private BCryptPasswordEncoder PasswordEncoder;

    public void registerUser(User user) {
        // Hash the user's password before storing it
        /*String hashedPassword = PasswordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        */
        // Set user roles as needed
        
        userRepository.save(user);
    }

    public User updateUserInformation(String username, User updatedUser) {
    	User existingUser = userRepository.findByUsername(username);
    	if (existingUser != null) {
    		existingUser.setUsername(updatedUser.getUsername());       
    		return userRepository.save(existingUser);
    	}
    	return null; 
		}
    
    public boolean changeUserPassword(String username, String newPassword) {
        User existingUser = userRepository.findByUsername(username);        
        if (existingUser != null) {
            /*existingUser.setPassword(PasswordEncoder.encode(newPassword));
            userRepository.save(existingUser);*/
            
            return true;
        }
        
        return false; // User not found
    }
    public boolean deleteUserAccount(String username) {
        User existingUser = userRepository.findByUsername(username);
        
        if (existingUser != null) {
            userRepository.delete(existingUser);
            return true;
        }
        
        return false; // User not found
    }
    



    
    public boolean lockUserAccount(String username) {
        User existingUser = userRepository.findByUsername(username);
        
        /*if (existingUser != null) {
            existingUser.setActive(false);*/
            userRepository.save(existingUser);
            return true;
        //}
        
       // return false; // User not found
    }














    /*public boolean unlockUserAccount(String username) {
        User existingUser = userRepository.findByUsername(username);
        
        if (existingUser != null) {
            existingUser.setActive(true);
            userRepository.save(existingUser);
            return true;
        }
        
        return false; // User not found
    }
    
    private String generateResetToken() {
        byte[] randomBytes = new byte[32]; // You can adjust the token length as needed
        secureRandom.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }*/
    /*public void sendPasswordResetEmail(String email) {
        User user = userRepository.findByEmail(email);
        
        if (user != null) {
            // Generate a token and send a password reset email
            String resetToken = generateResetToken();
            user.setResetToken(resetToken);
            userRepository.save(user);
            
            // Send the password reset email with the token
            emailService.sendPasswordResetEmail(user.getEmail(), resetToken);
        }
    }

    public boolean resetPassword(String token, String newPassword) {
        User user = userRepository.findByResetToken(token);
        
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setResetToken(null); // Clear the reset token
            userRepository.save(user);
            return true;
        }
        
        return false; // Token invalid or expired
    }*/
    
    /*public List<User> getAllUsers() {
        return userRepository.findAll();
    }*/
    
    
    /*
     * Remember to adapt these examples to your actual code structure and security requirements. 
     * Also, implement proper token generation and expiration handling for scenarios involving tokens
     */
}
