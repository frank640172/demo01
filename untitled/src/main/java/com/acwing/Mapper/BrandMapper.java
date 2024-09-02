package com.acwing.Mapper;

import com.acwing.Demo.Brand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;
import java.util.Map;
@Mapper
public interface BrandMapper extends BaseMapper<Brand> {
     List<Brand> selectAll();

     Brand selectById(Integer id);

//     List<Brand> selectByCondition(@Param("status") int status,
//                                   @Param("company_name") String companyName,
//                                   @Param("brand_name") String brandName);

     //sql不需要改，只需要将数据封装成对象传递到方法中即可
     //List<Brand> selectByCondition(Brand brand);
     List<Brand> selectByCondition(Map map);


     //增加增 ，删 ，改操作
     void add(Brand brand);

     void update(Brand brand);
     
}
