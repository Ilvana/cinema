package kino.model.presentation;

import kino.model.entities.Movie;
import kino.model.entities.Screening;
import kino.model.entities.Theater;

import java.util.Date;

/**
 * Created by ekusundzija on 3/29/16.
 */
public class ScreeningViewModel {

    private Integer id;
    private Date timeBegin;
    private Date timeEnd;
    private Movie movie;
    private Theater theater;

    public ScreeningViewModel() {
    }

    public ScreeningViewModel(Screening screening) {
        this.id = screening.getId();
        this.timeBegin = screening.getTimeBegin();
        this.timeEnd = screening.getTimeEnd();
        this.movie = screening.getMovie();
        this.theater = screening.getTheater();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(Date timeBegin) {
        this.timeBegin = timeBegin;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }
}