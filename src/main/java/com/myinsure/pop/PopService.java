package com.myinsure.pop;

import com.jfinal.plugin.activerecord.Page;
import com.myinsure.model.Pop;

import java.util.List;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * BlogService
 * 所有 sql 与业务逻辑写在 Service 中，不要放在 Model 中，更不
 * 要放在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
public class PopService {
	
	/**
	 * 所有的 dao 对象也放在 Service 中，并且声明为 private，避免 sql 满天飞
	 * sql 只放在业务层，或者放在外部 sql 模板，用模板引擎管理：
	 * 			http://www.jfinal.com/doc/5-13
	 */
	private Pop dao = new Pop().dao();
	
	public Page<Pop> paginate(int pageNumber, int pageSize) {
		return dao.paginate(pageNumber, pageSize, "select *", "from  china_pop order by view_order asc");
	}
	
	public List<Pop> findByPro(){
		return dao.find("select * from china_pop where parent_id=0 and population <>0 order by view_order desc");
	}

	public List<Pop> findProvince(){
		return dao.find("select * from china_pop where parent_id=0 and population <>0 order by view_order asc");
	}

	public List<Pop> findCity(String pid){
		return dao.find("select * from china_pop where parent_id="+pid+" order by view_order desc");
	}
	public List<Pop> findDistrict(String cid){
		return dao.find("select * from china_pop where parent_id="+cid+" order by view_order desc");
	}

	public void deleteById(int id) {
		dao.deleteById(id);
	}
}
