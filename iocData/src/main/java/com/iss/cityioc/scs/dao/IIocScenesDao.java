package com.iss.cityioc.scs.dao;




import com.iss.cityioc.scs.common.dao.GenericDao;
import com.iss.cityioc.scs.entity.IocScenes;

import java.util.List;
import java.util.Map;


public interface IIocScenesDao extends GenericDao<IocScenes> {
	/** 
	 * @Fields DAO_NAME : 场景表DAO
	 */ 
	public static final String DAO_NAME = "iIocScenesDao";
	
	public List<IocScenes> queryByCategoryId(String categoryId);
	
	public List<IocScenes> queryAllsort(Map<String, Object> map);
}
