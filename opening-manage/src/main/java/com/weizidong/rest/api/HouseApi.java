package com.weizidong.rest.api;

import com.github.pagehelper.PageInfo;
import com.weizidong.model.entity.House;
import com.weizidong.model.entity.HouseType;
import com.weizidong.service.HouseService;
import org.restful.api.filter.authority.Authority;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * 房源接口
 *
 * @author 魏自东
 * @date 2018/2/7 9:31
 */
@Path("/house")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HouseApi {
    @Autowired
    private HouseService houseService;

    @GET
    @Path("/all")
    public Map<Integer, Map<Integer, List<House>>> all() {
        return houseService.all();
    }


    @POST
    @Path("/addType")
    @Authority({"houseType"})
    public void addType(HouseType type) {
        houseService.addType(type);
    }
    
    @GET
    @Path("/listType")
    @Authority({"houseType"})
    public PageInfo<HouseType> listType() {
        return houseService.listType();
    }

    @PUT
    @Path("/updateType")
    @Authority({"houseType"})
    public void updateType(HouseType param) {
        houseService.updateType(param);
    }

    @DELETE
    @Path("/deleteType/{id}")
    @Authority({"houseType"})
    public void deleteType(@PathParam("id") Integer id) {
        houseService.deleteType(id);
    }
}
