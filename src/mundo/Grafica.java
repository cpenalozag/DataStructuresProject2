package mundo;

import java.awt.Dimension;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class Grafica extends ApplicationFrame{

	private DefaultCategoryDataset datos;

	public Grafica(String title, DefaultCategoryDataset pDatos ) {
		super(title);
		datos = pDatos;
	}

	public void mostrarGraficaLinea(String nombre, String x, String y){
		JFreeChart jf = ChartFactory.createLineChart(nombre, x, y, datos, PlotOrientation.VERTICAL,true,true,false);
		ChartPanel cp = new ChartPanel(jf);
		cp.setPreferredSize(new Dimension(500,400));
		setContentPane(cp);
	}

	public void mostrarGraficaBarras(String nom, String x, String y)
	{
		JFreeChart bar = ChartFactory.createBarChart(nom, x, y, datos, PlotOrientation.VERTICAL, true, true, false);
		ChartPanel chartPanel = new ChartPanel( bar );
		chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 400 ) );
		setContentPane( chartPanel );
	}

}
