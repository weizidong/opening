package com.weizidong.rest.api;

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
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConfigApi extends com.qcdl.rest.api.ConfigApi {
}
