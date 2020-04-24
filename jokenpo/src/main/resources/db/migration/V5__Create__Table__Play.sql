CREATE TABLE IF NOT EXISTS `play` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  	`match_id` bigint(20),
    CONSTRAINT fk_match FOREIGN KEY (`match_id`) REFERENCES matchs(`id`),
  	`player_id` bigint(20),
    CONSTRAINT fk_player FOREIGN KEY (`player_id`) REFERENCES player(`id`),
  `played` varchar(80) NOT NULL
);