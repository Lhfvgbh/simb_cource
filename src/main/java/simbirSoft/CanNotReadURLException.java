package simbirSoft;

public class CanNotReadURLException extends Exception {

    private String link;

    public String getURL() {
        return link;
    }

    public CanNotReadURLException(String message, String link) {
        super(message);
        this.link = link;
    }
}
