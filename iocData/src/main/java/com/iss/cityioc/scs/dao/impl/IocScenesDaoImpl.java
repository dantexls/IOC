package com.iss.cityioc.scs.dao.impl;

import java.util.List;
import java.util.Map;

import com.iss.cityioc.scs.common.dao.GenericDaoHibernateSupport;
import com.iss.cityioc.scs.dao.IIocScenesDao;
import com.iss.cityioc.scs.entity.IocScenes;
import org.springframework.stereotype.Repository;



@Repository(value = IIocScenesDao.DAO_NAME)
public class IocScenesDaoImpl extends GenericDaoHibernateSupport<IocScenes>
		implements IIocScenesDao {

	@Override
	public List<IocScenes> queryByCategoryId(String categoryId) {
		String hql = "from IocScenes s where s.status=1 and s.categoryId=? order by s.sort asc";
		List<IocScenes> scenes = this.findByHQLAndParams(hql, categoryId);
		return scenes;
	}

	@Override
	public List<IocScenes> queryAllsort(Map<String, Object> map) {
		String hql = null;

		if (!"0".equals(map.get("flag"))) {
			hql = "from IocScenes s where s.status=1 order by s."+map.get("column") +" desc";

		} else {
			hql = "from IocScenes s where s.status=1 order by s."+map.get("column") +" asc";
		}
		List<IocScenes> scenes = this.findByHQL(hql);
		return scenes;
	}

}
