package com.sanjay.app.repository;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.sanjay.app.model.Comment;
import com.sanjay.app.model.Message;

public class CommentRepo {

	private Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Comment.class);
	 private SessionFactory factory = con.buildSessionFactory();
	 private Session session = factory.openSession();
	 
	 public Integer addComment(Comment cm) {
		 Transaction tx = session.beginTransaction();
		 try {
			 LocalDateTime date = LocalDateTime.now();
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			 String dateTime = formatter.format(date);
			 cm.setDateCreated(LocalDateTime.parse(dateTime,formatter));
		 Integer res = (Integer) session.save(cm);
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
	 public List<Comment> getComments(){
		 Transaction tx = session.beginTransaction();
		 try {
			 Query query = session.createQuery("from Comment");
			 List<Comment> comments = query.list();
			 tx.commit();
			 return comments;
			 
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
	 public Comment searchById(int id) {
		 Transaction tx = session.beginTransaction();
		 try {
			 Comment cm = (Comment) session.get(Comment.class, id);
			 tx.commit();
			 return cm;
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
public Integer deleteComment(int c_id) {
		 
		 Transaction tx = session.beginTransaction();
		 try {
			 Query query = session.createQuery("delete from Comment where c_id=?1");
			 query.setParameter(1, c_id);
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
public List<Comment> getCommentsByMessageId(int id){
	Transaction tx = session.beginTransaction();
	try {
		 CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Comment> criteria = builder.createQuery(Comment.class);
		    Root<Comment> myroot = criteria.from(Comment.class);
		    Predicate idQuery = builder.equal(myroot.get("id"), id);
		    criteria.select(myroot).where(idQuery);
		    List<Order> orderList = new ArrayList();
		    orderList.add(builder.desc(myroot.get("dateCreated")));
		    criteria.orderBy(orderList);
		    List<Comment> comments = session.createQuery(criteria).list();
		    tx.commit();
		    return comments;
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
}
