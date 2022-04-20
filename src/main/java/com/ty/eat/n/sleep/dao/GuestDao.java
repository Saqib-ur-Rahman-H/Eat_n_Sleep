package com.ty.eat.n.sleep.dao;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.eat.n.sleep.ExcelSheetHelper.GuestExcelSheetGenerator;
import com.ty.eat.n.sleep.ExcelSheetHelper.UserExcelSheetGenerator;
import com.ty.eat.n.sleep.dto.Branch;
import com.ty.eat.n.sleep.dto.Guest;
import com.ty.eat.n.sleep.dto.Room;
import com.ty.eat.n.sleep.dto.User;
import com.ty.eat.n.sleep.repository.GuestRepository;

@Repository
public class GuestDao {
	@Autowired
	private GuestRepository guestRepository;
	@Autowired
	private RoomDao roomDao;

	public Guest saveGuest(int roomid,Guest guest) {
		Room room=roomDao.getRoomById(roomid);
			//roomDao.updateRoom(roomid,bid, room);
		      guest.setRoom(room);
			return guestRepository.save(guest);
	}

	public Guest getGuest(int id) {
		Optional<Guest> optional = guestRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		return optional.get();
	}

	public List<Guest> getAllGuests() {
		return guestRepository.findAll();
	}

	public boolean deleteGuest(int id) {
		Guest guest = getGuest(id);
		if (guest != null) {
			guestRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public Guest updateGuest(int id, Guest guest) {
		Guest existingGuest = getGuest(id);
		if (existingGuest != null) {
			guest.setId(id);
			guestRepository.save(guest);
			return guest;

		}
		return null;
	}
	
	public ByteArrayInputStream load() {
		List<Guest> guests = guestRepository.findAll();
		ByteArrayInputStream in = GuestExcelSheetGenerator.guestsToExcel(guests);
		return in;
	}

}
