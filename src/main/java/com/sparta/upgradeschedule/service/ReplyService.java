package com.sparta.upgradeschedule.service;

import com.sparta.upgradeschedule.dto.request.CreateReplyRequestDto;
import com.sparta.upgradeschedule.dto.request.GetReplyDto;
import com.sparta.upgradeschedule.dto.request.UpdateReplyRequestDto;
import com.sparta.upgradeschedule.dto.response.CreateReplyResponseDto;
import com.sparta.upgradeschedule.dto.response.DeleteReplyResponseDto;
import com.sparta.upgradeschedule.dto.response.GetReplyResponseDto;
import com.sparta.upgradeschedule.dto.response.UpdateReplyResponseDto;
import com.sparta.upgradeschedule.entity.Reply;
import com.sparta.upgradeschedule.entity.Schedule;
import com.sparta.upgradeschedule.repository.ReplyRepository;
import com.sparta.upgradeschedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReplyService {

    private ReplyRepository replyRepository;
    private ScheduleRepository scheduleRepository;

    public ReplyService(ReplyRepository replyRepository, ScheduleRepository scheduleRepository) {
        this.replyRepository = replyRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    public CreateReplyResponseDto createReply(Long scheduleId, CreateReplyRequestDto createReplyRequestDto) {

        // 1. 스케줄 존재 여부 확인 및 가져오기
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("선택한 일정은 존재하지 않습니다."));

        // 2. 댓글 생성
        Reply reply = new Reply(createReplyRequestDto.getUsername(), createReplyRequestDto.getContents(), schedule);

        // 3. 댓글 저장
        Reply savedReply = replyRepository.save(reply);

        // 4. 저장된 댓글 정보를 기반으로 응답 DTO 생성 후 반환
        return new CreateReplyResponseDto(savedReply);

    }


    @Transactional
    public GetReplyResponseDto getReplyAll(Long scheduleId) {
        // 1. 스케줄 존재 여부 확인 및 가져오기
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("선택한 일정은 존재하지 않습니다."));

        // 2. 스케줄에 연결된 모든 댓글 조회
        List<Reply> replies = schedule.getReplyList();

        // 3. 댓글 리스트를 DTO로 변환
        List<GetReplyDto> replyDtoList = new ArrayList<>();
        for (Reply reply : replies) {
            GetReplyDto getReplyDto = new GetReplyDto(reply.getReplyId(), reply.getUsername(), reply.getContents(), reply.getUpdatedate());
            replyDtoList.add(getReplyDto);
        }

        //4. 댓글 리스트 포함한 응답 DTO 반환
        return new GetReplyResponseDto(schedule.getScheduleId(),schedule.getTitle(), replyDtoList);

    }

    @Transactional
    public UpdateReplyResponseDto updateReply(Long replyId, UpdateReplyRequestDto updateReplyRequestDto) {
        try {
            // 1. 댓글 존재하는지 확인 및 가져오기
            Reply reply = findById(replyId);

            // 2. 댓글 내용 수정
            reply.updateReply(updateReplyRequestDto);

            // 3. 변경된 댓글 저장
            replyRepository.save(reply);

            // 4. 수정된 데이터를 포함한 응답 반환
            return new UpdateReplyResponseDto(reply.getReplyId(), reply.getUsername(), reply.getContents(), reply.getUpdatedate());

        }catch (Exception e){
            throw new IllegalArgumentException("댓글 업데이트 중 문제가 발생했습니다.", e);
        }
    }

    @Transactional
    public DeleteReplyResponseDto deleteReply(Long replyId) {
        try {
            replyRepository.deleteById(replyId);
            return new DeleteReplyResponseDto(replyId);
        }catch (Exception e){
            throw new IllegalArgumentException("이미 존재하지 않는 댓글 입니다.");
        }
    }

    private Reply findById(Long replyId) {
        return replyRepository.findById(replyId).orElseThrow(() ->
                new IllegalArgumentException("선택한 댓글은 존재하지 않습니다.")
        );
    }
}
