package com.capinfo.service;

import com.capinfo.base.BaseService;
import com.capinfo.entity.SysRole;
import java.util.List;

/**
 * @author zhuxiaomeng
 * @date 2017/12/19.
 * @email 154040976@qq.com
 */
public interface RoleService extends BaseService<SysRole,String> {


  int deleteByPrimaryKey(String id);

  @Override
  int insert(SysRole record);

  @Override
  int insertSelective(SysRole record);


  SysRole selectByPrimaryKey(String id);

  @Override
  int updateByPrimaryKeySelective(SysRole record);

  @Override
  int updateByPrimaryKey(SysRole record);

  @Override
  List<SysRole> selectListByPage(SysRole sysRole);

  List<SysRole> getUserListByRoleId(String userId);

}
