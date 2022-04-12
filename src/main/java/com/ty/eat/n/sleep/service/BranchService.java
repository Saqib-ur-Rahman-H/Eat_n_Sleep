package com.ty.eat.n.sleep.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.eat.n.sleep.dao.BranchDao;
import com.ty.eat.n.sleep.dto.Branch;

@Service
public class BranchService {
	@Autowired
	private BranchDao branchDao;

	public Branch saveBranch(Branch branch) {
		return branchDao.saveBranch(branch);
	}

	public Branch getBranch(int id) {
		if (branchDao.getBranchById(id) != null) {
			return branchDao.getBranchById(id);
		} else {
			return null;
		}
	}

	public boolean deleteBranch(int id) {
		if (branchDao.deleteBranchById(id)) {
			return true;
		} else {
			return false;
		}
	}

	public List<Branch> getAllBranchs() {
		if (branchDao.getAllBranchs().size() > 0) {
			return branchDao.getAllBranchs();
		} else {
			return null;
		}
	}

	public Branch updatebranch(int id, Branch branch) {
		if (branchDao.updateBranch(id, branch) != null) {
			return branchDao.updateBranch(id, branch);
		} else {
			return null;
		}
	}
}
