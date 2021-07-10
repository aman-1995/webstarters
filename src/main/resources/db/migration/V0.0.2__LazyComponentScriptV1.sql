CREATE TABLE IF NOT EXISTS `lzc_component_details`
(
   `component_id`             VARCHAR(100) NOT NULL,
   `component_description`    VARCHAR(200) NULL,
   `table_name`               VARCHAR(100) NULL,
   `query`                    TEXT NOT NULL,
   `query_type`               INT(4) NOT NULL,
   PRIMARY KEY(`component_id`)
)
ENGINE = INNODB
CHARACTER SET 'utf8'
COLLATE 'utf8_general_ci'