package com.ty.eat.n.sleep.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.eat.n.sleep.dao.PgDao;
import com.ty.eat.n.sleep.dto.Pg;
import com.ty.eat.n.sleep.dto.ResponseStructure;


@Service
public class PgService {
	@Autowired
	private PgDao pgDao;

	public ResponseStructure<Pg> savePg(Pg pg) {
		if (pg != null) {
			ResponseStructure<Pg> responseStructure = new ResponseStructure<Pg>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Saved Sucessfully");
			responseStructure.setData(pg);
			return responseStructure;
		} else {
			ResponseStructure<Pg> responseStructure = new ResponseStructure<Pg>();
			responseStructure.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			responseStructure.setMessage("Saved Sucessfully");
			responseStructure.setData(pg);
			return responseStructure;
		}

	}

	public ResponseStructure getPg(int id) {
		if (pgDao.getPg(id) != null) {
			ResponseStructure<Pg> responseStructure = new ResponseStructure<Pg>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage(" Sucessfully");
			responseStructure.setData(pgDao.getPg(id));
			return responseStructure;
		} else {
			ResponseStructure<Pg> responseStructure = new ResponseStructure<Pg>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Id Not Found");
			responseStructure.setData(null);
			return responseStructure;
		}
	}

	public ResponseStructure deletePg(int id) {
		if (pgDao.deletePg(id)) {
			ResponseStructure<Boolean> responseStructure = new ResponseStructure<Boolean>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Deleted Sucessfully");
			responseStructure.setData(true);
			return responseStructure;
		} else {
			ResponseStructure<Boolean> responseStructure = new ResponseStructure<Boolean>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Deleted Sucessfully");
			responseStructure.setData(false);
			return responseStructure;
		}
	}

	public ResponseStructure<List<Pg>> getAllPgs() {
		if (pgDao.getAllPgs().size() > 0) {
			ResponseStructure<List<Pg>> responseStructure = new ResponseStructure<List<Pg>>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Deleted Sucessfully");
			responseStructure.setData(pgDao.getAllPgs());
			return responseStructure;
		} else {
			ResponseStructure<List<Pg>> responseStructure = new ResponseStructure<List<Pg>>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Deleted Sucessfully");
			responseStructure.setData(null);
			return responseStructure;
		}
	}

	public ResponseStructure<Pg> updatePg(int id, Pg pg) {
		if (pgDao.updatePg(id, pg) != null) {
			ResponseStructure<Pg> responseStructure = new ResponseStructure<Pg>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Updated Sucessfully");
			responseStructure.setData(pgDao.updatePg(id, pg));
			return responseStructure;

		} else {
			ResponseStructure<Pg> responseStructure = new ResponseStructure<Pg>();
			responseStructure.setStatus(HttpStatus.NOT_MODIFIED.value());
			responseStructure.setMessage("Not Updated ");
			responseStructure.setData(null);
			return responseStructure;
		}
	}
}
