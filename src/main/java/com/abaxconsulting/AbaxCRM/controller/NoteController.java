package com.abaxconsulting.AbaxCRM.controller;


import com.abaxconsulting.AbaxCRM.exception.NoteNotFoundException;
import com.abaxconsulting.AbaxCRM.model.Note;
import com.abaxconsulting.AbaxCRM.service.NoteService;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("")
    public ResponseEntity<?> newNote(@RequestBody @Valid @Nonnull Note note){
        if (note == null) {
            throw new IllegalArgumentException("Note is missing");
        } else{
            Note newNote = noteService.save(note);
            return new ResponseEntity<Note>(newNote, HttpStatus.CREATED);
        }
    }

    @GetMapping("/{noteID}")
    public ResponseEntity<?> getNote(@Valid @Nonnull @PathVariable UUID noteID){
        Optional<Note> existingNote = noteService.getNote(noteID);
        if (existingNote.isEmpty()){
            throw new NoteNotFoundException("The Note ID '"+noteID+"' does not exist");
        } else {
            return new ResponseEntity<Note>(existingNote.get(), HttpStatus.OK);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAllNotes(){
        Iterable<Note> notesList = noteService.getAllNotes();
        if (((ArrayList) notesList).size() == 0){
            throw new NoteNotFoundException("There are not any Note yet");
        } else {
            return new ResponseEntity<Iterable<Note>>(notesList, HttpStatus.OK);
        }
    }

    @PatchMapping("/{noteID}")
    public ResponseEntity<?> updateNote(@RequestBody @Valid @Nonnull Note note,
                                            @Valid @Nonnull @PathVariable UUID noteID){
        if (note == null) {
            throw new IllegalArgumentException("Note is missing");
        } else if (noteID == null) {
            throw new IllegalArgumentException("Note ID is missing");
        } else {
            Optional<Note> existingNote = noteService.getNote(noteID);
            if (existingNote.isEmpty()){
                throw new NoteNotFoundException("The Note ID '"+noteID+"' does not exist");
            } else {
                int response = noteService.update(noteID, note);
                return new ResponseEntity<Note>(HttpStatus.OK);
            }
        }
    }

    @DeleteMapping("/{noteID}")
    public ResponseEntity<?> deleteNote(@Valid @Nonnull @PathVariable UUID noteID){
        if (noteID == null) {
            throw new IllegalArgumentException("Note ID is missing");
        } else {
            Optional<Note> existingNote = noteService.getNote(noteID);
            if (existingNote.isEmpty()){
                throw new NoteNotFoundException("The Note ID '"+noteID+"' does not exist");
            } else {
                noteService.delete(existingNote.get().getId());
                return new ResponseEntity<Note>(HttpStatus.OK);
            }
        }
    }

}