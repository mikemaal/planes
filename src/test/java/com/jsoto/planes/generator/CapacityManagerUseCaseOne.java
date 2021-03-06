package com.jsoto.planes.generator;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.qfs.planes.data.ICsvWritable;
import com.qfs.planes.data.impl.ACsvWritable;
import com.qfs.planes.data.impl.Flight;
import com.qfs.planes.data.impl.Itinerary;
import com.qfs.planes.data.impl.Passenger;
import com.qfs.planes.data.impl.Person;
import com.qfs.planes.data.impl.Plane;
import com.qfs.planes.util.PlanesUtil;

public class CapacityManagerUseCaseOne {

	@Test
	public void useCaseOne() throws IOException, ParseException{

		String folder = "src/test/resources/capacityManager/useCaseOne/";

		PlanesUtil.deleteFolder(new File(folder));

		// Flight
		List<ICsvWritable> csvWritable = new ArrayList<>();
		csvWritable.add(new Flight("flightId0", "planeId0", "Madrid", "Paris", ACsvWritable.SDF.parse("2014-08-14 12:30"), ACsvWritable.SDF.parse("2014-08-14 12:30"), ACsvWritable.SDF.parse("2014-08-14 13:30"), ACsvWritable.SDF.parse("2014-08-14 17:00")));
		csvWritable.add(new Flight("flightId1", "planeId0", "Madrid", "Paris", ACsvWritable.SDF.parse("2014-08-14 14:30"), ACsvWritable.SDF.parse("2014-08-14 14:30"), ACsvWritable.SDF.parse("2014-08-14 15:30"), ACsvWritable.SDF.parse("2014-08-14 15:30")));
		csvWritable.add(new Flight("flightId2", "planeId0", "Paris", "Amsterdam", ACsvWritable.SDF.parse("2014-08-14 14:30"), ACsvWritable.SDF.parse("2014-08-14 14:30"), ACsvWritable.SDF.parse("2014-08-14 15:30"), ACsvWritable.SDF.parse("2014-08-14 15:30")));


		// Plane
		csvWritable.add(new Plane("planeId0", 10, 5, 300, 1800));

		int itineraryId = 0 ;
		int passengerId = 0;
		for (int i = 0; i < 5; i++) {
			csvWritable.add(new Itinerary("itineraryId" + itineraryId++, "personId" + i, "Madrid", "Paris", Arrays.asList("flightId0")));
			csvWritable.add(new Passenger("passengerId" + passengerId++, "flightId0", "itineraryId" + i, 40, true));
		}
		for (int i = 5; i < 10; i++) {
			csvWritable.add(new Itinerary("itineraryId" + itineraryId++, "personId" + i, "Madrid", "Paris", Arrays.asList("flightId0", "flightId2")));
			csvWritable.add(new Passenger("passengerId" + passengerId++, "flightId0", "itineraryId" + i, 20, false));
			csvWritable.add(new Passenger("passengerId" + passengerId++, "flightId2", "itineraryId" + i, 20, false));
		}
		for (int i = 10; i < 15; i++) {
			csvWritable.add(new Itinerary("itineraryId" + itineraryId++, "personId" + i, "Madrid", "Paris", Arrays.asList("flightId0")));	
			csvWritable.add(new Passenger("passengerId" + passengerId++, "flightId0", "itineraryId" + i, 20, false));
		}

		for (int i = 0; i < 15; i++) {
			csvWritable.add(new Person("personId" + i, "John" + i, "Sullivan" + i));
		}

		PlanesUtil.write(csvWritable, folder);

	}

}
