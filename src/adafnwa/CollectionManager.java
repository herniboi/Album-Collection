package adafnwa;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * reads in inputs and completes the commands given
 * @author Andy Li, Henry Lin
 */
public class CollectionManager {
    private static final int FIRST_PARAMETER = 0;
    private static final int SECOND_PARAMETER = 1;
    private static final int THIRD_PARAMETER = 2;
    private static final int FOURTH_PARAMETER = 3;
    private static final int TOTAL_PARAMETERS = 4;
    Collection collection;

    /**
     * Driver for the project, takes in command lines
     */
    public void run(){
        System.out.println("Collection Manager starts running.");
        Scanner scanner = new Scanner(System.in);
        collection = new Collection();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            StringTokenizer tokenizer = new StringTokenizer(line, ",");
            while (tokenizer.hasMoreTokens()) {
                String command = tokenizer.nextToken();
                if (command.equals("A")) {
                    addAlbum(line, tokenizer);
                } else if (command.equals("D")) {
                    removeAlbum(line, tokenizer);
                } else if (command.equals("L")) {
                    lendAlbum(line, tokenizer);
                } else if (command.equals("R")) {
                    returnAlbum(line, tokenizer);
                } else if ((command.equals("PD") || command.equals("PG")
                            || command.equals("P")) && !tokenizer.hasMoreTokens()) {
                    display(line, command);
                } else if (command.equals("Q")) {
                    System.out.println("Collection Manager terminated.");
                    return;
                } else if (command.equals("\n")) {
                    continue;
                } else {
                    System.out.println("Invalid command!");
                }
            }
        }
    }

    /**
     * Adds album with its respective parameters and sets it to available
     * @param text
     * @param tokenizer
     */
    private void addAlbum(String text, StringTokenizer tokenizer) {
        int counter = FIRST_PARAMETER;
        String title = new String(), artist = new String(), genre = new String(), date = new String();
        while (tokenizer.hasMoreTokens()) {
            if (counter == FIRST_PARAMETER) {
                title = tokenizer.nextToken();
            } else if (counter == SECOND_PARAMETER){
                artist = tokenizer.nextToken();
            } else if (counter == THIRD_PARAMETER){
                genre = tokenizer.nextToken();
            }else if (counter == FOURTH_PARAMETER){
                date = tokenizer.nextToken();
            }
            counter++;
        }
        if (counter != TOTAL_PARAMETERS) {
            System.out.println("Invalid command!");
        }
        Album album;
        Date day = new Date(date);
        if (!day.isValid()) {
            System.out.println("Invalid Date!");
        } else {
            genre.toLowerCase();
            album = switch (genre) {
                case "classical" -> new Album(title, artist, Genre.Classical, day, true);
                case "country" -> new Album(title, artist, Genre.Country, day, true);
                case "jazz" -> new Album(title, artist, Genre.Jazz, day, true);
                case "pop" -> new Album(title, artist, Genre.Pop, day, true);
                default -> new Album(title, artist, Genre.Unknown, day, true);
            };

            if (collection.add(album)) {
                System.out.println(album.toString() + " >> added.");
            } else {
                System.out.println(album.toString() + " >> is already in the collection.");
            }
        }
    }

    /**
     * Looks for the album to be removed and removes it.
     * @param text
     * @param tokenizer
     */
    private void removeAlbum (String text, StringTokenizer tokenizer){
        int counter = FIRST_PARAMETER;
        String title = new String(), artist = new String(), genre;
        while (tokenizer.hasMoreTokens()) {
            if (counter == FIRST_PARAMETER) {
                title = tokenizer.nextToken();
            } else if (counter == SECOND_PARAMETER){
                artist = tokenizer.nextToken();
            }
            counter++;
        }
        if (counter != THIRD_PARAMETER){
            System.out.println("Invalid command!");
        }

        Album album = new Album (title, artist, Genre.Unknown, new Date("1/1/1111"), true);
        if (collection.remove(album)){
            System.out.println(album.titleToString() + " >> deleted.");
        } else {
            System.out.println(album.titleToString() + " >> is not in the collection.");
        }
    }

    /**
     * Function that finds the album to be lent out and lends it out
     * @param text
     * @param tokenizer
     */
    private void lendAlbum (String text, StringTokenizer tokenizer){
        int counter = FIRST_PARAMETER;
        String title = new String(), artist = new String(), genre;
        while (tokenizer.hasMoreTokens()) {
            if (counter == FIRST_PARAMETER) {
                title = tokenizer.nextToken();
            } else if (counter == SECOND_PARAMETER){
                artist = tokenizer.nextToken();
            }
            counter++;
        }
        if (counter != THIRD_PARAMETER){
            System.out.println("Invalid command!");
        }

        Album album = new Album (title, artist, Genre.Unknown, new Date("1/1/1111"), true);
        if (collection.lendingOut(album)){
            System.out.println(album.titleToString()  + " >> lending out and set to not available.");
        } else {
            System.out.println(album.titleToString()  + " >> is not available.");
        }
    }

    /**
     * Function that looks for the album to be returned and returns it
     * @param text
     * @param tokenizer
     */
    private void returnAlbum (String text, StringTokenizer tokenizer){
        int counter = FIRST_PARAMETER;
        String title = new String(), artist = new String(), genre;
        while (tokenizer.hasMoreTokens()) {
            if (counter == FIRST_PARAMETER) {
                title = tokenizer.nextToken();
            } else if (counter == SECOND_PARAMETER){
                artist = tokenizer.nextToken();
            }
            counter++;
        }
        if (counter != THIRD_PARAMETER){
            System.out.println("Invalid command!");
        }

        Album album = new Album (title, artist, Genre.Unknown, new Date("1/1/1111"), true);
        if (collection.returnAlbum(album)){
            System.out.println(album.titleToString()  + " >> returning and set to available.");
        } else {
            System.out.println(album.titleToString()  + " >> return cannot be completed.");
        }
    }

    /**
     * Prints the list of albums in the collection either chronologically, sorted by release date, or sorted by genre
     * @param text
     * @param command
     */
    private void display (String text, String command) {
        if (collection.isEmpty()) {
            System.out.println("The collection is empty!");
        } else if (command.equals("P")){
            System.out.println("*List of albums in the collection.");
            collection.print();
            System.out.println("*End of list");
        }
        else if (command.equals("PD")) {
            System.out.println("*Album collection by the release dates.");
            collection.printByReleaseDate();
            System.out.println("*End of list");
        } else {
            System.out.println("*Album collection by genre.");
            collection.printByGenre();
            System.out.println("*End of list");
        }
    }
}

