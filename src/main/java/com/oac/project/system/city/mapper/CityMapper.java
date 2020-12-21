package com.oac.project.system.city.mapper;

import com.oac.project.system.city.domain.City;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CityMapper接口
 */
@Repository
public interface CityMapper {

    /**
     * 查询所有的城市信息
     *
     * @return
     */
    List<City> getAllCity();

}
