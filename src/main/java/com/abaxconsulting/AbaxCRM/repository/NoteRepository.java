package com.abaxconsulting.AbaxCRM.repository;

import com.abaxconsulting.AbaxCRM.model.Note;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface NoteRepository extends CrudRepository<Note, UUID> {
    @Transactional
    @Modifying
    @Query("update Note n set n.note = ?1 where n.id = ?2")
    int updateNoteById(String note, UUID id);
}