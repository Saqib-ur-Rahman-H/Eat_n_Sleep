package com.ty.eat.n.sleep.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.eat.n.sleep.dao.PgDao;
import com.ty.eat.n.sleep.dto.Pg;

@Service
public class PgService {
	@Autowired
	private PgDao pgDao;

	public Pg savePg(Pg pg) {
		return pgDao.savePg(pg);
	}

	public Pg getPg(int id) {
		if (pgDao.getPg(id) != null) {
			return pgDao.getPg(id);
		} else {
			return null;
		}
	}

	public boolean deletePg(int id) {
		if (pgDao.deletePg(id)) {
			return true;
		} else {
			return false;
		}
	}

	public List<Pg> getAllPgs() {
		if (pgDao.getAllPgs().size() > 0) {
			return pgDao.getAllPgs();
		} else {
			return null;
		}
	}

	public Pg updatePg(int id, Pg pg) {
		if (pgDao.updatePg(id, pg) != null) {
			return pgDao.updatePg(id, pg);
		} else {
			return null;
		}
	}
}
