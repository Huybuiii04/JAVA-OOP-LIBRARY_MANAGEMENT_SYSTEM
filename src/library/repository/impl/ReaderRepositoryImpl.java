package library.repository.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import library.entitis.Reader;
import library.repository.AbsBaseRepository;
import library.repository.GenericRepository;

public class ReaderRepositoryImpl extends AbsBaseRepository implements GenericRepository<Reader> {

	public ReaderRepositoryImpl() {

	}

	@Override
	public List<Reader> get(String s) {
		ArrayList<Reader> list = new ArrayList<>();
		String sql = "SELECT * FROM Reader " + s;
		try {
			ResultSet rs = this.conn.prepareStatement(sql).executeQuery();
			while (rs.next()) {
				Reader st = new Reader();
				st.setID(rs.getString("Id"));
				st.setFullName(rs.getString("FullName"));
				st.setGender(rs.getString("Gender"));
				st.setDob(rs.getDate("Dob"));
				st.setAddress(rs.getString("Address"));
				st.setLicense(rs.getString("License"));
				st.setPhoneNumber(rs.getString("PhoneNumber"));
				st.setGmail(rs.getString("Gmail"));
				st.setKindOfReader(rs.getString("KindOfReader"));
				st.setStartDate(rs.getDate("StartDate"));
				st.setEndDate(rs.getDate("EndDate"));
				list.add(st);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean add(Reader r) {
		String sql = "INSERT INTO Reader(Id,FullName,Gender,Dob,Address,License,PhoneNumber,Gmail,KindOfReader,StartDate,EndDate) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = this.conn.prepareStatement(sql);
			ps.setString(1, r.getID());
			ps.setString(2, r.getFullName());
			ps.setString(3, r.getGender());
			ps.setDate(4, r.getDob());
			ps.setString(5, r.getAddress());
			ps.setString(6, r.getLicense());
			ps.setString(7, r.getPhoneNumber());
			ps.setString(8, r.getGmail());
			ps.setString(9, r.getKindOfReader());
			ps.setDate(10, r.getStartDate());
			ps.setDate(11, r.getEndDate());
			return (ps.executeUpdate() > 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Reader r) {
		try {
			String update = "UPDATE Reader SET FullName = N'" + r.getFullName() + "', Gender = N'" + r.getGender()
					+ "', Dob = '" + r.getDob() + "',Address = N'" + r.getAddress() + "',License = '" + r.getLicense()
					+ "', PhoneNumber = '" + r.getPhoneNumber() + "',Gmail = '" + r.getGmail() + "', KindOfReader = N'"
					+ r.getKindOfReader() + "', StartDate = '" + r.getStartDate() + "', EndDate = '" + r.getEndDate()
					+ "' WHERE Id = '" + r.getID() + "'";
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
			String delete = "DELETE FROM Reader " + s;
			Statement statement = this.conn.createStatement();
			return statement.executeUpdate(delete) > 0;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}

}
