package com.cs4.CS4.config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.cs4.CS4.model.Room;
import com.cs4.CS4.model.RoomType;
import com.cs4.CS4.repository.RoomRepository;
@Component
public class DataLoader implements CommandLineRunner {
	 private final RoomRepository roomRepo;

	    public DataLoader(RoomRepository roomRepo) {
	        this.roomRepo = roomRepo;
	    }

	    @Override
	    public void run(String... args) throws Exception {
	        if(roomRepo.count() == 0) {
	            Room r1 = new Room();
	            r1.setRoomNumber(101);
	            r1.setType(RoomType.SINGLE);
	            r1.setPrice(1200);
	            r1.setAvailable(true);
	            
	            Room r2 = new Room();
	            r2.setRoomNumber(202);
	            r2.setType(RoomType.DOUBLE);
	            r2.setPrice(2000);
	            r2.setAvailable(true);

	            Room r3 = new Room();
	            r3.setRoomNumber(303);
	            r3.setType(RoomType.FAMILY);
	            r3.setPrice(3500);
	            r3.setAvailable(true);

	            roomRepo.save(r1);
	            roomRepo.save(r2);
	            roomRepo.save(r3);
	        }
	    }
}
