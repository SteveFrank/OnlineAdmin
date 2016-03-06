package com.ordering.admin.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ordering.admin.dao.AdminDao;
import com.ordering.admin.po.Admin;
import com.ordering.admin.service.AdminService;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
	
	@Resource(name="adminDao")
	private AdminDao adminDao;
	
	@Override
	@Transactional
	public Admin AdminLogin(Admin admin) {
		return adminDao.adminFindByEmailAndPassword(admin);
	}

}
