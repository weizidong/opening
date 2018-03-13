package com.weizidong.model.mapper;

import com.weizidong.model.entity.Favorite;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface FavoriteMapper extends Mapper<Favorite>, MySqlMapper<Favorite> {
}