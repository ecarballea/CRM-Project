package com.abaxconsulting.AbaxCRM.service;

import com.abaxconsulting.AbaxCRM.model.Note;
import com.abaxconsulting.AbaxCRM.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public Note save(Note note) {
        Note newNote =  noteRepository.save(note);
        return newNote;
    }

    public Optional<Note> getNote(UUID noteID) {
        Optional<Note> existingNote = noteRepository.findById(noteID);
        return existingNote;

    }

    public Iterable<Note> getAllNotes() {
        Iterable<Note> notesList = noteRepository.findAll();
        return notesList;
    }

    public void delete(UUID id) {
        noteRepository.deleteById(id);
    }

    public int update(UUID noteID, Note note) {
        int result = noteRepository.updateNoteById(note.getNote(), noteID);
        return result;
    }

}
