package juegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used as view craps class
 * @autor Jhon Alexander Valencia Hilamo jhon.hilamo@correounivalle.edu.co
 * @version v.1.0.0 date:21/11/2021
 */
public class GUI extends JFrame {
    private static final String MENSAJE_INICIO="Bienvenido al juego Craps\n" +
            "Oprime el boton lanzar para inicar el juego" +
            "\nSi tu tiro de salida es 7 u 11 ganas con Natural" +
            "\nSi tu tiro de salida es 2, 3 u 12 pierdes con Craps" +
            "\nSi sacas cualquier otro valor estableceras Punto" +
            "\nEstando en Punto podras seguir tirando tus dados" +
            "\nPero ahora ganas si sacas nuevamente el valor del Punto" +
            "\nsin que previamente hayas sacado 7";
    private Header headerProject;
    private JLabel dado1,dado2;
    private JButton lanzar;
    private JPanel panelDados,panelResultados;
    private ImageIcon imageDados;
    private JTextArea mensajeSalida, resultadoDados;
    private Escucha escucha;
    private ModelCraps modelCraps;
    private JSeparator separador;

    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Juego Craps");
        //this.setSize(200,100);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        escucha = new Escucha();
        modelCraps = new ModelCraps();
        //Create Listener Object and Control Object
        //Set up JComponents
        headerProject = new Header("Mesa juego Craps", Color.BLACK);

        this.add(headerProject,BorderLayout.NORTH);

        imageDados= new ImageIcon(getClass().getResource("/resources/dado.png"));
        dado1= new JLabel(imageDados);
        dado2= new JLabel(imageDados);

        lanzar = new JButton("Lanzar");
        lanzar.addActionListener(escucha);

        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(300,180));
        panelDados.setBorder(BorderFactory.createTitledBorder("Tus Dados "));
        panelDados.add(dado1);
        panelDados.add(dado2);
        panelDados.add(lanzar);

        this.add(panelDados,BorderLayout.CENTER);

        mensajeSalida = new JTextArea(7,31);
        mensajeSalida.setText(MENSAJE_INICIO);
        mensajeSalida.setEditable(false);
        //mensajeSalida.setBorder(BorderFactory.createTitledBorder("Que debes hacer "));
        JScrollPane scroll = new JScrollPane(mensajeSalida);


        panelResultados = new JPanel();
        panelResultados.setBorder(BorderFactory.createTitledBorder("Que debes hacer "));
        panelResultados.add(scroll);
        panelResultados.setPreferredSize(new Dimension(370,180));


        this.add(panelResultados,BorderLayout.EAST);

        resultadoDados = new JTextArea(4,31);
        separador = new JSeparator();
        separador.setPreferredSize(new Dimension(320,7));
        separador.setBackground(Color.BLUE);
    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            modelCraps.calcularTiro();
            int [] caras = modelCraps.getCaras();
            imageDados= new ImageIcon(getClass().getResource("/resources/"+caras[0]+".png"));
            dado1.setIcon(imageDados);
            imageDados= new ImageIcon(getClass().getResource("/resources/"+caras[1]+".png"));
            dado2.setIcon(imageDados);
            modelCraps.determinarJuego();


            panelResultados.removeAll();
            panelResultados.setBorder(BorderFactory.createTitledBorder("Resultados "));
            panelResultados.add(resultadoDados);
            resultadoDados.add(separador);
            panelResultados.add(mensajeSalida);
            resultadoDados.setText(modelCraps.getEstadoToString()[0]);
            mensajeSalida.setRows(4);
            mensajeSalida.setText(modelCraps.getEstadoToString()[1]);
            revalidate();
            repaint();
        }
    }
}
