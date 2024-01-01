package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.model.Employee;
import com.spring.service.EmpService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private EmpService empService;

	@GetMapping("/")
	private String index(Model m) {
		List<Employee> list = empService.getAllEmp();
		m.addAttribute("emplist", list);
		return "index";
	}

	@GetMapping("/addEmp")
	private String addEmp() {
		return "add_emp";
	}

	@PostMapping("/createEmp")
	private String createEmp(@ModelAttribute Employee emp, HttpSession session) {
		// System.out.println(emp);
		Employee e = empService.createEmp(emp);
		if (e != null) {
			session.setAttribute("msg", "Employee Added Successfully");
		} else {
			session.setAttribute("msg", "Something went wrong on server!!");
		}
		return "redirect:addEmp";
	}

	@GetMapping("/editEmp/{id}")
	private String editEmp(@PathVariable int id, Model m) {
		Employee emp = empService.getEmpById(id);
		m.addAttribute("emp", emp);

		return "edit_emp";
	}

	@PostMapping("/updateEmp")
	public String updateEmp(@ModelAttribute Employee emp, HttpSession session) {
		Employee uemp = empService.createEmp(emp);
		if (uemp != null) {
			session.setAttribute("msg", "Employee Updated Successfully");
		} else {
			session.setAttribute("msg", "Something went wrong on server!!");

		}
		return "redirect:/";
	}

	@GetMapping("/deleteEmp/{id}")
	private String deleteEmp(@PathVariable int id, HttpSession session) {
		boolean f = empService.deleteEmp(id);
		if (f) {
			session.setAttribute("msg", "Employee Deleted Successfully");
		} else {
			session.setAttribute("msg", "Something went wrong on server!!");

		}
		return "redirect:/";
	}

}
