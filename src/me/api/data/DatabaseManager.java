package me.api.data;

import ru.fakeduck_king.messages.*;
import org.bukkit.*;
import java.util.*;
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
		try
		(
			Statement statement = this.getConnection().createStatement();
		)
		{
			statement.execute("CREATE TABLE IF NOT EXISTS playerAPI (name varchar(100) primary key, RUB int, lastJoin varchar(100), firstJoin varchar(100), messageStaff boolean, messageDonators boolean, "
			+ "friends text, "
			+ "ignoreAll boolean, reply varchar(16), ignoreList text)");
			Bukkit.getServer().getConsoleSender().sendMessage(Prefix.SUCCESSFULLY + "DATABASE START");
		}
		catch (SQLException e) {
			Bukkit.getServer().getConsoleSender().sendMessage(Prefix.ERROR + "FAILED TO CONNECT DATABASE");
		}
	}
	
	public void create(PlayerAPI playerAPI) {
		try
		(
			PreparedStatement preparedStatement = this.getConnection().prepareStatement("INSERT INTO playerAPI(name, RUB, lastJoin, firstJoin, messageStaff, messageDonators, "
			+ "friends, "
			+ "ignoreAll, reply, ignoreList) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		)
		{
			preparedStatement.setString(1, playerAPI.getName());
			preparedStatement.setInt(2, playerAPI.getRUB());
			preparedStatement.setString(3, playerAPI.getLastJoin());
			preparedStatement.setString(4, playerAPI.getFirstJoin());
			preparedStatement.setBoolean(5, playerAPI.getMessageStaff());
			preparedStatement.setBoolean(6, playerAPI.getMessageDonators());
			
			preparedStatement.setString(7, playerAPI.getFriends().isEmpty() ? null : String.join(",", playerAPI.getFriends()));
			
			preparedStatement.setBoolean(8, playerAPI.isIgnoreAll());
			preparedStatement.setString(9, playerAPI.getReply());
			preparedStatement.setString(10, playerAPI.getIgnoreList().isEmpty() ? null : String.join(",", playerAPI.getIgnoreList()));
			
			preparedStatement.executeUpdate();
		}
		catch (SQLException ignore) {}
	}
	
	public PlayerAPI load(String name) {
		PlayerAPI playerAPI = null;
		try
		(
			PreparedStatement preparedStatement = this.getConnection().prepareStatement("SELECT * FROM playerAPI WHERE name = ?");
		)
		{
			preparedStatement.setString(1, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				String resultSetFriends = resultSet.getString("friends");
				String resultSetIgnoreList = resultSet.getString("ignoreList");
				List<String> friends = resultSetFriends != null && !resultSetFriends.isEmpty() ? new ArrayList<>(Arrays.asList(resultSetFriends.split(","))) : new ArrayList<>();
				List<String> ignoreList = resultSetIgnoreList != null && !resultSetIgnoreList.isEmpty() ? new ArrayList<>(Arrays.asList(resultSetIgnoreList.split(","))) : new ArrayList<>();
				
				playerAPI = new PlayerAPI(
					resultSet.getString("name"),
					resultSet.getInt("RUB"),
					resultSet.getString("lastJoin"),
					resultSet.getString("firstJoin"),
					resultSet.getBoolean("messageStaff"),
					resultSet.getBoolean("messageDonators"),
					
					friends,
					
					resultSet.getBoolean("ignoreAll"),
					resultSet.getString("reply"),
					ignoreList
				);
			}
		}
		catch (SQLException ignore) {}
		return playerAPI;
	}
	
	public void save(PlayerAPI playerAPI) {
		try
		(
			PreparedStatement preparedStatement = this.getConnection().prepareStatement("UPDATE playerAPI SET RUB = ?, lastJoin = ?, firstJoin = ?, messageStaff = ?, messageDonators = ?, "
			+ "friends = ?, "
			+ "ignoreAll = ?, reply = ?, ignoreList = ? "
			+ "WHERE name = ?")
		)
		{
			preparedStatement.setInt(1, playerAPI.getRUB());
			preparedStatement.setString(2, playerAPI.getLastJoin());
			preparedStatement.setString(3, playerAPI.getFirstJoin());
			preparedStatement.setBoolean(4, playerAPI.getMessageStaff());
			preparedStatement.setBoolean(5, playerAPI.getMessageDonators());
			
			preparedStatement.setString(6, playerAPI.getFriends().isEmpty() ? null : String.join(",", playerAPI.getFriends()));
			
			preparedStatement.setBoolean(7, playerAPI.isIgnoreAll());
			preparedStatement.setString(8, playerAPI.getReply());
			preparedStatement.setString(9, playerAPI.getIgnoreList().isEmpty() ? null : String.join(",", playerAPI.getIgnoreList()));
			
			preparedStatement.setString(10, playerAPI.getName());
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