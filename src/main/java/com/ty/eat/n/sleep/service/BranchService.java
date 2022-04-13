package com.ty.eat.n.sleep.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.eat.n.sleep.dao.BranchDao;
import com.ty.eat.n.sleep.dto.Branch;
import com.ty.eat.n.sleep.dto.ResponseStructure;
import com.ty.eat.n.sleep.dto.Room;
import com.ty.eat.n.sleep.dto.User;

@Service
public class BranchService {
	@Autowired
	private BranchDao branchDao;

	public ResponseStructure<Branch> saveBranch(Branch branch) {
		if (branch != null) {
			ResponseStructure<Branch> responseStructure = new ResponseStructure<Branch>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Sucessfuly Saved");
			responseStructure.setData(branchDao.saveBranch(branch));
			return responseStructure;
		}
		ResponseStructure<Branch> responseStructure = new ResponseStructure<Branch>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Not Saved");
		responseStructure.setData(null);
		return responseStructure;
	}

	public ResponseStructure<Branch> getBranch(int id) {
		if (branchDao.getBranchById(id) != null) {
			ResponseStructure<Branch> responseStructure = new ResponseStructure<Branch>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Sucessful");
			responseStructure.setData(branchDao.getBranchById(id));
			return responseStructure;
		} else {
			ResponseStructure<Branch> responseStructure = new ResponseStructure<Branch>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not Saved");
			responseStructure.setData(null);
			return responseStructure;
		}
	}

	public ResponseStructure<Boolean> deleteBranch(int id) {
		if (branchDao.deleteBranchById(id)) {
			ResponseStructure<Boolean> responseStructure = new ResponseStructure<Boolean>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Sucessful");
			responseStructure.setData(branchDao.deleteBranchById(id));
			return responseStructure;
		} else {
			ResponseStructure<Boolean> responseStructure = new ResponseStructure<Boolean>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not Deleted");
			responseStructure.setData(null);
			return responseStructure;
		}
	}

	public ResponseStructure<List<Branch>> getAllBranchs() {
		if (branchDao.getAllBranchs().size() > 0) {
			ResponseStructure<List<Branch>> responseStructure = new ResponseStructure<List<Branch>>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Sucessful");
			responseStructure.setData(branchDao.getAllBranchs());
			return responseStructure;
		} else {
			ResponseStructure<List<Branch>> responseStructure = new ResponseStructure<List<Branch>>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Server Error");
			responseStructure.setData(null);
			return responseStructure;
		}
	}

	public ResponseStructure<Branch> updatebranch(int id, Branch branch) {
		if (branchDao.updateBranch(id, branch) != null) {
			ResponseStructure<Branch> responseStructure = new ResponseStructure<Branch>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Updated Sucessfully");
			responseStructure.setData(branchDao.updateBranch(id, branch));
			return responseStructure;
		} else {
			ResponseStructure<Branch> responseStructure = new ResponseStructure<Branch>();
			responseStructure.setStatus(HttpStatus.NOT_MODIFIED.value());
			responseStructure.setMessage("Not Updated");
			responseStructure.setData(null);
			return responseStructure;
		}
	}
}
