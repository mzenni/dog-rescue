package projects;

import java.sql.Connection;

import projects.dao.DbConnection;

@SuppressWarnings("unused")
public class ProjectsApp {

	public static void main(String[] args) {
		DbConnection.getConnection(); 

	}

}
