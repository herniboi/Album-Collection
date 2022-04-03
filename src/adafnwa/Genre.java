package adafnwa;

/**
 * creates the enum class Genre
 * @author Andy Li, Henry Lin
 */
public enum Genre {
    Classical, Country, Jazz, Pop, Unknown;

    /**
     * Turns genre to string
     * @param genre to become a string
     * @return string of genre
     */
    public String toString(Genre genre){
        if (genre == Classical){
            return "Classical";
        }
        else if (genre == Country){
            return "Country";
        }
        else if (genre == Jazz){
            return "Jazz";
        }
        else if (genre == Pop){
            return "Pop";
        }
        else {
            return "Unknown";
        }
    }
}
