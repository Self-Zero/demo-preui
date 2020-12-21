package com.oac.project.system.city.service;


import com.oac.project.system.city.domain.City;

import java.util.List;

/**
 * CityService接口
 */
public interface CityService {

    /**
     * 查询所有的City信息
     *
     * @return
     */
    List<City> getCityByParent();

    /**
     * 根据上级城市的id信息查询所有的Urban(二级)城市信息并返回
     *
     * @param parentId 上级城市的id
     * @return
     */
    List<City> getCityByUrban(Long parentId);
}
