package library.repository.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import library.entitis.LibStaff;
import library.repository.AbsBaseRepository;
import library.repository.GenericRepository;

public class LibStaffRepositoryImpl extends AbsBaseRepository implements GenericRepository<LibStaff> {

	public LibStaffRepositoryImpl() {
	}

	@Override
	public List<LibStaff> get(String s) {
		ArrayList<LibStaff> list = new ArrayList<>();
		String sql = "SELECT * FROM LibStaff " + s;
		try {
			ResultSet rs = this.conn.prepareStatement(sql).executeQuery();
			while (rs.next()) {
				LibStaff st = new LibStaff();
				st.setID(rs.getString("Id"));
				st.setFullName(rs.getString("FullName"));
				st.setGender(rs.getString("Gender"));
				st.setDob(rs.getDate("Dob"));
				st.setAddress(rs.getString("Address"));
				st.setLicense(rs.getString("License"));
				st.setPhoneNumber(rs.getString("PhoneNumber"));
				st.setGmail(rs.getString("Gmail"));
				st.setStartWorkDate(rs.getDate("StartWorkDate"));
				st.setPosition(rs.getString("Position"));
				st.setBasicSalary(rs.getInt("BasicSalary"));
				st.setSalaryBonus(rs.getInt("SalaryBonus"));
				st.setPenalty(rs.getInt("Penalty"));
				st.setActualSalary();
				list.add(st);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean add(LibStaff ls) {
		String sql = "INSERT INTO LibStaff (Id,FullName,Gender,Dob,Address,License,PhoneNumber,Gmail, StartWorkDate,Position,BasicSalary,SalaryBonus,Penalty,ActualSalary) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = this.conn.prepareStatement(sql);
			ps.setString(1, ls.getID());
			ps.setString(2, ls.getFullName());
			ps.setString(3, ls.getGender());
			ps.setDate(4, ls.getDob());
			ps.setString(5, ls.getAddress());
			ps.setString(6, ls.getLicense());
			ps.setString(7, ls.getPhoneNumber());
			ps.setString(8, ls.getGmail());
			ps.setDate(9, ls.getStartWorkDate());
			ps.setString(10, ls.getPosition());
			ps.setInt(11, ls.getBasicSalary());
			ps.setInt(12, ls.getSalaryBonus());
			ps.setInt(13, ls.getPenalty());
			ps.setInt(14, ls.getActualSalary());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(LibStaff e) {
		try {
			String update = "UPDATE LibStaff SET FullName = N'" + e.getFullName() + "', Gender = N'" + e.getGender()
					+ "', Dob = '" + e.getDob() + "',Address = N'" + e.getAddress() + "',License = '" + e.getLicense()
					+ "', PhoneNumber = '" + e.getPhoneNumber() + "',Gmail = '" + e.getGmail() + "',StartWorkDate = '"
					+ e.getStartWorkDate() + "', Position = N'" + e.getPosition() + "',BasicSalary = '"
					+ e.getBasicSalary() + "', SalaryBonus = '" + e.getSalaryBonus() + "', Penalty = '" + e.getPenalty()
					+ "' WHERE Id = '" + e.getID() + "'";
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
			String sql = "DELETE FROM LibStaff " + s;
			Statement statement = this.conn.createStatement();
			return statement.executeUpdate(sql) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
