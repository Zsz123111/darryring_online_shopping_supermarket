package com.zsz.darryring.mapper;

import com.zsz.darryring.domain.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsz.darryring.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
* @author zsz
* @description 针对表【order】的数据库操作Mapper
* @createDate 2023-01-12 20:02:58
* @Entity com.zsz.darryring.pojo.Order
*/
public interface OrderMapper extends BaseMapper<Order> {
    List<Order> findOrdersByUid(Integer uid);


    void insertNewOrder(@Param("oid")String oid, @Param("date")Date date, @Param("uid")Integer uid,@Param("aid") Integer aid,@Param("trans") String trans);


    void insertNewOrderRing(@Param("oid")String oid,@Param("rid") Integer rid);

    void updateOrderStatuesByOid(String oid);
}




