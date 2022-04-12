package com.ty.eat.n.sleep.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.eat.n.sleep.dao.GuestDao;
import com.ty.eat.n.sleep.dao.RoomDao;
import com.ty.eat.n.sleep.dto.Branch;
import com.ty.eat.n.sleep.dto.Guest;
import com.ty.eat.n.sleep.dto.Room;

@Service
public class GuestService {
	@Autowired
	private GuestDao guestDao;
	@Autowired
	private RoomDao roomDao;
	@Autowired
	private Guest guest;

	public Guest saveGuest(int roomid, Guest guest) {
		Room room = roomDao.getRoomById(roomid);
		List<Guest> guests = room.getGuests();
		if (guests.size() >= room.getSize()) {
			guests.add(guest);
			room.setGuests(guests);
			return guestDao.saveGuest(roomid, guest);
		}

		else {
			Branch brabch = room.getBranch();
			List<Room> avlRooms = brabch.getAvailableRooms();
			avlRooms.remove(room);
			List<Room> takenRooms = brabch.getBookedRooms();
			takenRooms.add(room);
			return null;
		}

	}

	public Guest getGuest(int id) {
		if (guestDao.getGuest(id) != null) {
			return guestDao.getGuest(id);
		} else {
			return null;
		}
	}

	public boolean deleteGuest(int id) {

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
			return true;
		} else {
			return false;
		}
	}

	public List<Guest> getAllGuests() {
		if (guestDao.getAllGuests().size() > 0) {
			return guestDao.getAllGuests();
		} else {
			return null;
		}
	}

	public Guest updateguest(int id, Guest guest) {
		if (guestDao.updateGuest(id, guest) != null) {
			return guestDao.updateGuest(id, guest);
		} else {
			return null;
		}
	}
	
	public List<Guest> getCheckOutGuests(){
		List<Guest> guests = guestDao.getAllGuests();
		List<Guest> outGuest=new ArrayList<Guest>();
		for(Guest guest:guests) {
			if (guest.getCheckoutDate()==LocalDate.now()) {
				 outGuest.add(guest);
			}
		}
		return outGuest;
	}
	
	public Guest makePayment(int guestid,double amt) {
		Guest guest = guestDao.getGuest(guestid);
		guest.setPaidAmount(guest.getPaidAmount()+amt);
		guest.setPendingAmount(guest.getTotalAmount()-guest.getPaidAmount());
		if (guest.getTotalAmount()==guest.getPaidAmount()) {
			guest.setPaymentStatus("Fully payed");
		}
		else
			guest.setPaymentStatus("Pendibg");
		return guestDao.updateGuest(guest.getId(), guest);
	}
}
