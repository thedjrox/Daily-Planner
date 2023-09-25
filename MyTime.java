
/**
 * The class represents the time specifically the hour and minute for the start and end time of the event.
 * @author Danielle Joseph
 *
 */
public class MyTime implements Comparable<MyTime> {
	
	/**
	 * instance variable hour representing time.
	 */
	private int hour;
	
	/**
	 * instance variable hour representing time.
	 */
	private int min;
	
	/**
	 * parameterless constructor initializes the hour and min to zero.
	 */
	public MyTime(){
		
		this.hour = 0;
		this.min = 0;
	}
	
	/**
	 * Constructor initializes hour if the value is valid.
	 * min is initialized to zero
	 * @param hour integer value
	 */
	public MyTime(int hour){
		
		if (hour > 23 || hour < 0) {
			
			throw new  IllegalArgumentException("Hour must be within [0, 23]!");
		}
		
		this.hour = hour;
		this.min = 0;

	}
	
	/**
	 * Constructor initializes hour and min if the values are valid.
	 * @param hour integer value
	 * @param min minute
	 */
	public MyTime(int hour, int min){
		
		if (hour > 23 || hour < 0) {
			
			throw new  IllegalArgumentException("Hour must be within [0, 23]!");
		}
		
		if (min > 59 || min < 0) {
			
			throw new  IllegalArgumentException("Minute must be within [0, 59]!");
		}
		
		this.hour = hour;
		this.min = min;
	}
	
	/**
	 * method returns the hour.
	 * @return hour
	 */
	public int getHour(){
		
		
		return this.hour;
		
		 
	}
	/**
	 * method returns the minutes.
	 * @return minute
	 */
	public int getMin(){
		
		
		return this.min; 
	}
	
	/**
	 * Method compares the this MyTime object with another one. 
	 * If the other MyTime obj is valid, then it returns 0 if they're the same, -1 
	 * if the other MyTime object is greater, and 1 if the MyTime obj is less.
	 * @return integer value that indicates less than, equal to, or greater than. Default return is also included.
	 */
	@Override 
	public int compareTo(MyTime otherTime){
		
		if (otherTime == null) {
			
			throw new IllegalArgumentException("Null Time object!");
		}
		
		Integer hour1 = this.hour;
		Integer min1  = this.min;
		
		if (hour1.compareTo(otherTime.hour) == 0 && min1.compareTo(otherTime.min) == 0) {
			
			return 0;
		}
		
		if (hour1.compareTo(otherTime.hour) < 0) {
			
			return -1;
			
		}
		
		else {
		
			if (hour1.compareTo(otherTime.hour) == 0) {
			
				if (min1.compareTo(otherTime.min) < 0) {
				
					return -1;
				
				}
			
				else {
				
					return 1;
				
				}
			}
		}
		
		//default return
		return 1;	
	}

	/**
	 * Method gets the duration between the start time and end time. If the end time is valid, subtract end time from start time to get duration.
	 * @param endTime the ending time for the event
	 * @return the in between time from start time to end time
	 */
	public int getDuration(MyTime endTime){
		int minDuration = 0;
		if (endTime == null) {
			
			throw new IllegalArgumentException("Null Time Object");
		}
		
		if (this.compareTo(endTime) > 0) {
			
			return -1;
			
		}
		
		//if the minutes in end time is greater than start time minutes
		//avoids getting negative numbers
		if (endTime.min >= this.min) {
			
			minDuration = (endTime.hour - this.hour)*60;
			minDuration += endTime.min - this.min;
			
			
		}
		else if(endTime.min < this.min) {
			
			endTime.min += 60;
			endTime.hour -= 1;
			
			//convert to military time
			minDuration = (endTime.hour + 12 - this.hour + 12)*60;
			minDuration += endTime.min - this.min;
			
			
		}
		
		return minDuration;	
	}
	
	/**
	 * Method returns the end Time with the given duration. 
	 * If the duration is valid, then end Time is converted to minutes.
	 * Then adds the duration to start time, and then converts it to 00:00 time.
	 * @param duration the in between time
	 * @return the end time
	 */
	public MyTime getEndTime(int duration){
		
		if (duration < 0) {
			
			throw new IllegalArgumentException("Duration must be non-negative!");
			
		}
		
		int minutes = this.hour *60;
		minutes += this.min;
		minutes += duration;
		
		int endHour = minutes/60;
		int endMin = minutes - (endHour * 60);
		
		
		if (endHour > 23 || endMin > 59) {
			
			return null;
		}
		
		MyTime time2 = new MyTime(endHour, endMin);
		
		return time2; 	
	}
	
	/**
	 * Method creates a string in time format 00:00 and returns it.
	 * @return the string
	 */
	public String toString() {
		//%02 to format double digit number ex. 09, 23
		String str1 = String.format("%02d", this.hour);
		String str2 = String.format("%02d", this.min);
		String totalStr = str1 + ":" + str2;
		
		return totalStr; 
	}
	
	/**
	 * empty main method.
	 * @param args empty
	 */
	public static void main(String[] args){
		
		
	}
}