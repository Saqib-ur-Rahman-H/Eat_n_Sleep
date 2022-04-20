package com.ty.eat.n.sleep.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.eat.n.sleep.dao.PgDao;
import com.ty.eat.n.sleep.dto.Pg;
import com.ty.eat.n.sleep.dto.ResponseStructure;


@Service
public class PgService {
	@Autowired
	private PgDao pgDao;

	public ResponseEntity<ResponseStructure<Pg>> savePg(Pg pg) {
		Pg p=pgDao.savePg(pg);
		if (p != null) { 
			 
			
			ResponseStructure<Pg> responseStructure = new ResponseStructure<Pg>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Saved Sucessfully");
			responseStructure.setData(p);
			ResponseEntity<ResponseStructure<Pg>> responseEntity=new ResponseEntity<ResponseStructure<Pg>>(responseStructure,HttpStatus.OK); 
			return responseEntity;
		} else {
  
			ResponseStructure<Pg> responseStructure = new ResponseStructure<Pg>();
			responseStructure.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			responseStructure.setMessage("Saved Sucessfully");
			responseStructure.setData(p);
			ResponseEntity<ResponseStructure<Pg>> responseEntity=new ResponseEntity<ResponseStructure<Pg>>(responseStructure,HttpStatus.NOT_FOUND);
			return responseEntity;
		}

	}

	public ResponseEntity<ResponseStructure<Pg>> getPg(int id) {
		if (pgDao.getPg(id) != null) {
  
			ResponseStructure<Pg> responseStructure = new ResponseStructure<Pg>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Sucessfully");
			responseStructure.setData(pgDao.getPg(id));
			ResponseEntity<ResponseStructure<Pg>> responseEntity=new ResponseEntity<ResponseStructure<Pg>>(responseStructure,HttpStatus.OK);
			return responseEntity;
		} else {
 
			ResponseStructure<Pg> responseStructure = new ResponseStructure<Pg>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Id Not Found");
			responseStructure.setData(null);
			ResponseEntity<ResponseStructure<Pg>> responseEntity=new ResponseEntity<ResponseStructure<Pg>>(responseStructure,HttpStatus.NOT_FOUND); 
			return responseEntity;
		}
	}

	public ResponseEntity<ResponseStructure<Boolean>> deletePg(int id) {
		if (pgDao.deletePg(id)) {

			ResponseStructure<Boolean> responseStructure = new ResponseStructure<Boolean>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Deleted Sucessfully");
			responseStructure.setData(true);
			ResponseEntity<ResponseStructure<Boolean>> responseEntity=new ResponseEntity<ResponseStructure<Boolean>>(responseStructure,HttpStatus.OK);  
			return responseEntity;
		} else {
	 
			ResponseStructure<Boolean> responseStructure = new ResponseStructure<Boolean>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("not deleted");
			responseStructure.setData(false);
			ResponseEntity<ResponseStructure<Boolean>> responseEntity=new ResponseEntity<ResponseStructure<Boolean>>(responseStructure,HttpStatus.NOT_FOUND); 
			return responseEntity;
		}
	}

	public  ResponseEntity<ResponseStructure<List<Pg>>>  getAllPgs() {
		if (pgDao.getAllPgs().size() > 0) {

			ResponseStructure<List<Pg>> responseStructure = new ResponseStructure<List<Pg>>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Deleted Sucessfully");
			responseStructure.setData(pgDao.getAllPgs());
			ResponseEntity<ResponseStructure<List<Pg>>> responseEntity=new ResponseEntity<ResponseStructure<List<Pg>>>(responseStructure,HttpStatus.OK); 
			return responseEntity;
		} else {
		 
			ResponseStructure<List<Pg>> responseStructure = new ResponseStructure<List<Pg>>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("no pg list");
			responseStructure.setData(null);
			ResponseEntity<ResponseStructure<List<Pg>>> responseEntity=new ResponseEntity<ResponseStructure<List<Pg>>>(responseStructure,HttpStatus.NOT_FOUND);
			return responseEntity;
		}
	}

	public ResponseEntity<ResponseStructure<Pg>> updatePg(int id, Pg pg) {
		if (pgDao.updatePg(id, pg) != null) {

			ResponseStructure<Pg> responseStructure = new ResponseStructure<Pg>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Updated Sucessfully");
			responseStructure.setData(pgDao.updatePg(id, pg));
			ResponseEntity<ResponseStructure<Pg>> responseEntity=new ResponseEntity<ResponseStructure<Pg>>(responseStructure,HttpStatus.OK); 
			return responseEntity;

		} else {

			ResponseStructure<Pg> responseStructure = new ResponseStructure<Pg>();
			responseStructure.setStatus(HttpStatus.NOT_MODIFIED.value());
			responseStructure.setMessage("Not Updated ");
			responseStructure.setData(null);
			ResponseEntity<ResponseStructure<Pg>> responseEntity=new ResponseEntity<ResponseStructure<Pg>>(responseStructure,HttpStatus.NOT_FOUND); 
			return responseEntity;
		}
	}
	public ByteArrayInputStream load() {
		ByteArrayInputStream in = pgDao.load();
		return in;
	}
}
