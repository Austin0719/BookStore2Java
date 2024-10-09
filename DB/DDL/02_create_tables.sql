-- 創建 book 表
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[book]') AND type in (N'U'))
BEGIN
    CREATE TABLE [dbo].[book](
        [isbn] [varchar](255) NOT NULL,
        [author] [varchar](255) NULL,
        [introduction] [varchar](255) NULL,
        [name] [varchar](255) NOT NULL,
        PRIMARY KEY CLUSTERED ([isbn] ASC)
    );
    PRINT 'Table book created.';
END
ELSE
BEGIN
    PRINT 'Table book already exists.';
END
GO

-- 創建 inventory 表
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[inventory]') AND type in (N'U'))
BEGIN
    CREATE TABLE [dbo].[inventory](
        [inventory_id] [int] IDENTITY(1,1) NOT NULL,
        [isbn] [varchar](255) NULL,
        [status] [varchar](255) NULL,
        [store_time] [datetime2](6) NULL,
        PRIMARY KEY CLUSTERED ([inventory_id] ASC)
    );
    PRINT 'Table inventory created.';
END
ELSE
BEGIN
    PRINT 'Table inventory already exists.';
END
GO

-- 創建 users 表
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[users]') AND type in (N'U'))
BEGIN
    CREATE TABLE [dbo].[users](
        [user_id] [int] IDENTITY(1,1) NOT NULL,
        [lastlogin_time] [datetime2](6) NULL,
        [password] [varchar](255) NOT NULL,
        [phone_number] [varchar](255) NOT NULL,
        [registration_time] [datetime2](6) NULL,
        [user_name] [varchar](255) NOT NULL,
        PRIMARY KEY CLUSTERED ([user_id] ASC),
        CONSTRAINT [UK9q63snka3mdh91as4io72espi] UNIQUE NONCLUSTERED ([phone_number] ASC)
    );
    PRINT 'Table users created.';
END
ELSE
BEGIN
    PRINT 'Table users already exists.';
END
GO

-- 創建 borrowingRecord 表
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[borrowingRecord]') AND type in (N'U'))
BEGIN
    CREATE TABLE [dbo].[borrowingRecord](
        [id] [int] IDENTITY(1,1) NOT NULL,
        [borrowing_time] [datetime2](6) NULL,
        [return_time] [datetime2](6) NULL,
        [inventory_id] [int] NULL,
        [user_id] [int] NULL,
        PRIMARY KEY CLUSTERED ([id] ASC)
    );
    PRINT 'Table borrowingRecord created.';
END
ELSE
BEGIN
    PRINT 'Table borrowingRecord already exists.';
END
GO