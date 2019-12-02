package si.rso.ratings.persistence;

import si.rso.ratings.lib.Comment;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ratings")
@NamedQueries(value = {
    @NamedQuery(name = RatingEntity.FIND_BY_PRODUCT, query = "SELECT r FROM RatingEntity r WHERE r.productId = :productId")
})
public class RatingEntity extends BaseEntity {

    public static final String FIND_BY_PRODUCT = "RatingEntity.findByProduct";

    @Column(name = "product_id")
    private String productId;
    
    @Column(name = "rating_number")
    private int ratingNumber;

//    @Column(name = "comments")
//    private List<Comment> comments;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getRatingNumber() {
        return ratingNumber;
    }

    public void setRatingNumber(int ratingNumber) {
        this.ratingNumber = ratingNumber;
    }

//    public List<Comment> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<Comment> comments) {
//        this.comments = comments;
//    }
}