package ru.oks.spring.MVC.DTO;

/**
 * ДТО для определения разницы во времени.
 */
public class TimeDifferenceDTO {
    /**
     * Текущее время.
     */
    private Long currentTime;
    /**
     * Разница во времени
     */
    private long timediff;

    public Long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Long currentTime) {
        this.currentTime = currentTime;
    }

    public long getTimediff() {
        return timediff;
    }

    public void setTimediff(long timediff) {
        this.timediff = timediff;
    }
}
