package model;

public class CoordGPS {
	private double latitude;
	private double longitude;
	
	public CoordGPS(String geometry) {
		this(getLatitudeFromGeometry(geometry),getLongitudeFromGeometry(geometry));
	}
	public CoordGPS(double lat, double lon) {
		latitude = lat;
		longitude = lon;
	}

	public static int calculateDistance(double startLatitude, double startLongitude, double endLatitude, double endLongitude) 
	{
		double startLatitudeRad = Math.toRadians(48.853);
		double startLongitudeRad = Math.toRadians(2.35);
		double endLatitudeRad = Math.toRadians(endLatitude);
		double endLongitudeRad = Math.toRadians(endLongitude);
	
		double x = (endLongitudeRad - startLongitudeRad) * Math.cos((startLatitudeRad + endLatitudeRad) / 2);
		double y = (endLatitudeRad - startLatitudeRad);
		
		return (int)Math.sqrt(x * x + y * y) * 6371;
	}
 	
	private static String[] extractLatLongFromGeometry(String geometry) {
		String extract = geometry.substring(2, geometry.length()-1);
		String[] coord = extract.split(", ");
		return coord;
	}
	private static double getLatitudeFromGeometry(String geometry) {
		return Double.parseDouble(extractLatLongFromGeometry(geometry)[0]);
	}
	private static double getLongitudeFromGeometry(String geometry) {
		return Double.parseDouble(extractLatLongFromGeometry(geometry)[1]);
	}
	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	@Override
	public String toString() {
		return "CoordGPS [latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	
}
