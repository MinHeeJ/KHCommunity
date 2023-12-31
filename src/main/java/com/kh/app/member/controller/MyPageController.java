package com.kh.app.member.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

import com.kh.app.chat.dto.AdminChatListDto;
import com.kh.app.chat.entity.ChatMessage;
import com.kh.app.curriculum.entity.Curriculum;
import com.kh.app.member.dto.EmployeeDto;
import com.kh.app.member.dto.EmployeeInfoDto;
import com.kh.app.member.dto.StudentListDto;
import com.kh.app.member.dto.StudentMypageInfoDto;
import com.kh.app.member.dto.StudentVacationApproveDto;
import com.kh.app.member.entity.MemberDetails;
import com.kh.app.member.entity.Student;
import com.kh.app.member.service.MemberService;
import com.kh.app.ticket.dto.TicketBuyDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@Controller
@RequestMapping("/member")
public class MyPageController {

	@Autowired
	private MemberService memberService;

	@GetMapping("/myPage.do")
	public void myPage(Model model, @AuthenticationPrincipal MemberDetails principal, @RequestParam(defaultValue = "1") int page) throws Exception {
		// 시작
		StudentMypageInfoDto studentInfo = memberService.findByMemberInfo(principal.getMemberId());
		model.addAttribute("studentInfo", studentInfo);
		// 끝
		Student student = memberService.findStudentById(principal.getMemberId());
		model.addAttribute("studentAuthInfo", student);
		
		// 식권정보 시작
		int limit = 5;
		Map<String, Object> params = Map.of(
				"page", page,
				"limit", limit
			);
		
		List<TicketBuyDto> studentTicketInfo = memberService.findByTicketInfo(principal.getMemberId(), params);
		model.addAttribute("studentTicketInfo", studentTicketInfo);
		
		model.addAttribute("currentPage", page);
		
		int totalCount = memberService.totalCountTicket(principal.getMemberId());
		
		 int totalPages = (int) Math.ceil((double) totalCount / limit);
		 model.addAttribute("totalPages", totalPages);
		// 식권정보 끝

		// Dday 시작
		if (studentInfo.getCurriculumId() != 0) {
			Curriculum curriculumDday = memberService.findByDdayInfo(studentInfo.getCurriculumId());

			model.addAttribute("curriculumDday", curriculumDday);

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			LocalDate endDateFrm = studentInfo.getCurriculumEndAt();

			String todayFm = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())); // 오늘날짜

			Date today = new Date(dateFormat.parse(todayFm).getTime());
			Date endDate = Date.from(endDateFrm.atStartOfDay(ZoneId.systemDefault()).toInstant());

			long calculate = endDate.getTime() - today.getTime();

			int Ddays = (int) (calculate / (24 * 60 * 60 * 1000));
			model.addAttribute("Ddays", Ddays);
		}
		// Dday 끝 }
		
	}

	@GetMapping("/employeeMyPage.do")
	public void employeeMyPage(Model model, @AuthenticationPrincipal MemberDetails principal,
			@RequestParam(value = "classId", required = false) Integer classId,
			@RequestParam(value = "subject", required = false) String subject,
			@RequestParam(value = "jobCode", required = false) String jobCode,
			@RequestParam(value = "employeeId", required = false) String employeeId,
			@RequestParam(value = "curriculumName", required = false) String curriculumName, 
			@RequestParam(defaultValue = "1") int page) throws Exception {

		String auth = principal.getAuthorities() + "";
		if("[TEACHER]".equals(auth)) {
			
			List<EmployeeInfoDto> employeeInfo = memberService.findByEmployeeInfo(principal.getMemberId());
			model.addAttribute("employeeInfo", employeeInfo);
		}


		List<StudentVacationApproveDto> studentVacationApprove = memberService.findAllVacationApproveList(principal.getMemberId());
		model.addAttribute("vacationApprove", studentVacationApprove);
	
		LocalDate currentDate = LocalDate.now();
		model.addAttribute("currentDate", currentDate);

		List<StudentListDto> studentList = memberService.findStudentByTeacher(principal.getMemberId());
		model.addAttribute("studentList", studentList);
		
		EmployeeDto adminInfo = memberService.findEmployeeById(principal.getMemberId());
		model.addAttribute("adminInfo", adminInfo);
		
		// 식권정보 시작
		int limit = 5;
		Map<String, Object> params = Map.of(
				"page", page,
				"limit", limit
			);
		
		List<TicketBuyDto> studentTicketInfo = memberService.findByTicketInfo(principal.getMemberId(), params);
		model.addAttribute("studentTicketInfo", studentTicketInfo);
		
		model.addAttribute("currentPage", page);
		
		int totalCount = memberService.totalCountTicket(principal.getMemberId());
		
		 int totalPages = (int) Math.ceil((double) totalCount / limit);
		 model.addAttribute("totalPages", totalPages);
		 // 식권정보 끝
	
		 
	}
	
	@GetMapping("/studentChatList.do")
	public ResponseEntity<?> StudentChatList(@RequestParam(defaultValue = "1") int page,
			@AuthenticationPrincipal MemberDetails principal
			) {
		// 페이징
	    int limit = 5;
		Map<String, Object> params = Map.of(
				"page", page,
				"limit", limit
		);
		
	    int totalChatListCount = memberService.getTotalCountOfChatList(principal.getMemberId());
	    int totalPages = (int) Math.ceil((double) totalChatListCount / limit);
	    
	    List<AdminChatListDto> studentChatList = memberService.findAllChat(params, principal.getMemberId());
	    
	    return ResponseEntity
				.status(HttpStatus.OK)
				.body(Map.of("currentPage", page, "totalPages", totalPages, "studentChatList", studentChatList));
	}	

	
	@PostMapping("vacationApprove.do")
	public String postReport(
			@RequestParam String vacationId ,
			@RequestParam String approveResult
			) {
		
		int result = memberService.updateVacationApprove(vacationId, approveResult);
		
		return "redirect:/member/employeeMyPage.do";
	}
	
	@GetMapping("/chatView.do")
	@ResponseBody
	public ResponseEntity<List<ChatMessage>> chatView(@RequestParam(value="chatId", required=false) int chatId) {
	    List<ChatMessage> chatMsgs = memberService.getChatMessagesByChatId(chatId);
	    return ResponseEntity.ok(chatMsgs);
	}
}
