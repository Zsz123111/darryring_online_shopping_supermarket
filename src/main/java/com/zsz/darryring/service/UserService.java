package com.zsz.darryring.service;

import com.zsz.darryring.domain.Address;
import com.zsz.darryring.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 91067
* @description 针对表【user】的数据库操作Service
* @createDate 2023-01-12 19:58:41
*/
public interface UserService extends IService<User> {
    User findUserByUPhone(String uphone);
    void InsertNewUser(User user);

    User findUserAllInfoByUid(Integer uid);

    void deleteCartByRidAndUid(Integer rid,Integer uid);

    List<Address> findAllAddressByUid(Integer uid);

    void insertCartByRidAndUid(Integer rid,Integer uid);

    void updatePwd(Integer uid, String newpwd);

    void updateUserMsg(User user);

    void changePhoto(User user);
}
