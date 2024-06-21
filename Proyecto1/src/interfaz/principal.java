/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaz;
import Analizadores.Sintactico;
import Analizadores.scanner;
import Errores.Excepcion;
import java.awt.Desktop;
import Token.TokenInfo;
import proyecto.GeneradorL;
import proyecto.GeneradorS;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import abstracto.Instruccion;
import java.util.LinkedList;
import simbolo.Arbol;
import simbolo.tablaSimbolos;

/**
 *
 * @author amaya
 */
public class principal extends javax.swing.JFrame {

    /**
     * Creates new form principal
     */

    private String archivoActual;

    public principal() {
        initComponents();
        
        // Llamada a GeneradorS
        GeneradorS.main(new String[]{});

        // Llamada a GeneradorL
        GeneradorL.main(new String[]{});

    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenuItem5.setText("jMenuItem5");

        jMenuItem8.setText("jMenuItem8");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JavaCraft");
        setMaximumSize(new java.awt.Dimension(2147483647, 2147483547));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Consola");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Entrada");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextArea2.setBackground(new java.awt.Color(0, 0, 0));
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        jTextArea2.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jMenuBar2.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N

        jMenu3.setText("Archivo");

        jMenuItem1.setText("Abrir Archivo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem2.setText("Guardar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuItem3.setText("Guardar Como");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar2.add(jMenu3);

        jMenu5.setText("Herramientas");

        jMenuItem10.setText("Ejecutar");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem10);

        jMenuBar2.add(jMenu5);

        jMenu6.setText("Reporte");

        jMenuItem7.setText("Errores");
        jMenuItem7.setActionCommand("Errores ");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem7);

        jMenuItem4.setText("Generar AST");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem4);

        jMenuItem9.setText("Tabla Simbolos");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem9);

        jMenuItem6.setText("Tokens");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem6);

        jMenuBar2.add(jMenu6);

        jMenu8.setText("Salir");
        jMenu8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu8MouseClicked(evt);
            }
        });
        jMenu8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu8ActionPerformed(evt);
            }
        });
        jMenuBar2.add(jMenu8);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                        .addGap(19, 19, 19))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("DataForge");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        //ABRIR ARCHIVO

    JFileChooser fileChooser = new JFileChooser();

    int choice = fileChooser.showOpenDialog(this);

    if (choice == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();

        // Guarda la ruta del archivo actualmente abierto
        archivoActual = selectedFile.getAbsolutePath();

        // Lee el contenido del archivo y muestra en jTextArea1
        try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
            StringBuilder fileContents = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                fileContents.append(line).append("\n");
            }
            jTextArea1.setText(fileContents.toString());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al leer el archivo seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
            try {
        // Especifica la ruta completa al archivo HTML de la tabla de errores
        //String path = "C:/Users/eliza/OneDrive/Documentos/GitHub/OLC1_VJ24_20200558/Proyecto1/Reportes/ReporteTokens.html";
        String relativePath = "." + File.separator + "Reportes" + File.separator + "ReporteTokens.html";
        // Verifica si el archivo existe antes de intentar abrirlo
        File file = new File(relativePath);
        if (file.exists()) {
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        } else {
            JOptionPane.showMessageDialog(this, "El archivo de la tabla de errores no existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (IOException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al abrir el archivo de la tabla de errores.", "Error", JOptionPane.ERROR_MESSAGE);
    }
 
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
    try {
        // Especifica la ruta completa al archivo HTML de la tabla de errores
        //String path = "C:/Users/eliza/OneDrive/Documentos/GitHub/OLC1_VJ24_20200558/Proyecto1/Reportes/ReporteErrores.html";
        String relativePath = "." + File.separator + "Reportes" + File.separator + "ReporteErrores.html";
        // Verifica si el archivo existe antes de intentar abrirlo
        File file = new File(relativePath);
        if (file.exists()) {
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        } else {
            JOptionPane.showMessageDialog(this, "El archivo de la tabla de errores no existe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (IOException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al abrir el archivo de la tabla de errores.", "Error", JOptionPane.ERROR_MESSAGE);
    }
     
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        //GUARDAR
    if (archivoActual != null) {
        try (FileWriter writer = new FileWriter(archivoActual);
            BufferedWriter bw = new BufferedWriter(writer)) {
            // Escribe el contenido del jTextArea1 en el archivo
            bw.write(jTextArea1.getText());
            JOptionPane.showMessageDialog(this, "Contenido guardado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        // Si no hay archivo abierto, muestra un mensaje de error
        JOptionPane.showMessageDialog(this, "No hay archivo abierto para guardar.", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        //GUARDAR COMO
         JFileChooser fileChooser = new JFileChooser();
    
        int choice = fileChooser.showSaveDialog(this);

        if (choice == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try (FileWriter writer = new FileWriter(selectedFile);
                 BufferedWriter bw = new BufferedWriter(writer)) {
                // Escribe el contenido del jTextArea1 en el archivo
                bw.write(jTextArea1.getText());
                JOptionPane.showMessageDialog(this, "Contenido guardado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al guardar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenu8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu8ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenu8ActionPerformed

    private void jMenu8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu8MouseClicked
        // TODO add your handling code here:
        //SALIR
        System.exit(0);
    }//GEN-LAST:event_jMenu8MouseClicked

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        //boton tabla de simbolos
        try {
            // Especifica la ruta completa al archivo HTML de la tabla de errores
            //String path = "C:/Users/eliza/OneDrive/Documentos/GitHub/OLC1_VJ24_20200558/Proyecto1/Reportes/ReporteTabla.html";
            String relativePath = "." + File.separator + "Reportes" + File.separator + "ReporteTabla.html";
            // Verifica si el archivo existe antes de intentar abrirlo
            File file = new File(relativePath);
            if (file.exists()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
            } else {
                JOptionPane.showMessageDialog(this, "El archivo de la tabla de simbolos no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al abrir el archivo de la tabla de simbolos.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        //------EJECUTAR------
        Analizadores.scanner scanner; //.java
        Analizadores.Sintactico parse;
        //ArrayList<Excepcion> errores = new ArrayList(); //agregar errores
        ArrayList<TokenInfo> tokens = new ArrayList();

        try {
            scanner = new scanner(new BufferedReader(new StringReader(jTextArea1.getText())));
            parse = new Sintactico(scanner);
            var resultado = parse.parse(); 

            //errores.addAll(scanner.Errores); //errores lexicos
            //errores.addAll(parse.getErrores()); //errores sintacticos
            //generarReporteHTML(errores);   //generar reporte de errores lexicos y sintacticos
            tokens.addAll(scanner.getTokens()); 
            generarReporteTokensHTML(tokens); //generar reporte de tokens

            if (resultado.value instanceof LinkedList) {
                var ast = new Arbol((LinkedList<Instruccion>) resultado.value);
                var tabla = new tablaSimbolos();
                tabla.setNombre("GLOBAL");
                ast.setConsola("");
                LinkedList<Excepcion> lista = new LinkedList<>();
                lista.addAll(scanner.Errores);
                lista.addAll(parse.Errores);
                for (var a : ast.getInstrucciones()) {
                    if (a == null) {
                        continue;
                    }

                    var res = a.interpretar(ast, tabla);
                    if (res instanceof Excepcion) {
                        lista.add((Excepcion) res); //errores semanticos
                    }
                }

                StringBuilder result = new StringBuilder();
                result.append(ast.getConsola()).append("\n");
                
                generarReporteHTML(lista); //html de errores
                
                for (var i : lista) {
                    result.append(i.toString()).append("\n");
                }
                this.jTextArea2.setText(result.toString());
            } else {
                throw new Exception("El resultado del análisis no es una lista de instrucciones");
            }

        } catch (Exception ex) {
            Logger.getLogger(principal.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error fatal en compilación de entrada: " + ex.getMessage());
        }
    }//GEN-LAST:event_jMenuItem10ActionPerformed



    public static void generarReporteHTML(LinkedList<Excepcion> errores) throws IOException {
        FileWriter fichero = null;
        PrintWriter pw = null;

        try {
            // Define la ruta relativa usando el separador de archivos del sistema
            String relativePath = "." + File.separator + "Reportes" + File.separator + "ReporteErrores.html";
            
            // Crear directorios si no existen
            File file = new File(relativePath);
            file.getParentFile().mkdirs();
            
            // Inicializa el FileWriter con la ruta relativa
            fichero = new FileWriter(file);
            pw = new PrintWriter(fichero);

            // Comienza a escribir el HTML
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<title>REPORTE DE ERRORES</title>");
            pw.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN\" crossorigin=\"anonymous\">");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<div class=\"container\">");
            pw.println("<h1 class=\"mt-5\">Reporte de Errores</h1>");
            pw.println("<br></br>");

            // Agrega la tabla con clases de Bootstrap
            pw.println("<table class=\"table\">");
            pw.println("<thead class=\"thead-dark\">");
            pw.println("<tr>");
            pw.println("<th scope=\"col\">#</th>");
            pw.println("<th scope=\"col\">ERROR</th>");
            pw.println("<th scope=\"col\">DESCRIPCION</th>");
            pw.println("<th scope=\"col\">FILA</th>");
            pw.println("<th scope=\"col\">COLUMNA</th>");
            pw.println("</tr>");
            pw.println("</thead>");
            pw.println("<tbody>");

            // Itera sobre la lista de errores y los agrega a la tabla
            int count = 1;
            for (Excepcion err : errores) {
                pw.println("<tr>");
                pw.println("<td>" + count + "</td>");
                pw.println("<td>" + err.tipo + "</td>");
                pw.println("<td>" + err.descripcion + "</td>");
                pw.println("<td>" + err.linea + "</td>");
                pw.println("<td>" + err.columna + "</td>");
                pw.println("</tr>");
                count++;
            }

            pw.println("</tbody>");
            pw.println("</table>");

            // Continúa con el resto del contenido HTML
            pw.println("</div>");
            pw.println("</body>");
            pw.println("</html>");
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fichero != null) {
                fichero.close();
            }
        }
    }
    

    public static void generarReporteTokensHTML(ArrayList<TokenInfo> tokens) throws IOException {
        FileWriter fichero = null;
        PrintWriter pw = null;

        try {
            // Define la ruta relativa usando el separador de archivos del sistema
            String relativePath = "." + File.separator + "Reportes" + File.separator + "ReporteTokens.html";
            
            // Crear directorios si no existen
            File file = new File(relativePath);
            file.getParentFile().mkdirs();
            
            // Inicializa el FileWriter con la ruta relativa
            fichero = new FileWriter(file);
            pw = new PrintWriter(fichero);

            // Comienza a escribir el HTML
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<title>REPORTE DE TOKENS</title>");
            pw.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN\" crossorigin=\"anonymous\">");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<div class=\"container\">");
            pw.println("<h1 class=\"mt-5\">Reporte de Tokens</h1>");
            pw.println("<br></br>");

            // Agrega la tabla con clases de Bootstrap
            pw.println("<table class=\"table\">");
            pw.println("<thead class=\"thead-dark\">");
            pw.println("<tr>");
            pw.println("<th scope=\"col\">#</th>");
            pw.println("<th scope=\"col\">LEXEMA</th>");
            pw.println("<th scope=\"col\">TOKEN</th>");
            pw.println("<th scope=\"col\">LÍNEA</th>");
            pw.println("<th scope=\"col\">COLUMNA</th>");
            pw.println("</tr>");
            pw.println("</thead>");
            pw.println("<tbody>");

            // Itera sobre la lista de tokens y los agrega a la tabla
            for (int i = 0; i < tokens.size(); i++) {
                TokenInfo token = tokens.get(i);
                pw.println("<tr>");
                pw.println("<td>" + (i + 1) + "</td>");
                pw.println("<td>" + token.getLexema() + "</td>");
                pw.println("<td>" + token.getToken() + "</td>");
                pw.println("<td>" + token.getLinea() + "</td>");
                pw.println("<td>" + token.getColumna() + "</td>");
                pw.println("</tr>");
            }

            pw.println("</tbody>");
            pw.println("</table>");

            // Continúa con el resto del contenido HTML
            pw.println("</div>");
            pw.println("</body>");
            pw.println("</html>");
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fichero != null) {
                fichero.close();
            }
        }
    }
    
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
