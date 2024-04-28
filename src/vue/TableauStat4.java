package vue;

import java.util.HashMap;

import javax.swing.table.AbstractTableModel;

import controleur.ConvertCSV;
import controleur.StatEtab;

public class TableauStat4 extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private final String[] entetes = { "Département ", "NO2", "PM10", "PM25" };	
	private final double[] evolutionDepartementNO2;
	private final double[] evolutionDepartementPM10;
	private final double[] evolutionDepartementPM25;
	
	public TableauStat4(String dpt) {
		evolutionDepartementNO2 = new double[5];
		evolutionDepartementPM10 = new double[5];
		evolutionDepartementPM25 = new double[5];

		double startEvolutionNO2 = StatEtab.getMoyennePolluantNO2Dpt(ConvertCSV.listeEtab, dpt, 2012);
		double startEvolutionPM10 = StatEtab.getMoyennePolluantPM10Dpt(ConvertCSV.listeEtab, dpt, 2012);
		double startEvolutionPM25 = StatEtab.getMoyennePolluantPM25Dpt(ConvertCSV.listeEtab, dpt, 2012);

		for (int i = 0; i < 5; i++)
		{
			evolutionDepartementNO2[i] = ((StatEtab.getMoyennePolluantNO2Dpt(ConvertCSV.listeEtab, dpt, i + 2013) - startEvolutionNO2) / startEvolutionNO2) * 100;
			evolutionDepartementPM10[i] = ((StatEtab.getMoyennePolluantPM10Dpt(ConvertCSV.listeEtab, dpt, i + 2013) - startEvolutionPM10) / startEvolutionPM10) * 100;
			evolutionDepartementPM25[i] = ((StatEtab.getMoyennePolluantPM25Dpt(ConvertCSV.listeEtab, dpt, i + 2013) - startEvolutionPM25) / startEvolutionPM25) * 100;
		}
		entetes[0] += dpt;
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
		return 5;
	}
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		double value;
		switch (columnIndex) 
		{
			case 0:
				return 2013 + rowIndex;
			case 1:
				value = (double)Math.round(evolutionDepartementNO2[rowIndex] * 100) / 100;
				if (value >= 0)
				{
					return "+" + value + "%";
				}
				return value + "%";

			case 2:
				value = (double)Math.round(evolutionDepartementPM10[rowIndex] * 100) / 100;
				if (value >= 0)
				{
					return "+" + value + "%";
				}
				return value + "%";

			case 3:
				value = (double)Math.round(evolutionDepartementPM25[rowIndex] * 100) / 100;
				if (value >= 0)
				{
					return "+" + value + "%";
				}
				return value + "%";

			default:
				throw new IllegalArgumentException();
		}
	}
}