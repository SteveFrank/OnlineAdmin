package com.ordering.admin.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ordering.admin.dao.AdminDao;
import com.ordering.admin.po.Admin;

@Repository("adminDao")
public class AdminDaoImpl implements AdminDao {
	
	@Autowired(required=true)
	@PersistenceContext(name="unitName")
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public Admin adminFindByEmailAndPassword(Admin admin) {
		String jpql="select admin from com.ordering.admin.po.Admin admin where admin.aemail=:aemail and admin.apassword=:apassword";
		System.out.println(admin);
		List<Admin> adminlist = entityManager.createQuery(jpql)
					.setParameter("aemail", admin.getAemail())
					.setParameter("apassword", admin.getApassword())
					.getResultList();
		
		if (adminlist.isEmpty()) {
			entityManager.close();
			return null;
		} else {
			Admin adminfind = adminlist.get(0);
			System.out.println(adminfind);
			return adminfind;
		}
		
	}

}
