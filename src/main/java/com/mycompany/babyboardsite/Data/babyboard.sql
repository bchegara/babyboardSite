-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 20, 2014 at 11:59 PM
-- Server version: 5.5.25
-- PHP Version: 5.4.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `babyboard`
--

-- --------------------------------------------------------

--
-- Table structure for table `activities`
--

CREATE TABLE `activities` (
  `idActivitie` int(11) NOT NULL AUTO_INCREMENT,
  `idBaby` int(11) NOT NULL,
  `date` varchar(50) NOT NULL,
  `kind` varchar(100) NOT NULL,
  `duree` int(11) NOT NULL,
  `note` int(11) NOT NULL,
  `hour` int(11) NOT NULL,
  `minute` int(11) NOT NULL,
  PRIMARY KEY (`idActivitie`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `activities`
--

INSERT INTO `activities` (`idActivitie`, `idBaby`, `date`, `kind`, `duree`, `note`, `hour`, `minute`) VALUES
(15, 1, 'Fri Jun 20  WEST 2014', 'foot', 1, 3, 18, 30),
(16, 1, 'Wed Dec 24  WET 2014', 'achats cadeaux noel', 5, 5, 12, 12);

-- --------------------------------------------------------

--
-- Table structure for table `babies`
--

CREATE TABLE `babies` (
  `idBaby` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `age` varchar(100) NOT NULL,
  `sex` int(10) NOT NULL,
  `firstName` varchar(200) NOT NULL,
  `idParent` int(11) NOT NULL,
  `postit` varchar(255) NOT NULL DEFAULT 'laissez un message ici grâce à cet ingénieux système de post-it !',
  PRIMARY KEY (`idBaby`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `babies`
--

INSERT INTO `babies` (`idBaby`, `name`, `age`, `sex`, `firstName`, `idParent`, `postit`) VALUES
(1, 'Agostinho', 'Fri Jun 20 23:46:25 WEST 2014', 1, 'David', 1, 'donnez lui du doliprane si il sent ses dents ');

-- --------------------------------------------------------

--
-- Table structure for table `jonction`
--

CREATE TABLE `jonction` (
  `idJonction` int(10) NOT NULL AUTO_INCREMENT,
  `idUser` int(10) NOT NULL,
  `idBaby` int(10) NOT NULL,
  PRIMARY KEY (`idJonction`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `jonction`
--

INSERT INTO `jonction` (`idJonction`, `idUser`, `idBaby`) VALUES
(1, 1, 1),
(2, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `mainfacts`
--

CREATE TABLE `mainfacts` (
  `idFact` int(10) NOT NULL AUTO_INCREMENT,
  `idBaby` int(10) NOT NULL,
  `title` varchar(200) NOT NULL,
  `description` varchar(500) NOT NULL,
  `date` varchar(50) NOT NULL,
  `hours` varchar(10) NOT NULL,
  PRIMARY KEY (`idFact`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=26 ;

--
-- Dumping data for table `mainfacts`
--

INSERT INTO `mainfacts` (`idFact`, `idBaby`, `title`, `description`, `date`, `hours`) VALUES
(24, 1, 'Perte premiere dent', 'emouvant', 'Fri Jun 20  WEST 2014', '15:15:00'),
(25, 1, 'Premier pas', 'très très emouvant', 'Fri Jun 20  WEST 2014', '12:12:00');

-- --------------------------------------------------------

--
-- Table structure for table `numeroutile`
--

CREATE TABLE `numeroutile` (
  `idNumeroUtile` int(11) NOT NULL AUTO_INCREMENT,
  `idBaby` int(11) NOT NULL,
  `role` varchar(100) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `numero` int(11) NOT NULL,
  `adresse` varchar(100) NOT NULL,
  PRIMARY KEY (`idNumeroUtile`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `numeroutile`
--

INSERT INTO `numeroutile` (`idNumeroUtile`, `idBaby`, `role`, `nom`, `numero`, `adresse`) VALUES
(1, 1, 'medecin généraliste', 'Dr Rymeyre', 897654468, '39 rue de l''enfer'),
(2, 1, 'dresseur Pokémon', 'Sacha', 345678, 'Bourg Palet');

-- --------------------------------------------------------

--
-- Table structure for table `repas`
--

CREATE TABLE `repas` (
  `idRepas` int(11) NOT NULL AUTO_INCREMENT,
  `idBaby` int(11) NOT NULL,
  `type` varchar(50) NOT NULL,
  `heure` int(11) NOT NULL,
  `minute` int(11) NOT NULL,
  `contenu` varchar(100) NOT NULL,
  `note` int(11) NOT NULL,
  `date` varchar(50) NOT NULL,
  PRIMARY KEY (`idRepas`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

-- --------------------------------------------------------

--
-- Table structure for table `siestes`
--

CREATE TABLE `siestes` (
  `idSieste` int(11) NOT NULL AUTO_INCREMENT,
  `idBaby` int(11) NOT NULL,
  `heure` int(11) NOT NULL,
  `minute` int(11) NOT NULL,
  `duree` int(11) NOT NULL,
  `note` int(11) NOT NULL,
  `date` varchar(50) NOT NULL,
  PRIMARY KEY (`idSieste`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `idUser` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  `adress` varchar(200) NOT NULL,
  `zip` int(10) NOT NULL,
  `city` varchar(200) NOT NULL,
  `tel` varchar(15) NOT NULL,
  `firstName` varchar(200) NOT NULL,
  `rightLevel` varchar(10) NOT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`idUser`, `name`, `email`, `password`, `adress`, `zip`, `city`, `tel`, `firstName`, `rightLevel`) VALUES
(1, 'Agostinho', 'kikiago@gmail.Com', 'password', 'rue de rennes', 75006, 'Paris', '09876553', 'Kevin', 'USER'),
(2, 'AngeGardien', 'jo@paradis.org', 'password', 'rue des anges', 666666, 'Le paradis', '0666666666', 'Josephine', 'NURSE');
