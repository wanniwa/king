package com.wanniwa.king.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wanniwa
 * 社交登录类型
 */
@Getter
@AllArgsConstructor
public enum LoginTypeEnum {
	/**
	 * 账号密码登录
	 */
	PWD("PWD", "账号密码登录"),

	/**
	 * 验证码登录
	 */
	SMS("SMS", "验证码登录"),

	/**
	 * QQ登录
	 */
	QQ("QQ", "QQ登录"),

	/**
	 * 微信登录
	 */
	WECHAT("WX", "微信登录"),

	/**
	 * 码云登录
	 */
	GITEE("GITEE", "码云登录"),

	/**
	 * 开源中国登录
	 */
	OSC("OSC", "开源中国登录");

	/**
	 * 类型
	 */
	private String type;
	/**
	 * 描述
	 */
	private String description;
}
