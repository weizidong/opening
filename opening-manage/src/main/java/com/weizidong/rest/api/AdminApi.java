package com.weizidong.rest.api;

import io.swagger.annotations.Api;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
}
