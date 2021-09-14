package com.sanjay.app.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.sanjay.app.model.User;

public class UserRepo {

	private Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class);
	 private SessionFactory factory = con.buildSessionFactory();
	 private Session session = factory.openSession();
	 
	 public Integer addUser(User u) {
		 Transaction tx = session.beginTransaction();
		 try {
		 Integer res = (Integer) session.save(u);
		 tx.commit();
		 return res;}
		 catch(HibernateException e) {
			 e.printStackTrace();
			 tx.rollback();
			 return 0;
			 
		 }
		 finally {
			 session.close();
		 }
	 }
	 public List<User> getUsers(){
		 Transaction tx = session.beginTransaction();
		 try {
			 Query query = session.createQuery("from User");
			 List<User> users = query.list();
			 tx.commit();
			 return users;
			 
		 }
		 catch(HibernateException e) {
			 e.printStackTrace();
			 tx.rollback();
			 return null;
			 
		 }
		 finally {
			 session.close();
		 }
	 }
	 public User searchUser(String email,String password) {
		 Transaction tx = session.beginTransaction();
		 try {
			 Query query = session.createQuery("from User where email=?1 and password=?2");
			 query.setParameter(1, email);
			 query.setParameter(2, password);
			 List<User> users = query.list();
			 if(users.size()>0)
			 return users.get(0);
			 else
				 return null;
		 }
		 catch(HibernateException e) {
			 e.printStackTrace();
			 tx.rollback();
			 return null;
		 }
		 finally {
			 session.close();
		 }
	 }
public Integer deleteUser(int u_id) {
		 
		 Transaction tx = session.beginTransaction();
		 try {
			 Query query = session.createQuery("delete from User where u_id=?1");
			 query.setParameter(1, u_id);
			 int res = query.executeUpdate();
			 tx.commit();
			 return res;
			 
		 }
		 catch(HibernateException e) {
			 e.printStackTrace();
			 tx.rollback();
			 return  -5;
		 }
		 finally {
			 session.close();
		 }
	 }
public List<User> searchUserByName(String name) {
	Transaction tx = session.beginTransaction();
	try {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
	    Root<User> myroot = criteria.from(User.class);
	    Predicate likeQuery = builder.like(myroot.get("name"), "%"+name+"%");
	    criteria.select(myroot).where(likeQuery);
	    List<User> users = session.createQuery(criteria).list();
	    tx.commit();
	    return users;
	}
	catch(HibernateException e) {
		e.printStackTrace();
		tx.rollback();
		return null;
		
	}
}
}
