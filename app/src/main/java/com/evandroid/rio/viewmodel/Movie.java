package com.evandroid.rio.viewmodel;

import com.evandroid.rio.ui.MovieListActivity;

import java.util.List;

/**
 * Created by allovince on 15/9/9.
 */
public class Movie extends com.evandroid.rio.entity.Movie {
    private Integer fill_color;

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + super.getId() + '\'' +
                ", title='" + super.getTitle() + '\'' +
                ", fill_color=" + fill_color +
                ", images=" + super.getImages() +
                '}';
    }

    public int getFill_color() {
        if (null != fill_color) {
            return fill_color;
        }
        int[] colors = MovieListActivity.colors;
        int colorIndex = ((int) (Math.random() * 100)) % colors.length;
        return fill_color = colors[colorIndex];
    }

    public void setFill_color(int fill_color) {
        this.fill_color = fill_color;
    }

    public ImagesEntity getImages() {
        if (super.getImages() == null) {
            super.setImages(new ImagesEntity());
        }
        return super.getImages();
    }
}
