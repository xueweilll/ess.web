package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.hibernate.entity.Instrument;
import com.service.InstrumentService;

@Service
public class InstrumentServiceImpl implements InstrumentService {

	@Autowired
	private BaseDao baseDao;
	@Override
	public void InsertInstrument(Instrument instrument) {
		baseDao.save(instrument);
	}

}
