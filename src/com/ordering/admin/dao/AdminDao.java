package com.ordering.admin.dao;

import com.ordering.admin.po.Admin;

public interface AdminDao {
	public Admin adminFindByEmailAndPassword(Admin admin);
}
