package com.ty.eat.n.sleep.service;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ty.eat.n.sleep.dao.GuestDao;
import com.ty.eat.n.sleep.dao.RoomDao;
import com.ty.eat.n.sleep.dto.Branch;
import com.ty.eat.n.sleep.dto.Guest;
import com.ty.eat.n.sleep.dto.ResponseStructure;
import com.ty.eat.n.sleep.dto.Room;
import com.ty.eat.n.sleep.dto.User;

@Service
public class GuestService {
	
	@Autowired
	private GuestDao guestDao;
	@Autowired
	private RoomDao roomDao;
	@Autowired
	MailService service;
	
	public ResponseEntity<ResponseStructure<Guest>> saveGuest(int roomid, Guest guest) {
		Room room = roomDao.getRoomById(roomid);
		ResponseEntity<ResponseStructure<Guest>> responseEntityNo=new ResponseEntity<ResponseStructure<Guest>>(HttpStatus.OK); 
		if (guestDao.getGuest(guest.getId()) != null) {
			ResponseStructure<Guest> responseStructure = new ResponseStructure<Guest>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not Found");
			responseStructure.setData(null);
			return responseEntityNo;
			
		} else {
			List<Guest> guests = room.getGuests();
			if (guests.size() < room.getSize()) {
				guest.setTotalAmount(room.getCost() / room.getSize());
				guests.add(guest);
				room.setGuests(guests);
				roomDao.updateRoom(guest.getRoom().getId(), guest.getRoom().getBranch().getId(), room);
				service.sendSimpleEmail(guest.getEmail(), "Hiiii "+guest.getName()+" thanks for choosing our pg Eat-N-Sleep hope u will have a good time hear\n Your wifi password is"+guest.getRoom().getBranch().getWifiPassword()+ "/n your Room No is "+guest.getRoom()+" Your CheckIn date: "+ guest.getJoiningDate()+"/n Your CheckIn date: "+guest.getCheckoutDate(), "Welcome To Eat_N_Sleep");
				ResponseStructure<Guest> responseStructure = new ResponseStructure<Guest>();
				responseStructure.setStatus(HttpStatus.OK.value());
				responseStructure.setMessage("Saved Sucessfuly");
				responseStructure.setData(guestDao.saveGuest(roomid, guest));
				ResponseEntity<ResponseStructure<Guest>> responseEntity=new ResponseEntity<ResponseStructure<Guest>>(responseStructure,HttpStatus.OK); 
				return responseEntity;
			}

			else {
				Branch brabch = room.getBranch();
				List<Room> avlRooms = brabch.getAvailableRooms();
				avlRooms.remove(room);
				List<Room> takenRooms = brabch.getBookedRooms();
				takenRooms.add(room);
				ResponseStructure<Guest> responseStructure = new ResponseStructure<Guest>();
				responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
				responseStructure.setMessage(" rooms is not available full");
				responseStructure.setData(null);
				ResponseEntity<ResponseStructure<Guest>> responseEntity=new ResponseEntity<ResponseStructure<Guest>>(responseStructure,HttpStatus.NOT_FOUND); 
				return responseEntity;
			}
		}

	}

	public ResponseEntity<ResponseStructure<Guest>> getGuest(int id) {
		if (guestDao.getGuest(id) != null) {
			ResponseStructure<Guest> responseStructure = new ResponseStructure<Guest>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage(" Sucessfuly");
			responseStructure.setData(guestDao.getGuest(id));
			ResponseEntity<ResponseStructure<Guest>> responseEntity=new ResponseEntity<ResponseStructure<Guest>>(responseStructure,HttpStatus.OK); 
			return responseEntity;
		} else {
			ResponseStructure<Guest> responseStructure = new ResponseStructure<Guest>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not Found");
			responseStructure.setData(null);
			ResponseEntity<ResponseStructure<Guest>> responseEntity=new ResponseEntity<ResponseStructure<Guest>>(responseStructure,HttpStatus.NOT_FOUND); 
			return responseEntity;
		}
	}

	public ResponseEntity<ResponseStructure<Boolean>> deleteGuest(int id) {

		if (guestDao.getGuest(id)!=null) {
			Guest guest = guestDao.getGuest(id);
			Room room = guest.getRoom();
			List<Guest> guests = room.getGuests();
			if (guests.size() == room.getSize()) {
				Branch branch = room.getBranch();
				List<Room> bookedRooms = branch.getBookedRooms();
				bookedRooms.remove(room);
				List<Room> avlRooms = branch.getAvailableRooms();
				avlRooms.add(room);
			}
			ResponseStructure<Boolean> responseStructure = new ResponseStructure<Boolean>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage(" Sucessfuly Deleted");
			responseStructure.setData(guestDao.deleteGuest(id));
			ResponseEntity<ResponseStructure<Boolean>> responseEntity=new ResponseEntity<ResponseStructure<Boolean>>(responseStructure,HttpStatus.OK); 
			return responseEntity;
		} else {
			ResponseStructure<Boolean> responseStructure = new ResponseStructure<Boolean>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not Deleted");
			responseStructure.setData(null);
			ResponseEntity<ResponseStructure<Boolean>> responseEntity=new ResponseEntity<ResponseStructure<Boolean>>(responseStructure,HttpStatus.NOT_FOUND); 
			return responseEntity;
		}
	}

	public ResponseEntity<ResponseStructure<List<Guest>>> getAllGuests() {
		if (guestDao.getAllGuests().size() > 0) {
			ResponseStructure<List<Guest>> responseStructure = new ResponseStructure<List<Guest>>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage(" Sucessfuly");
			responseStructure.setData(guestDao.getAllGuests());
			ResponseEntity<ResponseStructure<List<Guest>>> responseEntity=new ResponseEntity<ResponseStructure<List<Guest>>>(responseStructure,HttpStatus.OK); 
			return responseEntity;
		} else {
			ResponseStructure<List<Guest>> responseStructure = new ResponseStructure<List<Guest>>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage(" Failed");
			responseStructure.setData(null);
			ResponseEntity<ResponseStructure<List<Guest>>> responseEntity=new ResponseEntity<ResponseStructure<List<Guest>>>(responseStructure,HttpStatus.NOT_FOUND); 
			return responseEntity;
		}
	}

	public ResponseEntity<ResponseStructure<Guest>> updateguest(int id, Guest guest) {
		if (guestDao.updateGuest(id, guest) != null) {
			ResponseStructure<Guest> responseStructure = new ResponseStructure<Guest>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage(" Sucessfuly Updated ");
			responseStructure.setData(guestDao.updateGuest(id, guest));
			ResponseEntity<ResponseStructure<Guest>> responseEntity=new ResponseEntity<ResponseStructure<Guest>>(responseStructure,HttpStatus.OK); 
			return responseEntity;
		} else {
			ResponseStructure<Guest> responseStructure = new ResponseStructure<Guest>();
			responseStructure.setStatus(HttpStatus.NOT_MODIFIED.value());
			responseStructure.setMessage("Upated Falied ");
			responseStructure.setData(null);
			ResponseEntity<ResponseStructure<Guest>> responseEntity=new ResponseEntity<ResponseStructure<Guest>>(responseStructure,HttpStatus.NOT_FOUND); 
			return responseEntity;
		}
	}

	public ResponseEntity<ResponseStructure<List<Guest>>>  getCheckOutGuests() {
		List<Guest> guests = guestDao.getAllGuests();
		List<Guest> outGuest = new ArrayList<Guest>();
		for (Guest guest : guests) {
			if (guest.getCheckoutDate() == LocalDate.now()) {
				outGuest.add(guest);
			}
		}
		ResponseStructure<List<Guest>> responseStructure = new ResponseStructure<List<Guest>>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage(" Sucessfuly ");
		responseStructure.setData(outGuest);
		ResponseEntity<ResponseStructure<List<Guest>>> responseEntity=new ResponseEntity<ResponseStructure<List<Guest>>>(responseStructure,HttpStatus.OK); 
		return responseEntity;
	}

	public ResponseEntity<ResponseStructure<Guest>> makePayment(int guestid, double amt) {
		Guest guest = guestDao.getGuest(guestid);
		guest.setPaidAmount(guest.getPaidAmount() + amt);
		guest.setPendingAmount(guest.getTotalAmount() - guest.getPaidAmount());
		if (guest.getTotalAmount() == guest.getPaidAmount()) {
			guest.setPaymentStatus("Fully payed");
		} else
			guest.setPaymentStatus("Pendibg");
		
		ResponseStructure<Guest> responseStructure = new ResponseStructure<Guest>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage(" Sucessfuly Updated payment ");
		responseStructure.setData(guestDao.updateGuest(guest.getId(), guest));
		ResponseEntity<ResponseStructure<Guest>> responseEntity=new ResponseEntity<ResponseStructure<Guest>>(responseStructure,HttpStatus.OK); 
		return responseEntity;
		
	}

	public ByteArrayInputStream load() {
		ByteArrayInputStream in = guestDao.load();
		return in;
	}
	
}
