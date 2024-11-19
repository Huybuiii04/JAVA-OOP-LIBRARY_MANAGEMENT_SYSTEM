package library.repository.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import library.entitis.RentalInformation;
import library.repository.AbsBaseRepository;
import library.repository.GenericRepository;

public class RentalInforRepositoryImpl extends AbsBaseRepository implements GenericRepository<RentalInformation> {

	public RentalInforRepositoryImpl() {
	}

	@Override
	public List<RentalInformation> get(String s) {
		ArrayList<RentalInformation> list = new ArrayList<>();
		String sql = "SELECT * FROM RentalInformation " + s;
		try {
			ResultSet rs = this.conn.prepareStatement(sql).executeQuery();
			while (rs.next()) {
				RentalInformation ri = new RentalInformation();
				ri.setID(rs.getString("Id"));
				ri.setReaderID(rs.getString("ReaderId"));
				ri.setBookID(rs.getString("BookId"));
				ri.setLibStaffID(rs.getString("LibStaffId"));
				ri.setBookBorrowDate(rs.getDate("BookBorrowDate"));
				ri.setBookReturnDate(rs.getDate("BookReturnDate"));
				ri.setNote(rs.getString("Note"));
				list.add(ri);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean add(RentalInformation ri) {
		String sql = "INSERT INTO RentalInformation (Id,ReaderId,BookId,LibStaffId,BookBorrowDate,BookReturnDate,Note) VALUES(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = this.conn.prepareStatement(sql);
			ps.setString(1, ri.getID());
			ps.setString(2, ri.getReaderID());
			ps.setString(3, ri.getBookID());
			ps.setString(4, ri.getLibStaffID());
			ps.setDate(5, ri.getBookBorrowDate());
			ps.setDate(6, ri.getBookReturnDate());
			ps.setString(7, ri.getNote());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(RentalInformation ri) {
		try {
			String update = "UPDATE RentalInformation SET ReaderId = '" + ri.getReaderID() + "', BookId = '"
					+ ri.getBookID() + "', LibStaffId = '" + ri.getLibStaffID() + "', BookBorrowDate = '"
					+ ri.getBookBorrowDate() + "', BookReturnDate = '" + ri.getBookReturnDate() + "', Note = N'"
					+ ri.getNote() + "' WHERE Id = '" + ri.getID() + "'";
			Statement statement = this.conn.createStatement();
			return statement.executeUpdate(update) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(String s) {
		try {
			String delete = "DELETE FROM RentalInformation " + s;
			Statement statement = this.conn.createStatement();
			return statement.executeUpdate(delete) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
