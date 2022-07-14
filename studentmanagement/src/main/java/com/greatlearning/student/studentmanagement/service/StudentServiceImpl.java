package com.greatlearning.student.studentmanagement.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.student.studentmanagement.model.Student;

@Service
public class StudentServiceImpl implements StudentService {

	SessionFactory sessionFactory;
	Session session;

	@Autowired
	public StudentServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			this.session = sessionFactory.getCurrentSession();
		} catch (HibernateException he) {
			this.session = sessionFactory.openSession();
		}
	}

	@Override
	@Transactional
	public List<Student> getAllStudents() {
		@SuppressWarnings("unchecked")
		List<Student> list = (List<Student>) session.createQuery("from Student").list();
		return list;
	}

	@Override
	public void save(Student student) {
		Transaction trx = session.beginTransaction();
		session.saveOrUpdate(student);
		trx.commit();
	}

	@Override
	@Transactional
	public Student delete(int id) {
		Transaction trx = session.beginTransaction();
		Student student = session.get(Student.class, id);
		session.delete(student);
		trx.commit();
		return student;
	}

	@Override
	public Student getStudentById(int id) {
		return session.get(Student.class, id);
	}

}
