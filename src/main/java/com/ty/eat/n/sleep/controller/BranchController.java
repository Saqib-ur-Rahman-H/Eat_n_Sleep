package com.ty.eat.n.sleep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.eat.n.sleep.dto.Branch;
import com.ty.eat.n.sleep.dto.Pg;
import com.ty.eat.n.sleep.dto.ResponseStructure;
import com.ty.eat.n.sleep.service.BranchService;

@RestController
public class BranchController {
	
	
	@Autowired
	private BranchService branchService;
	
	
	@PostMapping("branch")
	public ResponseEntity<ResponseStructure<Branch>> saveBranch(@RequestParam int pgId,@RequestBody Branch branch)
	{
		return branchService.saveBranch(pgId,branch);
		
	}
	
	
	
	@GetMapping("branch")
	public ResponseEntity<ResponseStructure<Branch>> getBranch(@RequestParam int id)
	{
		return  branchService.getBranch(id);
		
	}
	@PutMapping("branch")
	public  ResponseEntity<ResponseStructure<Branch>> updateBranch(@RequestParam int id,@RequestBody Branch branch)
	{
		return branchService.updatebranch(id, branch);
		
	}
	
	@DeleteMapping("branch")
	public  ResponseEntity<ResponseStructure<Boolean>> deleteBranch(@RequestParam int id)
	{
		return branchService.deleteBranch(id);
		
	}
	@GetMapping("allbranchs")
	public ResponseEntity<ResponseStructure<List<Branch>>> allBranches()
	{
		return branchService.getAllBranchs();
		
	}
	
		

}
