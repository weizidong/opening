package com.weizidong.rest.api;

import com.github.pagehelper.PageInfo;
import com.qcdl.model.entity.Admin;
import com.qcdl.model.param.PageParam;
import com.weizidong.model.entity.User;
import com.weizidong.rest.dto.UserHouseDto;
import com.weizidong.service.UserService;
import org.restful.api.filter.authority.Authority;
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

    @GET
    @Path("/get")
    public User get(@Context HttpServletRequest request) {
        return SessionUtil.getUser(request, User.class);
    }

    @GET
    @Path("/get/{id}")
    public User getById(@PathParam("id") Integer id) {
        return userService.getById(id);
    }

    /**
     * 创建用户，并获取临时二维码
     *
     * @param user    用户
     * @param request request
     * @return 二维码地址
     */
    @POST
    @Path("/create")
    public String create(User user, @Context HttpServletRequest request) {
        return userService.create(user);
    }

    @POST
    @Path("/bind")
    public void bind(User user, @Context HttpServletRequest request) {
        User db = SessionUtil.getUser(request, User.class);
        db.setIdNumber(user.getIdNumber());
        db.setId(user.getId());
        userService.bind(db);
    }


    @POST
    @Path("/add")
    @Authority({"user"})
    public String add(User user, @Context HttpServletRequest request) {
        Admin a = SessionUtil.getUser(request, Admin.class);
        return userService.add(user, a.getId());
    }

    @POST
    @Path("/user-list")
    @Authority({"user-list"})
    public PageInfo<UserHouseDto> userList(PageParam param) {
        return userService.userList(param);
    }

    @POST
    @Path("/export-user-list")
    @Authority({"user-list"})
    public String exportUserList() {
        return userService.exportUserList();
    }

    @POST
    @Path("/list")
    @Authority({"user-admin"})
    public PageInfo<User> list(PageParam param) {
        return userService.list(param);
    }

    @PUT
    @Path("/update")
    @Authority({"user-admin"})
    public void update(User param) {
        userService.update(param);
    }

    @DELETE
    @Path("/delete/{id}")
    @Authority({"user-admin"})
    public void delete(@PathParam("id") Integer id) {
        userService.delete(id);
    }
}
