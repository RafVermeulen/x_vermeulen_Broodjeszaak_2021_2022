package view.admin.panels;

import controller.StatistiekController;
import javafx.geometry.Insets;
import javafx.scene.chart.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.Item;
import java.util.Collection;

public class StatistiekPane extends GridPane{
	private StatistiekController controller;
	private BarChart broodjesChart;
	private BarChart belegChart;

	public StatistiekPane() {
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

		this.broodjesChart = this.createBarChart("Omzet broodjes (in aantal stuks)");
		this.belegChart = this.createBarChart("Omzet beleg (in aantal stuks)");

		this.add(this.broodjesChart, 0, 0);
		this.add(this.belegChart, 1, 0);
	}

	private BarChart createBarChart(String title) {
		BarChart chart = new BarChart(new NumberAxis(), new CategoryAxis());
		chart.setTitle(title);
		chart.setLegendVisible(false);
		chart.setBackground(
				new Background(
						new BackgroundFill(Color.TRANSPARENT, new CornerRadii(5), new Insets(0))
				)
		);
		return chart;
	}

	public void refresh() {
		Collection broodjes = this.controller.getBroodjes();
		Collection beleg = this.controller.getBeleg();

		this.refreshDataset(this.broodjesChart, broodjes);
		this.refreshDataset(this.belegChart, beleg);
	}

	private void refreshDataset(BarChart chart, Collection list) {
		chart.getData().clear();
		XYChart.Series data = new XYChart.Series();

		for (Object obj : list) {
			Item item = (Item) obj;
			String label = item.getName() + "          " + item.getSold();
			data.getData().add(new XYChart.Data(item.getSold(), label));
		}

		chart.getData().addAll(data);
	}

	public void setController(StatistiekController controller) {
		this.controller = controller;
		this.controller.setView(this);
	}
}
