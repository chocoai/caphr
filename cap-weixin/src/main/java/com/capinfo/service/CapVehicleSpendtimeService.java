package com.capinfo.service;

import com.capinfo.base.BaseMapper;
import com.capinfo.base.CurrentUser;
import com.capinfo.base.impl.BaseServiceImpl;
import com.capinfo.entity.CapVehicleInfo;
import com.capinfo.entity.CapVehicleSpendtime;
import com.capinfo.entity.CapWorkOrderRecord;
import com.capinfo.mapper.CapVehicleSpendtimeMapper;
import com.capinfo.mapper.CapWorkOrderRecordMapper;
import com.capinfo.vehicle.utilEntity.VehicleConstant;
import com.capinfo.vehicle.utilEntity.VehicleFlowEntity;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 用于处理工单的逻辑功能
 */
@Service
public class CapVehicleSpendtimeService extends BaseServiceImpl<CapVehicleSpendtime, String> {

    @Autowired
    private CapVehicleSpendtimeMapper capVehicleSpendtimeMapper;
    @Autowired
    private SysUserService sysUserService;


    @Override
    public BaseMapper<CapVehicleSpendtime, String> getMappser() {
        return capVehicleSpendtimeMapper;
    }


    public void save(CapVehicleSpendtime capVehicleSpendtime) {
        CurrentUser currentUser = (CurrentUser) SecurityUtils.getSubject().getSession().getAttribute("curentUser");
        String userId = StringUtils.isBlank(currentUser.getId())?VehicleConstant.USER_WORKER_ID:currentUser.getId();
        String id = capVehicleSpendtime.getId();
        if (StringUtils.isBlank(id)) {
            capVehicleSpendtime.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            capVehicleSpendtime.setCreateBy(userId);
            capVehicleSpendtime.setCreateDate(new Date());
            capVehicleSpendtime.setUpdateBy(userId);
            capVehicleSpendtime.setUpdateDate(new Date());
            capVehicleSpendtime.setDelFlag("0");
            this.insertSelective(capVehicleSpendtime);
        } else {
            capVehicleSpendtime.setUpdateBy(userId);
            capVehicleSpendtime.setUpdateDate(new Date());
            this.updateByPrimaryKey(capVehicleSpendtime);
        }
    }


    public List<CapVehicleSpendtime> selectListByCondition(CapVehicleSpendtime capVehicleSpendtime) {
        return capVehicleSpendtimeMapper.selectListByCondition(capVehicleSpendtime);
    }


}