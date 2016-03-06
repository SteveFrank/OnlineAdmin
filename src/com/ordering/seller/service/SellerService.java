package com.ordering.seller.service;

import com.ordering.seller.po.Seller;
import com.ordering.utils.PageBean;

public interface SellerService {
	public int registSeller(Seller seller);
	public void active(String code);
	public Seller findSellerByEmailCode(String code);
	public Seller findSellerByEmail(String email);
	public int login(Seller seller);
	public Seller findSellerBySid(String sid);
	public PageBean<Seller> queryAll_PageSeller(int pageCurrent, int pageSize);
	public void passAccpet(String sid, boolean b);
}
