package com.mycompany.myapp.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.mycompany.myapp.dto.GasSensor;

@Component
public interface GasSensorDao {
	
	public List<GasSensor> selectByAll();
	public int insert(GasSensor gasSensor);
}
