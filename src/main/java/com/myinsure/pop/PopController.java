package com.myinsure.pop;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.myinsure.model.Pop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by songxiaofei on 2018-12-19.
 */
public class PopController extends Controller {

    @Inject
    PopService service;

    public void main() {

        List<Pop> lists = service.findByPro();
        StringBuffer xAxis = new StringBuffer();
        StringBuffer yAxis = new StringBuffer();

        for(Pop list : lists){
            xAxis.append(list.get("population").toString());
            xAxis.append(",");
            yAxis.append(list.get("area_name").toString());
            yAxis.append(",");
        }

        setAttr("xAxis",xAxis.toString() );
        setAttr("yAxis",yAxis.toString() );
        render("/population/main.html");
    }
    public void getProvince() {
        List<Pop> lists=service.findProvince();
        setAttr("province",lists);
        renderJson(lists);
    }
    public void getCity() {
        String pid = getPara("pid");
        List list=service.findCity(pid);
        System.out.println(list.size());
        setAttr("city",list);
        renderJson(list);
    }
    public void getDistrict() {
        String cid = getPara("cid");
        List list=service.findDistrict(cid);
        System.out.println(list.size());
        setAttr("district",list);
        renderJson(list);
    }

}
