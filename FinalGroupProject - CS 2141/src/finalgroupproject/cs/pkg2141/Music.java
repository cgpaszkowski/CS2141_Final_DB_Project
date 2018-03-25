
package finalgroupproject.cs.pkg2141;

public class Music {
    
    private String genre;
    private String country;
    private String label;
    private String artist;
    private String album;
    private String song;
    private String format;
    private String releaseDate;
    private String releaseDesc;
    private int reviewScore;
    private String reviewComm;
    
    public Music(String genre, String country, String label, String artist, String album, String song, String format, String releaseDate, String releaseDesc, int reviewScore, String reviewComm){
        this.genre = genre;
        this.country = country;
        this.label = label;
        this.artist = artist;
        this.album = album;
        this.song = song;
        this.format = format;
        this.releaseDate = releaseDate;
        this.releaseDesc = releaseDesc;
        this.reviewScore = reviewScore;
        this.reviewComm = reviewComm;
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
    public String getFormat(){
        return format;
    }
    public String getReleaseDate(){
        return releaseDate;
    }
    public String getReleaseDesc(){
        return releaseDesc;
    }
    public int getReviewscore(){
        return reviewScore;
    }
    public String getReviewcomm(){
        return reviewComm;
    }
    
}
