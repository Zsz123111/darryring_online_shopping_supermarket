package com.zsz.darryring.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsz.darryring.domain.Ring;
import com.zsz.darryring.service.RingService;
import com.zsz.darryring.mapper.RingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author zsz
* @description 针对表【ring】的数据库操作Service实现
* @createDate 2023-01-12 19:59:46
*/
@Service
@Transactional
public class RingServiceImpl extends ServiceImpl<RingMapper, Ring> implements RingService{
    @Autowired
    private RingMapper ringMapper;

    @Override
    public List<Ring> findHotRings(Integer num) {
        QueryWrapper<Ring> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("rsales").last("limit 0,"+num);
        List<Ring> rings = ringMapper.selectList(queryWrapper);
        return rings;
    }

    @Override
    public List<Ring> findRingsByLikeRName(String condition) {
        List<Ring> rings = null;
        if ("all".equals(condition)){
            rings = ringMapper.selectList(null);
            return rings;
        }else {
            QueryWrapper<Ring> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("rname",condition);
            rings=ringMapper.selectList(queryWrapper);
        }
        return rings;
    }

    @Override
    public Ring findRingByRid(Integer rid) {
        Ring ring = ringMapper.selectById(rid);
        return ring;
    }
}




