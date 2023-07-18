import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.*;
import java.io.*;

class vural_ManageCarData implements ManageCarDataFunctions {
	// Fields
	private final ArrayList<CarFunctions> carList;
	private final PriorityQueue<CarFunctions> carListByTotalRange;
	private final PriorityQueue<CarFunctions> carListByRemainingRange;

	// Constructor
	public vural_ManageCarData() {
		// instantiate the fields
		carList = new ArrayList<CarFunctions>();
		carListByTotalRange = new PriorityQueue<CarFunctions>(new TotalRangeComparator());
		carListByRemainingRange = new PriorityQueue<CarFunctions>(new RemainingRangeComparator());
	}

	// method the read the input file and populate the Arraylist and two
	// PriorityQueues
	public void readData(String filename) {
		try {
			// #1: Open the input file as a BufferedReader
			java.io.BufferedReader input = new java.io.BufferedReader(
					new java.io.InputStreamReader(new java.io.FileInputStream(filename)));

			// #2: Loop over the lines of the input file:
			String inn;
			while ((inn = input.readLine()) != null) {
				// data is delimitted by tabs
				StringTokenizer text_lines = new StringTokenizer(inn, "\t");

				String id = text_lines.nextToken();
				int FuelEconomyInMilesPerGallon = Integer.parseInt(text_lines.nextToken());
				int FuelCapacityInGallons = Integer.parseInt(text_lines.nextToken());
				double CurrentFuelInGallons = Double.parseDouble(text_lines.nextToken());

				// Instantiate a new Car object with the above four values
				vural_Car c = new vural_Car(id, FuelEconomyInMilesPerGallon, FuelCapacityInGallons,
						CurrentFuelInGallons);

				// Add the Car object to the ArrayList and two PriorityQueues
				carList.add(c);
				carListByTotalRange.add(c);
				carListByRemainingRange.add(c);

			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	/**
	 * @return a copy of carList
	 */
	public ArrayList<CarFunctions> getCarList() {
		ArrayList<CarFunctions> copyOfCarList = new ArrayList<>();
		for (int i = 0; i < carList.size(); i++) {
			copyOfCarList.add(carList.get(i));
		}
		return copyOfCarList;
	}

	/**
	 * 
	 * @return a copy of carListByTotalRange
	 */
	public PriorityQueue<CarFunctions> getCarListByTotalRange() {
		// return new PriorityQueue<>();
		PriorityQueue<CarFunctions> copyOfCarListByTotalRange = new PriorityQueue<CarFunctions>(
				new TotalRangeComparator());
		for (int i = 0; i < carList.size(); i++) {
			copyOfCarListByTotalRange.add(carList.get(i));
		}
		return copyOfCarListByTotalRange;
	}

	// return an ArrayList of cars for the PriorityQueue of cars that is in total
	// range order
	// by using an iterator to populate the ArrayList
	public ArrayList<CarFunctions> getCarListByTotalRangeUsingIterator() {

		// Create a new ArrayList of type CarFunctions
		ArrayList<CarFunctions> newArrayList = new ArrayList<>();
		// Create an Iterator of type CarFunctions using the iterator
		java.util.Iterator<CarFunctions> carListIterator = carListByTotalRange.iterator();
		// Loop over the elements of carListByTotalRange using the iterator
		while (carListIterator.hasNext()) {
			// Add each element of carListByTotalRange returned by the iterator to the
			// ArrayList
			newArrayList.add(carListIterator.next());
		}
		// Return the ArrayList
		return newArrayList;
	}

	// return the PriorityQueue of cars that is in remaining range order
	public PriorityQueue<CarFunctions> getCarListByRemainingRange() {
		// return new PriorityQueue<>();
		PriorityQueue<CarFunctions> copyOfCarListByRemainingRange = new PriorityQueue<CarFunctions>(
				new RemainingRangeComparator());
		for (int i = 0; i < carList.size(); i++) {
			copyOfCarListByRemainingRange.add(carList.get(i));
		}
		return copyOfCarListByRemainingRange;
	}

	// return an ArrayList of cars for the PriorityQueue of cars that is in
	// remaining range order
	// by using an iterator to populate the ArrayList
	public ArrayList<CarFunctions> getCarListByRemainingRangeUsingIterator() {
		// Create a new ArrayList of type CarFunctions
		ArrayList<CarFunctions> newArrayList2 = new ArrayList<>();
		// Create an Iterator of type CarFunctions using the iterator
		java.util.Iterator<CarFunctions> carListIterator2 = carListByRemainingRange.iterator();
		// Loop over the elements of carListByRemainingRange using the iterator
		while (carListIterator2.hasNext()) {
			// Add each element of carListByRemainingRange returned by the iterator to the
			// ArrayList
			newArrayList2.add(carListIterator2.next());
		}
		// Return the ArrayList
		return newArrayList2;
	}

	// return an ArrayList of String of cars from the PriorityQueue ordered by total
	// order using the poll() method
	// in which the cars have a total range in [minTotalRange, maxTotalRange]
	// and include the index of the car from the ArrayList and the indices of cars
	// that have the same fuel economy
	public ArrayList<String> getCarListByTotalRangeViaPoll(double minTotalRange, double maxTotalRange) {
		ArrayList<String> newArrayList3 = new ArrayList<>();
		while (carListByTotalRange.size() > 0) {
			CarFunctions currentCar = carListByTotalRange.poll();
			String currentCarString = currentCar.toString();
			if (currentCar.getTotalRangeInMiles() < minTotalRange) {
				continue;
			}
			if (currentCar.getTotalRangeInMiles() > maxTotalRange) {
				continue;
			}
			String tempStr1 = "";
			String tempStr2 = "";
			for (int i = 0; i < carList.size(); i++) {
				CarFunctions otherCar = carList.get(i);
				if (currentCar.equals(otherCar)) {
					tempStr1 += "\t" + i;
				}
				if (otherCar.getFuelEconomyInMilesPerGallon() == currentCar.getFuelEconomyInMilesPerGallon()) {
					tempStr2 += "\t" + i;
				}
			}
			currentCarString += tempStr1 + tempStr2;
			newArrayList3.add(currentCarString);
		}

		for (int i = 0; i < carList.size(); i++) {
			carListByTotalRange.add(carList.get(i));
		}
		return newArrayList3;
	}

	// return an ArrayList of String of cars from the PriorityQueue ordered by
	// remaining order using the poll() method
	// in which the cars have a remaining range in [minRemainingRange,
	// minRemainingRange]
	// and include the index of the car from the ArrayList and the indices of cars
	// that have the same fuel economy
	public ArrayList<String> getCarListByRemainingRangeViaPoll(double minRemainingRange, double maxRemainingRange) {
		ArrayList<String> newArrayList4 = new ArrayList<>();
		while (carListByRemainingRange.size() > 0) {
			CarFunctions currentCar = carListByRemainingRange.poll();

			String currentCarString = currentCar.toString();

			if (currentCar.getRemainingRangeInMiles() > maxRemainingRange) {
				continue;
			}
			if (currentCar.getRemainingRangeInMiles() < minRemainingRange) {
				continue;
			}
			String tempStr1 = "";
			String tempStr2 = "";
			for (int i = 0; i < carList.size(); i++) {
				CarFunctions otherCar = carList.get(i);
				if (currentCar.equals(otherCar)) {
					tempStr1 += "\t" + i;
				}
				if (otherCar.getFuelEconomyInMilesPerGallon() == currentCar.getFuelEconomyInMilesPerGallon()) {
					tempStr2 += "\t" + i;
				}
			}
			currentCarString += tempStr1 + tempStr2;
			newArrayList4.add(currentCarString);
		}
		for (int i = 0; i < carList.size(); i++) {
			carListByRemainingRange.add(carList.get(i));
		}
		return newArrayList4;
	}
}
