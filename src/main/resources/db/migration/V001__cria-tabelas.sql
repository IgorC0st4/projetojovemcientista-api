CREATE TABLE `nivel` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `numero` INTEGER NOT NULL,
    `parametro_inferior` INTEGER NOT NULL,
    `parametro_superior` INTEGER NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `administrador` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `email` VARCHAR(60) NOT NULL,
    `senha` VARCHAR(16) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `usuario` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `nick` VARCHAR(10) NOT NULL UNIQUE,
    `idade` INT NOT NULL,
    `escolaridade` INTEGER NOT NULL,
    `tipo_escola` VARCHAR(10) NOT NULL,
    `sexo` VARCHAR(1) NOT NULL,
    `transtorno` VARCHAR(20) NOT NULL,
    `senha` TEXT NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `resultado` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `tempo_final` INTEGER NOT NULL,
    `erros` INTEGER NOT NULL,
    `id_usuario` BIGINT NOT NULL,
    `id_nivel` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    KEY `nivel_resultado_fk` (`id_nivel`),
    KEY `usuario_resultado_fk` (`id_usuario`),
    CONSTRAINT `nivel_resultado_fk` FOREIGN KEY (`id_nivel`)
        REFERENCES `nivel` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `usuario_resultado_fk` FOREIGN KEY (`id_usuario`)
        REFERENCES `usuario` (`id`)
        ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO nivel (numero, parametro_inferior, parametro_superior) VALUES (1, 10, 10);
INSERT INTO nivel (numero, parametro_inferior, parametro_superior) VALUES (2, 10, 10);
INSERT INTO nivel (numero, parametro_inferior, parametro_superior) VALUES (3, 10, 10);
INSERT INTO nivel (numero, parametro_inferior, parametro_superior) VALUES (4, 10, 10);
INSERT INTO nivel (numero, parametro_inferior, parametro_superior) VALUES (5, 10, 10);
INSERT INTO nivel (numero, parametro_inferior, parametro_superior) VALUES (6, 10, 10);
INSERT INTO nivel (numero, parametro_inferior, parametro_superior) VALUES (7, 10, 10);