USE master;
GO

IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = N'BookStore')
BEGIN
    CREATE DATABASE BookStore;
    PRINT 'Database BookStore created.';
END
ELSE
BEGIN
    PRINT 'Database BookStore already exists.';
END
GO

USE BookStore;
GO