package com.ty.eat.n.sleep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.eat.n.sleep.dto.Branch;
import com.ty.eat.n.sleep.dto.Pg;
import com.ty.eat.n.sleep.dto.ResponseStructure;
import com.ty.eat.n.sleep.service.BranchService;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api/excel")
public class BranchController {
	
	
	@Autowired
	private BranchService branchService;
	
	
	@PostMapping("branch")
	public ResponseEntity<ResponseStructure<Branch>> saveBranch(@RequestParam int Id,@RequestBody Branch branch)
	{
		return branchService.saveBranch(Id,branch);
		
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
	
	@GetMapping("/downloadbranches")
	public ResponseEntity<Resource> getFile() {
		String filename = "branchesExcel.xlsx";
		InputStreamResource file = new InputStreamResource(branchService.load());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
	}

}
