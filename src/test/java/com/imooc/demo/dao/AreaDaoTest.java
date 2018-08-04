package com.imooc.demo.dao;

import com.imooc.demo.entity.Area;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaDaoTest {

    @Autowired
    private AreaDao areaDao;

    @Test
    public void queryArea() {
        List<Area> areas = areaDao.queryArea();
        assertEquals(2, areas.size());
    }

    @Test
    public void queryAreaById() {
        Area area = areaDao.queryAreaById(1);
        System.out.print(area);
        assertEquals("dongyuan", area.getAreaName());
    }

    @Test
    public void insertArea() {
        Area area = new Area();
        area.setAreaName("nanyuan");
        area.setPriority(1);
        int effectedNum = areaDao.insertArea(area);
        assertEquals(1, effectedNum);
    }

    @Test
    public void updateArea() {
        Area area = new Area();
        area.setAreaName("xiyuan");
        area.setAreaId(3);
        area.setLastEditTime(new Date());
        areaDao.updateArea(area);
    }

    @Test
    public void deleteArea() {
        int effectedNum = areaDao.deleteArea(3);
        assertEquals(1, effectedNum);
    }
}