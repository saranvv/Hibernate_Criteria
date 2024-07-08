package com.view;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class ProductOperation {
	public static void main(String[]args)
	{
	@SuppressWarnings("unchecked")
	SessionFactory sessionFactory=HibernateUtil.getSesFactory();
	Session session = sessionFactory.getCurrentSession();
	Transaction tx=session.beginTransaction();
	Criteria criteria=session.createCriteria(Product.class);
	
	List <Product> proList=criteria.list();
	for(Product pro:proList)
	{
		System.out.println("Slno="+pro.getSlno()+" "+pro.getName()+" "+pro.getQuantity()+" "+pro.getPrice());
	}
	
	
	proList = session.createCriteria(Product.class)
            .add(Restrictions.gt("price", 500.0f))
            .list();
	System.out.println();
	System.out.println("Products with price greater than 500: ");
    for (Product pro : proList) {
        System.out.println(pro.getName()+" "+pro.getQuantity()+" "+pro.getPrice());
    }
    
    
    System.out.println();
    List<Product> proList1 = session.createCriteria(Product.class)
            .add(Restrictions.gt("quantity", 50))
            .list();

    System.out.println();
    System.out.println("Products with quantity greater than 50: ");
    for (Product product : proList1) {
        System.out.println(product.getName() + " " + product.getQuantity()+" "+product.getPrice());
    }
    
    
    System.out.println();
    Criteria criteria1 = session.createCriteria(Product.class)
            .setProjection(Projections.rowCount());
    Long count = (Long) criteria1.uniqueResult();
    System.out.println("Number of products available: " + count);
    
    
    
    System.out.println();
    Criteria criteria2 = session.createCriteria(Product.class)
            .setProjection(Projections.sum("price"));
    Double totalSum = (Double) criteria2.uniqueResult();
    System.out.println("Total sum of all product prices: " + totalSum);
    
    
  
   
    

}
}