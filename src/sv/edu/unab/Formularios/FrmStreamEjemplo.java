package sv.edu.unab.Formularios;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import sv.edu.unab.Dominio.Estudiante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;

public class FrmStreamEjemplo {
    //<editor-fold defaultstate="Collapsed" desc="Componentes">
    public JPanel pnlRoot;
    private JTextField txtNombre;
    private JTextField txtApellidoMaterno;
    private JFormattedTextField ftxtFechaNacimiento;
    private JTextArea atxtDireccion;
    private JTextField txtGrado;
    private JTextField txtSeccion;
    private JComboBox cboGenero;
    private JButton btnNuevo;
    private JButton btnEditar;
    private JButton btnConsultar;
    private JButton btnEliminar;
    private JTable tblEstudiantes;
    private JScrollPane jscrollpane;
    private JTextField txtApellidoPaterno;
    private JLabel lblEstudianteMenor;
    private JLabel lblEstudianteMayor;
    private JLabel lblPromedioEdad;
    //</editor-fold>
    List<Estudiante> estudiantesModel;
    public FrmStreamEjemplo(){
        initComponentes();

    }
    public void initComponentes(){
        tblEstudiantes.setFillsViewportHeight(true);
        if (estudiantesModel==null){
            estudiantesModel=new ArrayList<>();
        }
        DefaultTableModel modelo=new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        if (estudiantesModel==null){
            estudiantesModel=new ArrayList<>();
        }
        estudiantesModel.add(new Estudiante(String.valueOf(new Random().nextInt()), "Susan Giselle", "Batres", "Alvarenga", LocalDate.of(1999,05,25),"El paraiso", "6to", 'C', 'F'));
        estudiantesModel.add(new Estudiante(String.valueOf(new Random().nextInt()), "Marcos Alexander", "Gomez", "Perez", LocalDate.of(1997,04,26),"Sierpe", "8vo", 'C', 'M'));
        estudiantesModel.stream().forEach(estudiante -> {
            StringJoiner nombreCompleto=new StringJoiner(" ");
            nombreCompleto.add(estudiante.getApellidoPaterno());
            nombreCompleto.add(estudiante.getApellidoMaterno());
            nombreCompleto.add(estudiante.getNombre());

            modelo.addRow(new Object[]{
                    estudiante.getCodigo(),
                    nombreCompleto
            });

        });
        tblEstudiantes.setModel(modelo);

        //formato ftxt
        try{
            MaskFormatter mascara=new MaskFormatter("##/##/####");
            ftxtFechaNacimiento=new JFormattedTextField(mascara);
            //agregando eventos a botones
            btnNuevo.addActionListener(evt->{
                Estudiante estudiante=new Estudiante(String.valueOf(new Random().nextInt()));
                estudiante.setNombre(txtNombre.getText());
                estudiante.setApellidoPaterno(txtApellidoPaterno.getText());
                estudiante.setApellidoMaterno(txtApellidoMaterno.getText());
                estudiante.setFechaNacimiento(LocalDate.parse(ftxtFechaNacimiento.getText()));
                System.out.println(estudiante.getFechaNacimiento());
            });
        }catch(ParseException e){
            e.printStackTrace();
        }


    }
    private void actualizarDatos(List<Estudiante> listado){
        reiniciarJTable(tblEstudiantes);
        Estudiante edadMenor=listado.stream().min((e1,e2)->{
            Long edad1=e1.getFechaNacimiento().until(LocalDate.now(), ChronoUnit.YEARS);
            Long edad2=e2.getFechaNacimiento().until(LocalDate.now(),ChronoUnit.YEARS);
            return  edad1.compareTo(edad2);
        }).get();
        Estudiante edadMayor=listado.stream().max((e1,e2)->{
            Long edad1=e1.getFechaNacimiento().until(LocalDate.now(), ChronoUnit.YEARS);
            Long edad2=e2.getFechaNacimiento().until(LocalDate.now(),ChronoUnit.YEARS);
            return  edad1.compareTo(edad2);
        }).get();
        lblEstudianteMenor.setText(edadMenor.getNombre());
        lblEstudianteMayor.setText(edadMayor.getNombre());


        
    }
    private void reiniciarJTable(JTable jTable){
        DefaultTableModel modelo=(DefaultTableModel)jTable.getModel();
        while(modelo.getColumnCount()>0){
            modelo.removeRow(0);
        }
    }
    private void limpiarcomponentes(){
        txtNombre.setText(null);
        txtApellidoPaterno.setText(null);
        txtApellidoMaterno.setText(null);
        ftxtFechaNacimiento.setValue(null);
        atxtDireccion.setText(null);
        txtGrado.setText(null);
        txtSeccion.setText(null);
        cboGenero.setSelectedIndex(0);
    }

}
