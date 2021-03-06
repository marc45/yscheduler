package com.yeahmobi.yscheduler.model.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yeahmobi.yscheduler.model.ScheduleProgress;
import com.yeahmobi.yscheduler.model.dao.ScheduleProgressDao;
import com.yeahmobi.yscheduler.model.service.ScheduleProgressService;

@Service
public class ScheduleProgressServiceImpl implements ScheduleProgressService {

    /** 仅使用一条记录存放进度 */
    private static final Integer ID = 1;

    @Autowired
    private ScheduleProgressDao  scheduleProgressDao;

    public Long getCurrentScheduleTime() {
        ScheduleProgress progress = this.scheduleProgressDao.selectByPrimaryKey(ID);
        if (progress == null) {
            return null;
        }
        Date currentScheduleTime = progress.getCurrentScheduleTime();
        return currentScheduleTime.getTime();
    }

    public void saveCurrentScheduleTime(long currentScheduleTime) {
        ScheduleProgress scheduleProgress = new ScheduleProgress();
        scheduleProgress.setId(ID);
        scheduleProgress.setCurrentScheduleTime(new Date(currentScheduleTime));
        this.scheduleProgressDao.updateByPrimaryKey(scheduleProgress);
        // this.scheduleProgressDao.insertOnDuplicateKeyUpdate(scheduleProgress);
    }
}
