/*
    
Object Oriented Analysis & Design

Assignment #1
Section: CS-A

1.  Name: Mohsin Hayat   |   Roll Number: L16-4333
2.  Name: Aanish Amir    |   Roll Number: L16-4144

*/


package lms;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class dbConnectivity {

    Connection con;
    Statement stmt;

    dbConnectivity() {
        try {
            String s = "jdbc:sqlserver://localhost:1433;databaseName=lms";
            con = DriverManager.getConnection(s, "mohsin", "mohsinhayat007");
            stmt = con.createStatement();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    void loadUsers() {
        try {
            ResultSet rs = stmt.executeQuery("select * from librarian");
            LMS.Users.clear();
            while (rs.next()) {
                LMS.Users.add(new Librarian(rs.getString(5).trim(), rs.getString(6).trim(), rs.getString(2).trim(), rs.getString(3).trim(), rs.getInt(4)));
            }

            rs = stmt.executeQuery("select * from Clerk");
            while (rs.next()) {
                LMS.Users.add(new Clerk(rs.getString(4).trim(), rs.getString(5).trim(), rs.getString(2).trim(), rs.getString(6).trim(), rs.getInt(3)));
            }

            rs = stmt.executeQuery("select * from Student");
            while (rs.next()) {
                LMS.Users.add(new Borrower(rs.getString(6).trim(), rs.getString(4).trim(), rs.getString(5).trim(), rs.getString(7).trim(), rs.getString(8).trim(), rs.getString(2).trim(), rs.getString(9).trim(), rs.getInt(3)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void loadBooks() {
        try {
            ResultSet rs = stmt.executeQuery("select * from book");
            LMS.Books.clear();
            while (rs.next()) {
                LMS.Books.add(new Book(rs.getString(2).trim(), rs.getString(3).trim(), rs.getString(4).trim(), rs.getInt(5)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void insertBook(String Name, String Author, String ISBN, int quantity) {
        try {
            PreparedStatement addedBook = con.prepareStatement("INSERT INTO Book values(?, ?, ?, ?, ?)");
            addedBook.setInt(1, LMS.Books.size());
            addedBook.setString(2, Name);
            addedBook.setString(3, Author);
            addedBook.setString(4, ISBN);
            addedBook.setInt(5, quantity);
            addedBook.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void loadPendingReservations() {
        try {
            ResultSet rs = stmt.executeQuery("select * from reservationDate");
            while (rs.next()) {
                String username = rs.getString(2).trim();
                String isbn = rs.getString(1).trim();
                String date = rs.getString(3).trim();
                String status = rs.getString(4).trim();
                if (status.equals("pending")) {
                    LMS.getBook(isbn).reserveBook(LMS.getUser(username));
                }
                SimpleDateFormat format = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy");
                LMS.getUser(username).reserveBook(LMS.getBook(isbn), format.parse(date), status);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void reservationDate(String username, String isbn, String status, Date d) {
        try {
            PreparedStatement addedBook = con.prepareStatement("INSERT INTO reservationDate values(?, ?, ?, ?)");
            addedBook.setString(1, isbn);
            addedBook.setString(2, username);
            addedBook.setString(3, d.toString());
            addedBook.setString(4, "pending");
            addedBook.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void updateBorrower(String rollNo, String dept, String campus, String username, String password, String Name, String Gender, int Age) {
        try {
            PreparedStatement updatedProfile = con.prepareStatement("UPDATE student SET "
                    + "	name = ?,"
                    + "	age = ?,"
                    + "	department = ?,"
                    + "	campus = ?,"
                    + "	rollno = ?,"
                    + "	password = ?,"
                    + "	gender = ? "
                    + " WHERE username = ?");
            updatedProfile.setString(1, Name);
            updatedProfile.setInt(2, Age);
            updatedProfile.setString(3, dept);
            updatedProfile.setString(4, campus);
            updatedProfile.setString(5, rollNo);
            updatedProfile.setString(6, password);
            updatedProfile.setString(7, Gender);
            updatedProfile.setString(8, username);
            updatedProfile.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void updateClerk(String username, String password, String Name, String Gender, int Age) {
        try {
            PreparedStatement updatedProfile = con.prepareStatement("UPDATE Clerk SET "
                    + "	name = ?,"
                    + "	age = ?,"
                    + "	password = ?,"
                    + "	gender = ? "
                    + " WHERE username = ?");

            updatedProfile.setString(1, Name);
            updatedProfile.setInt(2, Age);
            updatedProfile.setString(3, password);
            updatedProfile.setString(4, Gender);
            updatedProfile.setString(5, username);
            updatedProfile.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void updateLibrarian(String username, String password, String Name, String Gender, int Age) {
        try {
            PreparedStatement updatedProfile = con.prepareStatement("UPDATE Librarian SET "
                    + "	name = ?,"
                    + "	age = ?,"
                    + "	password = ?,"
                    + "	gender = ? "
                    + " WHERE username = ?");

            updatedProfile.setString(1, Name);
            updatedProfile.setInt(2, Age);
            updatedProfile.setString(3, password);
            updatedProfile.setString(4, Gender);
            updatedProfile.setString(5, username);
            updatedProfile.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void changeResStatus(String Status, String isbn, String username) {
        try {
            PreparedStatement updatedRS = con.prepareStatement("UPDATE reservationDate SET "
                    + "	status = ? "
                    + " WHERE username = ? and isbn = ?");

            updatedRS.setString(1, Status);
            updatedRS.setString(2, username);
            updatedRS.setString(3, isbn);
            updatedRS.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addStudent(String username, String password, String Name, String Gender, int Age, String Rollnum, String Dept, String Campus) {
        try {
            PreparedStatement borroweradded = con.prepareStatement("INSERT INTO student values( ?, ?, ?, ?, ?, ?, ?, ?)");
            borroweradded.setString(1, Name);
            borroweradded.setInt(2, Age);
            borroweradded.setString(3, Dept);
            borroweradded.setString(4, Campus);
            borroweradded.setString(5, Rollnum);
            borroweradded.setString(6, username);
            borroweradded.setString(7, password);
            borroweradded.setString(8, Gender);
            borroweradded.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addClerk(String username, String password, String Name, String Gender, int Age) {
        try {
            PreparedStatement borroweradded = con.prepareStatement("INSERT INTO clerk (Name, Age, username, password, gender) values(?, ?, ?, ?, ?)");
            borroweradded.setString(1, Name);
            borroweradded.setInt(2, Age);
            borroweradded.setString(3, username);
            borroweradded.setString(4, password);
            borroweradded.setString(5, Gender);
            borroweradded.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertBookRecord(String isbn, String issuedTo, String issuedBy, String issueDate, String dueDate, String returnDate) {
        try {
            PreparedStatement addedRecord = con.prepareStatement("INSERT INTO BookRecord (isbn, issuedTo, issuedBy, date, dueDate, returnDate) values (?,?,?,?,?,?)");
            addedRecord.setString(1, isbn);
            addedRecord.setString(2, issuedTo);
            addedRecord.setString(3, issuedBy);
            addedRecord.setString(4, issueDate);
            addedRecord.setString(5, dueDate);
            addedRecord.setString(6, returnDate);
            addedRecord.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void retrieveBookRecords() {
        try {
            ResultSet rs = stmt.executeQuery("select * from BookRecord");
            LMS.records.clear();
            while (rs.next()) {

                Book book = LMS.getBook(rs.getString(1).trim());
                User issuedTo = LMS.getUser(rs.getString(2).trim());
                User issuedBy = LMS.getUser(rs.getString(3).trim());
                String issueDateString = rs.getString(4).trim();
                String dueDateString = rs.getString(5).trim();
                String returnDateString = rs.getString(6).trim();

                SimpleDateFormat format = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy");
                Date issueDate = format.parse(issueDateString);
                Date dueDate = format.parse(dueDateString);

                if (!returnDateString.equals("-")) {
                    Date returnDate = format.parse(returnDateString);
                    LMS.records.add(new BookRecord(issuedBy, issuedTo, book, issueDate, returnDate, dueDate));
                } else {
                    LMS.records.add(new BookRecord(issuedBy, issuedTo, book, issueDate, dueDate));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateReturnDate(String isbn, String issuedTo, String issueDate, String returnDate) {
        try {
            PreparedStatement updatedRS = con.prepareStatement("UPDATE BookRecord SET "
                    + "	returnDate = ? "
                    + " WHERE isbn = ? and issuedto = ? and date = ?");

            updatedRS.setString(1, returnDate);
            updatedRS.setString(2, isbn);
            updatedRS.setString(3, issuedTo);
            updatedRS.setString(4, issueDate);
            updatedRS.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void removeBookFromReservation(String isbn, String username) {
        try {
            PreparedStatement updatedRS = con.prepareStatement("Delete from ReservationDate "
                    + " WHERE isbn = ? and username = ?");

            updatedRS.setString(1, isbn);
            updatedRS.setString(2, username);
            updatedRS.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void updateBookQty(String isbn, int qty) {
        try {
            PreparedStatement updatedRS = con.prepareStatement("UPDATE Book SET "
                    + "	quantity = ? "
                    + " WHERE isbn = ?");

            updatedRS.setInt(1, qty);
            updatedRS.setString(2, isbn);
            updatedRS.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}





/*
DB SCHEMA FOR TABLES:

CREATE DATABASE [lms]


CREATE TABLE [dbo].[book](
	[id] [int] NOT NULL,
	[title] [nchar](30) NOT NULL,
	[author] [nchar](30) NOT NULL,
	[isbn] [nchar](10) NOT NULL,
	[quantity] [int] NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[bookRecord](
	[isbn] [nchar](10) NOT NULL,
	[issuedto] [varchar](50) NOT NULL,
	[issuedby] [varchar](50) NOT NULL,
	[date] [varchar](50) NOT NULL,
	[dueDate] [varchar](50) NOT NULL,
	[returnDate] [varchar](50) NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[Clerk](
	[id] [int] NOT NULL,
	[name] [nchar](50) NOT NULL,
	[age] [int] NOT NULL,
	[username] [nchar](50) NOT NULL,
	[password] [nchar](50) NOT NULL,
	[gender] [nchar](10) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[librarian](
	[id] [int] NOT NULL,
	[name] [nchar](30) NOT NULL,
	[gender] [nchar](10) NOT NULL,
	[age] [int] NOT NULL,
	[username] [nchar](20) NOT NULL,
	[password] [nchar](20) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[reservationDate](
	[isbn] [nchar](10) NOT NULL,
	[username] [varchar](50) NOT NULL,
	[reservedOn] [varchar](50) NOT NULL,
	[status] [nchar](10) NULL,
	[id] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_reservationDate] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[student](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nchar](50) NOT NULL,
	[age] [int] NOT NULL,
	[department] [nchar](50) NULL,
	[campus] [nchar](50) NULL,
	[rollno] [nchar](10) NULL,
	[username] [varchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[gender] [nchar](10) NOT NULL
) ON [PRIMARY]
GO



INSERT [dbo].[book] ([id], [title], [author], [isbn], [quantity]) VALUES (1, N'In Search of Lost Time        ', N'Marcel Proust                 ', N'1251253   ', 2)
INSERT [dbo].[book] ([id], [title], [author], [isbn], [quantity]) VALUES (2, N'Don Quixote                   ', N'Miguel de Cervantes           ', N'3212421   ', 6)
INSERT [dbo].[book] ([id], [title], [author], [isbn], [quantity]) VALUES (3, N'The Great Gatsby              ', N'F. Scott Fitzgerald           ', N'1242144   ', 23)
INSERT [dbo].[book] ([id], [title], [author], [isbn], [quantity]) VALUES (4, N'War and Peace                 ', N'Leo Tolstoy
                 ', N'1235512   ', 2)
INSERT [dbo].[book] ([id], [title], [author], [isbn], [quantity]) VALUES (5, N'The Odyssey                   ', N'Homer                         ', N'7434544   ', 5)
INSERT [dbo].[book] ([id], [title], [author], [isbn], [quantity]) VALUES (6, N'Crime and Punishment          ', N'Fyodor Dostoyevsky            ', N'2342312   ', 2)
INSERT [dbo].[book] ([id], [title], [author], [isbn], [quantity]) VALUES (7, N'Alice''s Adventures            ', N'Lewis Carroll                 ', N'3243455   ', 7)
INSERT [dbo].[book] ([id], [title], [author], [isbn], [quantity]) VALUES (8, N'Pride and Prejudice           ', N'Jane Austen                   ', N'1242552   ', 4)
INSERT [dbo].[book] ([id], [title], [author], [isbn], [quantity]) VALUES (9, N'The Sound and the Fury        ', N'William Faulkner              ', N'3253256   ', 2)
INSERT [dbo].[book] ([id], [title], [author], [isbn], [quantity]) VALUES (10, N'Great Expectations            ', N'Charles Dickens               ', N'5464778   ', 1)
INSERT [dbo].[book] ([id], [title], [author], [isbn], [quantity]) VALUES (11, N'Middlemarch                   ', N'George Eliot                  ', N'4223677   ', 2)
INSERT [dbo].[book] ([id], [title], [author], [isbn], [quantity]) VALUES (12, N'Little Book of Semaphores     ', N'Aamir Raheem                  ', N'8152123   ', 9)
INSERT [dbo].[Clerk] ([id], [name], [age], [username], [password], [gender]) VALUES (1, N'Mr. Clerk                                         ', 23, N'clerk                                             ', N'123                                               ', N'Male      ')
INSERT [dbo].[librarian] ([id], [name], [gender], [age], [username], [password]) VALUES (1, N'Administrator                 ', N'Male      ', 25, N'admin               ', N'123  



INSERT [dbo].[reservationDate] ([isbn], [username], [reservedOn], [status], [id]) VALUES (N'3212421   ', N'mohsin', N'Sun Oct 21 01:48:44 PKT 2018', N'Issued    ', 14)
INSERT [dbo].[reservationDate] ([isbn], [username], [reservedOn], [status], [id]) VALUES (N'8152123   ', N'clerk', N'Sun Oct 21 14:28:42 PKT 2018', N'Issued    ', 15)
INSERT [dbo].[reservationDate] ([isbn], [username], [reservedOn], [status], [id]) VALUES (N'1251253   ', N'mohsin', N'Sun Oct 21 14:29:44 PKT 2018', N'pending   ', 16)


INSERT [dbo].[student] ([id], [name], [age], [department], [campus], [rollno], [username], [password], [gender]) VALUES (9, N'Mohsin', 21, N'Computer Science', N'Lahore', N'L16-4333  ', N'mohsin', N'123', N'Male ')
*/
