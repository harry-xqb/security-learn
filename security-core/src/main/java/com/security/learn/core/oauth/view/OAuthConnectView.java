package com.security.learn.core.oauth.view;

import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * create by： harry
 * date:  2019/12/1 0001 下午 6:00
 **/
public class OAuthConnectView  extends AbstractView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {
        if(map.get("connections") == null){
            response.setContentType("text/html;charset=utf-8;");
            response.getWriter().write("<h3>解绑成功</h3>");
        }else{
            response.setContentType("text/html;charset=utf-8;");
            response.getWriter().write("<h3>绑定成功</h3>");
        }
    }
}
