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

import com.ty.eat.n.sleep.dto.Pg;
import com.ty.eat.n.sleep.dto.ResponseStructure;
import com.ty.eat.n.sleep.service.PgService;

@RestController
public class PgContorller {
	
	@Autowired
	private PgService pgService;
	
	@PostMapping("pg")
	public ResponseEntity<ResponseStructure<Pg>> savePg(@RequestBody Pg pg)
	{
		return pgService.savePg(pg);
		
	}
	@GetMapping("pg")
	public ResponseEntity<ResponseStructure<Pg>> getPg(@RequestParam int id)
	{
		
		return  pgService.getPg(id);
		
	}
	@PutMapping("pg")
	public ResponseEntity<ResponseStructure<Pg>> updatePg(@RequestParam int id,@RequestBody Pg pg)
	{
		return pgService.updatePg(id, pg);
		
	}
	
	@DeleteMapping("pg")
	public ResponseEntity<ResponseStructure<Boolean>> deletePg(@RequestParam int id)
	{
		return pgService.deletePg(id);
		
	}
	@GetMapping("allpgs")
	public  ResponseEntity<ResponseStructure<List<Pg>>> allPgs()
	{
		return pgService.getAllPgs();
		
	}
	
	
	

}
