package MojaChata.pl.app.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity (name= "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviewSeq")
    @SequenceGenerator(name = "reviewSeq", sequenceName = "REVIEWS_SEQ")
    @Column(name = "review_id")
    private long id;

    private String text;

    @Column(name = "date_posted")
    private Date datePosted;

    @ManyToOne
    @JoinColumn(name = "cottage_id")
    @NotNull(message = "Cottage is mandatory")
    private Cottage cottage;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @NotNull(message = "Author is mandatory")
    private User author;

    @NotNull(message = "grade is mandatory")
    private Integer grade;

    public Review(){}

    public Review(Cottage cottage, User author) {
        this.cottage = cottage;
        this.datePosted = new java.sql.Date(System.currentTimeMillis());
        this.author = author;
        this.grade = 0;
    }


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

    public Date getDatePosted() {
        return datePosted;
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

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    @Override
    public String toString() {
        return "Review [id=" + id + ", text=" + text + ", datePosted=" + datePosted + ", cottage=" + cottage
                + ", author=" + author + ", grade=" + grade + "]";
    }

}
