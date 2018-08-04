package com.imooc.demo.service.impl;

import com.imooc.demo.dao.AreaDao;
import com.imooc.demo.entity.Area;
import com.imooc.demo.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }

    @Override
    public Area getAreaById(int areaId) {
        if (areaId < 0) {
            throw new RuntimeException("areaId is error!");
        }
        return areaDao.queryAreaById(areaId);
    }

    @Transactional
    @Override
    public boolean addArea(Area area) {
        if (!StringUtils.isEmpty(area.getAreaName())) {
            area.setCreateTime(new Date());
            area.setLastEditTime(new Date());
            try {
                int effectedNum = areaDao.insertArea(area);
                if (effectedNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("insert area failed!");
                }
            } catch (Exception e) {
                throw new RuntimeException("insert area failed:" + e.getMessage());
            }
        } else {
            throw new RuntimeException("area name can't be null");
        }
    }

    @Override
    public boolean modifyArea(Area area) {
        if (area.getAreaId() != null && area.getAreaId() > 0) {
            area.setLastEditTime(new Date());
            try {
                int effectedNum = areaDao.updateArea(area);
                if (effectedNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("update area failed!");
                }
            } catch (Exception e) {
                throw new RuntimeException("update area failed:" + e.getMessage());
            }
        } else {
            throw new RuntimeException("area id can't be null!");
        }
    }

    @Override
    public boolean deleteArea(int areaId) {
        if (areaId > 0) {
            try {
                int effectedNum = areaDao.deleteArea(areaId);
                if (effectedNum > 0) {
                    return true;
                } else {
                    throw new RuntimeException("delete area failed!");
                }
            } catch (Exception e) {
                throw new RuntimeException("delete area failed:" + e.getMessage());
            }
        } else {
            throw new RuntimeException("area id is error!");
        }
    }
}
