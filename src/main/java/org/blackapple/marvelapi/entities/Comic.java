package org.blackapple.marvelapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "comic")
public class Comic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "published")
    private Date published;
    @Column(name = "descript")
    private String description;
    @Lob
    @Column(name = "img")
    private byte[] img;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "comic_character1",
            joinColumns = {@JoinColumn(name="comic_id")},
            inverseJoinColumns = {@JoinColumn(name = "character1_id")}
    )
    Set<Character> characters = new HashSet<>();

    public Comic() {
    }

    public Comic(Long id, String title, String author, Date published, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.published = published;
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Date getPublished() {
        return published;
    }

    public String getDescription() {
        return description;
    }

    public Set<Character> getCharacters() {
        return characters;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comic comic = (Comic) o;
        return Objects.equals(id, comic.id) && Objects.equals(title, comic.title) && Objects.equals(author, comic.author) && Objects.equals(published, comic.published) && Objects.equals(description, comic.description) && Arrays.equals(img, comic.img);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, title, author, published, description);
        result = 31 * result + Arrays.hashCode(img);
        return result;
    }

    @Override
    public String toString() {
        return "Comic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", published=" + published +
                ", description='" + description + '\'' +
                ", img=" + Arrays.toString(img) +
                ", characters=" + characters +
                '}';
    }
}
