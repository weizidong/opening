package com.weizidong.rest.api;

import com.qcdl.model.entity.Admin;
import com.qcdl.model.enums.LogType;
import com.qcdl.model.enums.Succeed;
import io.swagger.annotations.*;
import org.restful.api.session.SessionUtil;
import org.wzd.framwork.utils.IPUtil;
import org.wzd.framwork.utils.ThreadPoolUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * 管理员API接口
 *
 * @author 魏自东
 * @date 2018/2/7 9:29
 */
@Path("/admin")
@Api("管理员")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminApi extends com.qcdl.rest.api.AdminApi {
    @Override
    @GET
    @Path("/logout")
    @ApiOperation(value = "登出")
    @ApiImplicitParams({@ApiImplicitParam(name = "ACCESS_TOKEN", value = "接口调用凭证", dataType = "string", required = true, paramType = "query")})
    @ApiResponses({@ApiResponse(code = 600, message = "登录状态失效")})
    public Boolean login(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        Admin a = SessionUtil.getUser(request, Admin.class);
        SessionUtil.delete(request, response);
        return Boolean.TRUE;
    }
}
