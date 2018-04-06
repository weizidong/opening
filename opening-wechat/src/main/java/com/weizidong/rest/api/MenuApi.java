package com.weizidong.rest.api;

import io.swagger.annotations.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 权限菜单API接口
 *
 * @author 魏自东
 * @date 2018/2/7 9:35
 */
@Path("/menu")
@Api("权限菜单")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApiImplicitParams({@ApiImplicitParam(name = "ACCESS_TOKEN", value = "接口调用凭证", dataType = "string", required = true, paramType = "query")})
@ApiResponses({@ApiResponse(code = 600, message = "登录状态失效"), @ApiResponse(code = 401, message = "权限不足")})
public class MenuApi extends com.qcdl.rest.api.MenuApi {
}
