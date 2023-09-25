/**
 * The class represents an event including the start and end time for it.
 * @author Danielle Joseph
 *
 */
public class Event implements Comparable<Event> {
	
	/**
	 * instance MyTime variable representing the start time of the event.
	 */
	private MyTime startTime;
	/**
	 * instance MyTime variable representing the end time of the event.
	 */
	private MyTime endTime;
	/**
	 * instance MyTime variable representing the description of the event.
	 */
	private String description;
	
	/**
	 * Constructor initializes the start and end time if valid and initializes description to an empty string.
	 * @param startTime when the event begins
	 * @param endTime when the event ends
	 */
	public Event(MyTime startTime, MyTime endTime){
		
		if (endTime.compareTo(startTime) < 0 ) {
			
			throw new IllegalArgumentException("End Time cannot come before Start Time!");
			
		}
		
		if (endTime == null || startTime == null) {
			
			throw new IllegalArgumentException("Null Time object");
			
		
		}
		
		this.startTime = startTime;
		this.endTime = endTime;
		
		this.description = "";
		
	}
	
	/**
	 * Constructor initializes start time, end time, and description if the given values are valid.
	 * @param startTime when event begins
	 * @param endTime when event ends
	 * @param description what the event is
	 */
	public Event(MyTime startTime, MyTime endTime, String description){
		
		if (endTime.compareTo(startTime) < 0 ) {
			
			throw new IllegalArgumentException("End Time cannot come before Start Time!");
			
		}
		
		if (endTime == null || startTime == null) {
			
			throw new IllegalArgumentException("Null Time object");	
		}
		
		if (description == null) {
			
			this.description = "";
		}
		
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
	
	}
	
	/**
	 * Returns when the event starts.
	 * @return startTime
	 */
	public MyTime getStart(){
	
		return this.startTime; 
	}
	
	/**
	 * Returns when the event ends.
	 * @return endTime
	 */
	public MyTime getEnd(){
	
		return this.endTime; 
	}

	/**
	 * Returns the description of what the event is.
	 * @return description
	 */
	public String getDescription(){
		
		return this.description; 
	}
	
	/**
	 * Method compares other event's start times to the current event obj start time if the given event obj is valid. 
	 * If the start time for this event obj is less than the other start time return -1.
	 * If the other way around, return 1, otherwise return 0.
	 * @param otherEvent obj
	 * @return an integer values indicating less than, equal to, or greater than
	 */
	@Override 
	public int compareTo(Event otherEvent){
		
		if(otherEvent == null) {
			
			throw new IllegalArgumentException("Null Event object");
		}
		
		if (this.startTime.compareTo(otherEvent.startTime) < 0) {
			
			return -1;
			
		}
		
		else if ((this.startTime.compareTo(otherEvent.startTime) > 0)) {
			
			
			return 1;
		}

		//if events have same start times
		return 0; 

	}

	/**
	 * Method moves the starting time of an event, if the given value is valid, but keeps the same duration therefore updating the ending time.
	 * @param newStart the new starting time of the event
	 * @return boolean value of whether updating the end time was successful or not
	 */
	public boolean moveStart(MyTime newStart){
		if (newStart == null) {
			
			return false;
		}
		int duration = this.startTime.getDuration(endTime);
		
		
		this.startTime = newStart;
		
		//call getEndTime method with the duration to find the new end time
		MyTime newEnd = this.startTime.getEndTime(duration);
		
		//check if the return value from getEndtime is valid
		if (newEnd == null) {
			return false;
		}
		
		
		if (newEnd.getHour() > 23 || newEnd.getMin() > 59) {
			
			return false;
			
		}
		
		this.endTime = newEnd;

		return true; 
	}
	
	/**
	 * Method changes the duration of the event if the the given int value is valid, then calls the getEndtime method to get the new end time.
	 * Returns the end time if it is valid.
	 * @param minute the duration of the event
	 * @return the boolean value of whether or not updating the end time was successful or not
	 */
	public boolean changeDuration(int minute){
		
		if (minute < 0) {
			
			return false;
		}
		MyTime newEnd = this.startTime.getEndTime(minute);
		
		//check if the return value from getEndtime is valid
		if (newEnd == null) {
			
			return false;
		}
		
		if (newEnd.getHour() > 23 || newEnd.getMin() > 59) {
			
			return false;
			
		}
		
		this.endTime = newEnd;

		return true;
	
	}
	
	/**
	 * Method sets the description of the event if the given string value is valid, otherwise returns an empty string.
	 * @param newDescription the current description of the event
	 */
	public void setDescription(String newDescription){
		
		if (newDescription.equals(null)) {
			
			this.description = "";
			
		}
		
		this.description = newDescription;
	}
	
	/**
	 * Method returns a formated string containing the details of an event including the start time, end time, and description.
	 * @return the string
	 */
	public String toString(){
		
		
		String str = String.format("%s", this.getStart() + "-" + this.getEnd() +"/" + this.getDescription());

		return str;	
	}
	
	/**
	 * empty main method.
	 * @param args empty
	 */
	public static void main(String[] args){
		
	}

}