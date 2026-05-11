package com.cs4.CS4.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.cs4.CS4.model.Room;
import com.cs4.CS4.service.RoomService;
@Controller
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/rooms")
    public String getRooms(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        return "rooms";
    }

    @GetMapping("/add-room")
    public String addRoomPage(Model model) {
        model.addAttribute("room", new Room());
        return "add-room";
    }

    @PostMapping("/add-room")
    public String saveRoom(Room room) {
        roomService.saveRoom(room);
        return "redirect:/rooms";
    }
    
    @GetMapping("/delete-room/{id}")
    public String deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return "redirect:/rooms";
    }
    
    @GetMapping("/edit-room/{id}")
    public String editRoomPage(@PathVariable Long id,Model model) {
        model.addAttribute("room", roomService.getRoomById(id));
        return "edit-room";
    }
    
    @PostMapping("/update-room")
    public String updateRoom(Room room) {
        roomService.saveRoom(room);
        return "redirect:/rooms";
    }
}