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
    static final String DB_URL = "jdbc:mysql://localhost:3306/musicdb?autoReconnect=true&useSSL=false";
    static final String USER = "root";
    static final String PASSWORD = "root";
    
    public GUI() {
        initComponents();
        populateTable();
        //createConnection();
    }
   
    public Connection getConnection(){
        Connection con;
        try {
            con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            return con;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Music> getMusicList(){
        ArrayList<Music> musicList = new ArrayList<Music>();
        Connection connect = getConnection();
        //String query = "SELECT Artist_Name, Track_Name, Album_Name, Genre_Name, Country_Name FROM " + "Artist full join Album using (Artist_id) " + "full join Track using (Album_id) " + "full join Country using (Country_ID) " + "full join Genre using (Genre_ID)";
        String query = "SELECT Artist.Artist_Name, Track.Track_Name, Album.Album_Name, Genre.Genre_Name, Country.Country_Name FROM Country right join Artist on Country.Country_ID = Artist.Country_ID right join Album on Artist.Artist_ID = Album.Artist_ID right join Track on Album.Album_ID = Track.Album_ID right join Genre on Album.Genre_ID = Genre.Genre_ID;";
        
        Statement stmt;
        ResultSet rs;
        
        try {
            stmt = connect.createStatement();
            rs = stmt.executeQuery(query);
            Music music;
            while(rs.next()){
                music = new Music(rs.getString("Artist_Name"), rs.getString("Track_Name"), rs.getString("Album_Name"), rs.getString("Genre_Name"), rs.getString("Country_Name"));
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
        Object[] row = new Object[5];
        for (int i=0; i< list.size(); i++){
            row[0] = list.get(i).getArtist();
            row[1] = list.get(i).getSong();
            row[2] = list.get(i).getAlbum();
            row[3] = list.get(i).getGenre();
            row[4] = list.get(i).getCountry();
            
            model.addRow(row);
        }
    }
    
    public void executeSQLSearch(String query, String status){
        Connection con = getConnection();
        Statement stmt;
        try {
            stmt = con.createStatement();
            if (stmt.executeUpdate(query) == 1){
                DefaultTableModel model = (DefaultTableModel)resultsTable.getModel();
                model.setRowCount(0);
                populateTable();
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
    
    public int exists(String check, int type) throws SQLException{
        Connection connect = getConnection();
        int value=-1;
        String query;
        
        if (type == 1){
            query = "SELECT Artist_Name, Artist_ID FROM Artist";
        }
        else {
            query = "SELECT Album_Name, Album_ID FROM Album";
        }
        
        Statement stmt = connect.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        while (rs.next()){
            if(check.equals(rs.getString(0))){
                value = rs.getInt(1);
                break;
            }
            else {
                value = -1;
            }
        }
        return value;
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
        updateButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        albumTextField = new javax.swing.JTextField();
        albumLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setForeground(new java.awt.Color(204, 255, 204));

        resultsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Artist", "Song", "Album", "Genre", "Country"
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
                            .addComponent(artistLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(artistTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(songTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(albumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addGap(48, 48, 48)
                        .addComponent(addButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE))
                .addContainerGap())
        );

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
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void artistTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_artistTextFieldActionPerformed
        
    }//GEN-LAST:event_artistTextFieldActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        Statement stmt;
        Connection connect = getConnection();
        String artist = artistTextField.getText() + " ";
        String song = songTextField.getText() + " ";
        String album = albumTextField.getText() + " ";
        
        try {
            int artistID = exists(artist, 1);
            int albumID = exists(album, 2);
            stmt = connect.createStatement();
            
            if (artistID >= 0){
                //existing artist
                //artist
                //song needs artist
                //album needs artist and song
                if (albumID >= 0){
                    if (!song.equals(null)){
                        stmt.addBatch("INSERT INTO Track (Track_Name, Album_ID, Artist_ID) VALUES ('" + song + "', '" + albumID + "' " + artistID + "); ");
                    }
                    
                }
                else {
                    stmt.addBatch("INSERT INTO Album (Album_Name, Genre_ID, Artist_ID) VALUES ('" + album + "', null, '" + artistID +"');");
                    stmt.addBatch("INSERT INTO Track (Track_Name, Album_ID, Artist_ID) VALUES ('" + song + "', null, " + artistID + "); ");
                }
            }
            else {
                //artist doesn't exists
                if (!song.equals(null)){
                    stmt.addBatch("INSERT INTO Track (Track_Name, Album_ID, Artist_ID) VALUES ('" + song + "', '" + albumID + "' " + artistID + "); ");
                }
                
                else {
                    stmt.addBatch("INSERT INTO Album (Album_Name, Genre_ID, Artist_ID) VALUES ('" + album + "', null, '" + artistID +"');");
                    stmt.addBatch("INSERT INTO Track (Track_Name, Album_ID, Artist_ID) VALUES ('" + song + "', null, " + artistID + "); ");
                }
                
            }
                /*
                if (!artist.equals(null)){
                    stmt.addBatch("INSERT INTO Track (Artist_ID) VALUES ('" + artistID +"); ");
                    if (!song.equals(null)){
                    stmt.addBatch("INSERT INTO Track (Track_Name, Album_ID) VALUES ('" + song + "'); ");
                }
                }
                
                if (!album.equals(null)){
                    stmt.addBatch("INSERT INTO Album (Album_Name) VALUES ('" + album + "') ");
                }
                
                */

        } catch (SQLException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        if (!artist.equals(null) && !song.equals(null) && !album.equals(null)){
            
            try {
                stmt = connect.createStatement();
                if (!artist.equals(null)){
                    
                    stmt.addBatch("INSERT INTO Artist (Artist_Name) VALUES ('" + artist + "'); ");
                }
                if (!song.equals(null)){
                    stmt.addBatch("INSERT INTO Track (Track_Name, Album_ID) VALUES ('" + song + "'); ");
                }
                if (!album.equals(null)){
                    stmt.addBatch("INSERT INTO Album (Album_Name) VALUES ('" + album + "') ");
                }
                stmt.executeBatch();
            } 
            catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            DefaultTableModel model = (DefaultTableModel) resultsTable.getModel();
            model.setRowCount(0);
            populateTable();
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void songTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_songTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_songTextFieldActionPerformed

    private void albumTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albumTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_albumTextFieldActionPerformed
    
    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        
        /*
            UPDATE Artist SET Artist_Name='' AND Track_Name = '' AND Album_Name = '' WHERE Artist_Name='' AND Track_Name = '' AND Album_Name = '';
            UPDATE Track SET Artist_Name='' AND Track_Name = '' AND Album_Name = '' WHERE Artist_Name='' AND Track_Name = '' AND Album_Name = '';
            UPDATE Album SET Artist_Name='' AND Track_Name = '' AND Album_Name = '' WHERE Artist_Name='' AND Track_Name = '' AND Album_Name = '';
        */
        int row = resultsTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) resultsTable.getModel();
        if (row >= 0){
            model.setValueAt(artistTextField.getText(), row, 0);
            model.setValueAt(songTextField.getText(), row, 1);
            model.setValueAt(albumTextField.getText(), row, 2);
        }
        else {
            JOptionPane.showMessageDialog(null, "Error");
        }
        
        /*
        boolean artist = artistTextField.getText().equals(null);
        boolean album = albumTextField.getText().equals(null);
        boolean song = songTextField.getText().equals(null);
        String query1, query2="", query3="";
        
        if (!artist || !album || !song){
            if (!artist){
                if (!album){
                    if(!song){
                        query2 = "SET Artist_Name = '" + artistTextField.getText() + "' AND Album_Name = '" + albumTextField.getText() + "' AND Song_Name = '" + songTextField.getText() + "';";
                        query3 = "WHERE Artist_Name = '" + artistTextField.getText() + "' AND Album_Name = '" + albumTextField.getText() + "' AND Song_Name = '" + songTextField.getText() + "';";
                    }
                    else 
                        query3 = "WHERE Artist_Name = '" + artistTextField.getText() + "' AND Album_Name = '" + albumTextField.getText() + "';";
                }
                if (!song){
                    query3 = "WHERE Artist_Name = '" + artistTextField.getText() + "' AND Song_Name = '" + songTextField.getText() + "';";
                }
                else 
                    query3 = "WHERE Artist_Name = '" + artistTextField.getText() + "';";                    
            }
            else if (!album){
                if (!song){
                    query3 = "WHERE Album_Name = '" + albumTextField.getText() + "' AND Song_Name = '" + songTextField.getText() + "';";
                }
                else 
                    query3 = "WHERE Album_Name = '" + albumTextField.getText() + "';";
            }
            else {
                query3 = "WHERE Song_Name = '" + songTextField.getText() + "';";
            }
            String query = "UPDATE `tableName` SET `columnName`='" + artistTextField.getText() + "', '" + songTextField.getText() + "', '" + albumTextField.getText() + "', '" + genreTextField.getText() + "', '" + labelTextField.getText() + "', '" + countryTextField.getText() + "', '" + dateTextField.getText() + "', '" + reviewTextField.getText() + "' WHERE `columnName`= +++id for selected row+++";
            executeSQLSearch(query, "Updated");
        }
        else {
            //---------ROW WAS NOT SELECTED---------POPUP MESSAGE----------
        }
        
        
        */  
          
    }//GEN-LAST:event_updateButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed

       
        boolean song = songTextField.getText().equals(null);
        String query;
        
        if (!song){
            query = "DELETE FROM Track WHERE Track_Name = '" + songTextField.getText() + "';";
            executeSQLSearch(query, "Success");
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        boolean artist = artistTextField.getText().equals(null);
        boolean album = albumTextField.getText().equals(null);
        boolean song = songTextField.getText().equals(null);
        String query1, query2="";
        
        if (!artist || !album || !song){
            if (!artist){
                if (!album){
                    if(!song){
                       query2 = "WHERE Artist_Name = " + artistTextField.getText() + " AND Album_Name = " + albumTextField.getText() + " AND Song_Name = " + songTextField.getText() + ";";
                    }
                    else 
                        query2 = "WHERE Artist_Name = " + artistTextField.getText() + " AND Album_Name = " + albumTextField.getText() + ";";
                }
                if (!song){
                    query2 = "WHERE Artist_Name = " + artistTextField.getText() + " AND Song_Name = " + songTextField.getText() + ";";
                }
                else 
                    query2 = "WHERE Artist_Name = " + artistTextField.getText() + ";";                    
            }
            else if (!album){
                if (!song){
                    query2 = "WHERE Album_Name = " + albumTextField.getText() + " AND Song_Name = " + songTextField.getText() + ";";
                }
                else 
                    query2 = "WHERE Album_Name = " + albumTextField.getText() + ";";
            }
            else {
                query2 = "WHERE Song_Name = " + songTextField.getText() + ";";
            }
        }
        
        
        query1 = "SELECT Artist_Name, Track_Name, Album_Name, Country_Name, Genre_Name from Artist join Album using (Artist_ID) join Track using (Album_id) join Country using (Country_ID) join Genre using (Genre_ID)" + query2 + ";";
        executeSQLSearch(query1, "Search");
    }//GEN-LAST:event_searchButtonActionPerformed

    private void resultsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultsTableMouseClicked
        int i = resultsTable.getSelectedRow();
        TableModel model = resultsTable.getModel();
        
        artistTextField.setText(model.getValueAt(i, 0).toString());
        songTextField.setText(model.getValueAt(i, 1).toString());
        albumTextField.setText(model.getValueAt(i, 2).toString());
                
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
    private javax.swing.JButton deleteButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable resultsTable;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel songLabel;
    private javax.swing.JTextField songTextField;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
