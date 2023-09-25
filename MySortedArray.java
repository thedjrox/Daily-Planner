/**
 * The class represents a sorted array list that does several actions such as adding, removing, and replacing elements and increasing or decreasing capacity when needed
 * all while keeping the array sorted.
 * @author Danielle Joseph
 * @param <T> elements of the array that can be any type
 */
public class MySortedArray<T extends Comparable<T>> {
	
	
	/**
	 * default and minimum capacity for array.
	 */
	
	private static final int DEFAULT_CAPACITY = 2;

	/**
	 * instance variable data which can be any type.
	 */
	
	private T[] data;
	/**
	 * instance variable size which is number of items.
	 */
	private int size;

	
	/**
	 * no parameter constructor initializes the capacity and size of array.
	 */
	@SuppressWarnings("unchecked")
	public MySortedArray() {
		
		
		data = (T[]) new Comparable[DEFAULT_CAPACITY];
		size = 0;
		
	}
	
	/**
	 * Constructor initializes the capacity of array.
	 * if initial capacity is less than the default capacity, an exception is thrown.
	 * @param initialCapacity the length of the array
	 */
	@SuppressWarnings("unchecked")
	public MySortedArray(int initialCapacity) {
		
		data = (T[]) new Comparable[initialCapacity];
		
		
		
		if (initialCapacity < 2) {
			
			throw new IllegalArgumentException("Capacity must be at least 2!");
		}
		
	}
	
	/**
	 * Returns number of items in the array.
	 * @return size number of elements
	 */
	public int size() {	
		return size;

	}  
	/**
	 * Returns the potential size of the array.
	 * @return the length of data
	 */
	public int capacity() {
		
		return data.length; 
	}
	
	/**
	 * Method adds value into array if the given value is valid and adds in a way that is still ascending order. 
	 * Doubles capacity if necessary.
	 * Method checks if element is to be added at the end, or beginning, or somewhere in the middle of the array and shifts based on where it is.
	 * @param value the generic type input
	 */
	public void add(T value){
		
		if (value == null) {
			
			
			throw new IllegalArgumentException("Cannot add: null value!");
			
		}
			
		if (size == data.length) {
				
				
			boolean full = doubleCapacity();
				
			if (full == false) {
					
				throw new IllegalStateException("Cannot add: capacity upper-bound reached!");
				
			}
		}
				
		int x;
					
		for (x= 0; x < size; x++) {
							
			if (data[x].compareTo(value) > 0) {
				
				//break when an index to put value in is found
				break;
				
			}
						
		}
		
		//if its not the last element in the array
		if (x < size) {
		
		
			for (int j = size - 1; j >= x; j--) {
					
				data[j + 1] = data[j];
								
			}
						
			data[x] = value;
			size++;
		}
						
		else {
						
			data[x] = value;
			size++;
		}			
	}
			
	
	/**
	 * Method gets the value at the given index if the index is valid.
	 * @param index the data at the index
	 * @return the value at the index
	 */
	public T get(int index) {
		
		if (index >= size || index < 0 || size == 0) {
			
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
			
		}
		
		else {
			
			return data[index];
			
		}

	}
	
	/**
	 * Method replaces a value at a certain index if the index and value is valid. The array will stay sorted after the replacement.
	 * @param index the index that the  replacement value will be put in
	 * @param value the new value
	 * @return a boolean value indicating whether or not the replacement was successful
	 */
	public boolean replace(int index, T value) {
		
		if (index >= size || index < 0 || size == 0) {
			
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
			
		}
		
		else if (value == null) {
			
			throw new IllegalArgumentException("Cannot add: null value!");
			
		}
		
		else {
			//if index is referring to the last element, just check the value before it
			if (index + 1 == size) {
				
				if (value.compareTo(data[index - 1]) >= 0){
					
					data[index] = value;
					
					return true;
	
				}
				
				else {
					
					return false;
				}
			}
			
			//if index is referring to the first element, just check the element after it
			else if(index == 0){
				
				if (value.compareTo(data[index + 1]) <= 0){
				
					data[index] = value;
					
					return true;
	
				}
				
				else {
					
					return false;
				}
				
			}
			//if index is somewhere in between the array
			else if (value.compareTo(data[index - 1]) >= 0 && value.compareTo(data[index + 1]) <= 0) {
				
				data[index] = value;
				
				return true;
			}
			
			else {
				
				return false;
			}
		}
		
	}
	
	/**
	 * Method adds value at a specific index if the index and value is valid. It is added in a way where the array will stay sorted and increases capacity if necessary.
	 * If addition is successful return true else return false.
	 * @param index where the value will be put
	 * @param value the new value to be added to the array
	 * @return boolean value if the addition of the value is successful or not
	 */
	public boolean add(int index, T value) {
		
		
		if (index > size || index < 0) {
			
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
			
		}
		
		else if (value == null) {
			
			throw new IllegalArgumentException("Cannot add: null value!");
			
		}
		
		else {
			// if there is no elements currently
			if (size == 0 && size == index) {
				
				data[index] = value;
				size++;
				return true;
			}
			//if the new value will be added at the end of the array
			else if(index == size) {
		
				if (value.compareTo(data[index - 1]) >= 0){
					
					
					if (size == data.length) {
						
						
						boolean check = doubleCapacity();
						
						if (check == false) {
							
							
							throw new IllegalStateException("Cannot add: capacity upper-bound reached!");
						}
						
					}
					
					
					data[index] = value;
					size++;
					return true;
				}
				
			}
				
			
			else if (index == 0){
				
				if (value.compareTo(data[index + 1]) <= 0){
					
					
					if (size == data.length) {
						
						
						boolean full = doubleCapacity();
						
						if (full == false) {
							
							
							throw new IllegalStateException("Cannot add: capacity upper-bound reached!");
						}	
							
					}
						
					
					
					for (int j = size - 1; j >= index; j--) {
						
						data[j + 1] = data[j];
						//shift elements to the right	
					}
					
					data[index] = value;
					size++;
					return true;
					
				}
				
			}
			
			else {
				//if the index is somewhere in the middle, compare the elements before and after
				if (value.compareTo(data[index - 1]) >= 0 && value.compareTo(data[index + 1]) <= 0) {
					
					//if at capacity
					if (size == data.length) {
					
						
						boolean check = doubleCapacity();
						
						if (check == false) {
							
							
							throw new IllegalStateException("Cannot add: capacity upper-bound reached!");
						}
							
					}
						
					//shift elements to right
					for (int j = size - 1; j >= index; j--) {
						
						data[j + 1] = data[j];
							
					}
						
					data[index] = value;
					size++;
					return true;
					
				}
			}

		}
		
		return false;
	}
	
	/**
	 * Method removes an element at a certain index if the index is valid and reduces capacity by half if necessary.
	 * @param index the place that contains the value to be removed
	 * @return the value removed
	 */
	public T delete(int index) {
		
		T element = data[index];

		if (index >= size || index < 0 || size == 0) {
			
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
			
		}
		
		else {
			
			data[index] = null;
			
			//if the value is not at the end of the array, shift all elements to the left
			if (index < size - 1) {
			
				
				for (int j = index + 1; j < size; j++) {
					
					data[j - 1] = data[j];
					
				}
				//makes the empty slot equal to null
				data[size - 1] = null;
				
			}
			
			size--;
			
			//if the number of elements is less than 1/3 of the capacity
			if(size <= (capacity()/3) ) {
				
				boolean check = halveCapacity();
				
				if (check == false) {
					
					
					throw new IllegalStateException("Cannot remove: capacity upper-bound reached!");
				}
			}		
			
		}
		
		return element;
	}  

	/**
	 * Method doubles the capacity of the array if the capacity is reached and more is needed.
	 * @return boolean value whether the expansion was successful or not
	 */
	@SuppressWarnings("unchecked")
	public boolean doubleCapacity(){
		
		if (size == Integer.MAX_VALUE - 50) {
			
			return false;
		}
		
		int newCap = size * 2;
		
		
		if (newCap > Integer.MAX_VALUE - 50) {
			
			newCap = Integer.MAX_VALUE - 50;
		}
		
		//create a new array with the doubled size
		T[] newData = (T []) new Comparable[newCap];

		for (int i = 0; i < size; i++) {
			
			newData[i] = data[i];
			
				
		}
		//data now references new_data
		this.data = newData;
	
		return true; 
	}
	

	/**
	 * Method halves the capacity of the array if the size is less than 1/3 the capacity.
	 * @return boolean value whether the shrinking was successful or not
	 */
	@SuppressWarnings("unchecked")
	public boolean halveCapacity(){
		
		int newCap = (data.length/2);
	
		
		if (newCap < DEFAULT_CAPACITY) {
			
			newCap = DEFAULT_CAPACITY;
			
		}
		//if the number of elements is greater than the default capacity
		if (size > newCap) {
		
			return false;
			
		}
		
		else {
			
			T[] newData = (T []) new Comparable[newCap];
			
			for (int i = 0; i < size; i++) {
				
				newData[i] = data[i];
				
			}
			
			this.data = newData;
			
			return true;
		}
	}
	//******************************************************
	//*******     BELOW THIS LINE IS TESTING CODE    *******
	//*******      Edit it as much as you'd like!    *******
	//*******		Remember to add JavaDoc			 *******
	//******************************************************
	/**
	 * Method prints the current state of the array with every test.
	 * @return string representation of the array
	 */
	public String toString() {
		
		StringBuilder s = new StringBuilder("MySortedArray with " + size()
			+ " items and a capacity of " + capacity() + ":");
		for (int i = 0; i < size(); i++) {
			s.append("\n  ["+i+"]: " + get(i));
		}
		return s.toString();
		
	}

	/**
	 * empty main method.
	 * @param args empty
	 */
	public static void main(String[] args){
		

	}
	

}