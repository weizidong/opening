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
import java.util.HashMap;
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
    public Map<String, Object> purchase(@PathParam("houseId") Integer houseId, @Context HttpServletRequest request) {
        User u = SessionUtil.getUser(request, User.class);
        return houseService.purchase(houseId, u);
    }

    @GET
    @Path("/getFavorite")
    public List<HouseDto> getFavorite(@Context HttpServletRequest request) {
        User u = SessionUtil.getUser(request, User.class);
        return houseService.getFavorite(u.getId());
    }

    @PUT
    @Path("/favorite/{houseId}")
    public void favorite(@PathParam("houseId") Integer houseId, @Context HttpServletRequest request) {
        User u = SessionUtil.getUser(request, User.class);
        houseService.favorite(u.getId(), houseId);
    }

    @PUT
    @Path("/delFavorite/{houseId}")
    public void delFavorite(@PathParam("houseId") Integer houseId, @Context HttpServletRequest request) {
        User u = SessionUtil.getUser(request, User.class);
        houseService.delFavorite(houseId, u.getId());
    }

    @GET
    @Path("/getMine")
    public Map<String, Object> getMine(@Context HttpServletRequest request) {
        User u = SessionUtil.getUser(request, User.class);
        House h = houseService.getMine(u.getId());
        Map<String, Object> res = new HashMap<>(16);
        res.put("user", u);
        res.put("house", h);
        return res;
    }
}
