package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.nt.entity.Product;

public class SaveObjectTest {

	public static void main(String[] args) {
		Configuration cfg=null;
		SessionFactory factory=null;
		Session ses=null;
		Product prod=null;
		Transaction tx=null;
		boolean flag=false;
	//Activate Hibernate f/w (Bootstrap hibernate)
		cfg=new Configuration();
		//supply hibernate cfg file as input file
	cfg.configure("/com/nt/cfgs/hibernate.cfg.xml");
	//build session factory
	factory=cfg.buildSessionFactory();
	//open session
	ses=factory.openSession();
	//create entity to save with DB s/w
	prod=new Product();
	prod.setPid(282);prod.setPname("sofa");prod.setPrice(60000);prod.setQty(2);
	try {
	   tx=ses.beginTransaction();//internally calls con.setAutoCommit(false) to begin the tx
	
	   //save object
	ses.save(prod);
	System.out.println("1");
	flag=true;
	
	}
	catch(HibernateException he) {
		he.printStackTrace();
		flag=false;
	}
	
	finally {
		//commit or rollback Tx
			
			
			  if(flag==true){
				  System.out.println("2");
			    tx.commit();//internally calls con.commit()
			    System.out.println("3");
			    System.out.println("Object saved");
			   }
			    else {
			  tx.rollback();//internally calls con.rollback();
			  System.out.println("Object is not saved");
			   }
			 
					//close session object
		ses.close();
		//close sessionFactory
		factory.close();
	}//finally
 }//main
	}
//class