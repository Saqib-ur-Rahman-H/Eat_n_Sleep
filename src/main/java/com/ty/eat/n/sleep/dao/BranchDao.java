package com.ty.eat.n.sleep.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.eat.n.sleep.dto.Branch;
import com.ty.eat.n.sleep.dto.Room;
import com.ty.eat.n.sleep.repository.BranchRepository;

@Repository
public class BranchDao {
	@Autowired
	private BranchRepository branchRepository;

	public Branch saveBranch(Branch branch) {
			branchRepository.save(branch);
			return branch;
	}

	public Branch getBranchById(int id) {

		Optional<Branch> optional = branchRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		return optional.get();

	}

	public List<Branch> getAllBranchs() {
		return branchRepository.findAll();
	}

	public boolean deleteBranchById(int id) {
		Branch branch = getBranchById(id);
		if (branch != null) {
			branchRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public Branch updateBranch(int id, Branch branch) {
		Branch existingBranch = getBranchById(id);
		if (existingBranch != null) {
			branch.setId(id);
			branchRepository.save(branch);
			return branch;

		}
		return null;
	}

}
