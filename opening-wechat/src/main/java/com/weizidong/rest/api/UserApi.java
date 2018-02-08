package com.weizidong.rest.api;

import com.weizidong.model.entity.User;
import com.weizidong.service.UserService;
import org.restful.api.session.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

    @GET
    @Path("/get")
    public User get(@Context HttpServletRequest request) {
        return SessionUtil.getUser(request, User.class);
    }
}
