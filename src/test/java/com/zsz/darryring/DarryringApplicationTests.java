package com.zsz.darryring;

import com.zsz.darryring.domain.Address;
import com.zsz.darryring.domain.User;
import com.zsz.darryring.mapper.UserMapper;
import com.zsz.darryring.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DarryringApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
//        User userAllInfoByUid = userMapper.findUserAllInfoByUid(1);
//        User userAllInfoByUid = userService.findUserAllInfoByUid(1);
//        System.out.println(userAllInfoByUid);
        List<Address> allAddressByUid = userService.findAllAddressByUid(1);
        System.out.println(allAddressByUid);
    }

}
