INSERT INTO `checking_account` VALUES (1,0,314001001),(2,2500,314001002),(3,0,314001003),(4,1100,314001004),(5,0,314001005),(6,0,314001006),(7,0,314001007),(8,0,314001008),(9,0,314001009),(10,0,314001010),(11,0,314001011),(12,0,314001012),(13,0,314001013);
INSERT INTO `checking_checkbook` VALUES (1,42,'standard',3),(2,42,'standard',3);
INSERT INTO `checking_transaction` VALUES (1,-100,1900,'2021-07-20 16:20:31.935000','Transfer to recipient RecpientName','Transfer','Finished',2),(2,2500,35833.33,'2021-07-20 16:20:32.788000','Deposit to Checking Account','Finished','Account',6),(3,200,2100,'2021-07-20 16:20:37.397000','Deposit to Checking Account','Finished','Account',2),(4,-200,1900,'2021-07-20 16:20:37.423000','Withdraw from Checking Account','Finished','Account',2),(5,-100,1800,'2021-07-20 16:20:37.812000','Between account transfer from Checking to Savings','Transfer','Finished',2),(6,200,2000,'2021-07-20 16:20:39.065000','Deposit to Checking Account','Finished','Account',2),(7,500,2500,'2021-07-20 16:20:39.092000','Deposit to Checking Account','Finished','Account',2),(8,-400,1600,'2021-07-20 16:20:39.525000','Withdraw from Checking Account','Finished','Account',4),(9,-500,1100,'2021-07-20 16:20:39.547000','Withdraw from Checking Account','Finished','Account',4);
INSERT INTO `savings_account` VALUES (1,0,514001001),(2,20800,514001002),(3,0,514001003),(4,2100,514001004),(5,0,514001005),(6,0,514001006),(7,0,514001007),(8,0,514001008),(9,0,514001009),(10,0,514001010),(11,0,514001011),(12,0,514001012),(13,0,514001013),(14,0,514001014),(15,0,514001015),(16,0,514001016),(17,0,514001017),(18,0,514001018),(19,0,514001019),(20,0,514001020),(21,0,514001021),(22,0,514001022),(23,0,514001023);
INSERT INTO `savings_checkbook` VALUES (1,42,'standard',3),(2,42,'standard',3),(3,24,'customized',3);
INSERT INTO `savings_transaction` VALUES (1,100,20100,'2021-07-20 16:20:37.812000','Between account transfer to Savings from Checking','Transfer','Finished',2),(2,200,20300,'2021-07-20 16:20:38.652000','Deposit to Savings Account','Finished','Account',2),(3,500,20800,'2021-07-20 16:20:38.684000','Deposit to Savings Account','Finished','Account',2),(4,-400,2600,'2021-07-20 16:20:40.278000','Withdraw from Savings Account','Finished','Account',4),(5,-500,2100,'2021-07-20 16:20:40.305000','Withdraw from Savings Account','Finished','Account',4),(6,2500,35833.33,'2021-07-20 16:20:40.710000','Deposit to Checking Account','Finished','Account',23);
INSERT INTO `user_table` VALUES (1,'Address1','first.last@email.com',_binary '','firstName1','lastName1','$2a$10$j7mtvG2LGT946LmZYpht6OO.kIotsh6Q1vc9eU3dDJzyphgF9RHEi','8379478838','admin','username1',1,1),(2,'Address2','first2.last2@email.com',_binary '','firstName2','lastName2','$2a$10$XCjJeuXGcqN64cCc9i50TOA843FS3ykdAXMmkK.zS7w2.gxNXk7qa','9379479938','customer','username2',2,2),(3,'Address3','first3.last3@email.com',_binary '','firstName3','lastName3','$2a$10$jXOe4GKDtugW5l6DSMkhB.fNmYsu//hrhtTDYVV9AtmkOu6Nv9pjy','8379478838','customer','username3',3,3),(4,'Address4','first4.last4@email.com',_binary '','firstName4','lastName4',' $2a$10$Awv/pteuIvTn8uMhjAYSEenUgXoSXfBpIYQw7PG221qRNV5zpFYjO','9379479938','customer','username4',4,4),(5,'AdminAddress','admin@icin.com',_binary '','AdminFirst','AdminLast','$2a$10$ezFHFds6PVfkY33FADgQzu.N4JHHRwhqPOSJta/7vnVgl6vrEx.u.','9980873879','admin','adminUser',5,5),(6,'Address5','first5.last5@email.com',_binary '','firstName5','lastName5','$2a$10$NwLKoEssS7ZLj1SqFVDkJOfGEsfv1CZ9sZJ.CEOQ7y9XpDHvFtn5u','8379433838','admin','username5',12,21),(7,'Address6','first6.last6@email.com',_binary '','firstName6','lastName6','$2a$10$ljyIWND0gnzG3vP3Rq0qH.KG4ARXw3LIzHkf.tsHD7VFsSzlrSTNW','9379479938','customer','username6',13,22);
INSERT INTO `recipient` VALUES (1,'314001002','Settle splitwise','rec.name@email.com','RecpientName','6479388478',2);
INSERT INTO `checking_checkbook_request` VALUES (1,'Tue Jul 20 16:20:41 PDT 2021','Tue Jul 20 16:20:34 PDT 2021',_binary '','Approved',1,3,1),(2,'Tue Jul 20 16:20:41 PDT 2021','Tue Jul 20 16:20:41 PDT 2021',_binary '','Approved',1,3,2);
INSERT INTO `savings_checkbook_request` VALUES (1,'Tue Jul 20 16:20:36 PDT 2021','Tue Jul 20 16:20:36 PDT 2021',_binary '','Approved',1,3,1),(2,NULL,'Tue Jul 20 16:20:38 PDT 2021',_binary '\0','Pending',NULL,3,2),(3,NULL,'Tue Jul 20 16:20:38 PDT 2021',_binary '\0','Pending',NULL,3,3);