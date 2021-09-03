package liquibase.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ISODateFormatTest {

    private ISODateFormat dateFormat = new ISODateFormat();

    @Test
    public void parseNullValue() throws Exception {
        assertNull(dateFormat.parse(null));
    }

    @Test
    public void isoDateFormatWithNoLeadingZeroFractions() throws Exception {
        Date date = dateFormat.parse("2012-09-12T09:47:54.664");
        assertEquals("2012-09-12T09:47:54.664", dateFormat.format(date));
    }

    @Test
    public void isoDateFormatWithLeadingZeroFractions() throws Exception {
        Date date = dateFormat.parse("2011-04-21T10:13:40.044");
        assertEquals("2011-04-21T10:13:40.044", dateFormat.format(date));
    }

    @Test
    public void isoDateFormatWithLeadingNoFractions() throws Exception {
        Date date = dateFormat.parse("2011-04-21T10:13:40");
        assertEquals("2011-04-21T10:13:40", dateFormat.format(date));
    }

    @Test
    public void isoDateFormatWithLeadingFractions() throws Exception {
        Date date = dateFormat.parse("2011-04-21T10:13:40.12");
        assertEquals("2011-04-21T10:13:40.12", dateFormat.format(date));
    }

    @Test
    public void isoDateFormatWithNoNanos() throws Exception {
        Date date = dateFormat.parse("2011-04-21T10:13:40");
        assertEquals("2011-04-21T10:13:40", dateFormat.format(date));
    }

    @Test
    public void isoDateFormatWithZoneOffsetWithoutNanos() throws Exception {
        Date date = dateFormat.parse("2021-08-24T09:51:26+02:00");
        String result = dateFormat.format(date);
        assertEquals("2021-08-24T09:51:26", result);
    }

    @Test
    public void isoDateFormatWithUTCTimeZone() throws Exception {
        String zonedDateTimeString = "2011-04-21T10:13:40.084004Z";
        Date date = dateFormat.parse(zonedDateTimeString);
        assertEquals(
           ZonedDateTime.parse( zonedDateTimeString ).toEpochSecond(),
           date.toInstant().getEpochSecond()
        );
    }

    @Test
    public void isoDateFormatWithESTTimeZone() throws Exception {
        String zonedDateTimeString = "2011-04-21T10:13:40.084004-05:00";
        Date date = dateFormat.parse(zonedDateTimeString);

        assertEquals(
           ZonedDateTime.parse( zonedDateTimeString ).toEpochSecond(),
           date.toInstant().getEpochSecond()
        );
    }

    @Test
    public void isoDateFormatWithLeadingNanoFractions() throws Exception {
        Date date = dateFormat.parse("2011-04-21T10:13:40.01234567");
        assertEquals("2011-04-21T10:13:40.01234567", dateFormat.format(date));
    }
}
