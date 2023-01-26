package com.zsz.darryring.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsz.darryring.domain.Order;
import com.zsz.darryring.domain.Ring;
import com.zsz.darryring.domain.User;
import com.zsz.darryring.mapper.UserMapper;
import com.zsz.darryring.service.OrderService;
import com.zsz.darryring.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
* @author zsz
* @description 针对表【order】的数据库操作Service实现
* @createDate 2023-01-12 20:02:58
*/
@Service
@Transactional
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService{
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public String addOrder(Date date, User user, Integer aid, String trans) {
        String oid = UUID.randomUUID().toString();
        List<Ring> shopCart = user.getShopCart();

        orderMapper.insertNewOrder(oid,date,user.getUid(),aid,trans);
        for (Ring ring:shopCart){
            orderMapper.insertNewOrderRing(oid,ring.getRid());
        }
        userMapper.deleteAllByUid(user.getUid());
        return oid;
    }

    @Override
    public void changeOrderStatues(String oid) {
        orderMapper.updateOrderStatuesByOid(oid);
    }
}




