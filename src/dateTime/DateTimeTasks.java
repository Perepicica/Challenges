package dateTime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeTasks {
    public static void main(String[] args) {
        format();
        toMoscowTime(LocalDateTime.now(), "America/New_York");
    }

    /**
     * требуемый формат: 2007-M5-W3-Mon:21:27
     * описание примера требуемого формата: 2007 год, 5 месяц, 3 неделя месяца, понедельник, 21 час, 27 минут
     * использовать локаль Locale.UK
     *
     * @return DateTimeFormatter
     */
    static private DateTimeFormatter format() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-'M'MM-'W'W-EEE:H:m", Locale.UK);
        String text = date.format(formatter);
        System.out.println(text);
        return null;
    }

    /**
     * необходимо дату и время преобразовать к московским;
     * рекомендуется применять следующие классы: LocalDateTime, ZoneId, ZoneOffset, Instant.
     *
     * @param localDateTime локальная дата и время
     * @param zoneName      временная зона
     */
    static private LocalDateTime toMoscowTime(LocalDateTime localDateTime, String zoneName) {
        ZonedDateTime zonedDateTimeInUTC = localDateTime.atZone(ZoneId.of(zoneName));
        ZonedDateTime zonedDateTimeInEST = zonedDateTimeInUTC.withZoneSameInstant(ZoneId.of("Europe/Moscow"));
        return zonedDateTimeInEST.toLocalDateTime();
    }

}
