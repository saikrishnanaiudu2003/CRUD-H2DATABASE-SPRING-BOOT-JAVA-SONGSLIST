package com.example.song.controller;

import com.example.song.model.Song;
import com.example.song.service.SongH2Service;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

@RestController
public class SongController {
    @Autowired
    public SongH2Service songService;

    @GetMapping("/songs")
    public ArrayList<Song> getSongs() {
        return songService.getSongs();
    }

    @PostMapping("/songs")
    public Song addSong(@RequestBody Song song) {
        return songService.addSong(song);
    }

    @GetMapping("/songs/{songId}")
    public Song getSongById(@PathVariable("songId") int songId) {
        return songService.getSongById(songId);
    }

    @PutMapping("/songs/{songId}")
    public Song upadateSong(@PathVariable("songId") int songId, @RequestBody Song song) {
        return songService.upadateSong(songId, song);
    }

    @DeleteMapping("/songs/{songId}")
    public void deleteSongById(@PathVariable("songId") int songId) {
        songService.deleteSongById(songId);
    }
}