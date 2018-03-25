package finalgroupproject.cs.pkg2141;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class GUI extends javax.swing.JFrame {
    Connection con;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/group_project?autoReconnect=true&useSSL=false";
    static final String USER = "root";
    static final String PASSWORD = "root";
    
    public GUI() {
        initComponents();
        populateTable();
        //createConnection();
    }
    /*
    void createConnection(){
        try {
            Class.forName(JDBC_DRIVER);  
            con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(FinalGroupProjectCS2141.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (SQLException ex) {
            Logger.getLogger(FinalGroupProjectCS2141.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
    
    public Connection getConnection(){
        Connection con;
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            return con;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    
    public ArrayList<Music> getMusicList(){
        ArrayList<Music> musicList = new ArrayList<Music>();
        Connection connect = getConnection();
        String query = "SELECT * FROM `TableNames`";
        Statement stmt;
        ResultSet rs;
        
        try {
            stmt = connect.createStatement();
            rs = stmt.executeQuery(query);
            Music music;
            while(rs.next()){
                music = new Music(rs.getString("genre"), rs.getString("country"), rs.getString("label"), rs.getString("artist"), rs.getString("album"), rs.getString("song"), rs.getString("format"), rs.getString("releaseDate"), rs.getString("releaseDesc"), rs.getInt("reviewScore"), rs.getString("reviewComm"));
                musicList.add(music);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return musicList;
    }
    
    public void populateTable(){
        ArrayList<Music> list = getMusicList();
        DefaultTableModel model = (DefaultTableModel) resultsTable.getModel();
        Object[] row = new Object[11];
        for (int i=0; i< list.size(); i++){
            row[0] = list.get(i).getArtist();
            row[1] = list.get(i).getSong();
            row[2] = list.get(i).getAlbum();
            row[3] = list.get(i).getGenre();
            row[4] = list.get(i).getLabel();
            row[5] = list.get(i).getCountry();
            row[6] = list.get(i).getFormat();
            row[7] = list.get(i).getReleaseDate();
            row[8] = list.get(i).getReleaseDesc();
            row[9] = list.get(i).getReviewscore();
            row[10] = list.get(i).getReviewcomm();
            
            model.addRow(row);
        }
    }
    
    public void executeSQLSearch(String query, String status){
        Connection con = getConnection();
        Statement stmt;
        try {
            stmt = con.createStatement();
            if (stmt.executeUpdate(query) == 1){
                JOptionPane.showMessageDialog(null, "Success");
            }
            else {
                JOptionPane.showMessageDialog(null, "Fail");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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
        jScrollPane2 = new javax.swing.JScrollPane();
        resultsTable = new javax.swing.JTable();
        artistTextField = new javax.swing.JTextField();
        songTextField = new javax.swing.JTextField();
        artistLabel = new javax.swing.JLabel();
        songLabel = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        labelTextField = new javax.swing.JTextField();
        labelLabel = new javax.swing.JLabel();
        reviewTextField = new javax.swing.JTextField();
        reviewLabel = new javax.swing.JLabel();
        updateButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        albumTextField = new javax.swing.JTextField();
        albumLabel = new javax.swing.JLabel();
        genreTextField = new javax.swing.JTextField();
        genreLabel = new javax.swing.JLabel();
        reviewcommTextField = new javax.swing.JTextField();
        reviewcommLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        dateTextField = new javax.swing.JTextField();
        releasedescLabel = new javax.swing.JLabel();
        releasedescTextField = new javax.swing.JTextField();
        formatTextField = new javax.swing.JTextField();
        countryTextField = new javax.swing.JTextField();
        countryLabel = new javax.swing.JLabel();
        formatLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setForeground(new java.awt.Color(204, 255, 204));

        resultsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Artist", "Song", "Album", "Genre", "Label", "Country", "Format", "ReleaseDate", "ReleaseDesc", "ReviewScore", "ReviewComment"
            }
        ));
        resultsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resultsTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(resultsTable);

        artistTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                artistTextFieldActionPerformed(evt);
            }
        });

        songTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                songTextFieldActionPerformed(evt);
            }
        });

        artistLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        artistLabel.setText("Artist");

        songLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        songLabel.setText("Song");

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        labelTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labelTextFieldActionPerformed(evt);
            }
        });

        labelLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelLabel.setText("Label");

        reviewTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reviewTextFieldActionPerformed(evt);
            }
        });

        reviewLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        reviewLabel.setText("Review Score");

        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        albumTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                albumTextFieldActionPerformed(evt);
            }
        });

        albumLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        albumLabel.setText("Album");

        genreTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genreTextFieldActionPerformed(evt);
            }
        });

        genreLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        genreLabel.setText("Genre");

        reviewcommTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reviewcommTextFieldActionPerformed(evt);
            }
        });

        reviewcommLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        reviewcommLabel.setText("Review Com.");

        dateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dateLabel.setText("Release Date");

        dateTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateTextFieldActionPerformed(evt);
            }
        });

        releasedescLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        releasedescLabel.setText("Release Desc.");

        releasedescTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                releasedescTextFieldActionPerformed(evt);
            }
        });

        formatTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formatTextFieldActionPerformed(evt);
            }
        });

        countryTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                countryTextFieldActionPerformed(evt);
            }
        });

        countryLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        countryLabel.setText("Country");

        formatLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        formatLabel.setText("Format");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(songLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(albumLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(genreLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(reviewcommLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(releasedescLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                            .addComponent(artistLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(artistTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(songTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(albumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(genreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(releasedescTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(reviewcommTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(formatLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(countryLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(countryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(formatTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(reviewLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(13, 13, 13)
                        .addComponent(reviewTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(artistTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(artistLabel))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(songTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(songLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(albumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(albumLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(genreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(genreLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(countryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(countryLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(formatTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(formatLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateLabel))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(releasedescTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(releasedescLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(reviewTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(reviewLabel))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(reviewcommTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(reviewcommLabel))
                        .addGap(42, 42, 42)
                        .addComponent(addButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton)
                        .addGap(0, 435, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void artistTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_artistTextFieldActionPerformed
        
    }//GEN-LAST:event_artistTextFieldActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        String query = "INSERT INTO `TableName`(`columnName1`, `columnName2`) VALUES ('" + artistTextField.getText() + "', '" + songTextField.getText() + "', '" + albumTextField.getText() + "', '" + genreTextField.getText() + "', '" + labelTextField.getText() + "', '" + countryTextField.getText() + "', '" + formatTextField.getText() + "', '" + dateTextField.getText() + "', '" + releasedescTextField.getText() + "', '" + reviewTextField.getText() + "', '" + reviewcommTextField.getText() + "')";
        executeSQLSearch(query, "Added");
    }//GEN-LAST:event_addButtonActionPerformed

    private void songTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_songTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_songTextFieldActionPerformed

    private void albumTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albumTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_albumTextFieldActionPerformed

    private void genreTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genreTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genreTextFieldActionPerformed

    private void labelTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labelTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_labelTextFieldActionPerformed

    private void reviewTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reviewTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reviewTextFieldActionPerformed
    
    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        String query = "UPDATE `tableName` SET `columnName`='" + artistTextField.getText() + "', '" + songTextField.getText() + "', '" + albumTextField.getText() + "', '" + genreTextField.getText() + "', '" + labelTextField.getText() + "', '" + countryTextField.getText() + "', '" + formatTextField.getText() + "', '" + dateTextField.getText() + "', '" + releasedescTextField.getText() + "', '" + reviewTextField.getText() + "', '" + reviewcommTextField.getText() + "' WHERE `columnName`= +++id for selected row+++";
        executeSQLSearch(query, "Updated");
        
    }//GEN-LAST:event_updateButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchButtonActionPerformed

    private void reviewcommTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reviewcommTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reviewcommTextFieldActionPerformed

    private void dateTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateTextFieldActionPerformed

    private void releasedescTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_releasedescTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_releasedescTextFieldActionPerformed

    private void formatTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formatTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_formatTextFieldActionPerformed

    private void countryTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_countryTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_countryTextFieldActionPerformed

    private void resultsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultsTableMouseClicked
        int i = resultsTable.getSelectedRow();
        TableModel model = resultsTable.getModel();
        
        artistTextField.setText(model.getValueAt(i, 0).toString());
        songTextField.setText(model.getValueAt(i, 1).toString());
        albumTextField.setText(model.getValueAt(i, 2).toString());
        genreTextField.setText(model.getValueAt(i, 3).toString());
        labelTextField.setText(model.getValueAt(i, 4).toString());
        countryTextField.setText(model.getValueAt(i, 5).toString());
        formatTextField.setText(model.getValueAt(i, 6).toString());
        dateTextField.setText(model.getValueAt(i, 7).toString());
        releasedescTextField.setText(model.getValueAt(i, 8).toString());
        reviewTextField.setText(model.getValueAt(i, 9).toString());
        reviewcommTextField.setText(model.getValueAt(i, 10).toString());
                
    }//GEN-LAST:event_resultsTableMouseClicked

    
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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JLabel albumLabel;
    private javax.swing.JTextField albumTextField;
    private javax.swing.JLabel artistLabel;
    private javax.swing.JTextField artistTextField;
    private javax.swing.JLabel countryLabel;
    private javax.swing.JTextField countryTextField;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JTextField dateTextField;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel formatLabel;
    private javax.swing.JTextField formatTextField;
    private javax.swing.JLabel genreLabel;
    private javax.swing.JTextField genreTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelLabel;
    private javax.swing.JTextField labelTextField;
    private javax.swing.JLabel releasedescLabel;
    private javax.swing.JTextField releasedescTextField;
    private javax.swing.JTable resultsTable;
    private javax.swing.JLabel reviewLabel;
    private javax.swing.JTextField reviewTextField;
    private javax.swing.JLabel reviewcommLabel;
    private javax.swing.JTextField reviewcommTextField;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel songLabel;
    private javax.swing.JTextField songTextField;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
