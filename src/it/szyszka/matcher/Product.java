package it.szyszka.matcher;

/**
 * Created by E6520 on 2017-04-08.
 */
public class Product implements Comparable{
    private String productName;
    private int productFitness;

    public Product() {
    }

    public Product(String productName, int productFitness) {
        this.productName = productName;
        this.productFitness = productFitness;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductFitness() {
        return productFitness;
    }

    public void setProductFitness(int productFitness) {
        this.productFitness = productFitness;
    }

    @Override
    public String toString() {
        return "it.szyszka.matcher.Product{" +
                "productName='" + productName + '\'' +
                ", productFitness=" + productFitness +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Product) {
            if(((Product)o).productFitness > this.productFitness) {
                return 1;
            } else if(((Product)o).productFitness == this.productFitness) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
}
