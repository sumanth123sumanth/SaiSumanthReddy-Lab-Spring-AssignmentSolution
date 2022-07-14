package com.greatlearning.student.studentmanagement.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatlearning.student.studentmanagement.model.Student;
import com.greatlearning.student.studentmanagement.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	StudentService studentService;

	@RequestMapping("/list")
	public String getAllStudents(Model theModel) {
		List<Student> res = studentService.getAllStudents();
		theModel.addAttribute("studentModel", res);
		return "studentList";
	}

	@RequestMapping("/add")
	public String addStudent(Model theModel) {
		Student student = new Student();
		theModel.addAttribute("student", student);
		return "studentsave";
	}

	@RequestMapping("/update")
	public String updateStudent(@RequestParam("id") int id, Model theModel) {
		Student student = studentService.getStudentById(id);
		theModel.addAttribute("student", student);
		return "studentsave";
	}

	@RequestMapping("/delete")
	public String deleteStudent(@RequestParam("id") int id) {
		Student student = studentService.delete(id);
		System.out.println("Deleted Student Id" + student.getId());
		return "redirect:/students/list";
	}

	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id, @RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname, @RequestParam("course") String course,
			@RequestParam("country") String country) {
		Student student;
		if (id != 0) {
			student = studentService.getStudentById(id);
		} else {
			student = new Student();
		}
		student.setFirstName(firstname);
		student.setLastName(lastname);
		student.setCourse(course);
		student.setCountry(country);
		studentService.save(student);
		System.out.println(id + " " + firstname + " " + lastname + " " + course + " " + country);
		return "redirect:/students/list";
	}

	@RequestMapping("/403")
	public ModelAndView delete(Principal user) {
		ModelAndView model = new ModelAndView();
		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", you don't have permission to perform this action");
		} else {
			model.addObject("msg", "Hi, you don't have permission to perform this action");
		}
		model.setViewName("403");
		return model;
	}
}
