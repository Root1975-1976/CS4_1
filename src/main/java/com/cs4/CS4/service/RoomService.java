package com.cs4.CS4.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cs4.CS4.model.Room;
import com.cs4.CS4.repository.RoomRepository;
@Service
public class RoomService {
    @Autowired 
    private RoomRepository roomRepo;

    public Room addRoom(Room room) {
        room.setAvailable(true);
        return roomRepo.save(room);
    }

    public Room updateRoom(Room room) {
        return roomRepo.save(room);
    }

    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }

    public Room getRoomById(Long id) {
        return roomRepo.findById(id).orElse(null);
    }

    public void deleteRoom(Long id) {
        roomRepo.deleteById(id);
    }
}