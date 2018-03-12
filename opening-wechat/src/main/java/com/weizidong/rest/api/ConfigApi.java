package com.weizidong.rest.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.ws.rs.*;
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
    @POST
    @Path("/put/{key}")
    @ApiOperation(value = "保存配置", notes = "权限：system")
    @Override
    public void put(@ApiParam(value = "配置的键", required = true) @PathParam("key") String key, @ApiParam(value = "要保存的配置", required = true) Object val) {
        super.put(key, val);
    }
}
