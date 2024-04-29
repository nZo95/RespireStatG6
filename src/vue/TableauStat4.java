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

	private int yearCount;
	
	public TableauStat4(String dpt, int yearCount) {

		this.yearCount = yearCount;

		evolutionDepartementNO2 = new double[yearCount];
		evolutionDepartementPM10 = new double[yearCount];
		evolutionDepartementPM25 = new double[yearCount];

		double startEvolutionNO2 = StatEtab.getMoyennePolluantNO2Dpt(ConvertCSV.listeEtab, dpt, 2012);
		double startEvolutionPM10 = StatEtab.getMoyennePolluantPM10Dpt(ConvertCSV.listeEtab, dpt, 2012);
		double startEvolutionPM25 = StatEtab.getMoyennePolluantPM25Dpt(ConvertCSV.listeEtab, dpt, 2012);

		for (int i = 0; i < yearCount; i++)
		{
			if (i != 0)
			{
				startEvolutionNO2 = StatEtab.getMoyennePolluantNO2Dpt(ConvertCSV.listeEtab, dpt, i - 1 + 2013);
				startEvolutionPM10 = StatEtab.getMoyennePolluantPM10Dpt(ConvertCSV.listeEtab, dpt, i - 1 + 2013);
				startEvolutionPM25 = StatEtab.getMoyennePolluantPM25Dpt(ConvertCSV.listeEtab, dpt, i - 1 + 2013);
			}

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
		return yearCount;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		double value;
		switch (columnIndex) 
		{
			case 0:
				return 2013 + rowIndex;

			case 1:
				value = evolutionDepartementNO2[rowIndex];

				if (value >= 0)
				{
					return "+" + String.format("%.2f", value) + "%";
				}

				return String.format("%.2f", value) + "%";

			case 2:
				value = evolutionDepartementPM10[rowIndex];
				
				if (value >= 0)
				{
					return "+" + String.format("%.2f", value) + "%";
				}

				return String.format("%.2f", value) + "%";

			case 3:
				value = evolutionDepartementPM25[rowIndex];
				
				if (value >= 0)
				{
					return "+" + String.format("%.2f", value) + "%";
				}

				return String.format("%.2f", value) + "%";

			default:
				throw new IllegalArgumentException();
		}
	}
}