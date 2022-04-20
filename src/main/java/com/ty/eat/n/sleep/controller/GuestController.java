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

import com.ty.eat.n.sleep.dto.Guest;
import com.ty.eat.n.sleep.dto.ResponseStructure;
import com.ty.eat.n.sleep.dto.Room;
import com.ty.eat.n.sleep.service.GuestService;

@RestController
public class GuestController {
	
	
	@Autowired
	private GuestService guestService;
	
	@PostMapping("guest")
	public ResponseEntity<ResponseStructure<Guest>> saveGuest(@RequestParam int rid,    @RequestBody Guest guest)
	{
		return guestService.saveGuest(rid,guest);
		
	}
	
	
	
	@GetMapping("guest")
	public ResponseEntity<ResponseStructure<Guest>> getGuest(@RequestParam int id)
	{
		return  guestService.getGuest(id);
		
	}
	@PutMapping("guest")
	public ResponseEntity<ResponseStructure<Guest>> updateGuest(@RequestParam int id,@RequestBody Guest guest)
	{
		return guestService.updateguest(id, guest);
		
	}
	
	@DeleteMapping("guest")
	public ResponseEntity<ResponseStructure<Boolean>> deleteGuest(@RequestParam int id)
	{
		return guestService.deleteGuest(id);
		
	}
	@GetMapping("allguests")
	public ResponseEntity<ResponseStructure<List<Guest>>> allGuests()
	{
		return guestService.getAllGuests();
		
	}
	@GetMapping("guestpayment")
	public ResponseEntity<ResponseStructure<Guest>> guestPaymet(@RequestParam int id,@RequestParam double d1)
	{
		return guestService.makePayment(id, d1);
		
	}
		
	
	
	

}
