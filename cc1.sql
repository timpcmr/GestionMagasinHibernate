-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 20 jan. 2023 à 22:26
-- Version du serveur : 8.0.31
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `cc1`
--

-- --------------------------------------------------------

--
-- Structure de la table `categoriemateriel`
--

DROP TABLE IF EXISTS `categoriemateriel`;
CREATE TABLE IF NOT EXISTS `categoriemateriel` (
  `idCategorieMateriel` int NOT NULL,
  `nomCategorieMateriel` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idCategorieMateriel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `categoriemateriel`
--

INSERT INTO `categoriemateriel` (`idCategorieMateriel`, `nomCategorieMateriel`) VALUES
(1, 'Longiligne'),
(2, 'Séparateur');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `idClient` int NOT NULL AUTO_INCREMENT,
  `prenomClient` varchar(50) DEFAULT NULL,
  `nomClient` varchar(50) DEFAULT NULL,
  `adresseClient` varchar(100) DEFAULT NULL,
  `telephoneClient` varchar(50) DEFAULT NULL,
  `idMagasin` int NOT NULL,
  PRIMARY KEY (`idClient`),
  KEY `idMagasin` (`idMagasin`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`idClient`, `prenomClient`, `nomClient`, `adresseClient`, `telephoneClient`, `idMagasin`) VALUES
(1, 'Lucas', 'Senlecque', '55, Avenue Édouard Michelin', '0123456789', 1),
(2, 'Timothée', 'Auffret', '17A, Rue de la Fourbisserie', '987653210', 2);

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `idCommande` int NOT NULL AUTO_INCREMENT,
  `idClient` int NOT NULL,
  `idMagasin` int DEFAULT NULL,
  PRIMARY KEY (`idCommande`),
  KEY `idClient` (`idClient`),
  KEY `idMagasin` (`idMagasin`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`idCommande`, `idClient`, `idMagasin`) VALUES
(2, 2, 2),
(3, 2, 2),
(17, 1, 1),
(18, 1, 1),
(19, 1, 1),
(20, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `composant`
--

DROP TABLE IF EXISTS `composant`;
CREATE TABLE IF NOT EXISTS `composant` (
  `idComposant` int NOT NULL AUTO_INCREMENT,
  `nomComposant` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idComposant`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `composant`
--

INSERT INTO `composant` (`idComposant`, `nomComposant`) VALUES
(1, 'Fer'),
(2, 'Verre'),
(3, 'Bois'),
(4, 'Parpaign'),
(5, 'Brique'),
(6, 'Plastique');

-- --------------------------------------------------------

--
-- Structure de la table `composer`
--

DROP TABLE IF EXISTS `composer`;
CREATE TABLE IF NOT EXISTS `composer` (
  `idMateriel` int NOT NULL,
  `idComposant` int NOT NULL,
  `quantiteComposant` int DEFAULT NULL,
  PRIMARY KEY (`idMateriel`,`idComposant`),
  KEY `idComposant` (`idComposant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `composer`
--

INSERT INTO `composer` (`idMateriel`, `idComposant`, `quantiteComposant`) VALUES
(1, 3, 3),
(1, 6, 1),
(2, 1, 3),
(2, 6, 1),
(3, 4, 25),
(4, 1, 2),
(4, 3, 10);

-- --------------------------------------------------------

--
-- Structure de la table `magasin`
--

DROP TABLE IF EXISTS `magasin`;
CREATE TABLE IF NOT EXISTS `magasin` (
  `idMagasin` int NOT NULL AUTO_INCREMENT,
  `nomMagasin` varchar(50) DEFAULT NULL,
  `adresseMagasin` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idMagasin`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `magasin`
--

INSERT INTO `magasin` (`idMagasin`, `nomMagasin`, `adresseMagasin`) VALUES
(1, 'Polytech Store', '64, Avenue Jean Portalis'),
(2, 'DI Store', '66, Avenue Jean Portalis');

-- --------------------------------------------------------

--
-- Structure de la table `materiel`
--

DROP TABLE IF EXISTS `materiel`;
CREATE TABLE IF NOT EXISTS `materiel` (
  `idMateriel` int NOT NULL AUTO_INCREMENT,
  `nomMateriel` varchar(50) DEFAULT NULL,
  `materielSubstitution` int DEFAULT NULL,
  `idCategorieMateriel` int DEFAULT NULL,
  PRIMARY KEY (`idMateriel`),
  KEY `sr_fk_emp_man` (`materielSubstitution`),
  KEY `idCategorieMateriel` (`idCategorieMateriel`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `materiel`
--

INSERT INTO `materiel` (`idMateriel`, `nomMateriel`, `materielSubstitution`, `idCategorieMateriel`) VALUES
(1, 'Barre de Bois', 2, 1),
(2, 'Barre de Fer', 1, 1),
(3, 'Mur', 4, 2),
(4, 'Claustra', 3, 2);

-- --------------------------------------------------------

--
-- Structure de la table `posseder`
--

DROP TABLE IF EXISTS `posseder`;
CREATE TABLE IF NOT EXISTS `posseder` (
  `idMagasin` int NOT NULL,
  `idMateriel` int NOT NULL,
  `quantite` int DEFAULT NULL,
  PRIMARY KEY (`idMagasin`,`idMateriel`),
  KEY `idMateriel` (`idMateriel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `posseder`
--

INSERT INTO `posseder` (`idMagasin`, `idMateriel`, `quantite`) VALUES
(1, 3, 12),
(1, 4, 7),
(2, 1, 5),
(2, 2, 12);

-- --------------------------------------------------------

--
-- Structure de la table `quantifier`
--

DROP TABLE IF EXISTS `quantifier`;
CREATE TABLE IF NOT EXISTS `quantifier` (
  `idMateriel` int NOT NULL,
  `idCommande` int NOT NULL,
  `quantiteCommande` int DEFAULT NULL,
  PRIMARY KEY (`idMateriel`,`idCommande`),
  KEY `idCommande` (`idCommande`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `quantifier`
--

INSERT INTO `quantifier` (`idMateriel`, `idCommande`, `quantiteCommande`) VALUES
(1, 2, 3),
(1, 3, 3),
(2, 3, 3),
(3, 17, 4),
(3, 18, 4),
(3, 20, 3);

-- --------------------------------------------------------

--
-- Structure de la table `seuil`
--

DROP TABLE IF EXISTS `seuil`;
CREATE TABLE IF NOT EXISTS `seuil` (
  `idClient` int NOT NULL,
  `idCategorieMateriel` int NOT NULL,
  `seuil` int DEFAULT NULL,
  PRIMARY KEY (`idClient`,`idCategorieMateriel`),
  KEY `idCategorieMateriel` (`idCategorieMateriel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `seuil`
--

INSERT INTO `seuil` (`idClient`, `idCategorieMateriel`, `seuil`) VALUES
(1, 1, 20),
(1, 2, 20),
(2, 1, 20),
(2, 2, 20);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `client_ibfk_1` FOREIGN KEY (`idMagasin`) REFERENCES `magasin` (`idMagasin`);

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `commande_ibfk_1` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`),
  ADD CONSTRAINT `commande_ibfk_2` FOREIGN KEY (`idMagasin`) REFERENCES `magasin` (`idMagasin`),
  ADD CONSTRAINT `commande_ibfk_3` FOREIGN KEY (`idMagasin`) REFERENCES `magasin` (`idMagasin`);

--
-- Contraintes pour la table `composer`
--
ALTER TABLE `composer`
  ADD CONSTRAINT `composer_ibfk_1` FOREIGN KEY (`idMateriel`) REFERENCES `materiel` (`idMateriel`),
  ADD CONSTRAINT `composer_ibfk_2` FOREIGN KEY (`idComposant`) REFERENCES `composant` (`idComposant`);

--
-- Contraintes pour la table `materiel`
--
ALTER TABLE `materiel`
  ADD CONSTRAINT `materiel_ibfk_1` FOREIGN KEY (`idCategorieMateriel`) REFERENCES `categoriemateriel` (`idCategorieMateriel`),
  ADD CONSTRAINT `sr_fk_emp_man` FOREIGN KEY (`materielSubstitution`) REFERENCES `materiel` (`idMateriel`);

--
-- Contraintes pour la table `posseder`
--
ALTER TABLE `posseder`
  ADD CONSTRAINT `posseder_ibfk_1` FOREIGN KEY (`idMagasin`) REFERENCES `magasin` (`idMagasin`),
  ADD CONSTRAINT `posseder_ibfk_2` FOREIGN KEY (`idMateriel`) REFERENCES `materiel` (`idMateriel`);

--
-- Contraintes pour la table `quantifier`
--
ALTER TABLE `quantifier`
  ADD CONSTRAINT `quantifier_ibfk_1` FOREIGN KEY (`idMateriel`) REFERENCES `materiel` (`idMateriel`),
  ADD CONSTRAINT `quantifier_ibfk_2` FOREIGN KEY (`idCommande`) REFERENCES `commande` (`idCommande`);

--
-- Contraintes pour la table `seuil`
--
ALTER TABLE `seuil`
  ADD CONSTRAINT `seuil_ibfk_1` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`),
  ADD CONSTRAINT `seuil_ibfk_2` FOREIGN KEY (`idCategorieMateriel`) REFERENCES `categoriemateriel` (`idCategorieMateriel`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
