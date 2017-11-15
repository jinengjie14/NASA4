package com.nasa4.note.dao;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nasa4.note.domain.Note;

@Repository
@Transactional
public interface NoteRepository extends JpaRepository<Note, String> {

	@Query("select n from Note n where n.title like %:keyword% or n.content like %:keyword%")  	
	public Page<Note> findByTitleLikeOrContentLike(Pageable pageable, @Param("keyword") String keyword);
	
	public Page<Note> findByUserId(Pageable pageable, String userId);
	
	/*@Query("update Note set comment_count=COUNT(select c from Comment c where c.note_id=:id) where id=:id") 
	public void updateCommentCount(@Param("id") String id);*/
}
