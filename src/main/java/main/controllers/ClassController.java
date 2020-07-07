package main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import main.model.Employee;
import main.services.DataService;

@Controller
public class ClassController implements ErrorController {
	
	/** Service Element*/
	@Autowired
	private DataService dataService;
	
	/** Denied capture */
	@RequestMapping(value = "/denied")
    public String denied() {
        return "errors/denied";
    }
	
	/** Error capture */
	@RequestMapping(value = "/error")
    public String error() {
        return "errors/error";
    }
	
	/** template Welcome */
	@RequestMapping(value = "/")
    public String welcome() {
        return "welcome";
    }

	/** template Login */
    @RequestMapping(value = "/login")
    public String login(SecurityContextHolderAwareRequestWrapper request) {
    	if(request.isUserInRole("ROLE_USER") || request.isUserInRole("ROLE_ADMIN")) {
    		return "home";
    	}
        return "login";
    }
	
    /** template Home */
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value = "/home")
	public String home() {			
		return "home";
	}
	
    /** template Add Employee */
    @Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/addEmployee")
	public String addEmployee() {			
		return "employees/addEmployee";
	}
	
    /** form add Employee */
    @Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/add")
	public String add(Employee employee) {
		this.dataService.add(employee);
		return "home";
	}
	
    /** template List */
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
	@RequestMapping(value = "/list")
	public String list(Model model) {
		model.addAttribute("employees", this.dataService.findAll());
		return "employees/list";
	}
	
    /** template Show */
	@RequestMapping(value = "/show/{numEmployee}")
	public String show(@PathVariable int numEmployee, Model model) {
		Employee employee = this.dataService.get(numEmployee);
		model.addAttribute("employee", employee);
		return "employees/show";
	}
	
	/** template delete */
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/delete/{numEmployee}")
	public String delete(@PathVariable int numEmployee, Model model) {
		this.dataService.remove(numEmployee);
		model.addAttribute("employees", this.dataService.findAll());
		return "employees/list";
	}
	
	/** template Edit Employee */
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/editEmployee/{numEmployee}")
	public String editEmployee (@PathVariable int numEmployee, Model model) {
		Employee employee = this.dataService.get(numEmployee);
		model.addAttribute("employee", employee);
		return "employees/editEmployee";
	}

	/** form Edit Employee */
	@Secured({"ROLE_ADMIN"})
	@RequestMapping(value = "/edit")
	public String edit(Employee employee, Model model) {
		this.dataService.add(employee);
		model.addAttribute("employees", this.dataService.findAll());
		return "employees/list";
	}

	/** override template Error */
	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}
}