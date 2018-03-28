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
        String query = "select artist_Name, track_name, alb.album_name, Country_Name, genre_name\n" +
"from artist join track using(artist_id) join album as alb using(album_id) join country using(country_ID) join genre using(genre_id)\n" +
"where\n" +
"alb.Artist_ID=artist.Artist_ID and alb.Album_ID=track.Album_ID and artist.Country_ID=country.Country_ID and alb.Genre_ID=genre.Genre_ID\n" +
"group by Track_Name \n" +
"order by\n" +
"Artist_Name asc;";
        
        Statement stmt;
        ResultSet rs;
        
        try {
            stmt = connect.createStatement();
            rs = stmt.executeQuery(query);
            Music music;
            while(rs.next()){
                music = new Music(rs.getString("Artist_Name"), rs.getString("Track_Name"), rs.getString("Album_Name"), rs.getString("Genre_name"), rs.getString("Country_Name"));
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
        model.setRowCount(0);
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
    
    public void populateSearchTable(String query){
        ArrayList<Music> musicList2 = new ArrayList<Music>();
        Connection connect = getConnection();
        Statement stmt;
        ResultSet rs;
        
        try {
            stmt = connect.createStatement();
            rs = stmt.executeQuery(query);
            Music music;
            while(rs.next()){
                music = new Music(rs.getString("Artist_Name"), rs.getString("Track_Name"), rs.getString("Album_Name"), rs.getString("Genre_name"), rs.getString("Country_Name"));
                musicList2.add(music);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        
        DefaultTableModel model = (DefaultTableModel) resultsTable.getModel();
        model.setRowCount(0);
        Object[] row = new Object[5];
        
        for (int i=0; i< musicList2.size(); i++){
            row[0] = musicList2.get(i).getArtist();
            row[1] = musicList2.get(i).getSong();
            row[2] = musicList2.get(i).getAlbum();
            row[3] = musicList2.get(i).getGenre();
            row[4] = musicList2.get(i).getCountry();
            
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
    
    public String songExist(String check, String id) throws SQLException{
        Connection connect = getConnection();
        String query = "SELECT Track_name, Artist_ID FROM track";
        Statement stmt = connect.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        while (rs.next()){
            
            if(check.equals(rs.getString(1))&& id.equals(rs.getString(2)) ){
                return "true";
            }
        }
        return "false";
    }
    
    public String exists(String check, int type) throws SQLException{
        Connection connect = getConnection();
        String value=null;
        String query;
        
        if (type == 1){
            query = "SELECT Artist_Name, Artist_ID FROM Artist";
        }
        else if(type == 2) {
            query = "SELECT Album_Name, Album_ID FROM Album";
        }
        else if(type == 3){
            query = "SELECT Country_name, Country_ID from country";
        }else {
            query = "SELECT genre_name, genre_ID from genre";
        }
        Statement stmt = connect.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        
        while (rs.next()){
            
            if(check.equals(rs.getString(1))){
                value = rs.getString(2);
                System.out.println(value);
                break;
            }
            else {
                value ="no";
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
        Country_ComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        Genre_ComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

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

        Country_ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "United States", "Canada", "Brazil", "United Kingdom", "Poland", "Australia", "Japan" }));
        Country_ComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Country_ComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Country");

        Genre_ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Speed Metal", "Black Metal", "Doom Metal", "Hard Rock", "Funk" }));
        Genre_ComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Genre_ComboBoxActionPerformed(evt);
            }
        });

        jLabel2.setText("Genre");

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
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(artistTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(songTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(albumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Country_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Genre_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(addButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 966, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(299, 299, 299))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Country_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(Genre_ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(74, 74, 74)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void artistTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_artistTextFieldActionPerformed
        
    }//GEN-LAST:event_artistTextFieldActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        Statement stmt;
        Connection connect = getConnection();
        String artist = artistTextField.getText();
        String song = songTextField.getText();
        String album = albumTextField.getText();
         
        try {
            stmt = connect.createStatement();           
            String artistID = exists(artist, 1);
            String albumID = exists(album, 2);
            stmt = connect.createStatement();
            String country = exists(Country_ComboBox.getSelectedItem().toString(),3);
            String genre = exists(Genre_ComboBox.getSelectedItem().toString(),4);
            int cID,gID;
            cID= Integer.parseInt(country);
            gID=Integer.parseInt(genre);
            if (!artistID.equals("no") ){
                //existing artist
                //artist
                //song needs artist
                //album needs artist and song
                int aID = Integer.parseInt(artistID);
                if (!albumID.equals("no")){
                    
                    int albID = Integer.parseInt(albumID);
                    if (songTextField.getText()!=null){
                        if(songExist(songTextField.getText(),artistID).equals("false"))
                            stmt.addBatch("INSERT INTO Track (Track_Name, Album_ID, Artist_ID) VALUES ('" + songTextField.getText()  + "', " + albID + ", " + aID + ");");
                    }     
                }
                else if(albumTextField.getText()== null){
                    stmt.addBatch("INSERT INTO Track (Track_Name, Album_ID, Artist_ID) VALUES ('" + song + "', null, " + aID + "); ");
                    
                }else{
                    stmt.addBatch("INSERT INTO Album (Album_Name, Genre_ID, Artist_ID) VALUES ('" + album + "', "+ gID +", " + aID +");");
                    stmt.executeBatch();
                    albumID=exists(album,2);
                    stmt = connect.createStatement();
                    int albID = Integer.parseInt(albumID);
                    
                    stmt.addBatch("INSERT INTO Track (Track_Name, Album_ID, Artist_ID) VALUES ('" + song + "',"+ albID+", " + aID + "); ");
                } 
            }else {
                if(!artistTextField.getText().equals("")){
                    stmt.addBatch("INSERT INTO artist (artist_name,country_ID,Label_ID) VALUES ('"+ artistTextField.getText()+"',"+ cID +", null);");
                    stmt.executeBatch();
                    stmt = connect.createStatement();
                    artistID = exists(artist, 1);
                    int aID = Integer.parseInt(artistID);
                    if (!albumID.equals("no")){

                        int albID = Integer.parseInt(albumID);
                        if (songTextField.getText()!=null){
                            stmt.addBatch("INSERT INTO Track (Track_Name, Album_ID, Artist_ID) VALUES ('" + songTextField.getText()  + "', " + albID + ", " + aID + ");");
                        }
                    }
                    else if(albumTextField.getText()== null){
                        stmt.addBatch("INSERT INTO Track (Track_Name, Album_ID, Artist_ID) VALUES ('" + song + "', null, " + aID + "); ");
                    }else {
                        stmt.addBatch("INSERT INTO Album (Album_Name, Genre_ID, Artist_ID) VALUES ('" + album + "',"+ gID +", " + aID +");");
                        stmt.executeBatch();
                        albumID=exists(album,2);
                        stmt = connect.createStatement();
                        int albID = Integer.parseInt(albumID);
                    
                        stmt.addBatch("INSERT INTO Track (Track_Name, Album_ID, Artist_ID) VALUES ('" + song + "',"+ albID+", " + aID + "); ");
                
                    }
                }
            }
                 stmt.executeBatch();
                 
        } catch (SQLException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultTableModel model = (DefaultTableModel) resultsTable.getModel();
            model.setRowCount(0);
            populateTable();
        
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
        boolean artist = artistTextField.getText().equals("");
        boolean album = albumTextField.getText().equals("");
        boolean song = songTextField.getText().equals("");
        String query1="", query2="";
        
        if (!artist || !album || !song){
            if (!artist){
                if (!album){
                    if(!song){
                       query2 = "WHERE artist.artist_Name = '" + artistTextField.getText() + "' AND alb.album_Name = '" + albumTextField.getText() + "' AND track.track_Name = '" + songTextField.getText() + "' ";
                    }
                    else 
                        query2 = "WHERE artist.artist_Name = '" + artistTextField.getText() + "' AND alb.album_Name = '" + albumTextField.getText() + "' ";
                }
                else if (!song){
                    query2 = "WHERE artist.artist_Name = '" + artistTextField.getText() + "' AND track.track_Name = '" + songTextField.getText() + "' ";
                }
                else 
                    query2 = "WHERE artist.artist_Name = '" + artistTextField.getText() + "' ";                    
            }
            else if (!album){
                if (!song){
                    query2 = "WHERE alb.album_Name = '" + albumTextField.getText() + "' AND track.track_Name = '" + songTextField.getText() + "' ";
                }
                else 
                    query2 = "WHERE alb.album_Name = '" + albumTextField.getText() + "' ";
            }
            else {
                query2 = "WHERE track.track_Name = '" + songTextField.getText() + "' ";
            }
            query1 = "SELECT artist_Name, track_name, alb.album_name, Country_Name, genre_name FROM artist JOIN track USING (artist_id) JOIN album AS alb USING (album_id) JOIN country USING (country_ID) JOIN genre USING (genre_id) " + query2 + " ORDER BY artist_Name ASC;";
            populateSearchTable(query1);
        }
        else {
            DefaultTableModel model = (DefaultTableModel) resultsTable.getModel();
            model.setRowCount(0);
            populateTable();
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void resultsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultsTableMouseClicked
        int i = resultsTable.getSelectedRow();
        TableModel model = resultsTable.getModel();
        
        artistTextField.setText(model.getValueAt(i, 0).toString());
        songTextField.setText(model.getValueAt(i, 1).toString());
        albumTextField.setText(model.getValueAt(i, 2).toString());
                
    }//GEN-LAST:event_resultsTableMouseClicked

    private void Genre_ComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Genre_ComboBoxActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_Genre_ComboBoxActionPerformed

    private void Country_ComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Country_ComboBoxActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_Country_ComboBoxActionPerformed

    
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
    private javax.swing.JComboBox<String> Country_ComboBox;
    private javax.swing.JComboBox<String> Genre_ComboBox;
    private javax.swing.JButton addButton;
    private javax.swing.JLabel albumLabel;
    private javax.swing.JTextField albumTextField;
    private javax.swing.JLabel artistLabel;
    private javax.swing.JTextField artistTextField;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable resultsTable;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel songLabel;
    private javax.swing.JTextField songTextField;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}