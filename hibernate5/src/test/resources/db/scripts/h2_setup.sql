-- Dumping structure for table telephone_dir.person
CREATE TABLE IF NOT EXISTS `person` (
  id bigint(20) NOT NULL,
  first_name varchar(100) NOT NULL,
  last_name varchar(100) DEFAULT NULL,
  PRIMARY KEY (id)
);

-- Data exporting was unselected.

-- Dumping structure for table telephone_dir.phone_details
CREATE TABLE IF NOT EXISTS `phone_details` (
  id int NOT NULL PRIMARY KEY,
  phone_num varchar(50) NOT NULL,
  num_type varchar(100) NOT NULL,
  is_active bit(1) NOT NULL,
  person_id int NOT NULL
);

ALTER TABLE phone_details ADD FOREIGN KEY ( person_id ) REFERENCES person (id) ;

-- Data exporting was unselected.


-- Dumping structure for table telephone_dir.sequence_table
CREATE TABLE IF NOT EXISTS SEQUENCE_TABLE (
  sequence_name varchar(255) NOT NULL PRIMARY KEY,
  next_val int DEFAULT NULL
);
