/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.capinfo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capinfo.base.BaseService;
import com.capinfo.utils.UserAgentUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * 手机端视图拦截器
 * @author ThinkGem
 * @version 2014-9-1
 */
public class MobileInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, 
			ModelAndView modelAndView) throws Exception {
				/*if(modelAndView!=null) {
                    if(true && !StringUtils.startsWithIgnoreCase(modelAndView.getViewName(), "redirect:")) {
                        modelAndView.setViewName("mobile/" + modelAndView.getViewName());

                        //					if(modelAndView.getViewName().startsWith("main")){
//						modelAndView.setViewName(modelAndView.getViewName().replaceFirst("main","redirect: mobile/main"));
//					}else{
//						modelAndView.setViewName("mobile/" + modelAndView.getViewName());
//
//					}
                    }
                }*/


		if (modelAndView != null){
			// 如果是手机或平板访问的话，则跳转到手机视图页面。
			if(UserAgentUtils.isMobileOrTablet(request) && !StringUtils.startsWithIgnoreCase(modelAndView.getViewName(), "redirect:")){
				//if(true && !StringUtils.startsWithIgnoreCase(modelAndView.getViewName(), "redirect:")) {
					modelAndView.setViewName("mobile" + modelAndView.getViewName());
				//}
			}
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) throws Exception {
		
	}

}
