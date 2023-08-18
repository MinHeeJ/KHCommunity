package com.kh.app.board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kh.app.board.dto.BoardListDto;
import com.kh.app.board.entity.BoardSearchDetails;

@Mapper
public interface BoardRepository {

	List<BoardSearchDetails> findAllByKeyword(String keyword);

	List<BoardListDto> freeBoardFindAll();

	List<BoardListDto> preStudentBoardFindAll();

	List<BoardListDto> graduateBoardFindAll();

}
