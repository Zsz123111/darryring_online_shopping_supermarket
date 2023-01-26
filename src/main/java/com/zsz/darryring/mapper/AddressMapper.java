package com.zsz.darryring.mapper;

import com.zsz.darryring.domain.Address;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

/**
* @author zsz
* @description 针对表【address】的数据库操作Mapper
* @createDate 2023-01-12 19:56:12
* @Entity com.zsz.darryring.pojo.Address
*/
public interface AddressMapper extends BaseMapper<Address> {

    Address findAddressByAid(Integer aid);

    @Delete("delete from address where aid = #{aid};")
    void deleteAddressByAid(Integer aid);
}




