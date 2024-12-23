package MojaChata.pl.app.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity (name= "reviews")
public class Review {

    @Id
    @Column(name = "review_id")
    private long id;

    private String text;

    @NotBlank(message = "date posted is mandatory")
    @Column(name = "date_posted")
    private Date datePosted;

    @ManyToOne
    @JoinColumn(name = "cottage_id")
    @NotNull(message = "Cottage is mandatory")
    private Cottage cottage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "Author is mandatory")
    private User author;

    @NotNull(message = "grade is mandatory")
    private Integer grade;

    public Long getId() {
        return this.id;
    }

    public String getText() {
        return this.text;
    }

    public Cottage getCottage() {
        return this.cottage;
    }

    public User getAuthor() {
        return this.author;
    }

    public Integer getGrade() {
        return this.grade;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCottage(Cottage cottage) {
        this.cottage = cottage;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
