package com.wanniwa.king.admin.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wanniwa.king.admin.entity.SysUser;
import com.wanniwa.king.admin.service.SysUserService;
import com.wanniwa.king.common.core.utils.R;
import com.wanniwa.king.common.data.util.PageQuery;
import com.wanniwa.king.common.data.util.PageUtil;
//import com.wanniwa.king.common.security.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 系统用户
 *
 * @author wanniwa
 * @date 2019/12/30
 */
@RestController
@RequestMapping("/sys/user")
@Api(value = "用户信息")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 获取当前用户全部信息
     *
     * @return 用户信息
     */
    @GetMapping(value = {"/info"})
    public R info(Integer userId) {
        //Integer userId = SecurityUtil.getUser().getId();
        SysUser user = sysUserService.getById(userId);
        if (user == null) {
            return R.fail("获取当前用户信息失败");
        }
        return R.ok(sysUserService.getUserInfo(user));
    }






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
    @ApiOperation(value = "用户分页查询")
    @GetMapping("/page")
    //@RequiresPermissions("sys:user:info")
    public R<IPage<SysUser>> page(PageQuery<SysUser> pageQuery, String username, String phone, Boolean lockFlag, Date startDate, Date endDate) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        //username
        queryWrapper.lambda().likeRight(StrUtil.isNotEmpty(username),SysUser::getUsername, username);
        //phone
        queryWrapper.lambda().likeRight(StrUtil.isNotEmpty(phone),SysUser::getPhone, phone);
        //state
        queryWrapper.lambda().eq(null== lockFlag,SysUser::getLockFlag, lockFlag);
        //startDate
        queryWrapper.lambda().ge(null ==startDate, SysUser::getCreateTime, startDate);
        //endDate
        queryWrapper.lambda().le(null == endDate, SysUser::getCreateTime, endDate);
        return R.ok(sysUserService.page(PageUtil.getPage(pageQuery), queryWrapper));
    }

    /**
     * 用户信息
     */
    @GetMapping("/info/{userId}")
    //@RequiresPermissions("sys:user:info")
    public R<SysUser> info(@PathVariable("userId") Long userId) {
        SysUser user = sysUserService.getById(userId);
        //获取用户所属的角色列表
        //List<Long> roleIdList = sysUserService.queryRoleIdList(userId);
        //user.setRoleIdList(roleIdList);
        return R.ok(user);
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
