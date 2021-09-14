package com.sanjay.app.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.sanjay.app.model.Message;

public class MessageRepo {

	 private Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Message.class);
	 private SessionFactory factory = con.buildSessionFactory();
	 private Session session = factory.openSession();
	 
	 public Integer addMessage(Message msg) {
		 Transaction tx = session.beginTransaction();
		 try {
			 LocalDateTime date = LocalDateTime.now();
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			 String dateTime = formatter.format(date);
			 msg.setDateCreated(LocalDateTime.parse(dateTime,formatter));
		 Integer res = (Integer) session.save(msg);
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
	 public List<Message> getMessages(){
		 Transaction tx = session.beginTransaction();
		 try {
			 Query query = session.createQuery("from Message order by dateCreated desc");
			 List<Message> messages = query.list();
			 tx.commit();
			 return messages;
			 
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
	 public Message searchById(int id) {
		 Transaction tx = session.beginTransaction();
		 try {
			 Message msg = (Message) session.get(Message.class, id);
			 tx.commit();
			 return msg;
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
	 public List<Comment> getComments(int id){
		 Transaction tx = session.beginTransaction();
		 try {
			 Query query = session.createQuery("from Comment where id=?1");
			 query.setParameter(1, id);
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
	 public Integer deleteMessage(int id) {
		 
		 Transaction tx = session.beginTransaction();
		 try {
			 Query query = session.createQuery("delete from Message where id=?1");
			 query.setParameter(1, id);
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
	 public List<Message> getMessagesByUserId(int uId){
		 Transaction tx = session.beginTransaction();
		 try {
			 CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<Message> criteria = builder.createQuery(Message.class);
			    Root<Message> myroot = criteria.from(Message.class);
			    Predicate uIdQuery = builder.equal(myroot.get("uId"), uId);
			    criteria.select(myroot).where(uIdQuery);
			    List<Order> orderList = new ArrayList();
			    orderList.add(builder.desc(myroot.get("dateCreated")));
			    criteria.orderBy(orderList);
			    List<Message> messages = session.createQuery(criteria).list();
			    tx.commit();
			    return messages;
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
	 public List<Message> searchMessageByName(String name) {
			Transaction tx = session.beginTransaction();
			try {
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<Message> criteria = builder.createQuery(Message.class);
			    Root<Message> myroot = criteria.from(Message.class);
			    Predicate likeQuery = builder.like(myroot.get("name"), "%"+name+"%");
			    criteria.select(myroot).where(likeQuery);
			    List<Order> orderList = new ArrayList();
			    orderList.add(builder.desc(myroot.get("dateCreated")));
			    criteria.orderBy(orderList);
			    List<Message> messages = session.createQuery(criteria).list();
			    tx.commit();
			    return messages;
			}
			catch(HibernateException e) {
				e.printStackTrace();
				tx.rollback();
				return null;
				
			}
		}
	 public Integer updateMessage(int id,String message) {
		 Transaction tx = session.beginTransaction();
		 try {
			Query query = session.createQuery("update Message m set m.message =?1 where m.id=?2");
			query.setParameter(1, message);
			query.setParameter(2, id);
			return (Integer)query.executeUpdate();
		 }
		 catch(HibernateException e) {
			 e.printStackTrace();
			 tx.rollback();
			 return 0;
		 }
		 finally {
			 session.close();
		 }
		 
	 }
}
