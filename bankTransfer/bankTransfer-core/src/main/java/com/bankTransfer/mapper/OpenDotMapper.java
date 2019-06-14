package com.bankTransfer.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bankTransfer.pojo.OpenDot;

@Mapper
public interface OpenDotMapper {
	public OpenDot queryOpenDotByDot_number(@Param("dot_number")int dot_number);
}
