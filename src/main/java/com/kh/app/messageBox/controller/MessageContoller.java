package com.kh.app.messageBox.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kh.app.member.entity.MemberDetails;
import com.kh.app.messageBox.dto.MessageReportDto;
import com.kh.app.messageBox.entity.AnonymousCheck;
import com.kh.app.messageBox.entity.MessageBox;
import com.kh.app.messageBox.entity.ReadCheck;
import com.kh.app.messageBox.service.MessageService;
import com.kh.app.notification.service.NotificationService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@Validated
@RequestMapping("/message")
public class MessageContoller {

   @Autowired
   private MessageService messageService;

   @Autowired
   private NotificationService notificationService;
   
   // 메세지 보내기 기능
   @PostMapping("/messageSend.do")
   @ResponseBody
   public ResponseEntity<?> messageSend (@RequestParam String sendId, @RequestParam String anonymousCheck,
         @RequestParam String receiveId, @RequestParam String messageContent) {

      MessageBox message = MessageBox.builder()
            .sendId(sendId)
            .receiveId(receiveId)
            .messageContent(messageContent)
            .anonymousCheck(AnonymousCheck.valueOf(anonymousCheck))
            .readCheck(ReadCheck.n)
            .build();

      int result = messageService.insertMessage(message);
      
      // 성근 - 쪽지 보내면 실시간 알림
      result = notificationService.notifyMsgSend(message);

      return ResponseEntity.status(HttpStatus.OK).body(Map.of("sendId", sendId, "receiveId", receiveId));
   }
   
   // 페이징된 메세지 리스트
   @GetMapping("/messageList.do")
   @ResponseBody
   public ResponseEntity<?> getMessageList(@RequestParam String page,
                                          @RequestParam String size,
                                          Model model,
                                          @AuthenticationPrincipal MemberDetails member) {
       String memberId = member.getMemberId();
       
       int pageInt = Integer.parseInt(page);
       int sizeInt = Integer.parseInt(size);
       
       List<MessageBox> messageAllList = messageService.getMessageList(memberId);
       
       List<MessageBox> messageList = messageService.getMessageListWithPaging(memberId, pageInt, sizeInt);
       int totalPages = messageAllList.size() / sizeInt  + (messageAllList.size() % sizeInt > 0? 1 : 0);
       
       return ResponseEntity.status(HttpStatus.OK).body(Map.of("totalPages", totalPages, "messageList", messageList));
   }
   
   
    // 메세지 삭제기능
	@GetMapping("/messageDelete.do")
	@ResponseBody
	public ResponseEntity<?> messageDelete(@RequestParam String messageId) {

		try {
	        int result = messageService.messageDelete(messageId);

	        return ResponseEntity.status(HttpStatus.OK).body(Map.of("messageId", messageId));
	    } catch (Exception e) {
	        
	        String errorMessage = "신고한 메세지는 신고 처리 전까지 삭제가 불가능합니다.";
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("errorMessage", errorMessage));
	    }
	}
	
	
	// 전체 메세지 수 
	@GetMapping("/messageAllList.do")
	@ResponseBody
	public ResponseEntity<?> messageAllList(@AuthenticationPrincipal MemberDetails member) {
		String memberId = member.getMemberId();
		List<MessageBox> messageList = messageService.getMessageList(memberId);
		return ResponseEntity.status(HttpStatus.OK).body(Map.of("listNum", messageList.size()));
	}
	
	
	
	// 읽음여부 처리 기능
	@GetMapping("/messageUpdate.do")
	@ResponseBody
	public ResponseEntity<?> messageUpdate(@RequestParam String checked, @RequestParam String messageId) {
		
		int result= messageService.messageUpdate(checked, messageId);
		log.debug("★★messageUpdate = {}", messageId);
		return ResponseEntity.status(HttpStatus.OK).body(Map.of("checked", checked));
	}
	
	
	// 메세지 신고기능
	@PostMapping("messageReport.do")
	public String postReport(
			@RequestParam int reportMessageId,
			@RequestParam String reporterId,
			@RequestParam String attackerId,
			@RequestParam String reportType,
			@RequestParam String reportContent
			) {
		MessageReportDto messageReport = MessageReportDto.builder()
				.messageId(reportMessageId)
				.reporterId(reporterId)
				.attackerId(attackerId)
				.reportType(reportType)
				.reportContent(reportContent)
				.build();
		
		int result = messageService.insertMessageReport(messageReport);
		return "redirect:/member/myPage.do";
	}
	


}