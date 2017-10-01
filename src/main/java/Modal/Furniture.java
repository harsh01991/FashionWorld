package Modal;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.File;

public class Furniture {
    private int id;
    private String prodname,prodcomp,rating,price,imageurl;
    private File f ;
    public Furniture()
    {

    }

    public File getF() {
        return f;
    }

    public void setF(File f) {
        this.f = f;
    }

    @JsonProperty
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @JsonProperty
    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }
    @JsonProperty
    public String getProdcomp() {
        return prodcomp;
    }

    public void setProdcomp(String prodcomp) {
        this.prodcomp = prodcomp;
    }
    @JsonProperty
    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }
    @JsonProperty
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    @JsonProperty
    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
