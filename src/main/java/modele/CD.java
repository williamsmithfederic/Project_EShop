package modele;

public class CD {
    private int id;
    private String album;
    private String artist;
    private String country;
    private float price;
    private int quantity;

    public CD() { this.quantity = 1; }

    public CD(String album, String artist, float price) {
        this.album = album;
        this.artist = artist;
        this.price = price;
        this.quantity = 1;
    }

    public CD(String album, String artist, String country, float price, int quantity) {
        this.album = album;
        this.artist = artist;
        this.country = country;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters / Setters
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getAlbum() { return album; }
    public void setAlbum(String album) { this.album = album; }

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return album + " - " + artist + " (" + price + "$ x " + quantity + ")";
    }
}
