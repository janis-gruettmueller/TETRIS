import java.io.Serial;
import java.io.Serializable;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    // Attribute
    private String username;
    private long highscore;

    public User(String username) {
        this.username = username;
        this.highscore = 0;
    }


    public void setHighscore(long highscore) {
        this.highscore = highscore;
    }

    public String getUsername() {
        return username;
    }
    public long getHighscore() {
        return highscore;
    }
}
