package ru.oks.spring.mvc.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * ДТО для определения разницы во времени.
 */
@Getter
@Setter
public class TimeDifferenceDto {
    /**
     * Текущее время.
     */
    private long currentTime;
    /**
     * Разница во времени
     */
    private long timeDifference;
}
