
package finalgroupproject.cs.pkg2141;

public class Music {
    
    private String genre;
    private String country;
    private String label;
    private String artist;
    private String album;
    private String song;
    private String releaseDate;
    private int reviewScore;
    
    public Music(String genre, String country, String label, String artist, String album, String song, String releaseDate, int reviewScore){
        this.genre = genre;
        this.country = country;
        this.label = label;
        this.artist = artist;
        this.album = album;
        this.song = song;
        this.releaseDate = releaseDate;
        this.reviewScore = reviewScore;
    }
    
    public String getGenre(){
        return genre;
    }
    public String getCountry(){
        return country;
    }
    public String getLabel(){
        return label;
    }
    public String getArtist(){
        return artist;
    }
    public String getAlbum(){
        return album;
    }
    public String getSong(){
        return song;
    }
    public String getReleaseDate(){
        return releaseDate;
    }
    public int getReviewscore(){
        return reviewScore;
    }
    
}
