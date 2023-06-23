-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 21 juin 2021 à 13:38
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `projetpermis`
--
drop database if  EXISTS `projet_permis`;
CREATE DATABASE IF NOT EXISTS `projet_permis` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `projet_permis`;
-- --------------------------------------------------------

--
-- Structure de la table `action`
--

DROP TABLE IF EXISTS `action`;
CREATE TABLE IF NOT EXISTS `action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_action` int(11) DEFAULT NULL,
  `wording` char(25) DEFAULT NULL,
  `score_minimum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Act_KEY_FK_ACTION_PREDECESSOR` (`fk_action`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `action`
--

INSERT INTO `action` (`id`, `fk_action`, `wording`, `score_minimum`) VALUES
(1, NULL, 'Se mettre en tenue', 4),
(2, 1, 'Préparation véhicule', 2),
(3, 2, 'Effectuer manoeuvre', 8),
(4, NULL, 'Analyser panne(s)', 2),
(5, 4, 'Résoudre panne(s)', 5);

-- --------------------------------------------------------

--
-- Structure de la table `action__mission`
--

DROP TABLE IF EXISTS `action__mission`;
CREATE TABLE IF NOT EXISTS `action__mission` (
  `fk_action` int(11) NOT NULL,
  `fk_mission` int(11) NOT NULL,
  PRIMARY KEY (`fk_action`,`fk_mission`),
  KEY `MisGoa_KEY_FK_MISSION` (`fk_mission`),
  KEY `ActGoa_KEY_FK_ACTION` (`fk_action`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `action__mission`
--

INSERT INTO `action__mission` (`fk_action`, `fk_mission`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(4, 2),
(5, 2);

-- --------------------------------------------------------

--
-- Structure de la table `indicator`
--

DROP TABLE IF EXISTS `indicator`;
CREATE TABLE IF NOT EXISTS `indicator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_action` int(11) NOT NULL,
  `wording` char(50) DEFAULT NULL,
  `value_if_check` int(11) DEFAULT NULL,
  `value_if_uncheck` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Ind_KEY_FK_ACTION` (`fk_action`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `indicator`
--

INSERT INTO `indicator` (`id`, `fk_action`, `wording`, `value_if_check`, `value_if_uncheck`) VALUES
(1, 1, 'Prendre ses outils', 1, -1),
(2, 1, 'Mettre sa tenue correctement', 3, -1),
(3, 1, 'Vérifier ses outils', 4, -2),
(4, 1, 'Prendre des explosifs', -1, 1),
(5, 2, 'Vérifier l\'état des pneus', 3, -2),
(6, 2, 'Vérifier le niveau d\'huile moteur', 1, 0),
(7, 2, 'Vérifier le niveau d\'essence', 3, -1),
(8, 3, 'Conduire à 110 km/h', -2, 2),
(9, 3, 'Conduire les yeux ouverts', 3, -6),
(10, 3, 'Avoir les deux mains sur le volant', 1, 0),
(11, 3, 'Réussir la manoeuvre', 6, -2),
(12, 3, 'Toucher le décor', -3, 3),
(13, 4, 'S\'informer auprès des techniciens', 2, 0),
(14, 4, 'Consulter le manuel', 1, 0),
(15, 4, 'Respecter la procédure', 2, -2),
(16, 4, 'Demander de l\'aide à la tour de contrôle', -1, 1),
(17, 5, 'Trouver la cause de la panne', 6, -3),
(18, 5, 'Utiliser les bons outils', 4, -1);

-- --------------------------------------------------------

--
-- Structure de la table `inscription`
--

DROP TABLE IF EXISTS `inscription`;
CREATE TABLE IF NOT EXISTS `inscription` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_user` int(11) NOT NULL,
  `fk_mission` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Ins_KEY_FK_USER` (`fk_user`),
  KEY `Ins_KEY_FK_GAME` (`fk_mission`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `inscription`
--

INSERT INTO `inscription` (`id`, `fk_user`, `fk_mission`, `date`) VALUES
(11, 2, 1, '2021-06-04'),
(12, 2, 2, '2021-06-04'),
(14, 3, 1, '2021-06-07');

-- --------------------------------------------------------

--
-- Structure de la table `inscription__action`
--

DROP TABLE IF EXISTS `inscription__action`;
CREATE TABLE IF NOT EXISTS `inscription__action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_inscription` int(11) NOT NULL,
  `fk_action` int(11) NOT NULL,
  `sort` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `LeaAct_KEY_FK_INSCRIPTION` (`fk_inscription`),
  KEY `LeaAct_KEY_FK_ACTION` (`fk_action`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `inscription__action`
--

INSERT INTO `inscription__action` (`id`, `fk_inscription`, `fk_action`, `sort`, `score`) VALUES
(1, 11, 1, NULL, 7),
(2, 11, 2, NULL, NULL),
(3, 11, 3, NULL, NULL),
(4, 11, 4, NULL, NULL),
(5, 11, 5, NULL, NULL),
(6, 12, 4, NULL, 1),
(7, 12, 5, NULL, NULL),
(13, 14, 1, NULL, 7),
(14, 14, 2, NULL, 7),
(15, 14, 3, NULL, -7),
(16, 14, 4, NULL, NULL),
(17, 14, 5, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `mission`
--

DROP TABLE IF EXISTS `mission`;
CREATE TABLE IF NOT EXISTS `mission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wording` char(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `mission`
--

INSERT INTO `mission` (`id`, `wording`) VALUES
(1, 'Mission A'),
(2, 'Mission B');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) COLLATE utf8_bin NOT NULL,
  `password` varchar(100) COLLATE utf8_bin NOT NULL,
  `role` varchar(100) COLLATE utf8_bin NOT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `surname` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `forename` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------
--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `role`, `email`, `surname`, `forename`) VALUES
(1, 'Merlot', '$2a$04$CMDLDuhgBAxnpYwy3Lfsr.nBFH.eGEep6bV6mh4j7Nxtns4awxIlq', 'admin', 'john.merlot@gmail.com', 'John', 'Merlot'),
(2, 'Jean', '$2a$04$CMDLDuhgBAxnpYwy3Lfsr.nBFH.eGEep6bV6mh4j7Nxtns4awxIlq', 'learner', 'jean.dakin@gmail.com', 'Jean', 'Dakin'),
(3, 'Paul', '$2a$04$CMDLDuhgBAxnpYwy3Lfsr.nBFH.eGEep6bV6mh4j7Nxtns4awxIlq', 'learner', 'paul.stan@gmail.com', 'Paul', 'Stan');

-- AJOUTEE --

-- --------------------------------------------------------

--
-- Structure de la table `inscription__indicator`
--

DROP TABLE IF EXISTS `inscription__indicator`;
CREATE TABLE IF NOT EXISTS `inscription__indicator` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_inscription` int(11) NOT NULL,
  `fk_action` int(11) NOT NULL,
  `fk_indicator` int(11) NOT NULL,
  `done` bool DEFAULT FALSE,
  PRIMARY KEY (`id`),
  KEY `InsInd_KEY_FK_INSCRIPTION` (`fk_inscription`),
  KEY `InsInd_KEY_FK_ACTION` (`fk_action`),
  KEY `InsInd_KEY_FK_INDICATOR` (`fk_indicator`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------
--
-- Déchargement des données de la table `inscription__indicator`
--

INSERT INTO `inscription__indicator` (`id`, `fk_inscription`, `fk_action`, `fk_indicator`, `done`) VALUES
(3, 11, 1, 1, 1),
(4, 11, 1, 2, 1),
(5, 11, 1, 3, 1),
(6, 11, 1, 4, 1),
(7, 11, 2, 5, 0),
(8, 11, 2, 6, 0),
(9, 11, 2, 7, 0),
(10, 11, 3, 8, 0),
(11, 11, 3, 9, 0),
(12, 11, 3, 10, 0),
(13, 11, 3, 11, 0),
(14, 11, 3, 12, 0),
(15, 11, 4, 13, 0),
(16, 11, 4, 14, 0),
(17, 11, 4, 15, 0),
(18, 11, 4, 16, 0),
(19, 11, 5, 17, 0),
(20, 11, 5, 18, 0),
(21, 12, 4, 13, 1),
(22, 12, 4, 14, 0),
(23, 12, 4, 15, 0),
(24, 12, 4, 16, 0),
(25, 12, 5, 17, 0),
(26, 12, 5, 18, 0),
(27, 14, 1, 1, 1),
(28, 14, 1, 2, 1),
(29, 14, 1, 3, 1),
(30, 14, 1, 4, 1),
(31, 14, 2, 5, 1),
(32, 14, 2, 6, 1),
(33, 14, 2, 7, 1),
(34, 14, 3, 8, 1),
(35, 14, 3, 9, 0),
(36, 14, 3, 10, 0),
(37, 14, 3, 11, 0),
(38, 14, 3, 12, 0),
(39, 14, 4, 13, 0),
(40, 14, 4, 14, 0),
(41, 14, 4, 15, 0),
(42, 14, 4, 16, 0),
(43, 14, 5, 17, 0),
(44, 14, 5, 18, 0);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `action`
--
ALTER TABLE `action`
  ADD CONSTRAINT `Act_FK_ACTION` FOREIGN KEY (`fk_action`) REFERENCES `action` (`id`);

--
-- Contraintes pour la table `action__mission`
--
ALTER TABLE `action__mission`
  ADD CONSTRAINT `ActGoa_FK_ACTION` FOREIGN KEY (`fk_action`) REFERENCES `action` (`id`),
  ADD CONSTRAINT `ActGoa_FK_MISSION` FOREIGN KEY (`fk_mission`) REFERENCES `mission` (`id`);

--
-- Contraintes pour la table `indicator`
--
ALTER TABLE `indicator`
  ADD CONSTRAINT `Ind_FK_ACTION` FOREIGN KEY (`fk_action`) REFERENCES `action` (`id`);

--
-- Contraintes pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD CONSTRAINT `Ins_FK_MISSION` FOREIGN KEY (`fk_mission`) REFERENCES `mission` (`id`),
  ADD CONSTRAINT `Ins_FK_USER` FOREIGN KEY (`fk_user`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `inscription__action`
--
ALTER TABLE `inscription__action`
  ADD CONSTRAINT `LeaAct_FK_ACTION` FOREIGN KEY (`fk_action`) REFERENCES `action` (`id`),
  ADD CONSTRAINT `LeaAct_FK_INSCRIPTION` FOREIGN KEY (`fk_inscription`) REFERENCES `inscription` (`id`);
COMMIT;

--
-- Contraintes pour la table `inscription__indicator`
--
ALTER TABLE `inscription__indicator`
  ADD CONSTRAINT `InsInd_FK_INDICATOR` FOREIGN KEY (`fk_indicator`) REFERENCES `indicator` (`id`),
  ADD CONSTRAINT `InsInd_FK_ACTION` FOREIGN KEY (`fk_action`) REFERENCES `action` (`id`),
  ADD CONSTRAINT `InsInd_FK_INSCRIPTION` FOREIGN KEY (`fk_inscription`) REFERENCES `inscription` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
