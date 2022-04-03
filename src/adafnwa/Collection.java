package adafnwa;

/**
 * Add, remove, find, lend out, return, print album to the collection.
 * prints either in order by genre or release date.
 * stores in albums in array list for future use.
 * @author Andy Li, Henry Lin
 */
public class Collection {
    private static final int START_INDEX = 0;
    private static final int BEFORE = 0;
    private static final int NOT_FOUND = -1;
    private Album[] albums;
    private int numAlbums; //number of albums currently in the collection

    /**
     * Finds the album that needs to be found
     * @param album to be found
     * @return the index of the album if found and NOT_FOUND if not found
     */
    private int find(Album album) {
        for (int i = 0; i < numAlbums; i++){
            if (albums[i].equals(album)){
                return i;
            }
        }
        return NOT_FOUND;
    } //find the album index, or return NOT_FOUND

    /**
     * Increases the size of Collection by 4
     */
    private void grow() {
        Album[] newarray = new Album[numAlbums + 4];
        if (numAlbums >= 0) System.arraycopy(albums, 0, newarray, 0, numAlbums);
        albums = newarray;
    } //increase the capacity of the array list by 4

    /**
     * Add a new album to the collection
     * @param album to the added
     * @return true if album was added successfully and false otherwise
     */
    public boolean add(Album album) {
        if (numAlbums == 0){
            albums = new Album[4];
        }

        if (find(album) != NOT_FOUND){
            return false;
        }

        if (numAlbums % 4 == 0){ //if the number of albums is divisible by 4 you need to grow the arraylist by 4
            grow();
        }
        albums[numAlbums++] = album;
        return true;
    }

    /**
     * Finds the album to be removed and removes it
     * @param album to be removed
     * @return true if album is found removed and false otherwise
     */
    public boolean remove(Album album) {
        int index = find(album);
        if (index == NOT_FOUND){
            return false;
        }
        Album[] removedAlbum = new Album[albums.length];
        int counter = START_INDEX;
        for (int i = 0; i < numAlbums; i++){
            if (i != index){
                removedAlbum[counter] = albums[i];
                counter++;
            }
        }
        albums = removedAlbum;
        numAlbums--;
        return true;
    }

    /**
     * Finds the album to be lent out and lends it out
     * @param album to be lent out
     * @return true if album is found and lent out and false if otherwise
     */
    public boolean lendingOut(Album album) {
        int index = find(album);
        if (index == NOT_FOUND){
            return false;
        }
        return albums[index].loanedOut();
    } //set to not available

    /**
     * finds the album to be returned and returns it
     * @param album to be returned
     * @return true if album is found and returned and false if otherwise
     */
    public boolean returnAlbum(Album album) {
        int index = find(album);
        if (index == NOT_FOUND){
            return false;
        }
        return albums[index].returnAlbum();
    } //set to available

    /**
     * prints the contents of the collection
     */
    public void print() {
        for (int i = 0; i < numAlbums; i++){
            System.out.println(albums[i].toString());
        }
    } //display the list without specifying the order

    /**
     * prints album by release date
     */
    public void printByReleaseDate() {
        for (int i = 0; i < numAlbums - 1; i++){
            int minIndex = i;
            for (int j = i + 1; j < numAlbums; j++){
                if (albums[j].getDate().compareTo(albums[minIndex].getDate()) < BEFORE ){
                    minIndex = j;
                }
            }
            Album temp = albums[minIndex];
            albums[minIndex] = albums[i];
            albums[i] = temp;
        }
        for (int i = 0; i < numAlbums; i++){
            System.out.println(albums[i].toString());
        }
    }

    /**
     * prints album by Genre
     */
    public void printByGenre() {
        for (int i = 0; i < numAlbums - 1; i++){
            int minIndex = i;
            for (int j = i + 1; j < numAlbums; j++){
                if (albums[j].getGenre().compareTo(albums[minIndex].getGenre()) < BEFORE ){
                    minIndex = j;
                }
            }
            Album temp = albums[minIndex];
            albums[minIndex] = albums[i];
            albums[i] = temp;
        }
        for (int i = 0; i < numAlbums; i++){
            System.out.println(albums[i].toString());
        }
    }

    /**
     * checks if the collection is empty
     * @return true if there is no albums and false otherwise
     */
    public boolean isEmpty() {
        if (numAlbums == 0) {
            return true;
        }
        return false;
    }
}
