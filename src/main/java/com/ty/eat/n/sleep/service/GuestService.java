package com.ty.eat.n.sleep.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ty.eat.n.sleep.dao.GuestDao;
import com.ty.eat.n.sleep.dao.RoomDao;
import com.ty.eat.n.sleep.dto.Branch;
import com.ty.eat.n.sleep.dto.Guest;
import com.ty.eat.n.sleep.dto.ResponseStructure;
import com.ty.eat.n.sleep.dto.Room;

@Service
public class GuestService {
	@Autowired
	private GuestDao guestDao;
	@Autowired
	private RoomDao roomDao;
	@Autowired
	private Guest guest;

	public ResponseStructure<Guest> saveGuest(int roomid, Guest guest) {
		Room room = roomDao.getRoomById(roomid);
		if (guestDao.getGuest(guest.getId()) == null) {
			ResponseStructure<Guest> responseStructure = new ResponseStructure<Guest>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not Found");
			responseStructure.setData(null);
			return responseStructure;
		} else {
			List<Guest> guests = room.getGuests();
			if (guests.size() >= room.getSize()) {
				guest.setTotalAmount(room.getCost() / room.getSize());
				guests.add(guest);
				room.setGuests(guests);
				ResponseStructure<Guest> responseStructure = new ResponseStructure<Guest>();
				responseStructure.setStatus(HttpStatus.OK.value());
				responseStructure.setMessage("Saved Sucessfuly");
				responseStructure.setData(guestDao.saveGuest(roomid, guest));
				return responseStructure;
			}

			else {
				Branch brabch = room.getBranch();
				List<Room> avlRooms = brabch.getAvailableRooms();
				avlRooms.remove(room);
				List<Room> takenRooms = brabch.getBookedRooms();
				takenRooms.add(room);
				ResponseStructure<Guest> responseStructure = new ResponseStructure<Guest>();
				responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
				responseStructure.setMessage("Not Found");
				responseStructure.setData(null);
				return responseStructure;
			}
		}

	}

	public ResponseStructure<Guest> getGuest(int id) {
		if (guestDao.getGuest(id) != null) {
			ResponseStructure<Guest> responseStructure = new ResponseStructure<Guest>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage(" Sucessfuly");
			responseStructure.setData(guestDao.getGuest(id));
			return responseStructure;
		} else {
			ResponseStructure<Guest> responseStructure = new ResponseStructure<Guest>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not Found");
			responseStructure.setData(null);
			return responseStructure;
		}
	}

	public ResponseStructure<Boolean> deleteGuest(int id) {

		if (guestDao.deleteGuest(id)) {
			Room room = guest.getRoom();
			List<Guest> guests = room.getGuests();
			if (guests.size() > room.getSize()) {
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
			return responseStructure;
		} else {
			ResponseStructure<Boolean> responseStructure = new ResponseStructure<Boolean>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Not Deleted");
			responseStructure.setData(null);
			return responseStructure;
		}
	}

	public ResponseStructure<List<Guest>> getAllGuests() {
		if (guestDao.getAllGuests().size() > 0) {
			ResponseStructure<List<Guest>> responseStructure = new ResponseStructure<List<Guest>>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage(" Sucessfuly");
			responseStructure.setData(guestDao.getAllGuests());
			return responseStructure;
		} else {
			ResponseStructure<List<Guest>> responseStructure = new ResponseStructure<List<Guest>>();
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage(" Failed");
			responseStructure.setData(null);
			return responseStructure;
		}
	}

	public ResponseStructure<Guest> updateguest(int id, Guest guest) {
		if (guestDao.updateGuest(id, guest) != null) {
			ResponseStructure<Guest> responseStructure = new ResponseStructure<Guest>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage(" Sucessfuly Updated ");
			responseStructure.setData(guestDao.updateGuest(id, guest));
			return responseStructure;
		} else {
			ResponseStructure<Guest> responseStructure = new ResponseStructure<Guest>();
			responseStructure.setStatus(HttpStatus.NOT_MODIFIED.value());
			responseStructure.setMessage("Upated Falied ");
			responseStructure.setData(null);
			return responseStructure;
		}
	}

	public ResponseStructure<List<Guest>>  getCheckOutGuests() {
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
		return responseStructure;
	}

	public Guest makePayment(int guestid, double amt) {
		Guest guest = guestDao.getGuest(guestid);
		guest.setPaidAmount(guest.getPaidAmount() + amt);
		guest.setPendingAmount(guest.getTotalAmount() - guest.getPaidAmount());
		if (guest.getTotalAmount() == guest.getPaidAmount()) {
			guest.setPaymentStatus("Fully payed");
		} else
			guest.setPaymentStatus("Pendibg");
		return guestDao.updateGuest(guest.getId(), guest);
	}

	public ResponseStructure<Guest> getGuestsbyGovtId(int govtid) {
		if (guestDao.getAllGuests().size() > 0) {
			ResponseStructure<Guest>responseStructure = new ResponseStructure<Guest>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Sucessfuly Get By Gov Id ");
			responseStructure.setData(guestDao.findGuestBygovtId(govtid));
			return responseStructure;
		} else {
			ResponseStructure<Guest> responseStructure = new ResponseStructure<Guest>();
			responseStructure.setStatus(HttpStatus.NOT_MODIFIED.value());
			responseStructure.setMessage(" Falied ");
			responseStructure.setData(null);
			return responseStructure;
		}
	}
}
