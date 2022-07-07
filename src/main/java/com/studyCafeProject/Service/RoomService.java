package com.studyCafeProject.Service;

import com.studyCafeProject.Model.Cafe;
import com.studyCafeProject.Model.Room;
import com.studyCafeProject.Repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    public void addRoom(Room room)  {
        roomRepository.save(room);
    }

    public void updateRoom(Room room) {
        roomRepository.save(room);
    }

    //delete room by id
    public boolean removeRoomById(Integer roomId) {
        Optional<Room> rr = roomRepository.findById(roomId);
        if (!rr.isPresent()){
            return false;
        }
        roomRepository.deleteById(roomId);
        return true;
    }

}
