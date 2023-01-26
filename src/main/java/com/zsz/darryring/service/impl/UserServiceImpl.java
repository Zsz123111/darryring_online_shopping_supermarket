package com.zsz.darryring.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsz.darryring.domain.Address;
import com.zsz.darryring.domain.User;
import com.zsz.darryring.service.UserService;
import com.zsz.darryring.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author zsz
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-01-12 19:58:41
*/
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUPhone(String uphone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uphone",uphone);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public void InsertNewUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public User findUserAllInfoByUid(Integer uid) {
        User user = userMapper.findUserAllInfoByUid(uid);
        return user;
    }

    @Override
    public void deleteCartByRidAndUid(Integer rid, Integer uid) {
        if (rid==0){
            userMapper.deleteAllByUid(uid);
        }else {
            userMapper.deleteCartByRidAndUid(rid,uid);
        }
    }

    @Override
    public List<Address> findAllAddressByUid(Integer uid) {
        List<Address> userAllAddressByUid = userMapper.findUserAllAddressByUid(uid);
        return userAllAddressByUid;
    }

    @Override
    public void insertCartByRidAndUid(Integer rid, Integer uid) {
        userMapper.insertCartByRidAndUid(rid,uid);
    }

    @Override
    public void updatePwd(Integer uid, String newpwd) {
        userMapper.updatePwd(uid,newpwd);
    }

    @Override
    public void updateUserMsg(User user) {
        userMapper.updateById(user);
    }

    @Override
    public void changePhoto(User user) {
        User user1 = new User(user.getUid(),null,null,null,null,null,user.getUurl(),null,null);
        userMapper.updateById(user);
    }


}




