package me.api.data;

import ru.fakeduck_king.messages.*;
import org.bukkit.*;
import java.sql.*;

public class DatabaseManager {
	private static final DatabaseManager databaseManager = new DatabaseManager();
	private Connection connection;
	
	private DatabaseManager() {}
	
	public static DatabaseManager getDatabaseManager() {
		return DatabaseManager.databaseManager;
	}
	
	public synchronized  Connection getConnection() throws SQLException {
		if (this.connection == null) {
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost/fluxmber?useSSL=false", "root", "");
		}
		return this.connection;
	}
	
	public void setup() {
		try (
			Statement statement = this.getConnection().createStatement();
		)
		{
			statement.execute("CREATE TABLE IF NOT EXISTS playerAPI (UUID varchar(100) primary key, RUB int, lastJoin varchar(100), firstJoin varchar(100), messageStaff boolean, messageDonators boolean)");
            Bukkit.getServer().getConsoleSender().sendMessage(Prefix.SUCCESSFULLY + "DATABASE START");
		}
		catch (SQLException e) {
			Bukkit.getServer().getConsoleSender().sendMessage(Prefix.ERROR + "FAILED TO CONNECT DATABASE");
		}
	}
	
	public void create(PlayerAPI playerAPI) {
		try (
			PreparedStatement preparedStatement = this.getConnection().prepareStatement("INSERT INTO playerAPI(UUID, RUB, lastJoin, firstJoin, messageStaff, messageDonators) VALUES (?, ?, ?, ?, ?, ?)");
		)
		{
			preparedStatement.setString(1, playerAPI.getName());
			preparedStatement.setInt(2, playerAPI.getRUB());
			preparedStatement.setString(3, playerAPI.getLastJoin());
			preparedStatement.setString(4, playerAPI.getFirstJoin());
			preparedStatement.setBoolean(5, playerAPI.getMessageStaff());
			preparedStatement.setBoolean(6, playerAPI.getMessageDonators());
			preparedStatement.executeUpdate();
		}
		catch (SQLException ignore) {}
	}
	
	public PlayerAPI load(String name) {
		PlayerAPI playerAPI = null;
		try (
			PreparedStatement preparedStatement = this.getConnection().prepareStatement("SELECT * FROM playerAPI WHERE UUID = ?");
		)
		{
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				playerAPI = new PlayerAPI(resultSet.getString("UUID"), resultSet.getInt("RUB"), resultSet.getString("lastJoin"), resultSet.getString("firstJoin"), resultSet.getBoolean("messageStaff"), resultSet.getBoolean("messageDonators"));
			}
		}
		catch (SQLException ignore) {}
		return playerAPI;
	}
	
	public void save(PlayerAPI playerAPI) {
		try (
			PreparedStatement preparedStatement = this.getConnection().prepareStatement("UPDATE playerAPI SET RUB = ?, lastJoin = ?, firstJoin = ?, messageStaff = ?, messageDonators = ? WHERE UUID = ?");
		)
		{
			preparedStatement.setInt(1, playerAPI.getRUB());
			preparedStatement.setString(2, playerAPI.getLastJoin());
			preparedStatement.setString(3, playerAPI.getFirstJoin());
			preparedStatement.setBoolean(4, playerAPI.getMessageStaff());
			preparedStatement.setBoolean(5, playerAPI.getMessageDonators());
			preparedStatement.setString(6, playerAPI.getName());
			preparedStatement.executeUpdate();
		}
		catch (SQLException ignore) {}
	}
	
	public void closeConnection() {
		if (this.connection != null) {
			try {
				this.connection.close();
			}
			catch (SQLException ignore) {}
		}
	}
}