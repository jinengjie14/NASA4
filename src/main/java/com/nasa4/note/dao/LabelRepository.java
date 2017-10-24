package com.nasa4.note.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nasa4.note.domain.Label;

@Repository
@Transactional
public interface LabelRepository extends JpaRepository<Label, String> {

}
