package com.ordering.seller.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ordering.seller.dao.SellerDao;
import com.ordering.seller.po.Seller;
import com.ordering.seller.service.SellerService;
import com.ordering.utils.PageBean;

@Service("sellerService")
public class SellerServiceImpl implements SellerService {

	@Resource(name="sellerDao")
	private SellerDao sellerDao;

	@Override
	@Transactional
	public int registSeller(Seller seller) {
		return sellerDao.saveSeller(seller);
	}

	@Override
	@Transactional
	public void active(String code) {
		/**
		 * 1、使用code查询数据库，得到seller
		 */
		Seller seller = sellerDao.findSellerByEmailCode(code);
		/**
		 * 2、如果不存在说明激活码无效
		 */
		if(seller == null) {
			try {
				throw new SellerException("激活码无效");
			} catch (SellerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/**
		 * 3、校验用户的状态是否为未激活状态，如果已经激活，说明是二次激活，抛出异常
		 */
		if(seller.getEmailActive().equalsIgnoreCase("1")) {
			try {
				throw new SellerException("已经激活，不需要重新激活");
			} catch (SellerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/**
		 * 4、修改用户状态
		 */
		sellerDao.updateEmailActive(seller);
	}

	@Override
	@Transactional
	public Seller findSellerByEmailCode(String code) {
		return sellerDao.findSellerByEmailCode(code);
	}

	@Override
	public Seller findSellerByEmail(String email) {
		return sellerDao.findSellerByEmail(email);
	}

	@Override
	public int login(Seller seller) {
		Seller seller_find = findSellerByEmail(seller.getEmail());
		if (seller_find==null) {
			//登录失败
			return 0;
		} else {
			if (seller_find.getEmailActive().equalsIgnoreCase("0")) {
				//邮箱没有激活
				return 1;
			} else {
				return 2;
			}
		}
	}
	
	@Override
	public Seller findSellerBySid(String sid) {
		Seller seller = sellerDao.findSellerBySid(sid);
		return seller;
	}

	@Override
	public PageBean<Seller> queryAll_PageSeller(int pageCurrent, int pageSize) {
		// TODO Auto-generated method stub
		PageBean<Seller> pageBean = sellerDao.queryAll_PageSeller(pageCurrent, pageSize);
		List<Seller> list_seller = pageBean.getBeanList();
		pageBean.setBeanList(list_seller);
		return pageBean;
	}

	@Override
	@Transactional
	public void passAccpet(String sid, boolean b) {
		sellerDao.updateAccpetActive(sid, b);
	}

}
