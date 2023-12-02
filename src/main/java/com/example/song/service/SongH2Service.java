package com.example.song.service;

import com.example.song.repository.SongRepository;
import com.example.song.model.Song;
import com.example.song.model.SongRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;

@Service
public class SongH2Service implements SongRepository {
    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Song> getSongs() {
        return (ArrayList<Song>) db.query("select * from PLAYLIST", new SongRowMapper());
    }

    @Override
    public Song addSong(Song song) {
        db.update("insert into PLAYLIST (songName,lyricist,singer,musicDirector)values(?,?,?,?)", song.getSongName(),
                song.getLyricist(), song.getSinger(), song.getMusicDirector());

        Song savedSong = db.queryForObject(
                "select * from PLAYLIST where songName=? and lyricist=? and singer=? and musicDirector=?",
                new SongRowMapper(), song.getSongName(), song.getLyricist(), song.getSinger(), song.getMusicDirector());
        return savedSong;
    }

    @Override
    public Song getSongById(int songId) {
        try {
            Song song = db.queryForObject("select * from PLAYLIST where songId=?", new SongRowMapper(), songId);
            return song;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Song upadateSong(int songId, Song song) {
        if (song.getSinger() != null) {
            db.update("update PLAYLIST set singer=? where songId=?", song.getSinger(), songId);
        }
        return getSongById(songId);
    }

    @Override
    public void deleteSongById(int songId) {
        db.update("delete from PLAYLIST where songId=?", songId);
    }
}