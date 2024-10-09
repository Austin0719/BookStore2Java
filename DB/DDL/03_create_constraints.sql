IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FKe2cadm0kh2k6nmp0qvtawt5t]') AND parent_object_id = OBJECT_ID(N'[dbo].[borrowingRecord]'))
BEGIN
    ALTER TABLE [dbo].[borrowingRecord] WITH CHECK ADD CONSTRAINT [FKe2cadm0kh2k6nmp0qvtawt5t] FOREIGN KEY([inventory_id])
    REFERENCES [dbo].[inventory] ([inventory_id]);
    PRINT 'Foreign key constraint FKe2cadm0kh2k6nmp0qvtawt5t added to borrowingRecord.';
END
GO

IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FKet73v2uy2h7se4d3bf6rnmjhh]') AND parent_object_id = OBJECT_ID(N'[dbo].[borrowingRecord]'))
BEGIN
    ALTER TABLE [dbo].[borrowingRecord] WITH CHECK ADD CONSTRAINT [FKet73v2uy2h7se4d3bf6rnmjhh] FOREIGN KEY([user_id])
    REFERENCES [dbo].[users] ([user_id]);
    PRINT 'Foreign key constraint FKet73v2uy2h7se4d3bf6rnmjhh added to borrowingRecord.';
END
GO