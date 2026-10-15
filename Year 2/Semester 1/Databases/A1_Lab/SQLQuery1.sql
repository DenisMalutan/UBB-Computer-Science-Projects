CREATE DATABASE EquipmentStore;
GO


USE EquipmentStore;
GO


CREATE TABLE Categories (
    CategoryID int PRIMARY KEY IDENTITY(1,1),
    Name varchar(50) NOT NULL,
    Description varchar(255)
);


CREATE TABLE Customers (
    CustomerID int PRIMARY KEY IDENTITY(1,1),
    FirstName varchar(50) NOT NULL,
    LastName varchar(50) NOT NULL,
    Email varchar(100) NOT NULL,
    Phone varchar(20)
);


CREATE TABLE Equipment (
    EquipmentID int PRIMARY KEY IDENTITY(1,1),
    Name varchar(100) NOT NULL,
    Price decimal(10,2) NOT NULL,
    CategoryID int,
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);


CREATE TABLE Orders (
    OrderID int PRIMARY KEY IDENTITY(1,1),
    CustomerID int,
    OrderDate datetime DEFAULT GETDATE(),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);


CREATE TABLE OrderDetails (
    OrderDetailID int PRIMARY KEY IDENTITY(1,1),
    OrderID int,
    EquipmentID int,
    Quantity int NOT NULL,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (EquipmentID) REFERENCES Equipment(EquipmentID)
);