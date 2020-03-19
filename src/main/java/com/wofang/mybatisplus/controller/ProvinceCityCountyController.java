package com.wofang.mybatisplus.controller;

import com.alibaba.fastjson.JSONObject;
import com.wofang.mybatisplus.model.Area;
import com.wofang.mybatisplus.model.City;
import com.wofang.mybatisplus.model.Province;
import com.wofang.mybatisplus.service.ProvinceCityCountyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("pcc")
public class ProvinceCityCountyController extends BaseController {

    @Autowired
    private ProvinceCityCountyService provinceCityCountyService;

    //@RequestMapping("initProvinceData")
    public Object initProvinceData(){
        String filePath = "E:\\provinceData.json";
        File dataFile = new File(filePath);
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(dataFile));
            String s = null;
            while ((s = br.readLine()) != null){
                builder.append(System.lineSeparator()+s);
            }
            br.close();
            if(builder.length()>0){
                List<Province> provinces = JSONObject.parseArray(builder.toString(), Province.class);
                if(provinces != null && !provinces.isEmpty()){
                    for(Province p :provinces){
                        p.getCityList();
                        provinceCityCountyService.addProvince(p);
                        if(p.getCityList() != null && !p.getCityList().isEmpty()){
                            String pcode = p.getCode();
                            for(City c:p.getCityList()){
                                c.setParentCode(pcode);
                                provinceCityCountyService.addCity(c);
                                if(c.getAreaList() != null && !c.getAreaList().isEmpty()){
                                    String ccode = c.getCode();
                                    for (Area a:c.getAreaList()){
                                        a.setParentCode(ccode);
                                        provinceCityCountyService.addCounty(a);
                                    }

                                }
                            }
                        }
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
