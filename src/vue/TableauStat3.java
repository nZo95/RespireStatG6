package vue;

import java.util.HashMap;

import javax.swing.table.AbstractTableModel;

import controleur.ConvertCSV;

public class TableauStat3 extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private final String[] entetes = { "Département", "NO2", "PM10", "PM25" };	
	private final HashMap<String, Double> moyenneDepartementNO2;
	private final HashMap<String, Double> moyenneDepartementPM10;
	private final HashMap<String, Double> moyenneDepartementPM25;
	
	public TableauStat3(HashMap<String, Double> moyenneDepartementNO2, HashMap<String, Double> moyenneDepartementPM10, HashMap<String, Double> moyenneDepartementPM25) {
		this.moyenneDepartementNO2 = moyenneDepartementNO2;
		this.moyenneDepartementPM10 = moyenneDepartementPM10;
		this.moyenneDepartementPM25 = moyenneDepartementPM25;
	}
	
	@Override
	public int getColumnCount() {
		return entetes.length;
	}
	@Override
	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	@Override
	public int getRowCount() {
		return ConvertCSV.listeDepartements.size();
	}
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

			switch (columnIndex) {
			case 0:
				return ConvertCSV.listeDepartements.get(rowIndex);
			case 1:
				return moyenneDepartementNO2.get(ConvertCSV.listeDepartements.get(rowIndex));

			case 2:
				return moyenneDepartementPM10.get(ConvertCSV.listeDepartements.get(rowIndex));

			case 3:
				return moyenneDepartementPM25.get(ConvertCSV.listeDepartements.get(rowIndex));

			default:
				throw new IllegalArgumentException();
			}
	}
}
