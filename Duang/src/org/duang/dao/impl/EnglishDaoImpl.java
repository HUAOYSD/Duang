package org.duang.dao.impl; 


import org.duang.dao.EnglishDao;
import org.duang.dao.base.BaseInterfaceImplByDao;
import org.duang.entity.English;
import org.springframework.stereotype.Repository;


@Repository("englishDao")
public class EnglishDaoImpl extends BaseInterfaceImplByDao<English> implements EnglishDao{

	public EnglishDaoImpl(){
		System.out.println("注入EnglishDao层");
	}
	
}

