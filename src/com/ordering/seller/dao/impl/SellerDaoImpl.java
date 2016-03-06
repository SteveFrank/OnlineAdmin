package com.ordering.seller.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ordering.seller.dao.SellerDao;
import com.ordering.seller.po.Seller;
import com.ordering.utils.PageBean;

@Repository("sellerDao")
public class SellerDaoImpl implements SellerDao {
	
	@Autowired(required=true)
	@PersistenceContext(name="unitName")
	private EntityManager entityManager;
	
	@Override
	public int saveSeller(Seller seller) {
		entityManager.persist(seller);
		entityManager.close();
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Seller findSellerByEmailCode(String code) {
		String jpql="select seller from com.ordering.seller.po.Seller seller where seller.activeCode=:code";
		List<Seller> seller_find = entityManager.createQuery(jpql).setParameter("code", code).getResultList();
		if(seller_find.isEmpty()) {
			return null;
		} else {
			Seller seller = seller_find.get(0);
			entityManager.close();
			return seller;
		}
	}

	@Override
	public void updateEmailActive(Seller seller) {
		Seller seller_find = (Seller)entityManager.find(Seller.class, seller.getSid());
		/**
		 * 设置已经激活完成
		 */
		seller_find.setEmailActive("1");
		entityManager.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Seller findSellerByEmail(String email) {
		String jpql="select seller from com.ordering.seller.po.Seller seller where seller.email=:email";
		List<Seller> seller_find = entityManager.createQuery(jpql).setParameter("email", email).getResultList();
		if(seller_find.isEmpty()) {
			return null;
		} else {
			Seller seller = seller_find.get(0);
			entityManager.close();
			return seller;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Seller findSellerBySid(String sid) {
		String jpql = "select seller from com.ordering.seller.po.Seller seller where seller.sid=:sid";
		List<Seller> seller_find = entityManager.createQuery(jpql).setParameter("sid", sid).getResultList();
		Seller seller = seller_find.get(0);
		return seller;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageBean<Seller> queryAll_PageSeller(int pageCurrent, int pageSize) {
		PageBean<Seller> pb = new PageBean<Seller>();
		//设置当前页面页码
		pb.setPageCurrent(pageCurrent);
		//设置当前页面的页面容量
		pb.setPageSize(pageSize);
		String jpql = "select seller from com.ordering.seller.po.Seller seller";
		List<Seller> seller_list = entityManager.createQuery(jpql).getResultList();
		pb.setRowCount(seller_list.size());
		
		if(((pageCurrent-1)*pageSize+pageSize)>seller_list.size()) {
			pageSize = seller_list.size();
		}
		
		seller_list.subList((pageCurrent-1)*pageSize, (pageCurrent-1)*pageSize+pageSize);
		
		pb.setBeanList(seller_list);
		return pb;
	}

	@Override
	public void updateAccpetActive(String sid,boolean b) {
		Seller seller = (Seller) entityManager.find(Seller.class, sid);
		if (b) {
			seller.setAccpetActive("1");
		} else {
			seller.setAccpetActive("2");
		}
		
		entityManager.close();
	}

}
