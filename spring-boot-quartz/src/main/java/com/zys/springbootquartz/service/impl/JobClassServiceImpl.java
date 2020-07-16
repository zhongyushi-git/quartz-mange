package com.zys.springbootquartz.service.impl;

import com.zys.springbootquartz.service.JobClassService;
import com.zys.springbootquartz.util.ClazzUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhongyushi
 * @date 2020/7/15 0015
 * @dec 描述
 */
@Service
public class JobClassServiceImpl implements JobClassService {

    private static final String PACKAGE_NAME ="com.zys.springbootquartz.job";


    @Override
    public List<Map<String, String>> getClassList() {
        List<String> list = ClazzUtils.getClazzName(PACKAGE_NAME, false);
        List<Map<String, String>> newList=new ArrayList<>();
        for (String str : list) {
            String shortName=str.substring(str.lastIndexOf(".")+1);
           Map<String,String> map=new HashMap<>();
           map.put("longName",str);
           map.put("shortName",shortName);
           newList.add(map);
        }
        return newList;
    }
}
