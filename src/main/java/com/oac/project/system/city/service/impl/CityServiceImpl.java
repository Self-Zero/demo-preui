package com.oac.project.system.city.service.impl;

import com.oac.project.system.city.domain.City;
import com.oac.project.system.city.mapper.CityMapper;
import com.oac.project.system.city.service.CityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * CityService实现类
 */
@Service
public class CityServiceImpl implements CityService {

    @Resource
    private CityMapper cityMapper;

    private List<City> allCity = null;

    /**
     * 查询所有的parentId=0的city信息
     *
     * @return
     */
    @Override
    public List<City> getCityByParent() {
        List<City> getCityByParent = new ArrayList<>();
        City cityParent = null;
        allCity = cityMapper.getAllCity();
        for (City city : allCity) {
            cityParent = new City();
            if (city.getParentId() == 0) {
                cityParent.setCityId(city.getCityId());
                cityParent.setCityName(city.getCityName());
                cityParent.setParentId(city.getParentId());
                getCityByParent.add(cityParent);
            }
        }
        return getCityByParent;
    }

    /**
     * 根据上级城市的id信息查询所有的Urban城市信息并返回
     *
     * @param parentId 上级城市的id
     * @return
     */
    @Override
    public List<City> getCityByUrban(Long parentId) {
        List<City> getCityByUrban = new ArrayList<>();
        City cityUrban = null;
        allCity = cityMapper.getAllCity();
        for (City city : allCity) {
            cityUrban = new City();
            if (city.getParentId().equals(parentId)) {
                cityUrban.setCityId(city.getCityId());
                cityUrban.setCityName(city.getCityName());
                cityUrban.setParentId(city.getParentId());
                getCityByUrban.add(cityUrban);
            }
        }
        return getCityByUrban;
    }

}
