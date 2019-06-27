/* Created by Lennu Metsäranta. */

package fi.academy.junakonsoli;

import java.util.Date;

// Luokka on TimeTableRow-luokan apuluokka.
public class TrainReady {
    String source;
    boolean accepted;
    Date timestamp;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
