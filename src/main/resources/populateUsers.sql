INSERT INTO user_table
VALUES (1, 'someAddress', 'email1@somewhere.com', false, 'SomeoneF1', 'SomeoneL1', 'somepassword1', '1234567890', 'admin', 'someusername1', 1, 2),
	   (2, 'someAddress2', 'email2@anywhere.com', true, 'SomeoneF2', 'SomeoneL2', 'somepassword2', '0987654321', 'customer', 'someusername2', 2, 3);
	   
INSERT INTO recipient
VALUES (1, '983298398', 'Rent payment', 'abc@xyz.com', 'AbcLastName', '7989823818', 1),
       (2, '093498498', 'lunch', 'cook@gmail.com', 'CookMaster', '4563849284', 2),
       (3, '984398439', 'Cab', 'cabdriver@yahoo.com', 'Cabbie', '7738880200', 1);
       
INSERT INTO checking_checkbook_request
VALUES (1, '03-05-2021 05:33:03', '03-05-2021 01:23:38', true, 'approved', 1, 1, 1),
       (2, '24-05-2021 02:12:23', '23-05-2021 01:21:23', true, 'approved', 1, 1, 3),
       (3, '21-06-2021 03:21:25', '19-06-2021 01:21:25', true, 'approved', 1, 2, 2),
       (4, null,			      '10-05-2021 00:37:40', false, 'pending', null, 3, null),
       (5, '17-06-2021 03:21:30', '16-06-2021 01:21:30', true, 'approved', 2 ,3, 4),
       (6, '19-07-2021 01:23:48', '18-07-2021 01:23:48', true, 'approved', 2, 4, 6);

INSERT INTO savings_checkbook_request
VALUES (1, '19-07-2021 01:23:48', '18-07-2021 01:23:48', true, 'approved', 1, 2, 1),
	   (2, '03-05-2021 05:33:03', '03-05-2021 01:23:38', true, 'approved', 1, 2, 2),
       (3, '17-06-2021 03:21:30', '16-06-2021 01:21:30', true, 'approved', 2 ,3, 4),
       (4, '21-06-2021 03:21:25', '19-06-2021 01:21:25', true, 'approved', 1, 1, 6);