package si.rso.ratings.lib;

public class Rating extends BaseType {
    
    public static final String FIELD_PRODUCT_ID = "productId";
    public static final String FIELD_ID = "_id";
    public static final String FIELD_RATING_NUM = "ratingNumber";
    public static final String FIELD_COMMENT = "comment";

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
