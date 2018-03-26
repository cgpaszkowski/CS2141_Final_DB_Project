
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
    
    public Music (String artist, String song, String album, String genre, String country){
        this.genre = genre;
        this.country = country;
        this.artist = artist;
        this.album = album;
        this.song = song;
    }
    
    public String getGenre(){
        return genre;
    }
    public String getCountry(){
        return country;
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
    
}
