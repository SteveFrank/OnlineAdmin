package com.ordering.seller.dao;

import com.ordering.seller.po.Seller;
import com.ordering.utils.PageBean;

public interface SellerDao {
	public int saveSeller(Seller seller);
	public Seller findSellerByEmailCode(String code);
	public void updateEmailActive(Seller seller);
	public Seller findSellerByEmail(String email);
	public Seller findSellerBySid(String sid);
	public PageBean<Seller> queryAll_PageSeller(int pageCurrent, int pageSize);
	public void updateAccpetActive(String sid, boolean b);
}
