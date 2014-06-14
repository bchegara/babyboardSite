-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Dim 15 Juin 2014 à 01:36
-- Version du serveur: 5.5.37-0ubuntu0.14.04.1
-- Version de PHP: 5.5.9-1ubuntu4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `babyboard`
--

-- --------------------------------------------------------

--
-- Structure de la table `activities`
--

CREATE TABLE IF NOT EXISTS `activities` (
  `idActivitie` int(11) NOT NULL AUTO_INCREMENT,
  `idBaby` int(11) NOT NULL,
  `date` varchar(50) NOT NULL,
  `kind` varchar(100) NOT NULL,
  `duree` int(11) NOT NULL,
  `note` int(11) NOT NULL,
  `hour` int(11) NOT NULL,
  `minute` int(11) NOT NULL,
  PRIMARY KEY (`idActivitie`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Contenu de la table `activities`
--

INSERT INTO `activities` (`idActivitie`, `idBaby`, `date`, `kind`, `duree`, `note`, `hour`, `minute`) VALUES
(1, 1, 'Sun Jun 15  CEST 2014', 'sport', 1, 3, 16, 12),
(2, 1, 'Sat Jun 14  CEST 2014', 'testAuto', 3, 3, 12, 12),
(3, 1, 'Fri Jun 13  CEST 2014', 'testAuto', 3, 3, 12, 12),
(4, 1, 'Sat Jun 14  CEST 2014', 'testAuto', 3, 3, 12, 12),
(5, 1, 'Sun Jun 15  CEST 2014', 'testAuto', 3, 3, 12, 12),
(6, 1, 'Sat Jun 21  CEST 2014', 'testAuto', 3, 3, 12, 12),
(7, 1, 'Fri Jun 20  CEST 2014', 'testAuto', 3, 3, 12, 12),
(8, 1, 'Thu Jun 05  CEST 2014', 'testAuto', 3, 3, 12, 12),
(9, 1, 'Fri Jun 06  CEST 2014', 'testAuto', 3, 3, 12, 12);

-- --------------------------------------------------------

--
-- Structure de la table `babies`
--

CREATE TABLE IF NOT EXISTS `babies` (
  `idBaby` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `age` date NOT NULL,
  `sex` int(10) NOT NULL,
  `firstName` varchar(200) NOT NULL,
  PRIMARY KEY (`idBaby`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `babies`
--

INSERT INTO `babies` (`idBaby`, `name`, `age`, `sex`, `firstName`) VALUES
(1, 'Gaetan', '2002-02-14', 1, 'Rouaix'),
(2, 'Toscan Junior', '2017-00-00', 0, 'Vertanessian');

-- --------------------------------------------------------

--
-- Structure de la table `jonction`
--

CREATE TABLE IF NOT EXISTS `jonction` (
  `idJonction` int(10) NOT NULL,
  `idUser` int(10) NOT NULL,
  `idBaby` int(10) NOT NULL,
  PRIMARY KEY (`idJonction`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `jonction`
--

INSERT INTO `jonction` (`idJonction`, `idUser`, `idBaby`) VALUES
(0, 0, 0),
(1, 2, 1),
(2, 2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `mainfacts`
--

CREATE TABLE IF NOT EXISTS `mainfacts` (
  `idFact` int(10) NOT NULL AUTO_INCREMENT,
  `idBaby` int(10) NOT NULL,
  `title` varchar(200) NOT NULL,
  `description` varchar(500) NOT NULL,
  `date` varchar(50) NOT NULL,
  `hours` varchar(10) NOT NULL,
  PRIMARY KEY (`idFact`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Contenu de la table `mainfacts`
--

INSERT INTO `mainfacts` (`idFact`, `idBaby`, `title`, `description`, `date`, `hours`) VALUES
(1, 1, 'Sieste', 'Le bébé fait la sieste depuis 14h', 'Wed Jun 04 CEST 2014', '00:00:00'),
(2, 1, 'fact2', 'descr du fact 2', 'Thu Jun 05 CEST 2014', '22:22:22'),
(3, 1, 'fact3', 'testAuto', 'Wed Jun 04  CEST 2014', '00:00:00'),
(4, 1, 'fact4', 'testAuto', 'Thu Jun 05  CEST 2014', '00:00:00'),
(5, 1, 'fact5', 'testAuto', 'Fri Jun 06  CEST 2014', '00:00:00'),
(6, 1, 'fact6', 'testAuto', 'Fri Jun 13  CEST 2014', '00:00:00'),
(7, 1, 'fact7', 'testAuto', 'Thu Jun 12  CEST 2014', '00:00:00'),
(8, 1, 'fact8', 'testAuto', 'Thu Jun 19  CEST 2014', '00:00:00'),
(9, 1, 'testAuto', 'testAuto', 'Sun Jun 15  CEST 2014', '00:00:00'),
(10, 1, 'testAuto', 'testAuto', 'Sat Jun 14  CEST 2014', '00:00:00'),
(11, 1, 'testAuto', 'testAuto', 'Fri Jun 13  CEST 2014', '00:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `idUser` int(10) NOT NULL,
  `name` varchar(200) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  `adress` varchar(200) NOT NULL,
  `zip` int(10) NOT NULL,
  `city` varchar(200) NOT NULL,
  `tel` int(10) NOT NULL,
  `firstName` varchar(200) NOT NULL,
  `rightLevel` varchar(10) NOT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`idUser`, `name`, `email`, `password`, `adress`, `zip`, `city`, `tel`, `firstName`, `rightLevel`) VALUES
(1, 'Geoffroy', 'Rouaix', 'tata', '57 rue michel ange', 75016, 'Paris', 618275025, 'Rouaix', 'ADMIN'),
(2, 'Cheg', 'b', 'a', 'adresse', 75018, 'Paris', 0, 'Baptiste', 'ADMIN'),
(3, 'name', 'a', 'a', 'add', 0, 'city', 0, 'fname', 'ADMIN'),
(4, 'namez', 'azdzadad', 'zdzd', 'zeedzdz', 6666, 'PPDPDP', 88888, 'DEDED', 'ADMIN');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
