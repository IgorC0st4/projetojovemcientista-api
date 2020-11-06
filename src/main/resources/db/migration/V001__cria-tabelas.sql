
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
    `nick` VARCHAR(100) NOT NULL,
    `idade` INT NOT NULL,
    `escolaridade` VARCHAR(20) NOT NULL,
    `genero` VARCHAR(1) NOT NULL,
    `senha` VARCHAR(16) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `resultado` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `tempo_final` VARCHAR(100) NOT NULL,
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

