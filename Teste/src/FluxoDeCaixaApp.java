import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import teste.FluxoDeCaixa;

public class FluxoDeCaixaApp extends JFrame {
    private List<FluxoDeCaixa> dados;
    private JTable table;
    private JTextField startDateField;
    private JTextField endDateField;

    public FluxoDeCaixaApp() {
        dados = new ArrayList<>();
        dados.add(new FluxoDeCaixa(LocalDate.of(2023, Month.SEPTEMBER, 1), "Venda A", 1000.0, 0.0));
        dados.add(new FluxoDeCaixa(LocalDate.of(2023, Month.OCTOBER, 2), "Compra B", 0.0, 500.0));
        dados.add(new FluxoDeCaixa(LocalDate.of(2023, Month.NOVEMBER, 3), "Venda C", 2000.0, 0.0));
        dados.add(new FluxoDeCaixa(LocalDate.of(2023, Month.SEPTEMBER, 4), "Venda D", 1000.0, 0.0));
        dados.add(new FluxoDeCaixa(LocalDate.of(2023, Month.OCTOBER, 5), "Compra E", 0.1, 1.0));
        dados.add(new FluxoDeCaixa(LocalDate.of(2023, Month.NOVEMBER, 6), "Venda F", 20.0, 900.0));

        setTitle("Fluxo de Caixa");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel de Filtros
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new FlowLayout());
        filterPanel.add(new JLabel("Data Inicial (YYYY-MM-DD):"));
        startDateField = new JTextField(10);
        filterPanel.add(startDateField);
        filterPanel.add(new JLabel("Data Final (YYYY-MM-DD):"));
        endDateField = new JTextField(10);
        filterPanel.add(endDateField);
        JButton filterButton = new JButton("Filtrar");
        filterPanel.add(filterButton);
        add(filterPanel, BorderLayout.NORTH);

        // Tabela
        table = new JTable();
        updateTable(dados);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Botão de Exportação
        JButton exportButton = new JButton("Exportar para Excel");
        add(exportButton, BorderLayout.SOUTH);

        // Ação do Botão de Filtrar
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterData();
            }
        });

        // Ação do Botão de Exportar
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportToExcel();
            }
        });
    }

    private void updateTable(List<FluxoDeCaixa> dados) {
        String[] columnNames = {"Data", "Descrição", "Entrada", "Saída"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (FluxoDeCaixa item : dados) {
            model.addRow(new Object[]{item.getData().toString(), item.getDescricao(), item.getEntrada(), item.getSaida()});
        }
        table.setModel(model);
    }

    private void filterData() {
        LocalDate startDate = LocalDate.parse(startDateField.getText());
        LocalDate endDate = LocalDate.parse(endDateField.getText());
        List<FluxoDeCaixa> filteredData = dados.stream()
                .filter(item -> !item.getData().isBefore(startDate) && !item.getData().isAfter(endDate))
                .collect(Collectors.toList());
        updateTable(filteredData);
    }

    private void exportToExcel() {
        // Implementação da exportação para Excel
        // O código será baseado no exemplo anterior usando Apache POI
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FluxoDeCaixaApp().setVisible(true);
            }
        });
    }
    
    
}
