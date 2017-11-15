import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.property.RRule;
import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.DateList;
import net.fortuna.ical4j.model.parameter.Value;

import java.text.ParseException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Arrays;

// Testing

/**
 * A sample class illustrating how to parse an iCalendar file using the 
 * iCal4j library
 * @author kingtin
 */
public class Scheduler {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws net.fortuna.ical4j.data.ParserException
     */
    public static void main(String[] args) throws IOException, ParseException, ParserException {
        
        //FileInputStream fin = new FileInputStream("data/hk_public_holidays_2017.ics");
        FileInputStream fin = new FileInputStream("data/King.ics");
        
        CalendarBuilder builder = new CalendarBuilder();
        Calendar calendar = builder.build(fin);
        
        // A Candidate Period of interest 
        Date periodStart = new DateTime("20170731T160000Z");    // HK time: 01 Aug 00:00
        Date periodEnd = new DateTime("20171231T155900Z");      // HK time: 31 Dec 23:59
                
        for (Iterator i = calendar.getComponents().iterator(); i.hasNext();) {
            Component component = (Component) i.next();
            System.out.println("Component <" + component.getName() + ">");

            for (Iterator j = component.getProperties().iterator(); j.hasNext();) {
                Property property = (Property) j.next();
                String[] filter = {"UID", "TRANSP", "DTSTAMP", "CREATED", "LAST-MODIFIED"};
                if (Arrays.asList(filter).contains(property.getName())) continue;
                if (property.getName().startsWith("X-")) continue;
                System.out.println("\tProperty [" + property.getName() + ", " + property.getValue() + "]");
                        
                for (Iterator k = property.getParameters().iterator(); k.hasNext();) {
                    Parameter param = (Parameter) k.next();
                    System.out.println("\t\tParameter {" + param.getName() + ", " + param.getValue() + "}");
                }
                
                if (property.getName().equals(Property.RRULE)) {
                    RRule rrule = new RRule(property.getValue());
                    Property dtStart = component.getProperty(Property.DTSTART);
                    String dtStartVal = dtStart.getValue();
                    Date seed;
                    Parameter p = dtStart.getParameter(Parameter.VALUE);
                    if (p != null && p.getValue().equals("DATE"))
                        seed = new Date(dtStartVal);
                    else
                        seed = new DateTime(dtStartVal);
                            
                    DateList list = rrule.getRecur().getDates(seed, periodStart, periodEnd, Value.DATE_TIME);
                    for (int n=0; n<list.size(); n++) {
                        DateTime date = (DateTime) list.get(n);
                        System.out.println("\t\t" + date.toString());
                    }
                }
            }
        }
    }
}
