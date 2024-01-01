package com.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.spring.model.Employee;
import com.spring.repository.EmpRepo;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class EmpService {

	@Autowired
	private EmpRepo empRepo;

	public Employee createEmp(Employee emp) {
		Employee newemp = empRepo.save(emp);
		return newemp;
	}

	public void removeMsg() {

		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();
		session.removeAttribute("msg");
	}

	public List<Employee> getAllEmp() {
		List<Employee> list = empRepo.findAll();
		return list;

	}

	public Employee getEmpById(int id) {
		Employee emp = empRepo.findById(id).get();
		return emp;

	}

	public boolean deleteEmp(int id) {
		Employee emp = empRepo.findById(id).get();
		if (emp != null) {
			empRepo.deleteById(id);
			return true;
		}

		return false;
	}

}
