package juegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIGridBagLayout extends JFrame {
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
    private JButton lanzar,ayuda,salir;
    private JPanel panelDados;
    private ImageIcon imageDados;
    private JTextArea mensajeSalida, resultadoDados;
    private Escucha escucha;
    private ModelCraps modelCraps;

    public GUIGridBagLayout(){
        initGUI();
        //Default JFrame configuration
        this.setTitle("Juego Craps");
        this.setUndecorated(true);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initGUI() {
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        //Set up JFrame Container's Layout
        escucha = new Escucha();
        modelCraps = new ModelCraps();
        //Create Listener Object and Control Object
        //Set up JComponents
        headerProject = new Header("Mesa juego Craps", Color.BLACK);
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.HORIZONTAL;

        this.add(headerProject,constraints);

        ayuda = new JButton(" ? ");
        ayuda.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_START;

        this.add(ayuda,constraints);

        salir = new JButton(" X ");
        salir.addActionListener(escucha);
        constraints.gridx=1;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_END;

        this.add(salir,constraints);
        imageDados= new ImageIcon(getClass().getResource("/resources/dado.png"));
        dado1= new JLabel(imageDados);
        dado2= new JLabel(imageDados);

        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(300,180));
        panelDados.setBorder(BorderFactory.createTitledBorder("Tus Dados "));
        panelDados.add(dado1);
        panelDados.add(dado2);
        constraints.gridx=0;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        add(panelDados,constraints);

        resultadoDados = new JTextArea(4,31);
        resultadoDados.setBorder(BorderFactory.createTitledBorder("Resultados "));
        resultadoDados.setText("Debes lanzar los dados");
        resultadoDados.setBackground(null);
        constraints.gridx=1;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        add(resultadoDados,constraints);

        lanzar = new JButton("Lanzar");
        lanzar.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=3;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        add(lanzar,constraints);

        mensajeSalida = new JTextArea(4,31);
        mensajeSalida.setText("Usa el boton (?) para ver las reglas del juego");
        mensajeSalida.setEditable(false);
        mensajeSalida.setBackground(null);
        mensajeSalida.setBorder(BorderFactory.createTitledBorder("Mensajes"));
        constraints.gridx=0;
        constraints.gridy=4;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        add(mensajeSalida,constraints);

    }

    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUIGridBagLayout miProjectGUI = new GUIGridBagLayout();
        });
    }

    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==lanzar){
                modelCraps.calcularTiro();
                int [] caras = modelCraps.getCaras();
                imageDados= new ImageIcon(getClass().getResource("/resources/"+caras[0]+".png"));
                dado1.setIcon(imageDados);
                imageDados= new ImageIcon(getClass().getResource("/resources/"+caras[1]+".png"));
                dado2.setIcon(imageDados);
                modelCraps.determinarJuego();
                resultadoDados.setText(modelCraps.getEstadoToString()[0]);
                mensajeSalida.setText(modelCraps.getEstadoToString()[1]);
            }else{
                if(e.getSource()==ayuda){
                    JOptionPane.showMessageDialog(null,MENSAJE_INICIO);
                }else{
                    System.exit(0);
                }
            }

        }
    }

}
