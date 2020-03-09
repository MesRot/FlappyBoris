package sample;
import java.sql.*;
import java.util.ArrayList;

class Pisteet {
    Connection db;
    public Pisteet() throws SQLException {
        this.db = DriverManager.getConnection("jdbc:sqlite:pisteet.db");
        this.luoTaulu();
    }

    /**Luo uuden tietokannan ja lisää sinne kolme 0 tulosta
     * @throws SQLException
     */
    public void luoTaulu() throws SQLException {
        try{
            Statement s = db.createStatement();
            s.execute("Create TABLE Pisteet (id INTEGER PRIMARY KEY, pisteet TXT)");
            for(int i = 0; i < 4; i++){
                s.execute("INSERT INTO Pisteet (pisteet) VALUES (0)");
            }

        }
        catch (Exception e){
            System.out.println("Pistetaulukko jo valmiina");
        }
    }

    /**Tallentaa pisteet tietokantaan
     * @param pisteet
     * @throws SQLException
     */
    public void tallenna(int pisteet) throws SQLException {
        PreparedStatement p = db.prepareStatement("INSERT INTO Pisteet (pisteet) VALUES (?)");
        p.setString(1, Integer.toString(pisteet));
        p.execute();
    }

    /**Hakee parhaimmat kolme tulosta tietokannasta
     * @return ArrayList<Integer></Integer>
     * @throws SQLException
     */
    public ArrayList<Integer> getTop3() throws SQLException {
        ArrayList<Integer> palautettava = new ArrayList<>();
        Statement s = db.createStatement();
        ResultSet r = s.executeQuery("SELECT pisteet FROM Pisteet ORDER BY pisteet DESC LIMIT 3"); //TODO: toiseen suuntaan order
        while(r.next()){
            palautettava.add(r.getInt("pisteet"));
        }
        return palautettava;
    }
}
