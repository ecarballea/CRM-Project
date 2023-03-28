package com.abaxconsulting.AbaxCRM.repository;

import com.abaxconsulting.AbaxCRM.model.Note;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface NoteRepository extends CrudRepository<Note, UUID> {
}