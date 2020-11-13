
CREATE TABLE `nivel` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `numero` INTEGER NOT NULL,
    `parametro_inferior` VARCHAR(5) NOT NULL,
    `parametro_superior` VARCHAR(5) NOT NULL,
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
    `genero` VARCHAR(1) NOT NULL,
    `senha` VARCHAR(16) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `resultado` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `tempo_final` VARCHAR(5) NOT NULL,
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

INSERT INTO nivel (numero, parametro_inferior, parametro_superior) VALUES (1, "00:10", "00:10");
INSERT INTO nivel (numero, parametro_inferior, parametro_superior) VALUES (2, "00:10", "00:10");
INSERT INTO nivel (numero, parametro_inferior, parametro_superior) VALUES (3, "00:10", "00:10");
INSERT INTO nivel (numero, parametro_inferior, parametro_superior) VALUES (4, "00:10", "00:10");
INSERT INTO nivel (numero, parametro_inferior, parametro_superior) VALUES (5, "00:10", "00:10");
INSERT INTO nivel (numero, parametro_inferior, parametro_superior) VALUES (6, "00:10", "00:10");
INSERT INTO nivel (numero, parametro_inferior, parametro_superior) VALUES (7, "00:10", "00:10");

