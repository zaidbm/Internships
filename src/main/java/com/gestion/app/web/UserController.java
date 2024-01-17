package com.gestion.app.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gestion.app.dao.InternRepository;
import com.gestion.app.dao.InternshipRepository;
import com.gestion.app.dao.InternshipTypeRepository;
import com.gestion.app.dao.RecruiterRepository;
import com.gestion.app.dao.UserRepository;
import com.gestion.app.entities.User;
import com.gestion.app.services.InternService;
import com.gestion.app.services.InternshipService;
import com.gestion.app.services.RecruiterService;
import com.gestion.app.services.UserService;
import com.internship.app.dto.InternshipCountPerRecruiter;
import com.internship.app.dto.InternshipsCountByType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class UserController {
    private UserService userService;
    private InternshipService internshipService;
    private InternService internService;
    private RecruiterService recruiterService;


    @Autowired
    public UserController(UserRepository userRepository,InternshipRepository internshipRepository
    		,RecruiterRepository recruiterRepository,InternshipTypeRepository internshipTypeRepository
    		,InternRepository internRepository){
        this.userService=new UserService(userRepository);
        this.internshipService=new InternshipService(internshipRepository, recruiterRepository, internshipTypeRepository);
        this.internService=new InternService(internRepository);
        this.recruiterService=new RecruiterService(recruiterRepository);
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user) {
        // Validate and register the user
        userService.registerUser(user);
        return "redirect:/login";
    }
	
	@RequestMapping(value="/login")
    public String Login() {
        return "login"; // Return the login page template1
    }
	@RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Invalidate the session
        }
        return "redirect:/login?logout"; // Redirect to login page with logout message
    }
    @RequestMapping("Admin/Dashboard")
    public String AdminDashboard(Model model) {
        //Internships Count
        Long internshipsCount= internshipService.Count();
        model.addAttribute("internshipCount", internshipsCount);
        //Internship Count By Recruiters
        List<InternshipCountPerRecruiter> internshipCountPerRecruiters=internshipService.internshipCountPerRecruiter();
        model.addAttribute("intershipCountPerRecruiter", internshipCountPerRecruiters);
        //Internship Count By InternshipType
        List<InternshipsCountByType> internshipsCountByType=internshipService.internshipsCountByType();
        model.addAttribute("internshipCountByType", internshipsCountByType);
        //Interns Count
        Long internCount=internService.InternsCount();
        model.addAttribute("internCount", internCount);
        //Recruiter Count
        Long recruiterCount=recruiterService.recruiterCount();
        model.addAttribute("recruiterCount", recruiterCount);
        return "";
    }
    
    
}
