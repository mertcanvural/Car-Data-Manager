import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Iterator;

class vural_Car implements CarFunctions
{
	// Fields
	private final String id;
	private final int FuelEconomyInMilesPerGallon;
	private final int FuelCapacityInGallons;
	private double CurrentFuelInGallons;


	public vural_Car(String id, int FuelEconomyInMilesPerGallon, int FuelCapacityInGallons, double CurrentFuelInGallons)
	{
		this.id = id;
		this.FuelEconomyInMilesPerGallon = FuelEconomyInMilesPerGallon;
		this.FuelCapacityInGallons = FuelCapacityInGallons;
		this.CurrentFuelInGallons = CurrentFuelInGallons;
	}

	@Override
	// return the fuel economy in miles per gallon of the car
	public int getFuelEconomyInMilesPerGallon()
	{
		return this.FuelEconomyInMilesPerGallon;
	}
	
	@Override	
	// return the fuel capacity in gallons of the car
	public int getFuelCapacityInGallons()
	{
		return this.FuelCapacityInGallons;
	}
	
	@Override	
	// return the current gallons of fuel of the car
	public double getCurrentFuelInGallons()
	{
		return this.CurrentFuelInGallons;
	}
	
	@Override	
	// return the id of the car
	public String getId()
	{
		return this.id;
	}
	
	@Override	
	// set the current gallons of fuel of the car
	public void setCurrentFuelInGallons(double v)
	{
		this.CurrentFuelInGallons = v;
	}

    @Override
	// get the total range of the car in miles 
	public double getTotalRangeInMiles()
	{
		return getFuelCapacityInGallons()*getFuelEconomyInMilesPerGallon();
	}
	@Override	
	// get the remaining range of the car in miles
	public double getRemainingRangeInMiles()
	{
		return getCurrentFuelInGallons()*getFuelEconomyInMilesPerGallon();
	}
	/**
	*
	*
	 */
	
	@Override
	public String toString()
	{
		return getId() + "\t" + getFuelEconomyInMilesPerGallon() + "\t" + getFuelCapacityInGallons() + "\t" + getCurrentFuelInGallons() + "\t" + getTotalRangeInMiles() + "\t" + getRemainingRangeInMiles();
	}
}
