INSERT INTO checking_transaction
VALUES
       (1, 1100, 2200.00, '2021-05-13 00:38:03', 'Between account transfer from Checking to Savings', 'Finished', 'Transfer', 1),
       (2, 1400, 800.00, '2021-06-15 00:37:31', 'Withdraw from Checking Account', 'Finished', 'Account', 1),
	   (3, 3300, 5300.00, '2021-05-16 00:37:26', 'Deposit to Checking Account', 'Finished', 'Account', 2),
       (4, 2100, 3200.00, '2021-06-05 01:21:38', 'Deposit to Checking Account', 'Finished', 'Account', 1),
       (5, 400, 2000.00, '2021-07-18 01:23:48', 'Between account transfer from Checking to Savings', 'Pending', 'Account', 1),
       (6, 300, 2800.00, '2021-05-22 01:21:46', 'Withdraw from Checking Account', 'Finished', 'Account', 3);
       
INSERT INTO savings_transaction
VALUES
       (1, 1500, 2250.00, '2021-05-03 01:23:38', 'Between account transfer from Savings to Checking', 'Finished', 'Transfer', 2),
       (2, 400, 1750.00, '2021-05-23 01:21:23', 'Withdraw from Savings Account', 'Finished', 'Account', 1),
       (3, 150, 2150.00, '2021-06-19 01:21:25', 'Withdraw from Savings Account', 'Finished', 'Account', 3),
	   (4, 1000, 1000.00, '2021-05-10 00:37:40', 'Deposit to Savings Account', 'Finished', 'Account', 3),
       (5, 2000, 3750.00, '2021-06-16 01:21:30', 'Deposit to Savings Account', 'Pending', 'Account', 1);
