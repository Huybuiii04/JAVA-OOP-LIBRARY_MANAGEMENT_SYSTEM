package library.repository.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import library.entitis.Book;
import library.repository.AbsBaseRepository;
import library.repository.GenericRepository;

public class BookRepositoryImpl extends AbsBaseRepository implements GenericRepository<Book> {

	public BookRepositoryImpl() {

	}

	@Override
	public List<Book> get(String s) {
		List<Book> list = new ArrayList<>();
		String sql = "SELECT * FROM Book " + s;
		try {
			ResultSet rs = this.conn.prepareStatement(sql).executeQuery();
			while (rs.next()) {
				Book st = new Book();
				st.setID(rs.getString("Id"));
				st.setName(rs.getString("Name"));
				st.setAuthor(rs.getString("Author"));
				st.setQuantity(rs.getInt("Quantity"));
				st.setKindOfBook(rs.getString("KindOfBook"));
				list.add(st);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean add(Book b) {
		String sql = "INSERT INTO Book (Id,Name,Author,Quantity,KindOfBook) VALUES(?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, b.getID());
			ps.setString(2, b.getName());
			ps.setString(3, b.getAuthor());
			ps.setInt(4, b.getQuantity());
			ps.setString(5, b.getKindOfBook());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Book b) {
		try {
			String update = "UPDATE Book SET Name = N'" + b.getName() + "', Author = N'" + b.getAuthor()
					+ "', Quantity = '" + b.getQuantity() + "', KindOfBook = N'" + b.getKindOfBook() + "' WHERE Id = '"
					+ b.getID() + "'";
			Statement statement = this.conn.createStatement();
			return statement.executeUpdate(update) > 0;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(String s) {
		try {
			String sql = "DELETE FROM Book " + s;
			Statement statement = conn.createStatement();
			return statement.executeUpdate(sql) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
