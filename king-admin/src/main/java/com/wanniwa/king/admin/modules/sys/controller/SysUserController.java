package com.wanniwa.king.admin.modules.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanniwa.king.admin.modules.sys.entity.SysUser;
import com.wanniwa.king.admin.modules.sys.service.SysUserService;
import com.wanniwa.king.common.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 系统用户
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    //@Autowired
    //private SysUserRoleService sysUserRoleService;
    //
    //
    ///**
    // * 所有用户列表
    // */
    //@GetMapping("/list")
    //public R list(@RequestParam Map<String, Object> params){
    //	//只有超级管理员，才能查看所有管理员列表
    //	if(getUserId() != Constant.SUPER_ADMIN){
    //		params.put("createUserId", getUserId());
    //	}
    //	PageUtils page = sysUserService.queryPage(params);
    //
    //	return R.ok().put("page", page);
    //}
    //
    ///**
    // * 获取登录的用户信息
    // */
    //@GetMapping("/info")
    //public R info(){
    //	return R.ok().put("user", getUser());
    //}
    //
    ///**
    // * 修改登录用户密码
    // */
    //@SysLog("修改密码")
    //@PostMapping("/password")
    //public R password(@RequestBody PasswordForm form){
    //	Assert.isBlank(form.getNewPassword(), "新密码不为能空");
    //
    //	//sha256加密
    //	String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
    //	//sha256加密
    //	String newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();
    //
    //	//更新密码
    //	boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
    //	if(!flag){
    //		return R.error("原密码不正确");
    //	}
    //
    //	return R.ok();
    //}
    @GetMapping("/page")
    //@RequiresPermissions("sys:user:info")
    public Result page(Page<SysUser> page, SysUser sysUser, Date startDate,Date endDate) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().likeRight(StringUtils.isNotEmpty(sysUser.getUsername()),SysUser::getUsername, sysUser.getUsername());
        queryWrapper.lambda().likeRight(StringUtils.isNotEmpty(sysUser.getPhone()),SysUser::getPhone, sysUser.getPhone());
        queryWrapper.lambda().eq(null== sysUser.getState(),SysUser::getState, sysUser.getState());
        queryWrapper.lambda().ge(null ==startDate, SysUser::getCreateTime, startDate);
        queryWrapper.lambda().le(null == endDate, SysUser::getCreateTime, endDate);
        return Result.success(sysUserService.page(page, queryWrapper));
    }

    /**
     * 用户信息
     */
    @GetMapping("/info/{userId}")
    //@RequiresPermissions("sys:user:info")
    public Result info(@PathVariable("userId") Long userId) {
        SysUser user = sysUserService.getById(userId);
        //获取用户所属的角色列表
        //List<Long> roleIdList = sysUserService.queryRoleIdList(userId);
        //user.setRoleIdList(roleIdList);
        return Result.success(user);
    }

    ///**
    // * 保存用户
    // */
    //@SysLog("保存用户")
    //@PostMapping("/save")
    //@RequiresPermissions("sys:user:save")
    //public R save(@RequestBody SysUserEntity user){
    //	ValidatorUtils.validateEntity(user, AddGroup.class);
    //
    //	user.setCreateUserId(getUserId());
    //	sysUserService.saveUser(user);
    //
    //	return R.ok();
    //}
    //
    ///**
    // * 修改用户
    // */
    //@SysLog("修改用户")
    //@PostMapping("/update")
    //@RequiresPermissions("sys:user:update")
    //public R update(@RequestBody SysUserEntity user){
    //	ValidatorUtils.validateEntity(user, UpdateGroup.class);
    //
    //	user.setCreateUserId(getUserId());
    //	sysUserService.update(user);
    //
    //	return R.ok();
    //}
    //
    ///**
    // * 删除用户
    // */
    //@SysLog("删除用户")
    //@PostMapping("/delete")
    //@RequiresPermissions("sys:user:delete")
    //public R delete(@RequestBody Long[] userIds){
    //	if(ArrayUtils.contains(userIds, 1L)){
    //		return R.error("系统管理员不能删除");
    //	}
    //
    //	if(ArrayUtils.contains(userIds, getUserId())){
    //		return R.error("当前用户不能删除");
    //	}
    //
    //	sysUserService.deleteBatch(userIds);
    //
    //	return R.ok();
    //}
}
