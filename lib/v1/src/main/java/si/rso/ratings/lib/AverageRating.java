package si.rso.ratings.lib;

public class AverageRating {

    private String productId;

    private double averageRatingNumber;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getAverageRatingNumber() {
        return averageRatingNumber;
    }

    public void setAverageRatingNumber(double averageRatingNumber) {
        this.averageRatingNumber = averageRatingNumber;
    }
}