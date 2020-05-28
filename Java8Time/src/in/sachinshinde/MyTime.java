package in.sachinshinde;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.swing.text.DateFormatter;

public class MyTime {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws Exception {
		
		dispalyTimeByInstantAndDuration();
		
		dispalyTimeByLocalDateAndPeriod();
		
		dispalyDateAdjuster();
		
		disaplayLocalTime();
		
		displayZonedTime();
		
		displayDateFormatter();
	}

	private static void dispalyTimeByInstantAndDuration() throws InterruptedException {
			//  ****  Instant & Duration
			Instant now = Instant.now();
			Thread.sleep(200);
			Instant end = Instant.now();
			
			Duration elapsed = Duration.between(now, end);
			long millis = elapsed.toMillis();

			System.out.println(millis);
	}
	
	private static void dispalyTimeByLocalDateAndPeriod() {
		// **** LocalDate 
		System.out.println();
		LocalDate dob = LocalDate.of(1990, Month.JANUARY, 27);
		System.out.println(dob);
		
		LocalDate curr = LocalDate.now();
		System.out.println(curr);
		
		Period time_elapsed = Period.between(dob, curr);
		Period elapsed = dob.until(curr);
		System.out.println("Days Elapsed: "+elapsed.getDays());
		System.out.println("Months Elapsed: "+elapsed.getMonths());
		System.out.println("Years Elapsed: "+time_elapsed.getYears());
		
		System.out.println("Number of days alive: "+dob.until(curr, ChronoUnit.DAYS));
		
	}

	private static void dispalyDateAdjuster() {
		System.out.println();
		
		LocalDate now = LocalDate.of(2020, Month.MAY, 28);
		System.out.println(" dayOfWeekInMonth-3rd-FRIDAY-ofthe-currentMonth: "+now.with(TemporalAdjusters.dayOfWeekInMonth(3, DayOfWeek.FRIDAY)));
		System.out.println(" firstDayOfMonth: "+now.with(TemporalAdjusters.firstDayOfMonth()));
		System.out.println(" firstDayOfNextMonth: "+now.with(TemporalAdjusters.firstDayOfNextMonth()));
		System.out.println(" firstDayOfNextYear: "+now.with(TemporalAdjusters.firstDayOfNextYear()));
		System.out.println(" firstDayOfYear: "+now.with(TemporalAdjusters.firstDayOfYear()));
		System.out.println(" firstInMonth-MONDAY: "+now.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));
		System.out.println(" lastDayOfMonth: "+now.with(TemporalAdjusters.lastDayOfMonth()));
		System.out.println(" lastDayOfYear: "+now.with(TemporalAdjusters.lastDayOfYear()));
		System.out.println(" lastInMonth-FRIDAY: "+now.with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY)));
		System.out.println(" next-SATURDAY: "+now.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)));
		System.out.println(" nextOrSame-THURSDAY: "+now.with(TemporalAdjusters.nextOrSame(DayOfWeek.THURSDAY)));
		System.out.println(" ofDateAdjuster-2 days later: "+now.with(TemporalAdjusters.ofDateAdjuster(date -> date.plusDays(2))));
		System.out.println(" previous-TUESDAY: "+now.with(TemporalAdjusters.previous(DayOfWeek.TUESDAY)));
		System.out.println(" previousOrSame-THURSDAY: "+now.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY)));

	}


	private static void disaplayLocalTime() {
		System.out.println();
		
		LocalTime now = LocalTime.now();
		LocalTime time_10_20 = LocalTime.of(10, 20);
		
		System.out.println("Current Time: "+now);
		System.out.println("10:20 time: "+time_10_20);
		
		LocalTime bedTime = LocalTime.of(23, 0);
		LocalTime wakeUpTime = bedTime.plusHours(7);
		
		System.out.println("Wake-Up Time: "+wakeUpTime);
	}
	
	private static void displayZonedTime() {
		System.out.println();
		
		// *** ZonedTime : https://www.iana.org/time-zones
		
		Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
		
		allZoneIds
			.stream()
			.filter(z -> z.startsWith("America"))
			.sorted()
			.forEach(System.out::println);
		
		Long numberofZoneIds = allZoneIds
									.stream()
									.count();
		
		System.out.println("\n numberofZoneIds: "+ numberofZoneIds);
		
		ZonedDateTime now = ZonedDateTime.now();
		
		ZoneId zoneId = now.getZone();
		System.out.println("My Zone Id:"+zoneId.getId());
		
		System.out.println();
		ZonedDateTime myUSPacificZoneDateTime = now.withZoneSameInstant(ZoneId.of("US/Pacific"));
		System.out.println("My local zone date-time: "+now);
		System.out.println("myUSPacificZoneDateTime: "+myUSPacificZoneDateTime);
		
		LocalDateTime myLocalDateTime = LocalDateTime.now();
		System.out.println("myHongKongZonedDateTime: "+ZonedDateTime.of(myLocalDateTime, ZoneId.of("Asia/Hong_Kong")));
		
		System.out.println();
		// One month later using ".plus()" operator on ZonedDateTime
		System.out.println("One Month later date: "+now.plusMonths(1));
		System.out.println("Totonto next month: "+now.plusMonths(1).withZoneSameInstant(ZoneId.of("America/Toronto")));

	}
	

	private static void displayDateFormatter() {
		System.out.println();
		
		ZonedDateTime lDate= ZonedDateTime.now();
		System.out.println("ISO_DATE format: "+
								DateTimeFormatter.ISO_DATE.format(lDate)
							);
		
		System.out.println("RFC_1123_DATE_TIME format: "+
								DateTimeFormatter.RFC_1123_DATE_TIME.format(lDate)
							);
	}

	

}
