/**
 * The class represents a planner containing an array of events.
 * @author Danielle Joseph
 *
 */
public class Planner{

	/**
	 * instance variable representing a MySortedArray called events.
	 */
	private MySortedArray<Event> events;
	
	
	/**
	 * Parameterless constructor that creates a list of events including assigning the default capacity.
	 */
	public Planner(){
		 
		events = new MySortedArray<Event>();
	
	}
	
	
	/**
	 * Returns the number of events.
	 * @return number of events
	 */
	public int size(){
		
		
		return events.size();
			
	}
	
	/**
	 * Returns a formated string containing each event with its index if there is at least one event.
	 * @return a string describing all the events in an ordered list
	 */
	public String toString(){
		
	    if(events.size()==0) {
	    	return "";
	    }
		StringBuilder str = new StringBuilder();
		for(int i = 0; i < size() - 1; i++) {
		
			str.append("["+ i +"]" + events.get(i) + "\n");
		
		}
		
		//add last event separately to prevent a new line
		str.append("["+ (size() - 1) +"]" + events.get(size() - 1));
		
		return str.toString();
	}
	
	/**
	 * Method adds an event to planner if it is valid using the add method from MySortedArray.
	 * @param event obj to be added to array of events
	 */
	public void addEvent(Event event){
		if (event == null) {
			
			throw new IllegalArgumentException("Null Event object!");
			
		}
					
		events.add(event);
	}
	
	/**
	 * Helper method that checks the placement of an event, specifically if its starting time is sorted correctly in ascending order with the other events.
	 * @param index  the event at the spot
	 * @return boolean value of whether event is sorted correctly
	 */
	private boolean check_placement(int index) {
		
		if (index + 1 == size()) {
			
			if (events.get(index - 1).compareTo(events.get(index)) <= 0) {
				
				return true;
			}
			
		}
			
		else if(index == 0) {
				
			if (events.get(index + 1).compareTo(events.get(index))  >= 0) {
					
				return true;
				
			}
			
		}
			
		else {
			
			if ( events.get(index - 1).compareTo(events.get(index)) <= 0 && events.get(index + 1).compareTo(events.get(index)) >= 0) {
				
				return true;
			}
			
		}
			
				
		return false;
	}
		
		

	/**
	 * Method moves the start time of an event if the given values are valid.
	 * It calls moveStart to move the starting time then checks if the vent is sorted correctly with check_placement method.
	 * If the order of the even should not be changed, just remove it, edit the starting time, then add it back in events
	 * @param index the event at that index
	 * @param newStart the new starting time for the event
	 * @return boolean value of whether or not changing the starting time of an event was successful
	 */
	public boolean moveEvent(int index, MyTime newStart){
		
		if (index >= size() || index < 0 || size() == 0) {
			
			return false;
		}
		
		if (newStart == null) {
			
			return false;
		}
		
		
		boolean check = events.get(index).moveStart(newStart);
		
		if (check == false) {
			
			return false;
		}
		
		//if the event is still sorted correctly
		if (check_placement(index) == false) {
				
			Event temp = events.get(index);
				
			events.delete(index);
				
			events.add(temp);
		}
		
		return true; 
	}

	/**
	 * Method changes the duration of an event at a specific index if the given values are valid.
	 * @param index the event at that spot
	 * @param minute the duration of the event
	 * @return boolean value of whether the duration change of the event is successful or not
	 */
	public boolean changeDuration (int index, int minute){
		
		if (index >= size() || index < 0 || size() == 0) {
			
			return false;
		}
		
		if (minute < 0) {
			
			return false;
		}
		
		//calls changeDuration method from Event, if valid return true
		events.get(index).changeDuration(minute);
	
		return true; 	
	}

	/**
	 * Method changes the description of an event at a certain index if the given values are valid. 
	 * If the given description is null, return an empty string for description.
	 * @param index the event at the index
	 * @param description what the event is
	 * @return boolean value whether or not the changing the description was successful or not
	 */
	public boolean changeDescription(int index, String description){
		if (index >= size() || index < 0 || size() == 0) {
			
			return false;
		}
		
		
		if (description == null) {
			
			events.get(index).setDescription("");
			return true;
			
		}
		
		events.get(index).setDescription(description);
	
		return true; 
	}
	
	/**
	 * Method removes an event at a specific index if the given index is valid.
	 * method calls the delete method from MySortedArray to delete the event.
	 * @param index the vent at that specific index
	 * @return boolean value whether or not the removal of an event was successful or not
	 */
	public boolean removeEvent(int index){
		
		if (index >= size() || index < 0 || size() == 0) {
			
			return false;
			
		}
		
		events.delete(index);

		return true;
	}
	
	/**
	 * Method gets the event at the given index if the value is valid.
	 * @param index the even at that index
	 * @return the event
	 */
	public Event getEvent(int index){
		
		if (index >= size() || index < 0 || size() == 0) {
			
			return null;
		}
		
		else {
			
			return events.get(index);
		}
	}
	
	/**
	 * empty main method.
	 * @param args empty
	 */
	public static void main(String[] args){
	
	
	}
}