package com.kh.app.admin.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.kh.app.member.entity.Authority;
import com.kh.app.member.entity.Employee;
import com.kh.app.member.entity.Member;
import com.kh.app.member.entity.Teacher;
import com.kh.app.messageBox.entity.MessageBox;
import com.kh.app.report.dto.AdminReportListDto;
import com.kh.app.board.dto.BoardChartDto;
import com.kh.app.curriculum.dto.AdminCurriculumDetailDto;
import com.kh.app.board.dto.BoardCreateDto;
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

public interface AdminService {

	List<AdminEmployeeListDto> findAllEmployee(Map<String, Object> filters, Map<String, Object> params);
	
	int todayNewStudentCount();

	int todayNewEmployee();
	
	int todayNewTeacher();
	
	int approvementStudentCount();

	int approvementStudentVacationCount();

	int todayNewPostCount();

	int todayNewReportCount();

	List<BoardChartDto> findBoardNameAndPostCount();
	
	Member findById(String id);

	int threeDaysAgoPostCount();

	int twoDaysAgoPostCount();

	int yesterdayPostCount();

	List<AdminStudentApproveDto> studentApproveListThree();

	List<AdminVacationApproveDto> vacationApproveListThree();

	int insertEmployee(EmployeeCreateDto employee);

	int insertMember(MemberCreateDto member);
	List<AdminReportListDto> reportListSix();

	List<AdminStudentListDto> findAllStudents(Map<String, Object> filters, Map<String, Object> params);

	int updateAdminStudent(AdminStudentListDto student);


	int deleteAdminStudent(AdminStudentListDto student);

	int totalCountStudents(Map<String, Object> filters);

	int updateAdminEmployee(AdminEmployeeListDto employee);

	int deleteAdminEmployee(AdminEmployeeListDto employee);

	int deleteAdminMember(AdminEmployeeListDto employee);

	int insertAuth(Authority auth);

	List<TeacherListDto> findAllTeacher(Map<String, Object> filters, Map<String, Object> params);

	int deleteAdminTeacher(String memberId);

	int totalCountEmployees(Map<String, Object> filters);

	int deleteAdminAuthority(String memberId);

	int insertTeacher(TeacherCreateDto teacher);
	int sendMessageToStudent(MessageBox message);

	List<Curriculum> findAllCurriculum();

	List<AdminStudentApproveDto> adminStudentApprovementList(Map<String, Object> params);

	int totalCountNonApprovementStudents();

	int approvementStudent(AdminStudentListDto student);

	int adminStudentApprovementNo(AdminStudentListDto student);

	List<CurriculumListDto> adminCourseList(Map<String, Object> filters, Map<String, Object> params);

	int totalCountCurriculum(Map<String, Object> filters);


	List<AdminCurriculumDetailDto> findStudentsByClassId(String classId, int curriculumId);

	List<Teacher> findAllTeachers();

	List<KhClass> findAllClass();

	int addCurriculum(CurriculumRegDto curriculum);

	int insertBoardNofiles(BoardCreateDto board);

	int insertBoard(BoardCreateDto board);

	int insertPostContent(BoardCreateDto board);

	int totalCountTeachers(Map<String, Object> filters);

	int deleteStore(int storeId);

	int insertStore(String storeName, String postNumber, String address);




}
