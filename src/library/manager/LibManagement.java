package library.manager;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import library.entitis.Account;
import library.entitis.Book;
import library.entitis.LibStaff;
import library.entitis.Reader;
import library.entitis.RentalInformation;
import library.repository.GenericRepository;
import library.repository.impl.AccountRepositoryImpl;
import library.repository.impl.BookRepositoryImpl;
import library.repository.impl.LibStaffRepositoryImpl;
import library.repository.impl.ReaderRepositoryImpl;
import library.repository.impl.RentalInforRepositoryImpl;

public class LibManagement {
	private GenericRepository<Reader> lstReaders;
	private GenericRepository<LibStaff> lstLibStaffs;
	private GenericRepository<Book> lstBooks;
	private GenericRepository<RentalInformation> lstRentalInfor;
	private GenericRepository<Account> lstAccounts;

	public LibManagement() {
		lstReaders = new ReaderRepositoryImpl();
		lstLibStaffs = new LibStaffRepositoryImpl();
		lstBooks = new BookRepositoryImpl();
		lstRentalInfor = new RentalInforRepositoryImpl();
		lstAccounts = new AccountRepositoryImpl();
	}

	public LibManagement(GenericRepository<Reader> lstReaders, GenericRepository<LibStaff> lstLibStaffs,
			GenericRepository<Book> lstBooks, GenericRepository<RentalInformation> lstRentalInfor,
			GenericRepository<Account> lstAccounts) {
		super();
		this.lstReaders = lstReaders;
		this.lstLibStaffs = lstLibStaffs;
		this.lstBooks = lstBooks;
		this.lstRentalInfor = lstRentalInfor;
		this.lstAccounts = lstAccounts;
	}

	// Thêm
	public boolean addReader(Reader rd) {
		return this.lstReaders.add(rd);
	}

	public boolean addLibStaff(LibStaff ls) {
		return this.lstLibStaffs.add(ls);
	}

	public boolean addBook(Book b) {
		return this.lstBooks.add(b);
	}

	public boolean addRentalInfor(RentalInformation ri) {
		return this.lstRentalInfor.add(ri);
	}

	public boolean addAccount(Account a) {
		return this.lstAccounts.add(a);
	}

	// Sửa
	public boolean updateReader(Reader r) {
		return this.lstReaders.update(r);
	}

	public boolean updateLibStaff(LibStaff ls) {
		return this.lstLibStaffs.update(ls);
	}

	public boolean updateBook(Book b) {
		return this.lstBooks.update(b);
	}

	public boolean updateRentalInfor(RentalInformation ri) {
		return this.lstRentalInfor.update(ri);
	}

	public boolean updateAccount(Account a) {
		return this.lstAccounts.update(a);
	}

	// Xóa theo ID
	public boolean deleteReaderByID(String id) {
		return this.lstReaders.delete("WHERE ID = '" + id + "'");
	}

	public boolean deleteLibStaffByID(String id) {
		return this.lstLibStaffs.delete("WHERE ID = '" + id + "'");
	}

	public boolean deleteBookByID(String id) {
		return this.lstBooks.delete("WHERE ID = '" + id + "'");
	}

	public boolean deleteRentalInforByID(String id) {
		return this.lstRentalInfor.delete("WHERE ID = '" + id + "'");
	}

	public boolean deleteAccountByUserName(String userName) {
		return this.lstAccounts.delete("WHERE UserName = '" + userName + "'");
	}

	// Clear
	public void clearBook() {
		lstBooks.delete("");
	}

	public void clearReader() {
		lstReaders.delete("");
	}

	public void clearLibStaff() {
		lstLibStaffs.delete("");
	}

	public void clearRentalInfor() {
		lstRentalInfor.delete("");
	}

	public boolean clearAccount(String lv) {
		if (lv.equals(""))
			return this.lstAccounts.delete("");
		return this.lstAccounts.delete("WHERE UserName LIKE '" + lv + "%'");
	}

	// Show all
	public List<Reader> showAllReader() {
		return this.lstReaders.get("");
	}

	public List<LibStaff> showAllLibStaff() {
		return this.lstLibStaffs.get("");
	}

	public List<Book> showAllBook() {
		return this.lstBooks.get("");
	}

	public List<RentalInformation> showAllRentalInfor() {
		return this.lstRentalInfor.get("");
	}

	public List<Account> showAllAccount(String lv) {
		if (lv.equals(""))
			return this.lstAccounts.get("");
		return this.lstAccounts.get("WHERE UserName LIKE '" + lv + "%'");
	}

	// Show by id
	public Reader showReaderByID(String id) {
		List<Reader> list = this.lstReaders.get("WHERE ID = '" + id + "'");
		return list.size() == 0 ? null : list.get(0);
	}

	public LibStaff showLibStaffByID(String id) {
		List<LibStaff> list = this.lstLibStaffs.get("WHERE ID = '" + id + "'");
		return list.size() == 0 ? null : list.get(0);
	}

	public Book showBookByID(String id) {
		List<Book> list = this.lstBooks.get("WHERE ID = '" + id + "'");
		return list.size() == 0 ? null : list.get(0);
	}

	public RentalInformation showRentalInforByID(String id) {
		List<RentalInformation> list = this.lstRentalInfor.get("WHERE ID = '" + id + "'");
		return list.size() == 0 ? null : list.get(0);
	}

	public String getAccountByUserName(String userName) {
		List<Account> list = this.lstAccounts.get("WHERE UserName = '" + userName + "'");
		return list.size() == 0 ? null : list.get(0).getPassword();
	}

	// Show By name
	public List<Reader> showReaderByName(String readerName) {
		return this.lstReaders.get("WHERE FullName = N'" + readerName + "'");
	}

	public List<LibStaff> showLibStaffByName(String libStaffName) {
		return this.lstLibStaffs.get("WHERE FullName = N'" + libStaffName + "'");
	}

	public List<Book> showBookByName(String bookName) {
		return this.lstBooks.get("WHERE FullName = N'" + bookName + "'");
	}

	// Show rental infor by book id, reader id, lib staff id
	public List<RentalInformation> showRentalInforByReaderID(String readerId) {
		return this.lstRentalInfor.get("WHERE ReaderId = '" + readerId + "'");
	}

	public List<RentalInformation> showRentalInforByBookID(String bookId) {
		return this.lstRentalInfor.get("WHERE BookId = '" + bookId + "'");
	}

	public List<RentalInformation> showRentalInforByLibStaffID(String libStaffId) {
		return this.lstRentalInfor.get("WHERE LibStaffId = '" + libStaffId + "'");
	}

	// Show overdue
	public List<Reader> showReaderOverdue() {
		return this.lstReaders.get("WHERE EndDate < GETDATE()");
	}

	public List<RentalInformation> showRentalInforOverdue() {
		return this.lstRentalInfor.get(
				"WHERE BookReturnDate < GETDATE() OR ReaderId in( SELECT Id	from Reader where endDate<GETDATE())");
	}

	// Hiển thị thông tin liên quan đến sách chưa trả
	public String[][] showInforOverdue() {
		List<String[]> lst = new ArrayList<>();
		String sql = "SELECT * FROM view_get_infor_borrow";
		try {
			ResultSet rs = this.lstRentalInfor.getConn().prepareStatement(sql).executeQuery();
			while (rs.next())
				lst.add(new String[] { rs.getString("ID"), rs.getString("ReaderID"), rs.getString("ReaderFullName"),
						rs.getString("ReaderLicense"), rs.getString("ReaderPhoneNumber"), rs.getString("ReaderGmail"),
						rs.getString("BookID"), rs.getString("BookName"), rs.getString("BookAuthor"),
						rs.getString("LibID"), rs.getString("LibFullName"), rs.getString("LibLicense"),
						rs.getString("LibPhoneNumber"), rs.getString("LibGmail") });
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		String[][] infors = new String[lst.size()][14];
		for (int i = 0; i < lst.size(); i++)
			for (int j = 0; j < 14; j++)
				infors[i][j] = lst.get(i)[j];
		return infors;
	}

	// DROP database//Làm mới toàn bộ hệ thống
	public void dropDataBase() {
		this.lstRentalInfor.delete("");
		this.lstReaders.delete("");
		this.lstLibStaffs.delete("");
		this.lstBooks.delete("");
		this.lstAccounts.delete("");
	}
}
