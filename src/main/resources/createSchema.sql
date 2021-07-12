CREATE TABLE `checking_account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `balance` double DEFAULT NULL,
  `number` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `checking_transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `available_balance` double DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `checking_account_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4n7jwmwbrdwmnraen5r133i5s` (`checking_account_id`),
  CONSTRAINT `FK4n7jwmwbrdwmnraen5r133i5s` FOREIGN KEY (`checking_account_id`) REFERENCES `checking_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `savings_account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `balance` double DEFAULT NULL,
  `number` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `savings_transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `available_balance` double DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `savings_account_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4bt1l2090882974glyn79q2s9` (`savings_account_id`),
  CONSTRAINT `FK4bt1l2090882974glyn79q2s9` FOREIGN KEY (`savings_account_id`) REFERENCES `savings_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

--CREATE TABLE `user_table` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `checking_account_id` int DEFAULT NULL,
  `savings_account_id` int DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  KEY `FKrcojjnhf0l6xygymvbua95igh` (`checking_account_id`),
  KEY `FKihums7d3g5cv9ehminfs1539e` (`savings_account_id`),
  CONSTRAINT `FKihums7d3g5cv9ehminfs1539e` FOREIGN KEY (`savings_account_id`) REFERENCES `savings_account` (`id`),
  CONSTRAINT `FKrcojjnhf0l6xygymvbua95igh` FOREIGN KEY (`checking_account_id`) REFERENCES `checking_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `recipient` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_number` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3041ks22uyyuuw441k5671ah9` (`user_id`),
  CONSTRAINT `FK3041ks22uyyuuw441k5671ah9` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci