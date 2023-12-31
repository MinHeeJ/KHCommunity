package com.kh.app.admin.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.app.admin.repository.AdminRepository;
import com.kh.app.board.dto.BoardChartDto;
import com.kh.app.member.entity.Authority;
import com.kh.app.member.entity.Employee;
import com.kh.app.member.entity.Member;
import com.kh.app.member.entity.Teacher;
import com.kh.app.messageBox.entity.MessageBox;
import com.kh.app.report.dto.AdminReportListDto;
import com.kh.app.report.entity.Report;
import com.kh.app.board.dto.BoardChartDto;
import com.kh.app.curriculum.dto.AdminCurriculumDetailDto;
import com.kh.app.board.dto.BoardCreateDto;
import com.kh.app.board.dto.MyClassBoardListDto;
import com.kh.app.board.entity.PostAttachment;
import com.kh.app.chat.dto.AdminChatListDto;
import com.kh.app.chat.entity.ChatMessage;
import com.kh.app.curriculum.dto.CurriculumListDto;
import com.kh.app.curriculum.dto.CurriculumRegDto;
import com.kh.app.curriculum.entity.Curriculum;
import com.kh.app.khclass.entity.KhClass;
import com.kh.app.member.dto.AdminEmployeeListDto;
import com.kh.app.member.dto.AdminStudentApproveDto;
import com.kh.app.member.dto.EmployeeCreateDto;
import com.kh.app.member.dto.MemberCreateDto;
import com.kh.app.member.dto.TeacherCreateDto;
import com.kh.app.member.dto.TeacherListDto;
import com.kh.app.member.dto.AdminStudentListDto;
import com.kh.app.vacation.dto.AdminVacationApproveDto;
import com.kh.app.vacation.dto.VacationNonApprovementListDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	
	public int todayNewStudentCount() {	
		return adminRepository.todayNewStudentCount();
	}
	
	
	@Override
	public int approvementStudentCount() {
		return adminRepository.approvementStudentCount();
	}
	
	@Override
	public int approvementStudentVacationCount() {
		return adminRepository.approvementStudentVacationCount();
	}
	
	@Override
	public int todayNewEmployee() {
		return adminRepository.todayNewEmployee();
	}
	
	@Override
	public int todayNewTeacher() {
		return adminRepository.todayNewTeacher();
	}
	
	@Override
	public int todayNewPostCount() {
		return adminRepository.todayNewPostCount();
	}
	
	@Override
	public int todayNewReportCount() {
		return adminRepository.todayNewReportCount();
	}
	
	@Override
	public List<BoardChartDto> findBoardNameAndPostCount() {
		return adminRepository.findBoardNameAndPostCount();
	}
	
	@Override
	public int threeDaysAgoPostCount() {
		return adminRepository.threeDaysAgoPostCount();
	}
	
	@Override
	public int twoDaysAgoPostCount() {
		return adminRepository.twoDaysAgoPostCount();
	}
	
	@Override
	public int yesterdayPostCount() {
		return adminRepository.yesterdayPostCount();
	}
	
	@Override
	public List<AdminStudentApproveDto> studentApproveListThree() {
		return adminRepository.studentApproveListThree();
	}
	
	@Override
	public List<AdminVacationApproveDto> vacationApproveListThree() {
		return adminRepository.vacationApproveListThree();
	}
	
	public Member findById(String id) {
		return adminRepository.findById(id);
	}
	  
	@Override
	public int insertEmployee(EmployeeCreateDto employee) {
		return adminRepository.insertEmployee(employee);
	}
	
	@Override
	public int insertMember(MemberCreateDto member) {
		return adminRepository.insertMember(member);
	}

	public List<AdminReportListDto> reportListSix() {
		return adminRepository.reportListSix();
	}
	
	@Override
	public List<AdminStudentListDto> findAllStudents(Map<String, Object> filters, Map<String, Object> params) {
		int limit = (int) params.get("limit");
		int page = (int) params.get("page");
		int offset = (page - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		return adminRepository.findAllStudents(filters, rowBounds);
	}
	
	@Override
	public List<AdminEmployeeListDto> findAllEmployee(Map<String, Object> filters, Map<String, Object> params) {
		int limit = (int)params.get("limit");
		int page = (int)params.get("page");
		int offset = (page-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		return adminRepository.findAllEmployee(filters, rowBounds);
	}
	
	@Override
	public List<TeacherListDto> findAllTeacher(Map<String, Object> filters, Map<String, Object> params) {
		int limit = (int)params.get("limit");
		int page = (int)params.get("page");
		int offset = (page-1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		return adminRepository.findAllTeacher(filters, rowBounds);
	}
	
	@Override
	public int updateAdminStudent(AdminStudentListDto student) {
		return adminRepository.updateAdminStudent(student);
	}
	
	@Override

	public int deleteAdminStudent(AdminStudentListDto student) {
		return adminRepository.deleteAdminStudent(student);
	}
	
	@Override
	public int totalCountStudents(Map<String, Object> filters) {
		return adminRepository.totalCountStudents(filters);
	}

	@Override
	public int totalCountEmployees(Map<String, Object> filters) {
		return adminRepository.totalCountEmployees(filters);
	}
	
	@Override
	public int totalCountTeachers(Map<String, Object> filters) {
		return adminRepository.totalCountTeachers(filters);
	}
	
	public int updateAdminEmployee(AdminEmployeeListDto employee) {
		return adminRepository.updateAdminEmployee(employee);
	}
	
	@Override
	public int deleteAdminEmployee(AdminEmployeeListDto employee) {
		return adminRepository.deleteAdminEmployee(employee);
	}
	
	@Override
	public int deleteAdminMember(AdminEmployeeListDto employee) {
		return adminRepository.deleteAdminMember(employee);
	}
	
	@Override
	public int insertAuth(Authority auth) {
		return adminRepository.insertAuth(auth);
	}
	
	
	@Override
	public int deleteAdminTeacher(String memberId) {
		return adminRepository.deleteAdminTeacher(memberId);
	}
	
	@Override
	public int deleteAdminAuthority(String memberId) {
		return adminRepository.deleteAdminAuthority(memberId);
	}
	
	@Override
	public int insertTeacher(TeacherCreateDto teacher) {
		return adminRepository.insertTeacher(teacher);
	}
	
	@Override
	public int sendMessageToStudent(MessageBox message) {
		return adminRepository.sendMessageToStudent(message);
	}
	
	@Override
	public List<Curriculum> findAllCurriculum() {
		return adminRepository.findAllCurriculum();
	}
	
	@Override
	public List<AdminStudentApproveDto> adminStudentApprovementList(Map<String, Object> params) {
		int limit = (int) params.get("limit");
		int page = (int) params.get("page");
		int offset = (page - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		return adminRepository.adminStudentApprovementList(rowBounds);
	}
	
	@Override
	public int totalCountNonApprovementStudents() {
		return adminRepository.totalCountNonApprovementStudents();
	}
	
	@Override
	public int approvementStudent(AdminStudentListDto student) {
		return adminRepository.approvementStudent(student);
	}
	
	@Override
	public int adminStudentApprovementNo(AdminStudentListDto student) {
		return adminRepository.adminStudentApprovementNo(student);
	}
	
	@Override
	public List<CurriculumListDto> adminCourseList(Map<String, Object> filters, Map<String, Object> params) {
		int limit = (int) params.get("limit");
		int page = (int) params.get("page");
		int offset = (page - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return adminRepository.adminCourseList(filters, rowBounds);
	}
	
	@Override
	public int totalCountCurriculum(Map<String, Object> filters) {
		return adminRepository.totalCountCurriculum(filters);
	}
	
	@Override
	public List<AdminCurriculumDetailDto> findStudentsByClassId(String classId, int curriculumId) {
		return adminRepository.findStudentsByClassId(classId, curriculumId);
	}
	
	@Override
	public List<Teacher> findAllTeachers() {
		return adminRepository.findAllTeachers();
	}
	
	@Override
	public List<KhClass> findAllClass() {
		return adminRepository.findAllClass();
	}
	
	@Override
	public int addCurriculum(CurriculumRegDto curriculum) {
		return adminRepository.addCurriculum(curriculum);
	}

	public int insertBoard(BoardCreateDto board) {
		int result = 0;
		
		result = adminRepository.insertBoard(board);
		
		List<PostAttachment> attachments = board.getAttachments();
		if(attachments != null && !attachments.isEmpty()) {
			for(PostAttachment attach : attachments) {
				attach.setPostId(board.getPostId());
				result = adminRepository.insertPostAttach(attach);
			}
		}
		
		return result;
	}
	@Override
	public int insertBoardNofiles(BoardCreateDto board) {
		return adminRepository.insertBoardNofiles(board);
	}
	@Override
	public int insertPostContent(BoardCreateDto board) {
		return adminRepository.insertPostContent(board);
	}
	
	@Override
	public int deleteStore(int storeId) {
		return adminRepository.deleteStore(storeId);
	}
	
	@Override
	public int getTotalCountOfNonApprovementStudent() {
		return adminRepository.getTotalCountOfNonApprovementStudent();
	}
	
	@Override
	public List<VacationNonApprovementListDto> findAllNonApprovementStudent() {
		return adminRepository.findAllNonApprovementStudent();
	}
	
	@Override
	public int updateCurriculum(CurriculumRegDto curriculum) {
		return adminRepository.updateCurriculum(curriculum);
	}
	

	public int insertStore(String storeName, String postNumber, String address) {
		return adminRepository.insertStore(storeName, postNumber, address);
	}

	@Override
	public List<Report> findAllReports(Map<String, Object> params) {
		int limit = (int) params.get("limit");
		int page = (int) params.get("page");
		int offset = (page - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		return adminRepository.findAllReports(rowBounds);
	}
	
	@Override
	public int countAllReports() {
		return adminRepository.countAllReports();
	}
	
	@Override
	public List<Report> findReportsByFilter(String reportType, Map<String, Object> params) {
		int limit = (int) params.get("limit");
		int page = (int) params.get("page");
		int offset = (page - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		return adminRepository.findReportsByFilter(reportType, rowBounds);
	}
	
	@Override
	public int countReportsByFilter(String reportType) {
		return adminRepository.countReportsByFilter(reportType);
	}
	
	@Override
	public List<AdminChatListDto> findAllChat(Map<String, Object> params) {
		int limit = (int) params.get("limit");
		int page = (int) params.get("page");
		int offset = (page - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return adminRepository.findAllChat(rowBounds);
	}
	
	@Override
	public int getTotalCountOfChatList() {
		return adminRepository.getTotalCountOfChatList();
	}
	
	@Override
	public List<ChatMessage> getChatMessagesByChatId(int chatId) {
		return adminRepository.getChatMessagesByChatId(chatId);
	}
	
	
	
	@Override
	public int insertTicket(String storeName) {
		return adminRepository.insertTicket(storeName);
	}
	
	@Override
	public int deleteReport(String reportId) {
		return adminRepository.deleteReport(reportId);
	}
	
	@Override
	public int sendReportToStudent(String attackerId, String admin, String messageContent) {
		return adminRepository.sendReportToStudent(attackerId, admin, messageContent);
	}
	
	@Override
	public int deleteMyClassBoard(String boardId) {
		return adminRepository.deleteMyClassBoard(boardId);
	}
	
	@Override
	public List<MyClassBoardListDto> findAllMyClassBoard() {
		return adminRepository.findAllMyClassBoard();
	}
	
	@Override
	public List<Curriculum> findRecentCurriculum() {
		return adminRepository.findRecentCurriculum();
	}
	
	@Override
	public int updateMyClass(String boardId, String selectedCurriculumId) {
		return adminRepository.updateMyClass(boardId, selectedCurriculumId);
	}
	
	@Override
	public int updateVacationOkById(int vacationId) {
		return adminRepository.updateVacationOkById(vacationId);
	}
	
	@Override
	public int updateVacationByNoId(int vacationId) {
		return adminRepository.updateVacationByNoId(vacationId);
	}
}
