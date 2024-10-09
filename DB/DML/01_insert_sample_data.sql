CREATE OR ALTER PROCEDURE InsertSampleBookAndInventoryData
AS
BEGIN
    SET NOCOUNT ON;

    INSERT INTO book (isbn, author, introduction, name)
    VALUES 
    ('9780001234567', '張三', '一個關於希望的故事', '春天的約定'),
    ('9780001234568', '李四', '探索宇宙的奧秘', '星際旅程'),
    ('9780001234569', '王五', '現代都市愛情故事', '城市之光'),
    ('9780001234570', '趙六', '歷史變革的關鍵時刻', '轉折點'),
    ('9780001234571', '錢七', '哲學與生活的交匯', '思考的藝術'),
    ('9780001234572', '孫八', '科技如何改變未來', '數字革命'),
    ('9780001234573', '周九', '探索人性的黑暗面', '迷霧森林'),
    ('9780001234574', '吳十', '關於成長的青春小說', '十七歲的夏天'),
    ('9780001234575', '鄭十一', '環球美食文化之旅', '舌尖上的世界'),
    ('9780001234576', '馮十二', '探索海洋生態系統', '藍色星球');

    -- 插入 inventory 數據
    INSERT INTO inventory (isbn, status, store_time)
    VALUES 
    ('9780001234567', '可借閱', DATEADD(month, -2, GETDATE())),
    ('9780001234568', '可借閱', DATEADD(month, -2, GETDATE())),
    ('9780001234569', '可借閱', DATEADD(month, -2, GETDATE())),
    ('9780001234570', '可借閱', DATEADD(month, -1, GETDATE())),
    ('9780001234571', '可借閱', DATEADD(month, -1, GETDATE())),
    ('9780001234572', '可借閱', DATEADD(month, -1, GETDATE())),
    ('9780001234573', '可借閱', DATEADD(day, -15, GETDATE())),
    ('9780001234574', '可借閱', DATEADD(day, -15, GETDATE())),
    ('9780001234575', '可借閱', DATEADD(day, -7, GETDATE())),
    ('9780001234576', '可借閱', DATEADD(day, -7, GETDATE()));

    PRINT 'Sample data for book and inventory tables has been inserted successfully.';
END
GO

EXEC InsertSampleBookAndInventoryData;
GO

PRINT 'Database setup and sample data insertion completed.';