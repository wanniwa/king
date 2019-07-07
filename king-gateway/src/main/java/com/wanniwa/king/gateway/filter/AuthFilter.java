//package com.wanniwa.king.gateway.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.wanniwa.king.gateway.constant.RedisConstant;
//import com.wanniwa.king.gateway.util.CookieUtil;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//
///**
// * 权限拦截
// * 区分买家和卖家
// */
//@Component
//public class AuthFilter extends ZuulFilter {
//    @Autowired
//    StringRedisTemplate stringRedisTemplate;
//    @Override
//    public String filterType() {
//        return FilterConstants.PRE_TYPE;
//    }
//
//    @Override
//    public int filterOrder() {
//        return FilterConstants.PRE_DECORATION_FILTER_ORDER -1;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//    @Override
//    public Object run()  {
//        RequestContext requestContext = RequestContext.getCurrentContext();
//        HttpServletRequest request = requestContext.getRequest();
//        /**
//         *  /order/create 只能买家访问(cookie里有openId)
//         *  /order/finish 只能卖家访问(cookie里有token 并且对应的redis中有值)
//         *  /product/list 都可访问
//         */
//        if ("/king-order/order/create".equals(request.getRequestURI())){
//            Cookie cookie = CookieUtil.get(request, "openid");
//            if (cookie == null || StringUtils.isEmpty(cookie.getValue())) {
//                requestContext.setSendZuulResponse(false);
//                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
//            }
//        }
//
//        if ("/king-order/order/finish".equals(request.getRequestURI())){
//            Cookie cookie = CookieUtil.get(request, "token");
//            if (cookie == null || StringUtils.isEmpty(cookie.getValue())
//                ||StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE,cookie.getValue())))
//            ) {
//                requestContext.setSendZuulResponse(false);
//                requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
//            }
//        }
//
//        return null;
//    }
//}
