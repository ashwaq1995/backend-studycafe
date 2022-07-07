package com.studyCafeProject.Controller;

import com.studyCafeProject.DTO.Api;
import com.studyCafeProject.Model.Room;
import com.studyCafeProject.Service.RoomService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/room")
public class RoomController {

    private final RoomService roomService;

    Logger logger = LoggerFactory.getLogger(RoomController.class);

    //get all rooms
    @GetMapping("/all")
    public ResponseEntity<List<Room>> getRooms(){
        logger.info("get rooms");
        List<Room> rooms = roomService.getRooms();
        return ResponseEntity.status(200).body(rooms);
    }

    //add new room
    @PostMapping("/add")
    public ResponseEntity addRoom(@RequestBody Room room){
        logger.info("add room");
        roomService.addRoom(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Api("Room added !",201));
    }

    //update room
    @PutMapping("/update/room")
    public ResponseEntity updateRoom(@RequestBody Room room){
        logger.info("edit room");
        roomService.updateRoom(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Api("Room updated !",201));
    }


    //delete cafe
    @DeleteMapping("/remove/{RoomId}")
    public ResponseEntity removeRoomById(@PathVariable Integer roomId){
        logger.info("delete room");
        roomService.removeRoomById(roomId);
        return ResponseEntity.status(200).body("Room deleted! ");
    }

}
