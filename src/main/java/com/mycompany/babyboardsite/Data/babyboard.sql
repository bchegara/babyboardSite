-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 17, 2014 at 04:02 PM
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `activities`
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
(9, 1, 'Fri Jun 06  CEST 2014', 'testAuto', 3, 3, 12, 12),
(10, 1, 'Sun Jun 15  CEST 2014', 'musique', 1, 5, 14, 0),
(11, 1, 'Sun Jun 15  CEST 2014', 'devoir', 1, 2, 16, 0),
(12, 1, 'Wed Jun 25  CEST 2014', 'aaa', 1, 4, 12, 13);

-- --------------------------------------------------------

--
-- Table structure for table `babies`
--

CREATE TABLE `babies` (
  `idBaby` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `age` date NOT NULL,
  `sex` int(10) NOT NULL,
  `firstName` varchar(200) NOT NULL,
  PRIMARY KEY (`idBaby`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `babies`
--

INSERT INTO `babies` (`idBaby`, `name`, `age`, `sex`, `firstName`) VALUES
(1, 'Gaetan', '2002-02-14', 1, 'Rouaix'),
(2, 'Toscan Junior', '2017-00-00', 0, 'Vertanessian');

-- --------------------------------------------------------

--
-- Table structure for table `jonction`
--

CREATE TABLE `jonction` (
  `idJonction` int(10) NOT NULL,
  `idUser` int(10) NOT NULL,
  `idBaby` int(10) NOT NULL,
  PRIMARY KEY (`idJonction`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jonction`
--

INSERT INTO `jonction` (`idJonction`, `idUser`, `idBaby`) VALUES
(0, 0, 0),
(1, 2, 1),
(2, 2, 2);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dumping data for table `mainfacts`
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
(11, 1, 'testAuto', 'testAuto', 'Fri Jun 13  CEST 2014', '00:00:00'),
(12, 1, 'testAuto', 'testAuto', 'Sun Jun 15  CEST 2014', '00:00:00'),
(13, 1, 'testAuto', 'testAuto', 'Sun Jun 15  CEST 2014', '10:10:00'),
(14, 1, 'testAuto', 'testAuto', 'Sun Jun 15  CEST 2014', '00:00:00'),
(15, 1, 'testManuel', 'zke,klz', 'Sun Jun 15  CEST 2014', '20:20:00'),
(16, 1, 'scs', 'scd', 'Sun Jun 15  CEST 2014', '12:12:00'),
(17, 1, 'test', 'testest', 'Sun Jun 15  CEST 2014', '10:10:00'),
(18, 1, 'testpoooo', 'testooo', 'Sun Jun 15  CEST 2014', '10:10:00'),
(19, 1, 'zak,', 'azda', 'Sun Jun 15  CEST 2014', '10:10:00'),
(20, 1, 'test', 'test2', 'Wed Jun 18  CEST 2014', '12:13:00');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `siestes`
--

INSERT INTO `siestes` (`idSieste`, `idBaby`, `heure`, `minute`, `duree`, `note`, `date`) VALUES
(1, 1, 15, 30, 1, 4, 'Sun Jun 15  CEST 2014');

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
  `tel` int(10) NOT NULL,
  `firstName` varchar(200) NOT NULL,
  `rightLevel` varchar(10) NOT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`idUser`, `name`, `email`, `password`, `adress`, `zip`, `city`, `tel`, `firstName`, `rightLevel`) VALUES
(1, 'Geoffroy', 'Rouaix', 'tata', '57 rue michel ange', 75016, 'Paris', 618275025, 'Rouaix', 'ADMIN'),
(2, 'Cheg', 'b', 'a', 'adresse', 75018, 'Paris', 0, 'Baptiste', 'ADMIN'),
(3, 'name', 'a', 'a', 'add', 0, 'city', 0, 'fname', 'ADMIN'),
(4, 'namez', 'azdzadad', 'zdzd', 'zeedzdz', 6666, 'PPDPDP', 88888, 'DEDED', 'ADMIN'),
(13, 'kkk', 'kkk', 'kkk', 'test', 13, 'test', 13, 'kkk', 'ADMIN'),
(14, 'kkk', 'kkk', 'kkk', 'default', 0, 'default', 666, 'kkk', 'ADMIN'),
(15, 'ppp', 'ppp', 'ppp', 'default', 0, 'default', 666, 'ppp', 'ADMIN');
