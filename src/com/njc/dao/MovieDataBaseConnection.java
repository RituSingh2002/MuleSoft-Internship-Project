package com.njc.dao;

import java.util.ArrayList;
import java.util.Scanner;

import com.njc.model.Movie;

import java.sql.*;

public class MovieDataBaseConnection {

	public static void insertRecord(Movie movie, Connection con) {
		try {
			String movieName = movie.getName();
			String actorName = movie.getActorName();
			String actressName = movie.getActressName();
			String directorName = movie.getDirectorName();
			long yor = movie.getYor();
			PreparedStatement prepareStatement = con.prepareStatement(
					"insert into Movie(name,actorName,actressName,directorName,year) values(?,?,?,?,?)");
			prepareStatement.setString(1, movieName);
			prepareStatement.setString(2, actorName);
			prepareStatement.setString(3, actressName);
			prepareStatement.setString(4, directorName);
			prepareStatement.setLong(5, yor);
			prepareStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Some Erorr" + e.getMessage());
		}

	}

	public static void getRecord(Connection con) {
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select * from Movie");
			ArrayList<Movie> list = new ArrayList<>();
			while (rs.next()) {
				Movie movie = new Movie();
				movie.setName(rs.getString(1));
				movie.setActorName(rs.getString(2));
				movie.setActressName(rs.getString(3));
				movie.setDirectorName(rs.getString(4));
				movie.setYor(rs.getLong(5));
				list.add(movie);
			}
			if (list.size() == 0) {
				System.out.println("No Record Found ");
			} else {
				for (Movie movie : list) {
					System.out.println("****************************************");
					System.out.print(" |  Movie Name -> " + movie.getName());
					System.out.print(" |  Movie actor Name -> " + movie.getActorName());
					System.out.print(" |  Movie actress Name -> " + movie.getActressName());
					System.out.print(" |  Movie Director Name --> " + movie.getDirectorName());
					System.out.print(" |  Movie Release Year -->  " + movie.getYor());
					System.out.println("******************************************");
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Connection con;
		try {
			String url = "jdbc:sqlite:C:/users/ritus/Desktop/mdb.db";
			con = DriverManager.getConnection(url);
			String sql = "CREATE TABLE IF NOT EXISTS Movie (\n" + " name text PRIMARY KEY,\n"
					+ " actorName text NOT NULL,\n" + " actressName text NOT NULL,\n" + " directorName text NOT NULL,\n"
					+ " year Integer NOT NULL\n" + ");";
			Statement stmt = con.createStatement();
			stmt.execute(sql);
			Scanner sc = new Scanner(System.in);
			while (true) {
				System.out.println("Please Enter valid Option ");
				System.out.println("1. Insert the Record ");
				System.out.println("2. Display the Record ");
				System.out.println("Press Any key For Exit ");
				int option = sc.nextInt();
				if (option == 1) {
					Movie movie = new Movie();
					sc.nextLine();
					System.out.println("Please Enter the Movie name ");
					String movieName = sc.nextLine();
					System.out.println("Please Enter the actor name ");
					String actorName = sc.nextLine();
					System.out.println("Please Enter the actress name ");
					String actressName = sc.nextLine();
					System.out.println("Please Enter the director name ");
					String directorName = sc.nextLine();
					System.out.println("Please Enter the year of release ");
					long yor = sc.nextLong();
					movie.setName(movieName);
					movie.setActorName(actorName);
					movie.setActressName(actressName);
					movie.setDirectorName(directorName);
					movie.setYor(yor);
					insertRecord(movie, con);
				} else if (option == 2) {
					getRecord(con);
				} else {
					System.out.println("Thank You");
					break;
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
