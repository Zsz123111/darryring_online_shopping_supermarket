package com.zsz.darryring.mapper;

import com.zsz.darryring.domain.Address;
import com.zsz.darryring.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
* @author zsz
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-01-12 19:58:41
* @Entity com.zsz.darryring.pojo.User
*/
public interface UserMapper extends BaseMapper<User> {

    User findUserAllInfoByUid(Integer uid);

    @Delete("delete from shopcart where rid=#{rid} and uid=#{uid}")
    void deleteCartByRidAndUid(@Param("rid") Integer rid,@Param("uid") Integer uid);

    @Delete("delete from shopcart where uid = #{uid}")
    void deleteAllByUid(Integer uid);

    List<Address> findUserAllAddressByUid(Integer uid);

    @Insert("insert into shopcart(rid,uid) values (#{rid},#{uid})")
    void insertCartByRidAndUid(@Param("rid")Integer rid,@Param("uid") Integer uid);

    @Update("update user set upassword=#{newpwd} where uid=#{uid}")
    void updatePwd(@Param("uid") Integer uid,@Param("newpwd") String newpwd);
}




