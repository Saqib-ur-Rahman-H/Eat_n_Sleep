package com.ty.eat.n.sleep.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.eat.n.sleep.dao.BranchDao;
import com.ty.eat.n.sleep.dao.PgDao;
import com.ty.eat.n.sleep.dto.Branch;
import com.ty.eat.n.sleep.dto.Pg;
import com.ty.eat.n.sleep.dto.ResponseStructure;
import com.ty.eat.n.sleep.dto.Room;
import com.ty.eat.n.sleep.dto.User;

@Service
public class BranchService {
	@Autowired
	private BranchDao branchDao;
	@Autowired
	private PgDao pgDao;

	public ResponseEntity<ResponseStructure<Branch>> saveBranch(int pgId, Branch branch) {
		if (branch != null) {

			ResponseStructure<Branch> responseStructure = new ResponseStructure<Branch>();
			Pg pg = pgDao.getPg(pgId);
			List<Branch> pgs = pg.getBranches();
			if (pg != null) {
				branch.setPgName(pg.getName());
				pgs.add(branch);
				pg.setBranches(pgs);
				responseStructure.setStatus(HttpStatus.OK.value());
				responseStructure.setMessage("Sucessfuly Saved");
				responseStructure.setData(branchDao.saveBranch(branch));
				ResponseEntity<ResponseStructure<Branch>> responseEntity = new ResponseEntity<ResponseStructure<Branch>>(
						responseStructure, HttpStatus.OK);
				return responseEntity;
			}

		} else {
			ResponseStructure<Branch> responseStructure = new ResponseStructure<Branch>();
			ResponseEntity<ResponseStructure<Branch>> entity = new ResponseEntity<ResponseStructure<Branch>>(
					responseStructure, HttpStatus.NOT_FOUND);
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not Saved");
			responseStructure.setData(null);
			return entity;
		}
		return null;
	}

	public ResponseEntity<ResponseStructure<Branch>> getBranch(int id) {
		if (branchDao.getBranchById(id) != null) {

			ResponseStructure<Branch> responseStructure = new ResponseStructure<Branch>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Sucessful");
			responseStructure.setData(branchDao.getBranchById(id));
			ResponseEntity<ResponseStructure<Branch>> entity = new ResponseEntity<ResponseStructure<Branch>>(
					responseStructure, HttpStatus.OK);
			return entity;
		} else {

			ResponseStructure<Branch> responseStructure = new ResponseStructure<Branch>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not Saved");
			responseStructure.setData(null);
			ResponseEntity<ResponseStructure<Branch>> entity = new ResponseEntity<ResponseStructure<Branch>>(
					responseStructure, HttpStatus.NOT_FOUND);
			return entity;
		}
	}

	public ResponseEntity<ResponseStructure<Boolean>> deleteBranch(int id) {
		if (branchDao.deleteBranchById(id)) {
			ResponseStructure<Boolean> responseStructure = new ResponseStructure<Boolean>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Sucessful");
			responseStructure.setData(branchDao.deleteBranchById(id));
			ResponseEntity<ResponseStructure<Boolean>> responseEntity = new ResponseEntity<ResponseStructure<Boolean>>(
					responseStructure, HttpStatus.OK);
			return responseEntity;
		} else {
			ResponseStructure<Boolean> responseStructure = new ResponseStructure<Boolean>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not Deleted");
			responseStructure.setData(null);
			ResponseEntity<ResponseStructure<Boolean>> responseEntity = new ResponseEntity<ResponseStructure<Boolean>>(
					responseStructure, HttpStatus.NOT_FOUND);
			return responseEntity;
		}
	}

	public ResponseEntity<ResponseStructure<List<Branch>>> getAllBranchs() {
		if (branchDao.getAllBranchs().size() > 0) {
			ResponseStructure<List<Branch>> responseStructure = new ResponseStructure<List<Branch>>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Sucessful");
			responseStructure.setData(branchDao.getAllBranchs());
			ResponseEntity<ResponseStructure<List<Branch>>> responseEntity = new ResponseEntity<ResponseStructure<List<Branch>>>(
					responseStructure, HttpStatus.OK);
			return responseEntity;
		} else {
			ResponseStructure<List<Branch>> responseStructure = new ResponseStructure<List<Branch>>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Server Error");
			responseStructure.setData(null);
			ResponseEntity<ResponseStructure<List<Branch>>> responseEntity = new ResponseEntity<ResponseStructure<List<Branch>>>(
					responseStructure, HttpStatus.NOT_FOUND);
			return responseEntity;
		}
	}

	public ResponseEntity<ResponseStructure<Branch>> updatebranch(int id, Branch branch) {
		if (branchDao.updateBranch(id, branch) != null) {
			ResponseStructure<Branch> responseStructure = new ResponseStructure<Branch>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Updated Sucessfully");
			responseStructure.setData(branchDao.updateBranch(id, branch));
			ResponseEntity<ResponseStructure<Branch>> responseEntity = new ResponseEntity<ResponseStructure<Branch>>(
					responseStructure, HttpStatus.OK);
			return responseEntity;
		} else {
			ResponseStructure<Branch> responseStructure = new ResponseStructure<Branch>();
			responseStructure.setStatus(HttpStatus.NOT_MODIFIED.value());
			responseStructure.setMessage("Not Updated");
			responseStructure.setData(null);
			ResponseEntity<ResponseStructure<Branch>> responseEntity = new ResponseEntity<ResponseStructure<Branch>>(
					responseStructure, HttpStatus.NOT_FOUND);
			return responseEntity;
		}
	}

	public ByteArrayInputStream load() {
		ByteArrayInputStream in = branchDao.load();
		return in;
	}
}
