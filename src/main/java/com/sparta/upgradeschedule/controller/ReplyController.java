package com.sparta.upgradeschedule.controller;

import com.sparta.upgradeschedule.dto.request.CreateReplyRequestDto;
import com.sparta.upgradeschedule.dto.request.UpdateReplyRequestDto;
import com.sparta.upgradeschedule.dto.response.CreateReplyResponseDto;
import com.sparta.upgradeschedule.dto.response.DeleteReplyResponseDto;
import com.sparta.upgradeschedule.dto.response.GetReplyResponseDto;
import com.sparta.upgradeschedule.dto.response.UpdateReplyResponseDto;
import com.sparta.upgradeschedule.service.ReplyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ReplyController {

    // reply 명확하게 분리하는게 좋다! ("/api/reply")

    // /reply/schedule -> 댓글 어떤거에 대한 댓글이야?

    private ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/schedule/{scheduleId}/reply")
    public CreateReplyResponseDto createReply(@PathVariable(value = "scheduleId")Long scheduleId, @RequestBody CreateReplyRequestDto createReplyRequestDto){
        return replyService.createReply(scheduleId, createReplyRequestDto);
    }

    @GetMapping("/schedule/{scheduleId}/replies")
    public GetReplyResponseDto getReplyAll(@PathVariable (value = "scheduleId") Long scheduleId){
        return replyService.getReplyAll(scheduleId);
    }

    @PutMapping("/reply/{replyId}")
    public UpdateReplyResponseDto updateReply(@PathVariable(value = "replyId") Long replyId, @RequestBody UpdateReplyRequestDto updateReplyRequestDto){
        return replyService.updateReply(replyId, updateReplyRequestDto);
    }

    @DeleteMapping("/reply/{replyId}")
    public DeleteReplyResponseDto deleteReply(@PathVariable(value = "replyId") Long replyId){
        return replyService.deleteReply(replyId);
    }

}
