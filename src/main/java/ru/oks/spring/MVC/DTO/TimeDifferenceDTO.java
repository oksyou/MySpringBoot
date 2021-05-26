package ru.oks.spring.MVC.DTO;

import lombok.Getter;
import lombok.Setter;

/**
 * ДТО для определения разницы во времени.
 */
@Getter
@Setter
public class TimeDifferenceDTO {
    /**
     * Текущее время.
     */
    private Long currentTime;
    /**
     * Разница во времени
     */
    private long timeDifference;
}
