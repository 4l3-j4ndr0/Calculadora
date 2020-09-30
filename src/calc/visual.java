/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calc;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.function.DoubleToLongFunction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.button.StandardButtonShaper;

/**
 *
 * @author 4L3
 */
public class visual extends javax.swing.JFrame implements Runnable {

    private JPanel contentPane;
    Thread h1;
    ArrayList cuenta = new ArrayList();
    ArrayList result = new ArrayList();
    
    String signo=null;
    int cont_pto=0;

    /**
     * Creates new form visual
     */
    public visual() {
        JFrame.setDefaultLookAndFeelDecorated(true); //que nos permite dejar a Substance la decoracion ( por asi decirlo)
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.RavenGraphiteGlassSkin"); // Setencia que aplica el skin Creme de Substance
        SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceEbonyTheme");
        SubstanceLookAndFeel.setCurrentWatermark("org.jvnet.substance.watermark.SubstanceMetalWallWatermark");
        SubstanceLookAndFeel.setCurrentGradientPainter("org.jvnet.substance.GradientPainter.GradientWeave");
        SubstanceLookAndFeel.setCurrentBorderPainter("org.jvnet.substance.BorderPainter.Standard");
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("logo.png")).getImage());
        this.setLocationRelativeTo(null);
        pantalla.requestFocus();
        this.boton_1.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
        this.boton_2.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
        this.boton_3.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
        this.boton_4.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
        this.boton_5.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
        this.boton_6.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
        this.boton_7.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
        this.boton_8.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
        this.boton_9.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
        this.boton_0.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
        this.boton_igual.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
        this.boton_coma.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
        this.boton_borrar.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
        this.boton_dividir.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
        this.boton_multiplicar.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
        this.boton_menos.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
        this.boton_mas.putClientProperty(SubstanceLookAndFeel.BUTTON_SHAPER_PROPERTY, new StandardButtonShaper());
        
        contentPane = jPanel1;
        contentPane.setLayout(null);
        setContentPane(contentPane);
        contentPane.setFocusable(true);
        contentPane.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                //Aqui no funcionara
            }
            
            

            public void keyPressed(KeyEvent e) {
                
                
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    int cont_signos=0;
                    int pos=-1;
                    boolean flag=false;
                    ArrayList array_numero=new ArrayList();
                    ArrayList array_signo=new ArrayList();
                    String texto=pantalla.getText();
                    if(texto.charAt(texto.length()-1)!='+'&&texto.charAt(texto.length()-1)!='-'&&texto.charAt(texto.length()-1)!='×'&&texto.charAt(texto.length()-1)!='÷'){
                    texto=pantalla.getText();
                }else{
                    texto=pantalla.getText().substring(0,texto.length()-1);
                }
                for(int i=1;i<texto.length();i++){
                        if(texto.charAt(i)=='+'||
                               texto.charAt(i)=='-'||
                                texto.charAt(i)=='×'||
                                texto.charAt(i)=='÷'){
                            cont_signos++;
                        }
                    }
                
                for(int i=1;i<texto.length();i++){
                    if(texto.charAt(i)=='+'||
                                texto.charAt(i)=='-'||
                                texto.charAt(i)=='×'||
                                texto.charAt(i)=='÷'){
                            
                            if(cont_signos>=0){
                                cont_signos--;
                                array_numero.add(texto.substring(pos+1,i));
                                array_signo.add(texto.charAt(i));
                                pos=i;
                            }
                        }else{
                        if(cont_signos==0&&flag==false){
                            flag=true;
                                array_numero.add(texto.substring(pos+1,texto.length()));
                            }
                    }
                }
                boolean primero=false;
                double result=0;
                    for (int i=0; i<array_numero.size(); i++) {
                       for (int j=0; j<array_signo.size(); j++) {
                           if(primero==false){
                               primero=true;
                           result=calcula(Double.parseDouble(array_numero.get(0).toString()),Double.parseDouble(array_numero.get(i+1).toString()),array_signo.get(j).toString());
                           i++;
                           }else{
                               result=calcula(result,Double.parseDouble(array_numero.get(i+1).toString()),array_signo.get(j).toString());
                           i++;
                           }
                       }
                    }
                    System.out.println(result);
                    String sin_cero=String.valueOf(result);
                    for(int i=0;i<sin_cero.length();i++){
                       if(sin_cero.charAt(i)=='.'){
                        if(i+1==sin_cero.length()-1&&sin_cero.charAt(sin_cero.length()-1)==48){
                            sin_cero=sin_cero.substring(0,i);
                        }
                    } 
                    }
                    
                    pantalla.setText(sin_cero);
                    
                    System.out.println(array_numero);
                    System.out.println(array_signo);
                   // System.out.println(calcula(uno, dos, signo));
                }
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    pantalla.setText("");
                    cont_pto=0;
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    pantalla.setText("");
                    cont_pto=0;
                }
                if (e.getKeyCode() == KeyEvent.VK_NUMPAD0) {
                    pantalla.setText(pantalla.getText() + "0");
                }
                if (e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
                    pantalla.setText(pantalla.getText() + "1");
                }
                if (e.getKeyCode() == KeyEvent.VK_NUMPAD2) {
                    pantalla.setText(pantalla.getText() + "2");
                }
                if (e.getKeyCode() == KeyEvent.VK_NUMPAD3) {
                    pantalla.setText(pantalla.getText() + "3");
                }
                if (e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
                    pantalla.setText(pantalla.getText() + "4");
                }
                if (e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
                    pantalla.setText(pantalla.getText() + "5");
                }
                if (e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
                    pantalla.setText(pantalla.getText() + "6");
                }
                if (e.getKeyCode() == KeyEvent.VK_NUMPAD7) {
                    pantalla.setText(pantalla.getText() + "7");
                }
                if (e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
                    pantalla.setText(pantalla.getText() + "8");
                }
                if (e.getKeyCode() == KeyEvent.VK_NUMPAD9) {
                    pantalla.setText(pantalla.getText() + "9");
                }
                if (e.getKeyCode() == KeyEvent.VK_0) {
                    pantalla.setText(pantalla.getText() + "0");
                }
                if (e.getKeyCode() == KeyEvent.VK_1) {
                    pantalla.setText(pantalla.getText() + "1");
                }
                if (e.getKeyCode() == KeyEvent.VK_2) {
                    pantalla.setText(pantalla.getText() + "2");
                }
                if (e.getKeyCode() == KeyEvent.VK_3) {
                    pantalla.setText(pantalla.getText() + "3");
                }
                if (e.getKeyCode() == KeyEvent.VK_4) {
                    pantalla.setText(pantalla.getText() + "4");
                }
                if (e.getKeyCode() == KeyEvent.VK_5) {
                    pantalla.setText(pantalla.getText() + "5");
                }
                if (e.getKeyCode() == KeyEvent.VK_6) {
                    pantalla.setText(pantalla.getText() + "6");
                }
                if (e.getKeyCode() == KeyEvent.VK_7) {
                    pantalla.setText(pantalla.getText() + "7");
                }
                if (e.getKeyCode() == KeyEvent.VK_8) {
                    pantalla.setText(pantalla.getText() + "8");
                }
                if (e.getKeyCode() == KeyEvent.VK_9) {
                    pantalla.setText(pantalla.getText() + "9");
                }
                if (e.getKeyCode() == KeyEvent.VK_COMMA) {
                    if (pantalla.getText().length() > 0) {
                        if( pantalla.getText().charAt(pantalla.getText().length()-1)>=48&&
                                pantalla.getText().charAt(pantalla.getText().length()-1)<=57)
                                        {
                        cont_pto++;
                        if (cont_pto<=1) {
                            pantalla.setText(pantalla.getText() + ".");
                        }
                    }
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    if (pantalla.getText().toString().length() > 0) {
                        pantalla.setText(pantalla.getText().substring(0, pantalla.getText().toString().length() - 1));
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_DECIMAL) {
                    if (pantalla.getText().length() > 0) {
                        if( pantalla.getText().charAt(pantalla.getText().length()-1)>=48&&
                                pantalla.getText().charAt(pantalla.getText().length()-1)<=57)
                                        {
                        cont_pto++;
                        if (cont_pto<=1) {
                            pantalla.setText(pantalla.getText() + ".");
                        }
                    }
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_PERIOD) {
                    if (pantalla.getText().length() > 0) {
                        if( pantalla.getText().charAt(pantalla.getText().length()-1)>=48&&
                                pantalla.getText().charAt(pantalla.getText().length()-1)<=57)
                                        {
                        cont_pto++;
                        if (cont_pto<=1) {
                            pantalla.setText(pantalla.getText() + ".");
                        }
                    }
                    }
                }
                
                if (e.getKeyCode() == KeyEvent.VK_MINUS) {
                    if (pantalla.getText().length() > 0) {
                        if (pantalla.getText().charAt(pantalla.getText().length() - 1) != '-') {
                            if (pantalla.getText().charAt(pantalla.getText().length() - 1) != '+') {
                                if (pantalla.getText().charAt(pantalla.getText().length() - 1) != '×') {
                                    if (pantalla.getText().charAt(pantalla.getText().length() - 1) != '÷') {
                                        if(pantalla.getText().charAt(pantalla.getText().length()-1)!='.'){
                                        pantalla.setText(pantalla.getText() + "-");
                                        cont_pto=0;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (pantalla.getText().length() > 0) {
                        if ((pantalla.getText().charAt(pantalla.getText().length() - 1) == '+') || (pantalla.getText().charAt(pantalla.getText().length() - 1) == '×') || (pantalla.getText().charAt(pantalla.getText().length() - 1) == '÷')) {
                            if(pantalla.getText().charAt(pantalla.getText().length()-1)!='.'){
                            pantalla.setText(pantalla.getText().substring(0, pantalla.getText().length() - 1) + "-");
                            cont_pto=0;
                            }
                        }
                    }
                    if (pantalla.getText().length() == 0) {
                        if(pantalla.getText().charAt(pantalla.getText().length()-1)!='.'){
                        pantalla.setText(pantalla.getText() + "-");
                        cont_pto=0;
                        }
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_SUBTRACT) {
                    if (pantalla.getText().length() > 0) {
                        if (pantalla.getText().charAt(pantalla.getText().length() - 1) != '-') {
                            if (pantalla.getText().charAt(pantalla.getText().length() - 1) != '+') {
                                if (pantalla.getText().charAt(pantalla.getText().length() - 1) != '×') {
                                    if (pantalla.getText().charAt(pantalla.getText().length() - 1) != '÷') {
                                        if(pantalla.getText().charAt(pantalla.getText().length()-1)!='.'){
                                        pantalla.setText(pantalla.getText() + "-");
                                        cont_pto=0;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (pantalla.getText().length() > 0) {
                        if ((pantalla.getText().charAt(pantalla.getText().length() - 1) == '+') || (pantalla.getText().charAt(pantalla.getText().length() - 1) == '×') || (pantalla.getText().charAt(pantalla.getText().length() - 1) == '÷')) {
                            if(pantalla.getText().charAt(pantalla.getText().length()-1)!='.'){
                            pantalla.setText(pantalla.getText().substring(0, pantalla.getText().length() - 1) + "-");
                            cont_pto=0;
                            }
                        }
                    }
                    if (pantalla.getText().length() == 0) {
                        if(pantalla.getText().charAt(pantalla.getText().length()-1)!='.'){
                        pantalla.setText(pantalla.getText() + "-");
                        cont_pto=0;
                        }
                    }

                }
                if (e.getKeyCode() == KeyEvent.VK_ADD) {
                    if (pantalla.getText().length() > 0) {
                        if (pantalla.getText().charAt(pantalla.getText().length() - 1) != '+') {
                            if (pantalla.getText().charAt(pantalla.getText().length() - 1) == '-' || pantalla.getText().charAt(pantalla.getText().length() - 1) == '×'
                                    || pantalla.getText().charAt(pantalla.getText().length() - 1) == '÷') {
                                if(pantalla.getText().charAt(pantalla.getText().length()-1)!='.'){
                                pantalla.setText(pantalla.getText().substring(0, pantalla.getText().length() - 1) + "+");
                                cont_pto=0;
                                }
                            } else {
                                if(pantalla.getText().charAt(pantalla.getText().length()-1)!='.'){
                                pantalla.setText(pantalla.getText() + "+");
                                cont_pto=0;
                                }
                            }
                        }
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_DIVIDE) {
                    if (pantalla.getText().length() > 0) {
                        if (pantalla.getText().charAt(pantalla.getText().length() - 1) != '÷') {
                            if (pantalla.getText().charAt(pantalla.getText().length() - 1) == '-' || pantalla.getText().charAt(pantalla.getText().length() - 1) == '×'
                                    || pantalla.getText().charAt(pantalla.getText().length() - 1) == '+') {
                                if(pantalla.getText().charAt(pantalla.getText().length()-1)!='.'){
                                pantalla.setText(pantalla.getText().substring(0, pantalla.getText().length() - 1) + "÷");
                                cont_pto=0;
                                }
                            } else {
                                if(pantalla.getText().charAt(pantalla.getText().length()-1)!='.'){
                                pantalla.setText(pantalla.getText() + "÷");
                                cont_pto=0;
                                }
                            }
                        }
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_MULTIPLY) {
                    if (pantalla.getText().length() > 0) {
                        if (pantalla.getText().charAt(pantalla.getText().length() - 1) != '×') {
                            if (pantalla.getText().charAt(pantalla.getText().length() - 1) == '-' || pantalla.getText().charAt(pantalla.getText().length() - 1) == '+'
                                    || pantalla.getText().charAt(pantalla.getText().length() - 1) == '÷') {
                                if(pantalla.getText().charAt(pantalla.getText().length()-1)!='.'){
                                pantalla.setText(pantalla.getText().substring(0, pantalla.getText().length() - 1) + "×");
                                cont_pto=0;
                                }
                            } else {
                                if(pantalla.getText().charAt(pantalla.getText().length()-1)!='.'){
                                pantalla.setText(pantalla.getText() + "×");
                                cont_pto=0;
                                }
                            }
                        }
                    }
                }
                

            }

            public void keyReleased(KeyEvent e) {
                //Aqui tambien puedes insertar el codigo
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        boton_1 = new javax.swing.JButton();
        boton_2 = new javax.swing.JButton();
        boton_3 = new javax.swing.JButton();
        boton_6 = new javax.swing.JButton();
        boton_5 = new javax.swing.JButton();
        boton_4 = new javax.swing.JButton();
        boton_7 = new javax.swing.JButton();
        boton_8 = new javax.swing.JButton();
        boton_9 = new javax.swing.JButton();
        boton_coma = new javax.swing.JButton();
        boton_0 = new javax.swing.JButton();
        boton_igual = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        boton_borrar = new javax.swing.JButton();
        boton_dividir = new javax.swing.JButton();
        boton_multiplicar = new javax.swing.JButton();
        boton_menos = new javax.swing.JButton();
        boton_mas = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        pantalla = new javax.swing.JLabel();
        desarrollador = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPanel2KeyTyped(evt);
            }
        });

        boton_1.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        boton_1.setText("1");
        boton_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_1ActionPerformed(evt);
            }
        });

        boton_2.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        boton_2.setText("2");
        boton_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_2ActionPerformed(evt);
            }
        });

        boton_3.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        boton_3.setText("3");
        boton_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_3ActionPerformed(evt);
            }
        });

        boton_6.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        boton_6.setText("6");
        boton_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_6ActionPerformed(evt);
            }
        });

        boton_5.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        boton_5.setText("5");
        boton_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_5ActionPerformed(evt);
            }
        });

        boton_4.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        boton_4.setText("4");
        boton_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_4ActionPerformed(evt);
            }
        });

        boton_7.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        boton_7.setText("7");
        boton_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_7ActionPerformed(evt);
            }
        });

        boton_8.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        boton_8.setText("8");
        boton_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_8ActionPerformed(evt);
            }
        });

        boton_9.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        boton_9.setText("9");
        boton_9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_9ActionPerformed(evt);
            }
        });

        boton_coma.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        boton_coma.setText(",");
        boton_coma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_comaActionPerformed(evt);
            }
        });

        boton_0.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        boton_0.setText("0");
        boton_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_0ActionPerformed(evt);
            }
        });

        boton_igual.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        boton_igual.setText("=");
        boton_igual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_igualActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boton_7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boton_coma, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boton_8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boton_0, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boton_9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boton_igual, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boton_4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boton_1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boton_2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boton_5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boton_3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boton_6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton_1)
                    .addComponent(boton_2)
                    .addComponent(boton_3))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton_4)
                    .addComponent(boton_6)
                    .addComponent(boton_5))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(boton_7)
                        .addComponent(boton_8))
                    .addComponent(boton_9))
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton_coma)
                    .addComponent(boton_0)
                    .addComponent(boton_igual)))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 137, -1, -1));

        boton_borrar.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        boton_borrar.setText("C");
        boton_borrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boton_borrarMouseClicked(evt);
            }
        });

        boton_dividir.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        boton_dividir.setText("÷");
        boton_dividir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_dividirActionPerformed(evt);
            }
        });

        boton_multiplicar.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        boton_multiplicar.setText("×");
        boton_multiplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_multiplicarActionPerformed(evt);
            }
        });

        boton_menos.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        boton_menos.setText("–");
        boton_menos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_menosActionPerformed(evt);
            }
        });

        boton_mas.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        boton_mas.setText("+");
        boton_mas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_masActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boton_mas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boton_dividir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boton_multiplicar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boton_menos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boton_borrar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(boton_borrar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(boton_dividir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(boton_multiplicar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(boton_menos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(boton_mas))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(269, 137, -1, -1));

        pantalla.setBackground(new java.awt.Color(204, 255, 204));
        pantalla.setFont(new java.awt.Font("Elephant", 1, 24)); // NOI18N
        pantalla.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        pantalla.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        pantalla.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, new java.awt.Color(153, 255, 153), java.awt.Color.white, java.awt.Color.black));
        pantalla.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        pantalla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pantallaKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(pantalla);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 38, 315, 93));

        desarrollador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        desarrollador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/calc/programador.png"))); // NOI18N
        desarrollador.setText("Desarrollador");
        desarrollador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                desarrolladorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                desarrolladorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                desarrolladorMouseExited(evt);
            }
        });
        jPanel1.add(desarrollador, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 3, 120, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public double calcula(double uno, double dos,String signo){
                double result=0;
                switch (signo) {
                    case "+":
                        result = uno + dos;
                        break;
                    case "-":
                        result = uno - dos;
                        break;
                    case "×":
                        result = uno * dos;
                        break;
                    case "÷":
                        result = uno / dos;
                        break;
                }
                return result;
            }
    
    private void boton_masActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_masActionPerformed
        // TODO add your handling code here:
        if (pantalla.getText().length() > 0) {
            if (pantalla.getText().charAt(pantalla.getText().length() - 1) != '+') {
                if (pantalla.getText().charAt(pantalla.getText().length() - 1) == '-' || pantalla.getText().charAt(pantalla.getText().length() - 1) == '×'
                        || pantalla.getText().charAt(pantalla.getText().length() - 1) == '÷') {
                    pantalla.setText(pantalla.getText().substring(0, pantalla.getText().length() - 1) + "+");
                } else {
                    pantalla.setText(pantalla.getText() + "+");
                }
            }
        }
        contentPane.requestFocus();
    }//GEN-LAST:event_boton_masActionPerformed

    private void boton_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_6ActionPerformed
        // TODO add your handling code here:
        pantalla.setText(pantalla.getText() + "6");
        contentPane.requestFocus();
    }//GEN-LAST:event_boton_6ActionPerformed

    private void jPanel2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel2KeyTyped
        // TODO add your handling code here:
        char car = evt.getKeyChar();
        switch (car) {
            case 1:
                if (car == KeyEvent.VK_1) {
                    pantalla.setText("1");
                }
                ;

        }
    }//GEN-LAST:event_jPanel2KeyTyped

    private void pantallaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pantallaKeyTyped
        // TODO add your handling code here:
        char car = evt.getKeyChar();
        switch (car) {
            case 1:
                if (car == KeyEvent.VK_1) {
                    pantalla.setText("1");
                }
                ;

        }
    }//GEN-LAST:event_pantallaKeyTyped

    private void boton_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_1ActionPerformed
        // TODO add your handling code here:
        pantalla.setText(pantalla.getText() + "1");
        contentPane.requestFocus();
    }//GEN-LAST:event_boton_1ActionPerformed

    private void boton_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_2ActionPerformed
        // TODO add your handling code here:
        pantalla.setText(pantalla.getText() + "2");
        contentPane.requestFocus();
    }//GEN-LAST:event_boton_2ActionPerformed

    private void boton_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_3ActionPerformed
        // TODO add your handling code here:
        pantalla.setText(pantalla.getText() + "3");
        contentPane.requestFocus();
    }//GEN-LAST:event_boton_3ActionPerformed

    private void boton_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_4ActionPerformed
        // TODO add your handling code here:
        pantalla.setText(pantalla.getText() + "4");
        contentPane.requestFocus();
    }//GEN-LAST:event_boton_4ActionPerformed

    private void boton_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_5ActionPerformed
        // TODO add your handling code here:
        pantalla.setText(pantalla.getText() + "5");
        contentPane.requestFocus();
    }//GEN-LAST:event_boton_5ActionPerformed

    private void boton_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_7ActionPerformed
        // TODO add your handling code here:
        pantalla.setText(pantalla.getText() + "7");
        contentPane.requestFocus();
    }//GEN-LAST:event_boton_7ActionPerformed

    private void boton_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_8ActionPerformed
        // TODO add your handling code here:
        pantalla.setText(pantalla.getText() + "8");
        contentPane.requestFocus();
    }//GEN-LAST:event_boton_8ActionPerformed

    private void boton_9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_9ActionPerformed
        // TODO add your handling code here:
        pantalla.setText(pantalla.getText() + "9");
        contentPane.requestFocus();
    }//GEN-LAST:event_boton_9ActionPerformed

    private void boton_comaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_comaActionPerformed
        // TODO add your handling code here:
        if (pantalla.getText().length() > 0) {
            if (!pantalla.getText().contains(".")) {
                pantalla.setText(pantalla.getText() + ".");
            }
        }
        contentPane.requestFocus();
    }//GEN-LAST:event_boton_comaActionPerformed

    private void boton_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_0ActionPerformed
        // TODO add your handling code here:
        pantalla.setText(pantalla.getText() + "0");
        contentPane.requestFocus();
    }//GEN-LAST:event_boton_0ActionPerformed

    private void boton_dividirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_dividirActionPerformed
        // TODO add your handling code here:
        if (pantalla.getText().length() > 0) {
            if (pantalla.getText().charAt(pantalla.getText().length() - 1) != '÷') {
                if (pantalla.getText().charAt(pantalla.getText().length() - 1) == '-' || pantalla.getText().charAt(pantalla.getText().length() - 1) == '×'
                        || pantalla.getText().charAt(pantalla.getText().length() - 1) == '+') {
                    pantalla.setText(pantalla.getText().substring(0, pantalla.getText().length() - 1) + "÷");
                } else {
                    pantalla.setText(pantalla.getText() + "÷");
                }
            }
        }
        contentPane.requestFocus();
    }//GEN-LAST:event_boton_dividirActionPerformed

    private void boton_multiplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_multiplicarActionPerformed
        // TODO add your handling code here:
        if (pantalla.getText().length() > 0) {
            if (pantalla.getText().charAt(pantalla.getText().length() - 1) != '×') {
                if (pantalla.getText().charAt(pantalla.getText().length() - 1) == '-' || pantalla.getText().charAt(pantalla.getText().length() - 1) == '+'
                        || pantalla.getText().charAt(pantalla.getText().length() - 1) == '÷') {
                    pantalla.setText(pantalla.getText().substring(0, pantalla.getText().length() - 1) + "×");
                } else {
                    pantalla.setText(pantalla.getText() + "×");
                }
            }
        }
        contentPane.requestFocus();
    }//GEN-LAST:event_boton_multiplicarActionPerformed

    private void boton_menosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_menosActionPerformed
        // TODO add your handling code here:
        if (pantalla.getText().length() > 0) {
            if (pantalla.getText().charAt(pantalla.getText().length() - 1) != '-') {
                if (pantalla.getText().charAt(pantalla.getText().length() - 1) != '+') {
                    if (pantalla.getText().charAt(pantalla.getText().length() - 1) != '×') {
                        if (pantalla.getText().charAt(pantalla.getText().length() - 1) != '÷') {
                            pantalla.setText(pantalla.getText() + "-");
                        }
                    }
                }
            }
        }
        if (pantalla.getText().length() > 0) {
            if ((pantalla.getText().charAt(pantalla.getText().length() - 1) == '+') || (pantalla.getText().charAt(pantalla.getText().length() - 1) == '×') || (pantalla.getText().charAt(pantalla.getText().length() - 1) == '÷')) {
                pantalla.setText(pantalla.getText().substring(0, pantalla.getText().length() - 1) + "-");
            }
        }
        if (pantalla.getText().length() == 0) {
            pantalla.setText(pantalla.getText() + "-");
        }
        contentPane.requestFocus();
    }//GEN-LAST:event_boton_menosActionPerformed


    private void boton_borrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton_borrarMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            pantalla.setText("");
        } else {
            if (pantalla.getText().toString().length() > 0) {
                pantalla.setText(pantalla.getText().substring(0, pantalla.getText().toString().length() - 1));
            }
        }
        contentPane.requestFocus();
    }//GEN-LAST:event_boton_borrarMouseClicked

    private void boton_igualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_igualActionPerformed
        // TODO add your handling code here:
        int cont_signos=0;
                    int pos=-1;
                    boolean flag=false;
                    ArrayList array_numero=new ArrayList();
                    ArrayList array_signo=new ArrayList();
                    String texto=pantalla.getText();
                    if(texto.charAt(texto.length()-1)!='+'&&texto.charAt(texto.length()-1)!='-'&&texto.charAt(texto.length()-1)!='×'&&texto.charAt(texto.length()-1)!='÷'){
                    texto=pantalla.getText();
                }else{
                    texto=pantalla.getText().substring(0,texto.length()-1);
                }
                for(int i=1;i<texto.length();i++){
                        if(texto.charAt(i)=='+'||
                               texto.charAt(i)=='-'||
                                texto.charAt(i)=='×'||
                                texto.charAt(i)=='÷'){
                            cont_signos++;
                        }
                    }
                
                for(int i=1;i<texto.length();i++){
                    if(texto.charAt(i)=='+'||
                                texto.charAt(i)=='-'||
                                texto.charAt(i)=='×'||
                                texto.charAt(i)=='÷'){
                            
                            if(cont_signos>=0){
                                cont_signos--;
                                array_numero.add(texto.substring(pos+1,i));
                                array_signo.add(texto.charAt(i));
                                pos=i;
                            }
                        }else{
                        if(cont_signos==0&&flag==false){
                            flag=true;
                                array_numero.add(texto.substring(pos+1,texto.length()));
                            }
                    }
                }
                boolean primero=false;
                double result=0;
                    for (int i=0; i<array_numero.size(); i++) {
                       for (int j=0; j<array_signo.size(); j++) {
                           if(primero==false){
                               primero=true;
                           result=calcula(Double.parseDouble(array_numero.get(0).toString()),Double.parseDouble(array_numero.get(i+1).toString()),array_signo.get(j).toString());
                           i++;
                           }else{
                               result=calcula(result,Double.parseDouble(array_numero.get(i+1).toString()),array_signo.get(j).toString());
                           i++;
                           }
                       }
                    }
                    System.out.println(result);
                    String sin_cero=String.valueOf(result);
                    for(int i=0;i<sin_cero.length();i++){
                       if(sin_cero.charAt(i)=='.'){
                        if(i+1==sin_cero.length()-1&&sin_cero.charAt(sin_cero.length()-1)==48){
                            sin_cero=sin_cero.substring(0,i);
                        }
                    } 
                    }
                    
                    pantalla.setText(sin_cero);
                    contentPane.requestFocus();
    }//GEN-LAST:event_boton_igualActionPerformed

    private void desarrolladorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desarrolladorMouseClicked
        // TODO add your handling code here:
        desarrollador d=new desarrollador();
        d.show();
    }//GEN-LAST:event_desarrolladorMouseClicked

    private void desarrolladorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desarrolladorMouseEntered
        // TODO add your handling code here:
        desarrollador.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(187,187,187)));
    }//GEN-LAST:event_desarrolladorMouseEntered

    private void desarrolladorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desarrolladorMouseExited
        // TODO add your handling code here:
        desarrollador.setBorder(null);
    }//GEN-LAST:event_desarrolladorMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(visual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new visual().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_0;
    private javax.swing.JButton boton_1;
    private javax.swing.JButton boton_2;
    private javax.swing.JButton boton_3;
    private javax.swing.JButton boton_4;
    private javax.swing.JButton boton_5;
    private javax.swing.JButton boton_6;
    private javax.swing.JButton boton_7;
    private javax.swing.JButton boton_8;
    private javax.swing.JButton boton_9;
    private javax.swing.JButton boton_borrar;
    private javax.swing.JButton boton_coma;
    private javax.swing.JButton boton_dividir;
    private javax.swing.JButton boton_igual;
    private javax.swing.JButton boton_mas;
    private javax.swing.JButton boton_menos;
    private javax.swing.JButton boton_multiplicar;
    private javax.swing.JLabel desarrollador;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel pantalla;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        Thread ct = Thread.currentThread();

        while (ct == h1) {
            
            // calcula();
            /* if (hora.equals("01") && minutos.equals("00") && segundos.equals("00")) {
                mantener_off.setSelected(false);
                try {

                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                }
            }*/

        }
    }

}
