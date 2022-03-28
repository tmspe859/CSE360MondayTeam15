public class Coupon {
    private String title;
    private Double percentOff;

    public Coupon(String title, Double percentOff) {
        this.title = title;
        this.percentOff = percentOff;
    }

    public String getTitle() { return this.title; }
    public Double getPercentOff() { return this.percentOff; }
}
