package com.zsz.darryring.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsz.darryring.domain.Address;
import com.zsz.darryring.service.AddressService;
import com.zsz.darryring.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author zsz
* @description 针对表【address】的数据库操作Service实现
* @createDate 2023-01-12 19:56:12
*/
@Service
@Transactional
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService{
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> findAllAddressByUid(Integer uid) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        List<Address> addresses = addressMapper.selectList(queryWrapper);
        return addresses;
    }

    @Override
    public void deleteAddressByAid(Integer aid) {
        addressMapper.deleteAddressByAid(aid);
    }

    @Override
    public void addNewAddress(Address address) {
        addressMapper.insert(address);
    }
}




