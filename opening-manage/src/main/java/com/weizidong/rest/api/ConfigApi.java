package com.weizidong.rest.api;

import com.qcdl.rest.dto.QiniuDto;
import io.swagger.annotations.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 系统配置API接口
 *
 * @author 魏自东
 * @date 2018/2/7 9:31
 */
@Path("/system")
@Api("系统配置")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApiImplicitParams({@ApiImplicitParam(name = "ACCESS_TOKEN", value = "接口调用凭证", dataType = "string", required = true, paramType = "query")})
@ApiResponses({@ApiResponse(code = 600, message = "登录状态失效")})
public class ConfigApi extends com.qcdl.rest.api.ConfigApi {
}
