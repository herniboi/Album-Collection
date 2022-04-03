package adafnwa;

/**
 * Stores all the parameters of an Album.
 * Compares if two albums are the same.
 * @author Andy Li, Henry Lin
 */
public class Album {
    private String title;
    private String artist;
    private Genre genre; //enum class; Classical, Country, Jazz, Pop, Unknown
    private Date releaseDate;
    private boolean isAvailable;

    /**
     * Constructor for an album object
     * @param title of the album
     * @param artist creating the album
     * @param genre of the ablbum represented by Genre object
     * @param releaseDate of the album represented by a Date object
     * @param isAvailable of the album representing if the album is being loaned out
     */
    public Album(String title, String artist, Genre genre, Date releaseDate, boolean isAvailable){
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.isAvailable = isAvailable;
    }

    /**
     * Compares title and album parameters of two albums
     * @param obj that is the album to be compared to
     * @return true if the two albums are the same and false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Album){
            Album comp = (Album) obj;
            return this.title.equals(comp.title) && this.artist.equals(comp.artist);
        }
       return false;
    }

    /**
     * Converts an album to string
     * @return the string form of the album
     */
    @Override
    public String toString() {
        String album = this.title + "::" + this.artist + "::" + genre.toString(genre)
                + "::" + this.releaseDate.toString() + "::";
        if (this.isAvailable){
            album += "is available";
        }
        else{
            album += "is not available";
        }
        return album;
    }

    /**
     * converts title and artist to a string
     * @return string form of title and artist
     */
    public String titleToString(){
        return this.title + "::" + this.artist;
    }

    /**
     * loans out the book and changes status to available
     * @return true if book is loanable and false if book is already lent out.
     */
    public boolean loanedOut(){
        if (this.isAvailable){
            this.isAvailable = false;
            return true;
        }
        return false;
    }

    /**
     * returns the book and changes status to available
     * @return true if book is returnable and false if book is not able to be returned
     */
    public boolean returnAlbum(){
        if (!this.isAvailable){
            this.isAvailable = true;
            return true;
        }
        return false;
    }

    /**
     * converts the genre class of an object to String
     * @return string form of the genre
     */
    public String getGenre(){
        return this.genre.toString();
    }

    /**
     * gets the date
     * @return the release date
     */
    public Date getDate(){
        return this.releaseDate;
    }
}
