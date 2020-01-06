package si.rso.ratings.lib;

public class Rating extends BaseType {

    private String productId;
    
    private int ratingNumber;
    
    private String comment;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
