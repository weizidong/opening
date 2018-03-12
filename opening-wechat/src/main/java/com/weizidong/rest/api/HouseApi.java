package com.weizidong.rest.api;

import com.weizidong.model.entity.House;
import com.weizidong.model.entity.User;
import com.weizidong.rest.dto.HouseDto;
import com.weizidong.service.HouseService;
import org.restful.api.session.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

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
    @Path("/find/{buildingNo}")
    public List<House> find(@PathParam("buildingNo") Integer buildingNo) {
        return houseService.find(buildingNo);
    }

    @GET
    @Path("/getBuilding")
    public List<Integer> getBuilding() {
        return houseService.getBuilding();
    }

    @PUT
    @Path("/purchase/{houseId}")
    public void purchase(@PathParam("houseId") Integer houseId, @Context HttpServletRequest request) {
        User u = SessionUtil.getUser(request, User.class);
        houseService.purchase(houseId, u);
    }

    @GET
    @Path("/getFavorite")
    public List<HouseDto> getFavorite(@Context HttpServletRequest request){
        User u = SessionUtil.getUser(request, User.class);
        return houseService.getFavorite(u.getId());
    }
}
