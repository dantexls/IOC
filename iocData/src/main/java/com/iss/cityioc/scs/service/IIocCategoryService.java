package com.iss.cityioc.scs.service;


import com.iss.cityioc.scs.entity.IocCategory;

import java.util.List;

public interface IIocCategoryService {


	public List<IocCategory> findCategoryById(String categoryId);
	
	public String save(IocCategory category);

}
