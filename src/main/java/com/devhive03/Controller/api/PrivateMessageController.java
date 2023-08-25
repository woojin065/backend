package com.devhive03.Controller.api;

import com.devhive03.Model.DAO.MessageRoom;
import com.devhive03.Model.DAO.PrivateMessage;
import com.devhive03.Model.DAO.User;
import com.devhive03.Service.PrivateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.devhive03.Repository.MessageRoomDAORepository;
import com.devhive03.Repository.UserDAORepository;

@RestController
public class PrivateMessageController {
    private final PrivateMessageService privateMessageService;
    private final MessageRoomDAORepository messageRoomDAORepository;
    private final UserDAORepository userDAORepository;

    @Autowired
    public PrivateMessageController(PrivateMessageService privateMessageService, MessageRoomDAORepository messageRoomDAORepository, UserDAORepository userDAORepository) {
        this.privateMessageService = privateMessageService;
        this.messageRoomDAORepository = messageRoomDAORepository;
        this.userDAORepository = userDAORepository;
    }

    @PostMapping("/privatemessages/post")
    public String sendPrivateMessage(
            @RequestParam Long messageRoomId,
            @RequestParam Long messageWriterId,
            @RequestParam String content
    ) {
        // 여기에서 messageRoomId와 messageWriterId를 사용하여 MessageRoom과 User를 가져오는 작업을 수행

        MessageRoom messageRoom = messageRoomDAORepository.findById(messageRoomId).orElse(null);;
        User messageWriter = userDAORepository.findById(messageWriterId).orElse(null);

        if (messageRoom != null && messageWriter != null) {
            PrivateMessage privateMessage = privateMessageService.sendPrivateMessage(messageRoom, messageWriter, content);
            return "{\"message\":\"success\"}";
        } else {
            return "{\"message\":\"false\"}";
        }
    }
}
