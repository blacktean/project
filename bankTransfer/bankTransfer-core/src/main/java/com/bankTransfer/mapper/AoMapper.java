package com.bankTransfer.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bankTransfer.pojo.Ao_accout_opening;
@Repository
public interface AoMapper {

	List<Ao_accout_opening> queryAll();

}
