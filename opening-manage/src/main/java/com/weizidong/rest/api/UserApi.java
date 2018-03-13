package com.weizidong.rest.api;

import com.github.pagehelper.PageInfo;
import com.qcdl.model.entity.Admin;
import com.qcdl.model.param.PageParam;
import com.weizidong.model.entity.User;
import com.weizidong.service.UserService;
import org.restful.api.session.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * 用户API接口
 *
 * @author 魏自东
 * @date 2018/2/8 15:59
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserApi {
    @Autowired
    private UserService userService;

    @POST
    @Path("/add")
    public String add(User user, @Context HttpServletRequest request) {
        Admin a = SessionUtil.getUser(request, Admin.class);
        return userService.add(user, a.getId());
    }

    @POST
    @Path("/list")
    public PageInfo<User> list(PageParam param, @Context HttpServletRequest request) {
        Admin a = SessionUtil.getUser(request, Admin.class);
        return userService.list(param);
    }
}